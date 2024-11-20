package labs.kpi.statistics;

import labs.kpi.model.SurveyParticipant;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Class to compute and store statistical data about survey participants.
 *
 * @author Oleksii Kyrychenko
 */
public class Statistics {

    private final double min;
    private final double max;
    private final double average;
    private final double standardDeviation;
    private double interquartileRange;
    private final Map<String, Long> outlierSummary;

    /**
     * Constructs a Statistics object and calculates statistical values.
     *
     * @param participants the list of participants to analyze
     */
    public Statistics(List<SurveyParticipant> participants) {
        DoubleSummaryStatistics stats = participants.stream()
                .mapToDouble(SurveyParticipant::getMonthlyIncome)
                .summaryStatistics();
        this.min = stats.getMin();
        this.max = stats.getMax();
        this.average = stats.getAverage();

        double variance = participants.stream()
                .mapToDouble(p -> Math.pow(p.getMonthlyIncome() - this.average, 2))
                .average().orElse(0.0);
        this.standardDeviation = Math.sqrt(variance);

        this.outlierSummary = calculateInterquartileRangeAndOutliers(participants);
    }

    private Map<String, Long> calculateInterquartileRangeAndOutliers(List<SurveyParticipant> participants) {
        List<Double> incomes = participants.stream()
                .map(SurveyParticipant::getMonthlyIncome)
                .sorted()
                .collect(Collectors.toList());

        int q1Index = incomes.size() / 4;
        int q3Index = incomes.size() * 3 / 4;
        double q1 = incomes.get(q1Index);
        double q3 = incomes.get(q3Index);
        this.interquartileRange = q3 - q1;

        double lowerBound = q1 - 1.5 * interquartileRange;
        double upperBound = q3 + 1.5 * interquartileRange;

        return participants.stream()
                .collect(Collectors.partitioningBy(
                        p -> p.getMonthlyIncome() < lowerBound || p.getMonthlyIncome() > upperBound,
                        Collectors.counting()
                )).entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey() ? "outliers" : "data",
                        Map.Entry::getValue
                ));
    }

    @Override
    public String toString() {
        return String.format("Min: %.2f, Max: %.2f, Avg: %.2f, Std Dev: %.2f, IQR: %.2f, Outliers: %s",
                min, max, average, standardDeviation, interquartileRange, outlierSummary);
    }
}
