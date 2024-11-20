package labs.kpi.gatherer;

import labs.kpi.model.SurveyParticipant;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Utility class to gather data with custom filtering.
 *
 * @author Oleksii Kyrychenko
 */
public class Gatherer {

    /**
     * Collects 500 objects, skipping the first N objects for a specific city.
     *
     * @param participants stream of participants
     * @param n            number of objects to skip for the given city
     * @param skipCity     city to apply skipping logic
     * @return a list of 500 participants
     */
    public static List<SurveyParticipant> gather(Stream<SurveyParticipant> participants, int n, String skipCity) {
        return participants
                .filter(new java.util.function.Predicate<SurveyParticipant>() {
                    private int skipped = 0;

                    @Override
                    public boolean test(SurveyParticipant participant) {
                        if (participant.getCity().equals(skipCity) && skipped < n) {
                            skipped++;
                            return false;
                        }
                        return true;
                    }
                })
                .limit(500)
                .collect(Collectors.toList());
    }
}
