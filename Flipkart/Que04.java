class Solution {

    public int rec(int n, int k){
        if(n==1){
            return 0;
        }
        return (rec(n-1, k)+k)%n;
    }

    public int findTheWinner(int n, int k) {
        int ans = rec(n,k)+1;
        return ans;

    }
}
