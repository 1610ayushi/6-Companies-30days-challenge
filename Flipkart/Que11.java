class Solution {
    public double new21Game(int N, int K, int W) {
        if (K == 0) {
            return 1;
        }
        int max = K + W - 1;
        double[] dp = new double[max + 1];
        dp[0] = 1;
        for (int i = 1; i <= max; i++) {
            for (int j = 1; j <= W; j++) {
                if (i - j >= 0 && i - j < K) {
                    dp[i] += dp[i - j] / W;
                }
            }
        }
        double result = 0;
        for (int i = K; i <= N; i++) {
            result += dp[i];
        }
        return result;
    }
}
