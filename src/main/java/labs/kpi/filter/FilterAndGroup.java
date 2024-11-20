package labs.kpi.filter;

import labs.kpi.model.SurveyParticipant;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Utility class to filter and group survey participants.
 *
 * @author Oleksii Kyrychenko
 */
public class FilterAndGroup {

    /**
     * Filters participants by age range and groups them by full name.
     *
     * @param participants list of participants
     * @param minAge       minimum age for filtering
     * @param maxAge       maximum age for filtering
     * @return a map grouping participants by their full name
     */
    public static Map<String, List<SurveyParticipant>> filterAndGroup(List<SurveyParticipant> participants, int minAge, int maxAge) {
        return participants.stream()
                .filter(p -> p.getAge() >= minAge && p.getAge() <= maxAge)
                .collect(Collectors.groupingBy(SurveyParticipant::getFullName));
    }
}
