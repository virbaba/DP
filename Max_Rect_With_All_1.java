import java.util.Arrays;
import java.util.Stack;

class Solution {
    public void preSmaller(int[] pre, int[] heights, int n) {
        Stack<Integer> st = new Stack<>();
        st.push(-1);

        for (int i = 0; i < n; i++) {
            int e = heights[i];
            while (st.peek() != -1 && heights[st.peek()] >= e) {
                st.pop();
            }
            pre[i] = st.peek();
            st.push(i);
        }
    }

    public void nextSmaller(int[] next, int[] heights, int n) {
        Stack<Integer> st = new Stack<>();
        st.push(n);

        for (int i = n - 1; i >= 0; i--) {
            int e = heights[i];
            while (st.peek() != n && heights[st.peek()] >= e) {
                st.pop();
            }
            next[i] = st.peek();
            st.push(i);
        }
    }

    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int n = matrix.length;
        int m = matrix[0].length;

        int[][] mat = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                mat[i][j] = matrix[i][j] - '0';
                if (i > 0 && mat[i][j] != 0) {
                    mat[i][j] += mat[i - 1][j];
                }
            }
        }

        int maxArea = 0;
        for (int i = 0; i < n; i++) {
            int[] arr = mat[i];
            int[] pre = new int[m];
            int[] next = new int[m];

            preSmaller(pre, arr, m);
            nextSmaller(next, arr, m);

            for (int j = 0; j < m; j++) {
                int area = (next[j] - pre[j] - 1) * arr[j];
                maxArea = Math.max(area, maxArea);
            }
        }

        return maxArea;
    }
}
