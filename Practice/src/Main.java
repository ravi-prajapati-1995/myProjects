public class Main {
    public static void main(String[] args) {

//        int arr[] = {32,54,12,52,56,8,30,44,94,44,39,65,19,51,91,1,5,89,34,25,58,20,51,38,65,30,7,20,10,51,18,43,71,97,61,26,5,57,70,65,0,75,29,86,93,87,87,64,75,88,89,100,7,40,37,38,36,44,24,46,95,43,89,32,5,15,58,77,72,95,8,38,69,37,24,27,90,77,92,31,30,80,30,37};
//        int arr[] = {2,3,1,1,2,4,2,0,1,1};
        int arr[] = {1,3,5,8,9,2,6,7,6,8,9};
//        int arr[] = {2,3,1,1,2,4,2,0,1,1};
        System.out.println(minJumps(arr));
        System.out.println(minJumps2(arr));
    }

    public static int minJumps2(int arr[]) {
        int jumps = 0;
        int steps = arr[0];
        int maxReach = arr[0];
        for(int idx = 1;idx<arr.length-1;idx++) {

            int current_reach = arr[idx] + idx;
            maxReach = Math.max(maxReach,current_reach);
            steps--;

            if(steps == 0){
                jumps++;
                steps = maxReach - idx;
            }
        }

        return jumps+1;
    }

    public static int minJumps(int arr[]){
        int len = arr.length;

        int jumps = 0;


        for(int idx=0;idx<len-1; ) {
            int element = arr[idx];

            if(element <= 0) {
                return -1;
            }

            int maxIdx = -1;
            int maxVal = Integer.MIN_VALUE;

            int tempIdx = idx + element;

            if(tempIdx >= len){
                jumps++;
                break;
            }

            for(int j = idx+1;j <= tempIdx && j<len;j++){


                if(maxVal > 0)
                    maxVal--;

                if(arr[j] >= maxVal) {
                    maxIdx = j;
                    maxVal = arr[j];
                }
            }

            idx = maxIdx;
//            System.out.println("now index: "+idx+"\t"+arr[idx]);
            jumps++;
        }

        return jumps;
    }
}