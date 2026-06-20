class Solution {



static boolean canPlaceBalls(int[] position, int m, int minDistance) {
        int count = 1; 
        int lastPlaced = position[0];
        
        for (int i = 1; i < position.length; i++) {
            if (position[i] - lastPlaced >= minDistance) {
                count++;
                lastPlaced = position[i];  
                if (count >= m) {
                    return true; 
                }
            }
        }
        return false;
}
    public int maxDistance(int[] position, int m) {
       Arrays.sort(position);
        
        int low = 1;
        int high = position[position.length - 1] - position[0];
        int ans = 0;
        
         
        while (low <= high) {
            int mid = low + (high - low) / 2;
            
            if (canPlaceBalls(position, m, mid)) {
                ans = mid;         
                low = mid + 1;
            } else {
                high = mid - 1;  
            }
        }
        return ans;
 
    }
}