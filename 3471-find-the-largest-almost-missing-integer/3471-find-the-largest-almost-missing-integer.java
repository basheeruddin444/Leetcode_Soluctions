class Solution {
    public int largestInteger(int[] array, int k) {

        Map<Integer, Integer> hm = new HashMap<>();

        int start = 0;
        int end = k;

        while (end <= array.length) {

            Set<Integer> set = new HashSet<>();

            for (int i = start; i < end; i++) {
                set.add(array[i]);
            }

            for (int num : set) {
                hm.put(num, hm.getOrDefault(num, 0) + 1);
            }

            start++;
            end++;
        }

        int largest = -1;

        for (int key : hm.keySet()) {
            if (hm.get(key) == 1) {
                largest = Math.max(largest, key);
            }
        }

        return largest;
    }
}