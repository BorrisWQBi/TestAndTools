import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

public class Solution {

    public static void main(String[] args) {
        System.out.println(isPalindrome(0));
    }

    public String intToRoman(int num) {
        TreeMap<Integer, String> map = new TreeMap<Integer, String>() {{
            put(1, "I");
            put(5, "V");
            put(10, "X");
            put(50, "L");
            put(100, "C");
            put(500, "D");
            put(1000, "M");
        }};
        if (map.get(num) != null) {
            return map.get(num);
        }
        StringBuilder sb = new StringBuilder();

        return sb.toString();
    }

    public static int maxArea(int[] height) {
        if (height.length < 2)
            return 0;
        if (height.length == 2)
            return Math.min(height[0], height[1]);

        int max = 0;
        int b = 0, e = height.length - 1;
        int minWidth;

        while (b < e) {
            minWidth = Math.min(height[b], height[e]);
            max = Math.max(minWidth * (e - b), max);
            if (height[b] < height[e])
                b++;
            else
                e--;
        }
        return max;
    }

    public static boolean isPalindrome(int x) {
        if (x < 0)
            return false;
        if (x < 10)
            return true;
        if (x % 10 == 0)
            return false;

        int b = 0;
        while (x > 0) {
            b = b * 10 + x % 10;
            x /= 10;
            if (b == x)
                return true;
            if (x != 0 && b != 0 && b == x / 10)
                return true;
        }
        return false;
    }

    public static int reverse(int x) {
        int b = 0;
        int max = Integer.MAX_VALUE / 10;
        int min = Integer.MIN_VALUE / 10;
        while (x != 0) {
            if ((b > max || b == max && x > 7)
                    || (b < min || b == min && x < -8))
                return 0;
            b = b * 10 + x % 10;
            x /= 10;
        }
        return b;
    }

    public static String convert(String s, int numRows) {
        if (numRows == 1)
            return s;

        boolean dir = true;
        char[][] carr = new char[numRows][s.length()];
        int r = 0, c = 0;
        char[] cs = s.toCharArray();
        for (char c1 : cs) {
            if (dir) {
                carr[c++][r] = c1;
                if (c == numRows) {
                    dir = !dir;
                    c--;
                }
            } else {
                carr[--c][++r] = c1;
                if (c == 0) {
                    dir = !dir;
                    c++;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (char[] chars : carr) {
            for (char aChar : chars) {
                if (aChar != '\u0000')
                    sb.append(aChar);
            }
        }
        return sb.toString();
    }

    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int closestSum = nums[0] + nums[1] + nums[2];
        if (nums.length == 3 || closestSum == target)
            return closestSum;

        int l = nums.length;

        for (int i = 0; i < l - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1])
                continue;

            int h = i + 1;
            int t = l - 1;
            while (h < t) {
                int sum = nums[i] + nums[h] + nums[t];
                if (sum == target)
                    return sum;
                while (sum > target && h < t) {
                    sum = nums[i] + nums[h] + nums[t--];
                    if (Math.abs(closestSum - target) > Math.abs(sum - target))
                        closestSum = sum;
                }
                while (sum <= target && h < t) {
                    sum = nums[i] + nums[h++] + nums[t];
                    if (Math.abs(closestSum - target) > Math.abs(sum - target))
                        closestSum = sum;
                }
            }
        }
        return closestSum;
    }

    public static String longestCommonPrefix(String... strs) {
        if (strs == null || strs.length < 1)
            return "";
        int l = strs[0].length();
        int maxL = 0;
        for (String s : strs) {
            l = Math.min(l, s.length());
        }
        for (int i = 0; i < l; i++) {
            boolean check = true;
            char c = strs[0].charAt(i);
            for (String s : strs) {
                check = s.charAt(i) == c;
                if (!check)
                    break;
            }
            if (check)
                maxL++;
            else
                break;
        }
        return strs[0].substring(0, maxL);
    }

    public static List<List<Integer>> threeSum(int... nums) {
        if (nums.length < 3) {
            return new ArrayList<>();
        }
        List<List<Integer>> ls = new ArrayList<>();
        if (nums.length == 3 && nums[0] + nums[1] + nums[2] == 0) {
            return Arrays.asList(Arrays.asList(nums[0], nums[1], nums[2]));
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            for (int j = i + 1; j < nums.length - 1; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;

                int sum = nums[i] + nums[j];
                int idx = Arrays.binarySearch(nums, j + 1, nums.length, -sum);
                if (idx > 0) {
                    ls.add(Arrays.asList(nums[i],
                            nums[j],
                            nums[idx]));
                }
            }
        }
        return ls;
    }

    public static Map<String, Integer> romanMap = new HashMap<String, Integer>() {{
        put("I", 1);
        put("IV", 4);
        put("V", 5);
        put("IX", 9);
        put("X", 10);
        put("XL", 40);
        put("L", 50);
        put("XC", 90);
        put("C", 100);
        put("CD", 400);
        put("D", 500);
        put("CM", 900);
        put("M", 1000);
    }};

