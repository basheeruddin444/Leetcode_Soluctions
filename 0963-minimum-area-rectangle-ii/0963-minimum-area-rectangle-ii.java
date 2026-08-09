class Solution {
    public double minAreaFreeRect(int[][] points) {
        int n = points.length;
        Map<String, List<int[]>> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int cx = points[i][0] + points[j][0];
                int cy = points[i][1] + points[j][1];

                int dx = points[i][0] - points[j][0];
                int dy = points[i][1] - points[j][1];

                int dist = dx * dx + dy * dy;

                String key = cx + "," + cy + "," + dist;

                map.computeIfAbsent(key, k -> new ArrayList<>())
                   .add(new int[]{i, j});
            }
        }

        double answer = Double.MAX_VALUE;

        for (List<int[]> pairs : map.values()) {
            for (int i = 0; i < pairs.size(); i++) {
                int a = pairs.get(i)[0];
                int b = pairs.get(i)[1];

                for (int j = i + 1; j < pairs.size(); j++) {
                    int c = pairs.get(j)[0];
                    int d = pairs.get(j)[1];

                    double area = getArea(points[a], points[c], points[b]);

                    if (area > 0) {
                        answer = Math.min(answer, area);
                    }
                }
            }
        }

        return answer == Double.MAX_VALUE ? 0.0 : answer;
    }

    private double getArea(int[] a, int[] c, int[] b) {
        double x1 = a[0] - c[0];
        double y1 = a[1] - c[1];

        double x2 = b[0] - c[0];
        double y2 = b[1] - c[1];

        return Math.abs(x1 * y2 - y1 * x2);
    }
}