class Solution {
    public int countDaysTogether(String arriveAlice, String leaveAlice,
                                 String arriveBob, String leaveBob) {
        int[] daysInMonth = {
            31, 28, 31, 30, 31, 30,
            31, 31, 30, 31, 30, 31
        };

        int aStart = getDay(arriveAlice, daysInMonth);
        int aEnd = getDay(leaveAlice, daysInMonth);
        int bStart = getDay(arriveBob, daysInMonth);
        int bEnd = getDay(leaveBob, daysInMonth);

        int start = Math.max(aStart, bStart);
        int end = Math.min(aEnd, bEnd);

        return Math.max(0, end - start + 1);
    }

    private int getDay(String date, int[] daysInMonth) {
        int month = Integer.parseInt(date.substring(0, 2));
        int day = Integer.parseInt(date.substring(3, 5));

        for (int i = 0; i < month - 1; i++) {
            day += daysInMonth[i];
        }

        return day;
    }
}