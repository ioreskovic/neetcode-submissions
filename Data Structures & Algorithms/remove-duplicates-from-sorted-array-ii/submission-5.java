class Solution {
    //         l
    // 1,1,2,2,3,3 - 1
    //             r
    // they are sorted, which means that if n[i] == n[i+k] => n[i+k-i] also same
    // move right pointer until you find a diff
    // move left pointer at most k times (k = maxDuplicates) or numb changed need to reemmebr last used numb
    // l can go over k if no dups or max 1 dup, l needs to always stay < r
    // then copy
    // move right pointer until you find a diff
    // l + 1


    //         l
    // 0,0,1,1,1,1,2,3,3
    //         r
    public int removeDuplicates(int[] nums) {
        if (nums.length <= 2) return nums.length;

        int l = 2;
        int r = 2;

        while (l < nums.length && r < nums.length) {
            if (nums[r] != nums[l - 2]) {
                nums[l++] = nums[r];
            }
            r++;
        }

        return l;
    }
}