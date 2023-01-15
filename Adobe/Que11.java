class StockPrice {
    //SPACE: O(N)
    Queue<int[]> maxQ;
    Queue<int[]> minQ;
    HashMap<Integer,Integer> tpMap;
    int latestTimestamp;
    public StockPrice() {
        maxQ = new PriorityQueue<>((a,b) -> b[1] - a[1]);
        minQ = new PriorityQueue<>((a,b) -> a[1] - b[1]);
        tpMap = new HashMap<>();
        latestTimestamp = 0;
    }
    //Time: O(LOG N)
    public void update(int timestamp, int price) {
        //Updating latestTimestamp
        latestTimestamp = timestamp > latestTimestamp ? timestamp : latestTimestamp;

        //adding prices to queues
        maxQ.add(new int[]{timestamp,price});
        minQ.add(new int[]{timestamp,price});

        //Updating-Adding register of price at timestamp
        tpMap.put(timestamp,price);
    }
    //Time: O(1)
    public int current() {
        return tpMap.get(latestTimestamp);
    }
    //Time: O(N LOG N)
    public int maximum() {
        //Find the first max value that corresponds with value saved in hashmap
        // to validate that is the correct one.
        while(maxQ.peek()[1] != tpMap.get(maxQ.peek()[0])){
            maxQ.poll();
        }
        return maxQ.peek()[1];
    }
    //Time: O(N LOG N)
    public int minimum() {
        while(minQ.peek()[1] != tpMap.get(minQ.peek()[0])){
            minQ.poll();
        }
        return minQ.peek()[1];
    }
}
