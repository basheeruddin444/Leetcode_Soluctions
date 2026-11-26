class Solution {
    public boolean canAliceWin(int n) {
        int remove = 10;
        int moves = 0;

        while (n >= remove) {
            n -= remove;
            remove--;
            moves++;
        }

        return moves % 2 == 1;
    }
}
