class Solution {
    public int[] closestPrimes(int left, int right) {
        boolean k[]=new boolean[right+1];
        Arrays.fill(k,true);
        k[0]=k[1]=false;
        for(int i=2;i<=Math.sqrt(right);i++)
        {
            if(k[i])
            {
                for(int j=i*i;j<=right;j+=i)
                {
                    k[j]=false;
                }
            }
        }
        int a[]=new int[2];
        a[0]=a[1]=-1;
        int p=Integer.MAX_VALUE,m=1,f=0;
        for(int i=left;i<=right;i++)
        {
            if(k[i] && m==0)
            {
                p=Math.min(p,i-f);
                f=i;
            }
            else if(k[i] && m==1)
            {
                f=i;
                m=0;
            }
        }
        //System.out.println(p);
        for(int i=left;i<=right;i++)
        {
            if(k[i] == true && a[0] != -1)
            {
                if(p == i-a[0])
                {
                    a[1]=i;
                    break;
                }
                else
                {
                    a[0]=i;
                }
            }
            else if(k[i] == true && a[0]==-1)
            {
                a[0]=i;
            }
        }
        //System.out.println(a[0]+" "+a[1]);
        if(a[0] == -1 || a[1] == -1)
        {
            a[0]=a[1]=-1;
        }
        return a;
    }
}
