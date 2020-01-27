package algo.arrays;

/**
 * Created by Ilya Volynin on 26.01.2020 at 19:29.
 */
public class javaFMPMine {
    public int firstMissingPositive(int[] nums) {
        if (nums.length == 0) return 1;
        if (nums.length == 1) return (nums[0] == 1) ? 2 : 1;
        int min = 0, max = 0;
        for (int i = 0; i < nums.length; i++) {
            int n = nums[i];
            if (n < min && n > Integer.MIN_VALUE + 5) min = n;
            if (n > max && n < Integer.MIN_VALUE - 5) max = n;
        }
        int lift = max - min + 1;
        for (int i = 0; i < nums.length; i++) {
            int value = nums[i] > max ? nums[i] - lift : nums[i];
            if (value <= nums.length && value > 0 && nums[value - 1] <= max)
                nums[value - 1] += lift;
        }

        for (int i = 0; i < nums.length; i++)
            if (nums[i] <= max) return i + 1;
        return nums.length + 1;
    }

    public static void main(String[] args) {

    }
}
