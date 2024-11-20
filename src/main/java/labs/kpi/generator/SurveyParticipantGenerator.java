package labs.kpi.generator;

import labs.kpi.model.SurveyParticipant;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

/**
 * Utility class to generate a stream of survey participants with random attributes.
 *
 * @author Oleksii Kyrychenko
 */
public class SurveyParticipantGenerator {

    private static final List<String> CITIES = List.of("Kyiv", "Lviv", "Odesa", "Kharkiv", "Zaporizhzhia", "Vinnytsia");
    private static final List<String> NAMES = List.of("Anna", "Victoria", "Maria", "Andriy", "Dmytro", "Maksym");
    private static final List<String> SURNAMES = List.of("Melnyk", "Shevchenko", "Kovalenko", "Bondarenko", "Boyko", "Tkachenko");
    private static final Random RANDOM = new Random();

    /**
     * Generates an infinite stream of survey participants.
     *
     * @return a stream of survey participants
     */
    public static Stream<SurveyParticipant> generateParticipants() {
        return Stream.generate(() -> new SurveyParticipant(
                randomFullName(),
                randomDateOfBirth(),
                randomCity(),
                randomIncome()
        ));
    }

    private static String randomFullName() {
        String name = NAMES.get(RANDOM.nextInt(NAMES.size()));
        String surname = SURNAMES.get(RANDOM.nextInt(SURNAMES.size()));
        return name + " " + surname;
    }

    private static String randomCity() {
        return CITIES.get(RANDOM.nextInt(CITIES.size()));
    }

    private static LocalDate randomDateOfBirth() {
        return LocalDate.now().minusYears(18 + RANDOM.nextInt(43));
    }

    private static double randomIncome() {
        return 10000 + RANDOM.nextDouble() * 90000;
    }
}
