// Meet Arora 2016055
// Incorrect

import java.util.Scanner;
import java.lang.*;
import java.util.*;
import java.io.*;

public class lab7 {
    public static void main(String[] args) {
        Scanner Reader=new Scanner(System.in);
        int t=Reader.nextInt();
        for (int x=0;x<t;x++) {
            int n=Reader.nextInt();
            int[][] w=new int[n][n];
            int[][] m=new int[n][n];

            for (int i=0;i<n;i++) {
                int temp=Reader.nextInt();
                for (int j=0;j<n;j++) {
                    w[i][j]=Reader.nextInt()-1;
                }
            }

            for (int i=0;i<n;i++) {
                int temp=Reader.nextInt();
                for(int j=0;j<n;j++) {
                    m[i][j]=Reader.nextInt()-1;
                }
            }

            int[] m_free=new int[n];
            int[] w_done=new int[n];

            for (int y=0;y<n;y++)
                w_done[y]=-1;

            int done=0;

            int twice_size=2*n;

            int[][] match=new int[twice_size][n];

            for (int i=0;i<twice_size;i++) {
                for (int j=0;j<n;j++) {
                    int index;
                    if (i>=n) {
                        index=i%n;
                        match[i][j]=m[index][j];
                    }
                    else {
                        match[i][j]=w[i][j];
                    }

                }
            }

            for (int i=0;i<twice_size;i++) {
                for (int j=0;j<n;j++) {
//                    System.out.print(match[i][j]+" ");
                }
//                System.out.println();
            }

            while(done<n) {
                int i=-1;
                for(i=0;i<n;i++) {
                    if (m_free[i]==0)
                        break;
                }

                for (int j=0;j<n && m_free[i]==0;j++) {
                    int k=match[i][j];

                    if (w_done[k]==-1) {
                        w_done[k]=i;
                        m_free[i]=1;
                        done++;
                    }
                    else {
                        int second_partner=w_done[k];
                        boolean bool=false;
                        for (int index=0;index<n;index++) {
                            if (match[k][index]==i) {
                                bool=true;
                            } else if(match[k][index]==second_partner) {
                                bool=false;
                            }
                        }
                        if (!bool) {
                            w_done[k]=i;
                            m_free[i]=1;
                            m_free[second_partner]=0;
                        }
                    }
                }
            }
            for (int i=0;i<n;i++)
                System.out.printf("%d %d\n", w_done[i]+1, i+1);
        }
    }

    private static boolean gale(int woman, int first, int[][] match, int second, int size) {
        for (int i=0;i<size;i++) {
            if (match[woman][i]==first) {
                return false;
            } else if(match[woman][i]==second) {
                return true;
            }
        }
        return false;
    }
}
