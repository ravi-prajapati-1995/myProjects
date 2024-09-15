import java.lang.reflect.Array;
import java.util.Arrays;

public class Sort012 {
    public static void main(String[] args) {
        int arr[]= {0,2,1,2,0,0,2,1,0,1,1,1,0,0,0};
        sort012_1(arr,arr.length);
        sort012_duchFlag(arr,arr.length);
    }

    public static void sort012_duchFlag(int a[], int n){
        int l=0;
        int m=0;
        int h=n-1;

        while (m<=h) {

            if(a[m] == 0){
                swap(a,l,m);
                l++;
                m++;
            } else if(a[m] == 1) {
                m++;
            } else {
                swap(a,m,h);
                h--;
            }
        }

        System.out.println("I am here: "+Arrays.toString(a));
    }
    public static void sort012_1(int a[], int n) {
        int _0s = 0;
        int _1s = 0;
        int _2s = 0;


        for(int idx = 0;idx<n;idx++) {
           if(a[idx] == 0) {
               _0s++;
           } else if (a[idx] == 1) {
               _1s++;
           } else  {
               _2s++;
           }
        }

        int newArr[] = new int[n];
        for(int i = 0 ;i<n;i++) {

            if(_0s >0 ){
                _0s--;
                newArr[i] = 0;
            } else if (_1s > 0) {
                _1s--;
                newArr[i] = 1;
            } else if (_2s > 0) {
                _2s--;
                newArr[i] = 2;
            }
        }

        a = newArr;

        System.out.println("I am here: "+Arrays.toString(a));
    }
    public static void sort012(int a[], int n) {
        for(int idx = 0;idx<n;idx++) {
            int element = a[idx];

            if(element == 0)
                continue;
            for(int j = idx+1; j<n;j++){
               if(element > a[j]){
                   swap(a,idx,j);
               }
            }
        }

        System.out.println("I am here: "+Arrays.toString(a));
    }

    public static void swap(int a[],int i, int j){
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
