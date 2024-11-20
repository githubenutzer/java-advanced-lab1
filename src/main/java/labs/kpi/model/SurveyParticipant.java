package labs.kpi.model;

import java.time.LocalDate;
import java.time.Period;

/**
 * Represents a survey participant.
 *
 * @author Oleksii Kyrychenko
 */
public class SurveyParticipant {

    private final String fullName;
    private final LocalDate birthDate;
    private final String city;
    private final double monthlyIncome;

    public SurveyParticipant(String fullName, LocalDate birthDate, String city, double monthlyIncome) {
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.city = city;
        this.monthlyIncome = monthlyIncome;
    }

    public String getFullName() {
        return fullName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getCity() {
        return city;
    }

    public double getMonthlyIncome() {
        return monthlyIncome;
    }

    public int getAge() {
        return Period.between(this.birthDate, LocalDate.now()).getYears();
    }

    @Override
    public String toString() {
        return String.format("SurveyParticipant{FullName='%s', Age=%d, City='%s', MonthlyIncome=%.2f}",
                fullName, getAge(), city, monthlyIncome);
    }
}
