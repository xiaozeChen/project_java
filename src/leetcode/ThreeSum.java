package leetcode;

import java.util.*;

/**
 * 求出数组中三个数字和位0的所有组合
 */
public class ThreeSum {
    public static void main(String[] args) {
        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
//        System.out.println(threeSum(nums));
        System.out.println(threeSum1(nums));
    }


    /**
     * 耗时过长
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Comparator<Integer> sort = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        };
        //将数组分成三个集合正数，负数，0
        Map<Integer, Integer> plus = new HashMap<>();
        Map<Integer, Integer> minus = new HashMap<>();
        List<Integer> zeros = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                plus.put(i, nums[i]);
            } else if (nums[i] < 0) {
                minus.put(i, nums[i]);
            } else {
                zeros.add(nums[i]);
            }
        }
        //要使得三个数相加为0，则这三个数字至少有一个为负数和正数，或者全为0
        //全为0
        int size = zeros.size();
        if (size >= 3) {
            List<Integer> list = new ArrayList<>();
            list.add(0);
            list.add(0);
            list.add(0);
            result.add(list);
        }
        //有一个数为负数,一个数为正数，一个数为0
        for (Map.Entry<Integer, Integer> plu : plus.entrySet()) {
            for (Map.Entry<Integer, Integer> minu : minus.entrySet()) {
                if (plu.getValue() + minu.getValue() == 0) {
                    for (Integer zero : zeros) {
                        List<Integer> list = new ArrayList<>();
                        list.add(plu.getValue());
                        list.add(minu.getValue());
                        list.add(zero);
                        list.sort(sort);
                        if (!isRepeat(result, list)) {
                            result.add(list);

                        }
                    }
                }
            }
        }
        //有两个数为负数，一个数为正数
        add2with1(result, sort, plus, minus);
        //有两个数为正数，一个数为负数
        add2with1(result, sort, minus, plus);
        return result;
    }

    private static boolean isRepeat(List<List<Integer>> result, List<Integer> list) {
        if (result.size() > 0) {
            for (List<Integer> vars : result) {
                int isRepeat = 0;
                for (int i = 0; i < vars.size(); i++) {
                    if (list.get(i) == vars.get(i)) {
                        isRepeat++;
                    }
                }
                if (isRepeat == 3) {
                    return true;
                }
            }
            return false;
        } else {
            return false;
        }
    }

    private static void add2with1(List<List<Integer>> result, Comparator<Integer> sort, Map<Integer, Integer> plus, Map<Integer, Integer> minus) {
        for (Map.Entry<Integer, Integer> minu1 : minus.entrySet()) {
            for (Map.Entry<Integer, Integer> minu2 : minus.entrySet()) {
                if (minu1.getKey() != minu2.getKey()) {
                    for (Integer plu1 : plus.values()) {
                        if (plu1 + minu1.getValue() + minu2.getValue() == 0) {
                            List<Integer> list = new ArrayList<>();
                            list.add(plu1);
                            list.add(minu1.getValue());
                            list.add(minu2.getValue());
                            list.sort(sort);
                            if (!isRepeat(result, list)) {
                                result.add(list);
                            }
                        }
                    }
                }
            }
        }
    }

    public static List<List<Integer>> threeSum1(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i + 2 < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {              // skip same result
                continue;
            }
            int j = i + 1, k = nums.length - 1;
            int target = -nums[i];
            while (j < k) {
                if (nums[j] + nums[k] == target) {
                    res.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    j++;
                    k--;
                    while (j < k && nums[j] == nums[j - 1]) j++;  // skip same result
                    while (j < k && nums[k] == nums[k + 1]) k--;  // skip same result
                } else if (nums[j] + nums[k] > target) {
                    k--;
                } else {
                    j++;
                }
            }
        }
        return res;
    }
}