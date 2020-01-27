package algo.arrays;

/**
 * Created by Ilya Volynin on 26.01.2020 at 19:25.
 */
public class javaFMPLev {
    public int FirstMissingPositive(int[] nums) {

           int l = nums.length;

           if(l == 0)
               return 1;

           if(l == 1)
           {
               if(nums[0] == 1)
                   return 2;
               else
                   return 1;
           }

           int a = 0;
           int b = 0;
           int c = 0;

           for(int i = 0; i < l; i++)
               if(nums[i] == 0)
                   nums[i] = -1;


           for(int i = 0; i < l; i++)
           {
               if(nums[i] == i)
               {
                   nums[i] = 0;
               }
               else
               {
                   if(nums[i] > 0)
                   {
                       if(nums[i] == l)
                       {
                           c = 1;
                       }
                       else
                       {
                           if(nums[i] < l)
                           {
                               a = nums[nums[i]];
                               nums[nums[i]] = 0;
                               if(a == l)
                               {
                                   c = 1;
                               }
                               else
                               {
                                   while((a > 0) && (a < l))
                                   {
                                       b = nums[a];
                                       nums[a] = 0;
                                       a = b;
                                       if(a == l)
                                           c = 1;
                                   }
                               }
                           }
                       }
                   }
               }

           }


           for(int i = 1; i < l; i++)
               if(nums[i] != 0)
                   return i;

           if(c > 0)
           {
               return (l+1);
           }

           return l;

       }

    public static void main(String[] args) {

    }
}
