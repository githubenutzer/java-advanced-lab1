package labs.kpi;

import labs.kpi.generator.SurveyParticipantGenerator;
import labs.kpi.gatherer.Gatherer;
import labs.kpi.filter.FilterAndGroup;
import labs.kpi.statistics.Statistics;
import labs.kpi.model.SurveyParticipant;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Main class to run the program.
 *
 * @author Oleksii Kyrychenko
 */
public class Main {

    /**
     * Entry point of the application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        // Generate an infinite stream of survey participants
        Stream<SurveyParticipant> generator = SurveyParticipantGenerator.generateParticipants();

        // Gather 500 participants, skipping the first N elements for a specified city
        List<SurveyParticipant> gatheredParticipants = Gatherer.gather(generator, 50, "Kyiv");
        System.out.println("Gathered participants:");
        gatheredParticipants.forEach(System.out::println);

        // Filter participants by age range and group them by full name
        Map<String, List<SurveyParticipant>> groupedParticipants = FilterAndGroup.filterAndGroup(gatheredParticipants, 20, 50);
        System.out.println("\nFiltered and grouped participants:");
        groupedParticipants.forEach((name, participants) -> {
            System.out.println("Name: " + name);
            participants.forEach(System.out::println);
        });

        // Calculate statistics for participant incomes
        Statistics statistics = new Statistics(gatheredParticipants);
        System.out.println("\nStatistics:");
        System.out.println(statistics);
    }
}
