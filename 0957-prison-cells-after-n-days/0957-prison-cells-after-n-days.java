class Solution {
    public int[] prisonAfterNDays(int[] cells, int n) {
        Map<String, Integer> seen = new HashMap<>();
        int day = 0;

        while (day < n) {
            String key = Arrays.toString(cells);

            if (seen.containsKey(key)) {
                int cycle = day - seen.get(key);
                int remaining = n - day;
                int skip = remaining / cycle;

                if (skip > 0) {
                    day += skip * cycle;
                    continue;
                }
            } else {
                seen.put(key, day);
            }

            int[] next = new int[8];

            for (int i = 1; i < 7; i++) {
                next[i] = cells[i - 1] == cells[i + 1] ? 1 : 0;
            }

            cells = next;
            day++;
        }

        return cells;
    }
}