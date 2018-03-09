import java.util.ArrayList;
import java.util.Scanner;

public class lab6b {
    public static int dp(ArrayList<Integer> a, ArrayList<Integer> weight, int T) {
        int size=a.size();
        int[][] dp=new int[size+1][size+1];

        for(int i=0;i<size;i++) {
            for(int j=0;j<size;j++) {
                if(i==0 || j==0)
                    dp[i][j]=0;
                else if(weight.get(i-1)<=T)
                    dp[i][j]=Math.max(weight.get(i-1)+dp[i-1][j-weight.get(i-1)], dp[i-1][j]);
                else
                    dp[i][j]=dp[i-1][j];
            }
        }

        return dp[size][T];
    }

    public static void split_arrays(int[] a, int I, int T) {
        ArrayList<Integer> b=new ArrayList<>();
        ArrayList<Integer> weight=new ArrayList<>();
        b.add(a[0]);
        weight.add(1);
        int j=0;
        for(int i=1;i<I;i++) {
            if(a[i]==a[i-1]) {
                int temp = weight.get(j);
                weight.set(j, temp+1);
            }
            else {
                j++;
                b.add(a[i]);
                weight.add(1);
            }
        }
        int res=dp(b, weight, T);
        System.out.println(res);
    }

    public static void main(String[] args) {
        Scanner Reader=new Scanner(System.in);
        int t=Reader.nextInt();
        while (t-->0){
            int T=Reader.nextInt();
            int I = Reader.nextInt();
            int[] a = new int[I];
            for (int i = 0; i < I; i++) a[i] = Reader.nextInt();

            int index=0;
            int max=0;
            int first=-1;
            int last=-1;
            int count=-1;
            while(index<I) {
                count=index+1;
                while(count<I) {
                    if (a[count] - a[index] < T) {
                        count += 1;
                        continue;
                    }
                    break;
                }
                count=count-index;
                if(count>max) {
                    last=a[count+index-1];
                    first=a[index];
                    max=count;
                }

                index+=1;
            }
            System.out.printf("%d %d %d\n", max, first, last);

//            split_arrays(a, I, T);
        }
    }
}
