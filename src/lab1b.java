import java.util.Scanner;

public class lab1b {
    public static int recurse(int n, int m, int i) {
        if(n==0)
            return i-1;

        if(i%m==0)
            n++;

        return recurse(n-1, m, i+1);
    }

    public static void main(String[] args) {
        Scanner Reader=new Scanner(System.in);
        int t=Reader.nextInt();
        while (t-->0) {
            int n=Reader.nextInt();
            int m=Reader.nextInt();
            if(m==1)
                System.out.println("No Sleep");
            else
                System.out.println(recurse(n, m, 1));
        }
    }
}