    public static int romanToInt(String s) {
        if (s == null || s.length() < 1) {
            return 0;
        }
        if (s.length() < 2) {
            return romanMap.get(s);
        }
        int i = 0;
        Integer sum = 0;
        while (i < s.length()) {
            String temp;
            if (i + 1 < s.length() && romanMap.containsKey(temp = s.substring(i, i + 2))) {
                sum += romanMap.get(temp);
                i += 2;
            } else {
                sum += romanMap.get(String.valueOf(s.charAt(i)));
                i++;
            }
        }
        return sum;
    }

    public static int rob(int[] nums) {
        if (nums == null || nums.length < 1)
            return 0;
        if (nums.length == 1)
            return nums[0];
        if (nums.length < 2)
            return Math.max(nums[0], nums[1]);

        int maxEarn = Math.max(nums[0], nums[1]);
        int[] earn = new int[nums.length];
        earn[0] = nums[0];
        earn[1] = nums[1];

        for (int i = 2; i < nums.length; i++) {
            for (int j = 0; j < i - 1; j++) {
                earn[i] = Math.max(earn[j] + nums[i], earn[i]);
            }
            maxEarn = Math.max(maxEarn, earn[i]);
        }
        return maxEarn;
    }

    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int[] profit = new int[prices.length];
        int maxp = 0;
        for (int i = 1; i < prices.length; i++) {
            int p1 = prices[i] - prices[i - 1];
            int p = Math.max(profit[i - 1] + p1, p1);
            maxp = Math.max(p, maxp);
            profit[i] = p;
        }
        return maxp;
    }

    public static int climbStairs(int n) {
        if (n <= 3) {
            return n;
        }
        int[] arr = new int[n + 1];
        for (int i = 0; i <= 3; i++) {
            arr[i] = i;
        }
        for (int i = 4; i < arr.length; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }
        return arr[n];
    }


    public static int maxSubArray(int[] nums) {
        int preSum = nums[0];
        int maxSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            preSum = Math.max(preSum + num, num);
            maxSum = Math.max(maxSum, preSum);
        }
        return maxSum;
    }


    /**
     * @return
     * @Author Borris.W.Q.Bi
     * @DateTime 2020/11/16 11:13
     * @Description 字符串全排列
     */
    public static String[] permutation(String s) {
        if (s == null || s.isEmpty()) {
            return null;
        }
        if (s.length() == 1) {
            return new String[]{s};
        }

        int l = s.length();
        char[] cs = s.toCharArray();

        List<String> strArr = new LinkedList<>();
        allSort(cs, 0, strArr);
        String[] sa = new String[strArr.size()];
        return strArr.toArray(sa);
    }

    private static void allSort(char[] sourceCArr, int currentIdx, List<String> strArr) {
        if (currentIdx >= sourceCArr.length) {
            String s = new String(sourceCArr);
            strArr.add(s);
            return;
        }
        Set<Character> charSet = new HashSet<>();
        for (int i = currentIdx; i < sourceCArr.length; i++) {
            if (charSet.contains(sourceCArr[i]))
                continue;
            swap(i, currentIdx, sourceCArr);
            allSort(sourceCArr, currentIdx + 1, strArr);
            swap(i, currentIdx, sourceCArr);
            charSet.add(sourceCArr[i]);
        }
    }

    private static void swap(int i1, int i2, char[] cs) {
        char temp = cs[i1];
        cs[i1] = cs[i2];
        cs[i2] = temp;
    }


    public static boolean isMatch(String s, String p) {
        if (s == null || s.length() < 1 || p == null || p.length() < 1) {
            return false;
        }
        char[] cas = s.toCharArray();
        char[] cap = p.toCharArray();

        boolean[][] check = new boolean[s.length() + 1][p.length() + 1];

        check[0][0] = cas[0] == cap[0] || cap[0] == '.';

        for (int i1 = 1; i1 < cas.length; i1++) {
            char cs = cas[i1];
            for (int i = 1; i < cap.length; i++) {
                char cp = cap[i];
                if (cp == '*')
                    cp = cap[i - 1];

                check[i1][i] = check[i1 - 1][i - 1] && (cp == '.' || cp == cs);
            }
        }

        System.out.println(print2Arr(check));
        System.out.println();

        return check[s.length() - 1][p.length() - 1];
    }

    public static String print2Arr(boolean[][] os) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < os.length; i++) {
            sb.append(Arrays.toString(os[i])).append("\n");
        }
        return sb.toString();
    }

    public static int[] sortArrayByParityII(int[] A) {
        if (A == null || A.length < 1) {
            return A;
        }
        int[] arr = new int[A.length];
        int i1 = 0;
        int i2 = 1;
        for (int i = 0; i < A.length; i++) {
            int v = A[i];
            if (v % 2 == 0) {
                arr[i1] = v;
                i1 += 2;
            } else {
                arr[i2] = v;
                i2 += 2;
            }

        }
        return arr;
    }

    public static void quickSort(int[] arr, int beginIdx, int endIdx) {
        int n = arr[beginIdx];
        int idx = beginIdx;

        int bi = beginIdx, ei = endIdx;

        while (bi < ei) {
            while (ei > bi && arr[ei] > n) {
                ei--;
            }
            if (ei > bi) {
                arr[idx] = arr[ei];
                idx = ei;
            }
            while (bi < ei && arr[bi] <= n) {
                bi++;
            }
            if (bi < ei) {
                arr[idx] = arr[bi];
                idx = bi;
            }
        }
        arr[bi] = n;
        if (bi + 1 < endIdx) {
            quickSort(arr, bi + 1, endIdx);
        }
        if (bi - 1 > beginIdx) {
            quickSort(arr, beginIdx, bi - 1);
        }
    }

    public static String longestPalindrome(String s) {
        if (s == null || s.length() == 1) {
            return s;
        }
        int l = s.length();
        if (l == 2) {
            if (s.charAt(0) == s.charAt(l - 1)) {
                return s;
            } else {
                return s.substring(1);
            }
        }
        boolean[][] record = new boolean[l][l];
        char[] c = s.toCharArray();
        for (int i = 0; i < l - 1; i++) {
            record[i][i] = true;
        }
        int maxBegin = 0;
        int maxL = 1;
        for (int ll = 1; ll < s.length(); ll++) {
            for (int i = 0; i + ll < l; i++) {
                int endIdx = i + ll;
                int tl = ll + 1;
                if (c[i] != c[endIdx]) {
                    record[i][endIdx] = false;
                } else {
                    if (tl == 2 || record[i + 1][endIdx - 1]) {
                        record[i][endIdx] = true;
                        if (ll + 1 > maxL) {
                            maxL = tl;
                            maxBegin = i;
                        }
                    }
                }
            }
        }
        return s.substring(maxBegin, maxBegin + maxL);
    }

    public static boolean checkIsPalind(int begin, int end, char[] s) {
        while (begin <= end) {
            if (s[begin++] != s[end--]) {
                return false;
            }
        }
        return true;
    }


    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int l = nums1.length + nums2.length;
        int[] midIdx = new int[2];
        double[] midNum = new double[2];
        if (l % 2 == 0) {
            midIdx[0] = l / 2 - 1;
            midIdx[1] = midIdx[0] + 1;
        } else {
            midIdx[0] = midIdx[1] = l / 2;
        }
        int idx = 0;
        int i1 = 0, i2 = 0;

        while (i1 < nums1.length || i2 < nums2.length) {
            int val;
            if (i1 >= nums1.length) {
                val = nums2[i2++];
            } else if (i2 >= nums2.length) {
                val = nums1[i1++];
            } else if (nums1[i1] <= nums2[i2]) {
                val = nums1[i1++];
            } else {
                val = nums2[i2++];
            }

            if (idx == midIdx[0]) {
                midNum[0] = val;
            }
            if (idx == midIdx[1]) {
                midNum[1] = val;
            }
            idx++;
        }
        return (midNum[0] + midNum[1]) / 2;
    }

    public static int lengthOfLongestSubstring(String s) {
        //滑动窗口法
        int begin = 0, end = 0;
        Set<Character> set = new HashSet<>(129);
        int maxL = 0;
        int l = s.length();
        while (begin < l && end < l) {
            if (set.contains(s.charAt(end))) {
                set.remove(s.charAt(begin++));
            } else {
                set.add(s.charAt(end++));
                maxL = Math.max(maxL, end - begin);
            }
        }
        return maxL;
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode l1t = l1;
        ListNode l2t = l2;
        ListNode head = new ListNode();
        ListNode sum = head;
        boolean over = false;
        for (int i = 0; ; i++) {
            int s = 0;
            if (l1t != null) {
                s += l1t.val;
                l1t = l1t.next;
            }
            if (l2t != null) {
                s += l2t.val;
                l2t = l2t.next;
            }
            if (over) {
                s++;
                over = false;
            }
            if (s >= 10) {
                over = true;
                s = s % 10;
            }
            sum.val = s;
            if (l1t == null && l2t == null)
                break;
            sum.next = new ListNode();
            sum = sum.next;
        }
        if (over) {
            sum.next = new ListNode(1);
        }
        return head;
    }

    public static ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        Set<ListNode> s = new HashSet<>();
        ListNode n = head;
        while (n.next != null) {
            if (!s.add(n)) {
                return n;
            }
            n = n.next;
        }
        return null;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(",", "[", "]");
        ListNode temp = this;
        while (temp != null) {
            sj.add(Integer.toString(temp.val));
            temp = temp.next;
        }
        return sj.toString();
    }
}