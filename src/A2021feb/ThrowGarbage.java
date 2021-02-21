package A2021feb;

/**
 * Created by twr143 on 19.02.2021 at 13:44.
 */
public class ThrowGarbage {
    /**
     * @param BagList: the weight of all garbage bags.
     * @return: an integer represent the minimum number of times.
     */
    public int Count_ThrowTimes(float[] BagList) {
        java.util.Arrays.sort(BagList);
        int i = 0;
        int cnt = 1;
        float sum = 0;
        while (i < BagList.length && BagList[i] <= 1.5f) {
            if (sum + BagList[i] <= 3.0f) {
                sum += BagList[i];
            } else {
                sum = BagList[i];
                cnt++;
            }
            i++;
        }
        return cnt + BagList.length - i - 1;
    }
}
