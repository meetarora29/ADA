// Meet 2016055

import java.util.Arrays;
import java.util.Scanner;

class Deadline implements Comparable<Deadline> {
    private int start, end;

    Deadline(int start, int end) {
        this.start=start;
        this.end=end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    @Override
    public int compareTo(Deadline o) {
        if (this.end<o.end)
            return -1;
        else if(this.end>o.end)
            return 1;
        return this.start-o.start;
    }

    @Override
    public String toString() {
        return start+" "+end;
    }
}

public class lab9b {

    public static void main(String[] args) {
        Scanner Reader = new Scanner(System.in);
        int t = Reader.nextInt();

        while (t-->0) {
            int n=Reader.nextInt();
            Deadline[] deadlines=new Deadline[n];

            for (int i=0;i<n;i++) {
                int start=Reader.nextInt();
                int end=Reader.nextInt();
                deadlines[i]=new Deadline(start, end);
            }

            Arrays.sort(deadlines);

            int prev=-1, ans=0;
            for (int i=0;i<n;i++) {
                if (i==0) {
                    prev=0;
                    continue;
                }
                if (deadlines[prev].getEnd()<=deadlines[i].getStart()) {
                    ans++;
                    prev=i;
                }
            }
            System.out.println(ans+1);
        }
    }
}
