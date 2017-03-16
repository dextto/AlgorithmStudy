// https://leetcode.com/problems/increasing-holders
// NOTE: the order of output is neglectable

package leetcode;


import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class No491_IncreasingSubsequence {

    @Test
    public void test() throws Exception {
        No491_IncreasingSubsequence instance = new No491_IncreasingSubsequence();
        System.out.println(instance.findSubsequences(new int[] {1, 2, 3, 2}));
        System.out.println(instance.findSubsequences(new int[] {4, 6, 7, 7}));
    }

    public List<List<Integer>> findSubsequences(int[] nums) {
        Set<List<Integer>> res = new HashSet<>();
        List<Integer> holder = new ArrayList<>();
        find(res, holder, 0, nums);
        ArrayList<List<Integer>> result = new ArrayList<>(res);
        return result;
    }

    private void find(Set<List<Integer>> res, List<Integer> holder, int index, int[] nums) {
        if (holder.size() >= 2) {
            res.add(new ArrayList<>(holder));
        }
        for (int i = index; i < nums.length; i++) {
            if (holder.size() == 0 || getLast(holder) <= nums[i]) {
                holder.add(nums[i]);
                find(res, holder, i + 1, nums);
                holder.remove(getLast(holder));
            }
        }
    }

    private Integer getLast(List<Integer> holder) {
        return holder.get(holder.size() - 1);
    }
}
