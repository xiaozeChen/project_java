package leetcode;

import java.util.*;

/**
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 */
public class TwoSum {

    public static void main(String[] args) {
        int[] nums = new int[]{2, 7, 11, 15};
        int target = 9;
//        int[] twoSum = twoSum(nums, target);
        int[] twoSum = twoSumUpdate1(nums, target);
        for (int i : twoSum) {
            System.out.println(i);
        }
    }

    /**
     * 时间复杂度 O(n(n-1))
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum(int[] nums, int target) {
        List<Integer> result = new ArrayList<>();
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                if (nums[i] + nums[j] == target) {
                    result.add(i);
                    result.add(j);
                }
            }
        }
        int[] results = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            results[i] = result.get(i);
        }
        return results;
    }

    /**
     * 时间复杂度
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSumUpdate1(int[] nums, int target) {
        List<Integer> result = new ArrayList<>();
        Map<Integer, Integer> odds = new HashMap<>();//奇数
        Map<Integer, Integer> evens = new HashMap<>();//偶数
        //对数组进行奇偶分类
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            if (nums[i] % 2 == 0) {
                evens.put(nums[i], i);
            } else {
                odds.put(nums[i], i);
            }
        }
        //判断target的奇偶性
        if (target % 2 != 0) {//奇数的情况,只有可能是奇数和偶数相加
            getResult(target, result, odds, evens);
        } else {//偶数的情况，只有可能是偶数和偶数或者奇数和奇数相加
            getResult(target, result, odds, odds);
            getResult(target, result, evens, evens);
        }
        int[] results = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            results[i] = result.get(i);
        }
        return results;
    }

    private static void getResult(int target, List<Integer> result, Map<Integer, Integer> odds, Map<Integer, Integer> evens) {
        for (Map.Entry<Integer, Integer> odd : odds.entrySet()) {
            int complement = target - odd.getKey();
            if (evens.containsKey(complement)) {
                result.add(odd.getValue());
                result.add(evens.get(complement));
            }
        }
    }

}

