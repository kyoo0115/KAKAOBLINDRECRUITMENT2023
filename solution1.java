import java.util.*;

class Solution {
    class DateTerm {
        String dateStr;
        int dateNum;

        DateTerm(String dateStr, int dateNum) {
            this.dateStr = dateStr;
            this.dateNum = dateNum;
        }
    }
    public int[] solution(String today, String[] terms, String[] privacies) {
        int todayNum = convert(today);

        List<DateTerm> privacyTerms = new ArrayList<>();
        Map<String, Integer> termMap = new HashMap<>();

        for (String privacy : privacies) {
            String[] parts = privacy.split(" ");
            privacyTerms.add(new DateTerm(parts[1], convert(parts[0])));
        }

        for (String term : terms) {
            String[] parts = term.split(" ");
            termMap.put(parts[0], Integer.parseInt(parts[1]) * 28);
        }

        for (DateTerm dt : privacyTerms) {
            dt.dateNum += termMap.getOrDefault(dt.dateStr, 0);
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < privacyTerms.size(); i++) {
            if (privacyTerms.get(i).dateNum <= todayNum) {
                result.add(i + 1);
            }
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    public int convert(String date) {
        String[] splitted = date.split("\\.");
        int years = Integer.parseInt(splitted[0]);
        int months = Integer.parseInt(splitted[1]) - 1;
        int days = Integer.parseInt(splitted[2]);
        return days + (months * 28) + (years * 12 * 28);
    }
}
