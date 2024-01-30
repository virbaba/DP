import java.util.*;
public class Solution {
    public static int minimumDishes(int n, int m, int[] arr) {
        // Write your code here.
         // Count the occurrences of each dish type
        Map<Integer, Integer> dishCount = new HashMap<>();

        for (int dish : arr) {
            dishCount.put(dish, dishCount.getOrDefault(dish, 0) + 1);
        }

        List<Integer> counts = new ArrayList<>(dishCount.values());
        Collections.sort(counts);

        int variety = counts.size();
        for (int count : counts) {
            if (m >= count) {
                m -= count;
                variety--;
            } else {
                break;
            }
        }

        return variety;
    }
}
