import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.util.*;

class Reader {
    static BufferedReader reader;
    static StringTokenizer tokenizer;

    /**
     * call this method to initialize reader for InputStream
     */
    static void init(InputStream input) {
        reader = new BufferedReader(
                new InputStreamReader(input));
        tokenizer = new StringTokenizer("");
    }

    /**
     * get next word
     */
    static String next() throws IOException {
        while (!tokenizer.hasMoreTokens()) {
            tokenizer = new StringTokenizer(
                    reader.readLine());
        }
        return tokenizer.nextToken();
    }

    static int nextInt() throws IOException {
        return Integer.parseInt(next());
    }

    static double nextDouble() throws IOException {
        return Double.parseDouble(next());
    }
}

public class lab8 {
    private static int size=100002;
    private static Long[] factorial=new Long[size];
//    private static Long[] mod_inverse=new Long[size];
    private static Long MOD=1000000007L;
    private static Long[] dp=new Long[size];

    public static Long dp(int n, int m) {
        if (n<0 || m<0)
            return 0L;

        if (dp[n]!=-1)
            return dp[n];

        if (n<m)
            return 1L;
        else {
            dp[n]=(dp(n-1, m) + dp(n-m, m))%MOD;
            return dp[n];
        }
    }

    public static Long combination(int n, int r, int m) {
        if (n==0 || r==0 || m<=0)
            return 1L;

        Long temp=(factorial[n] * modulo_inverse(factorial[r])%MOD * modulo_inverse(factorial[m])%MOD) % MOD;
//        Long temp=factorial[n]/factorial[r];
//        temp/=factorial[m];
//        System.out.println(n+ " " + r + " " + m + " "+temp);
        return temp;
    }

    public static Long gcd(Long a, Long b) {
        if (a!=0) {
            return gcd(b % a, a);
        }
        return b;
    }

    public static Long pow(Long n, Long a) {
        if(a==0)
            return 1L;

        Long half=pow(n, a/2) % MOD;
        half=(half*half) % MOD;

        if(a%2==1) {
            return (half*n)%MOD;
        }
        return half;
    }

    public static Long __calculate(int n, int m, int block) {
        if (m > n/block)
            return 0L;

        Long ans=combination(n-m*block+m, m, n-m*block);

        return (ans + __calculate(n, m+1, block))%MOD;
    }

    public static Long calculate(Long[] answers, int p, int q, int n) {
        Long sum=0L;

        for (int i=p;i<=q;i++) {
            if (answers[i]!=-1) {
                sum = (sum + answers[i]) % MOD;
                continue;
            }
//            answers[i]=__calculate(i, 0, n);
            answers[i]=dp(i, n);
            sum = (sum + answers[i]) % MOD;
        }

        return sum;
    }

    public static Long modulo_inverse(Long n) {
//        Long div=gcd(n, MOD);
//        if (div==1) {
//            return pow(n, MOD-2L);
//        }
//        return -1L;
        return pow(n, MOD-2);
    }

    public static void main(String[] args) throws IOException {
        Reader.init(System.in);
//        Scanner Reader=new Scanner(System.in);
        int t=Reader.nextInt();
        int n=Reader.nextInt();
        Long[] answers=new Long[size];

        factorial[0]=1L;
        answers[0]=-1L;
        dp[0]=-1L;

        for (int i=1;i<size;i++) {
            factorial[i]=(factorial[i-1]*i) % MOD;
//            mod_inverse[i]=modulo_inverse((long) i);
            answers[i]=-1L;
            dp[i]=-1L;
//            System.out.printf("%d\n", factorial[i]);
        }

        while (t-->0) {
            int p=Reader.nextInt();
            int q=Reader.nextInt();

            System.out.println(calculate(answers, p, q, n));
        }
    }


}
