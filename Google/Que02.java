class Solution {
    public int maximumGood(int[][] statements) {
          int n = statements.length;
        int[] assumptions = new int[n];
        return helper(statements, assumptions, 0);
    }
    
    private boolean checkStatements(int[][] statements, int[] assumptions, int person) {
        for (int i = 0; i < statements.length; i++) {
            if (statements[person][i] == 1 && assumptions[i] == 0) {
                return false;
            }
            if (statements[person][i] == 0 && assumptions[i] == 1) {
                return false;
            }
        }
        return true;
    }
    
    private int helper(int[][] statements, int[] assumptions, int curr) {
        if (curr == statements.length) {
            int good = 0;
            for (int i = 0; i < assumptions.length; i++) {
                if (assumptions[i] == 1) {
                    good++;
                }
            }
            return good;
        }
        assumptions[curr] = 1;
        if (checkStatements(statements, assumptions, curr)) {
            return Math.max(helper(statements, assumptions, curr + 1), count(assumptions, 1));
        }
        assumptions[curr] = 0;
        if (checkStatements(statements, assumptions, curr)) {
            return Math.max(helper(statements, assumptions, curr + 1), count(assumptions, 1));
        }
        return 0;
    }
    
    private int count(int[] assumptions, int val) {
        int count = 0;
        for (int i = 0; i < assumptions.length; i++) {
            if (assumptions[i] == val) {
                count++;
            }
        }
        return count;
    }
}
