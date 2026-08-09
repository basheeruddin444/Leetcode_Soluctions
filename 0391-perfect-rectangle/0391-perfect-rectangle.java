class Solution {
    public boolean isRectangleCover(int[][] rectangles) {
        long area = 0;
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;

        Set<String> corners = new HashSet<>();

        for (int[] r : rectangles) {
            int x1 = r[0], y1 = r[1];
            int x2 = r[2], y2 = r[3];

            minX = Math.min(minX, x1);
            minY = Math.min(minY, y1);
            maxX = Math.max(maxX, x2);
            maxY = Math.max(maxY, y2);

            area += (long) (x2 - x1) * (y2 - y1);

            String[] points = {
                x1 + "," + y1,
                x1 + "," + y2,
                x2 + "," + y1,
                x2 + "," + y2
            };

            for (String point : points) {
                if (!corners.add(point)) {
                    corners.remove(point);
                }
            }
        }

        long expectedArea = (long) (maxX - minX) * (maxY - minY);

        if (area != expectedArea || corners.size() != 4) {
            return false;
        }

        return corners.contains(minX + "," + minY)
                && corners.contains(minX + "," + maxY)
                && corners.contains(maxX + "," + minY)
                && corners.contains(maxX + "," + maxY);
    }
}