import java.util.Scanner;

public class lab1a {
    public static String recurse(char[] arr, int i, String res) {
        if(i==arr.length)
            return res;

        if(Math.abs(arr[i]-arr[i-1])>3) {
            if(arr[i]-arr[i-1]>0)
                res+="ab";
            else
                res+="ba";
        }
        else {
            char[] temp=res.toCharArray();
            if(i==1)
                res+="aa";
            else if(temp[temp.length-1]=='a')
                res+="aa";
            else
                res+="bb";
        }

        return recurse(arr, i+1, res);
    }

    public static boolean check(char[] arr, int i) {
        if(i==arr.length)
            return true;

        boolean temp=false;

        if(i==0 && arr[i]=='a')
            temp=check(arr, i+1);
        else if(i>0 && arr[i-1]=='a') {
            if(arr[i]=='a')
                temp=check(arr, i+1);
            else {
                if(i==arr.length-1)
                    return false;
                else if(arr[i+1]=='b')
                    temp=check(arr, i+2);
                else
                    return false;
            }
        }
        else if(i>1 && arr[i-1]=='b' && arr[i-2]=='b') {
            if(i==arr.length-1)
                return true;
            else if(arr[i]=='a')
                temp=check(arr, i+1);
            else
                return false;
        }
        else
            return false;

        return temp;
    }

    public static void main(String[] args) {
        Scanner Reader=new Scanner(System.in);
        int n=Reader.nextInt();
        for(int i=0;i<n;i++) {
            String s = Reader.next().toLowerCase();
            char[] arr = s.toCharArray();
            String ans = recurse(arr, 1, "");
            System.out.println(ans);
            char[] char_ans = ans.toCharArray();
            if (check(char_ans, 0))
                System.out.println("Valid");
            else
                System.out.println("Invalid");
        }
    }
}
