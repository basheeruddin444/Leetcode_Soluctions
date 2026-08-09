class Solution {
    public int[][] kClosest(int[][] points, int k) {
        int left = 0;
        int right = points.length - 1;

        while (left <= right) {
            int pivot = partition(points, left, right);

            if (pivot == k - 1) {
                break;
            }

            if (pivot < k) {
                left = pivot + 1;
            } else {
                right = pivot - 1;
            }
        }

        return Arrays.copyOf(points, k);
    }

    private int partition(int[][] points, int left, int right) {
        int[] pivotPoint = points[right];
        long pivot = distance(pivotPoint);

        int index = left;

        for (int i = left; i < right; i++) {
            if (distance(points[i]) <= pivot) {
                swap(points, index, i);
                index++;
            }
        }

        swap(points, index, right);
        return index;
    }

    private long distance(int[] point) {
        return (long) point[0] * point[0]
             + (long) point[1] * point[1];
    }

    private void swap(int[][] points, int i, int j) {
        int[] temp = points[i];
        points[i] = points[j];
        points[j] = temp;
    }
}