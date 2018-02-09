

import java.util.ArrayList;
import java.util.Scanner;

class Pair<T1, T2> {
    T1 key;
    T2 value;

    Pair(T1 key, T2 value) {
        this.key=key;
        this.value=value;
    }

    T1 getKey() {
        return key;
    }

    T2 getValue() {
        return value;
    }
}

public class lab2b {
    public static int dp(int[][] matrix, int[][] dp, int[][] visited, int i, int j, int r, int c) {
        if(i>r || j>c || i<0 || j<0) {
            return Integer.MAX_VALUE;
        }

        visited[i][j]=1;

        if(i==r && j==c) {
            return dp[i][j] = matrix[i][j];
        }

        if(dp[i][j]!=-1)
            return dp[i][j];

        int left=Integer.MAX_VALUE;
        int right=Integer.MAX_VALUE;
        int up=Integer.MAX_VALUE;
        int down=Integer.MAX_VALUE;

        if(i==5) {
            for (int x = 0; x < c; x++)
                System.out.print(visited[i][x]+" ");
            System.out.println();
        }
        if(i>0 && visited[i-1][j]!=1)
            left=dp(matrix, dp, visited, i-1, j, r, c);
        if(j>0 && visited[i][j-1]!=1)
            up=dp(matrix, dp, visited, i, j-1, r, c);
        if(j<c && visited[i][j+1]!=1)
            down=dp(matrix, dp, visited, i, j+1, r, c);
        if(i<r && visited[i+1][j]!=1)
            right=dp(matrix, dp, visited, i+1, j, r, c);

        dp[i][j] = matrix[i][j] + Math.min(Math.min(up, down), Math.min(left, right));
        System.out.println(i+" "+j+" "+dp[i][j]);
        return dp[i][j];
    }

    public static int djikstra(int[][] matrix, int[][] min, int r, int c) {
        ArrayList<Pair<Integer, Integer>> list=new ArrayList<Pair<Integer, Integer>>();
        list.add(new Pair<>(0, 0));
        min[0][0]=matrix[0][0];

        while (!list.isEmpty()) {
            Pair<Integer, Integer> top=list.remove(0);

            int i=top.getKey()-1;
            int j=top.getValue();
            if (i >= 0 && i < r && j >= 0 && j < c) {
                if(min[i][j] > min[top.getKey()][top.getValue()] + matrix[i][j]) {
                    if(min[i][j]!=Integer.MAX_VALUE)
                        list.remove(new Pair<>(i, j));
                    min[i][j]=min[top.getKey()][top.getValue()]+matrix[i][j];
                    list.add(new Pair<>(i, j));
                }
            }

            i=top.getKey();
            j=top.getValue()-1;
            if (i >= 0 && i < r && j >= 0 && j < c) {
                if(min[i][j] > min[top.getKey()][top.getValue()] + matrix[i][j]) {
                    if(min[i][j]!=Integer.MAX_VALUE)
                        list.remove(new Pair<>(i, j));
                    min[i][j]=min[top.getKey()][top.getValue()]+matrix[i][j];
                    list.add(new Pair<>(i, j));
                }
            }

            i=top.getKey()+1;
            j=top.getValue();
            if (i >= 0 && i < r && j >= 0 && j < c) {
                if(min[i][j] > min[top.getKey()][top.getValue()] + matrix[i][j]) {
                    if(min[i][j]!=Integer.MAX_VALUE)
                        list.remove(new Pair<>(i, j));
                    min[i][j]=min[top.getKey()][top.getValue()]+matrix[i][j];
                    list.add(new Pair<>(i, j));
                }
            }

            i=top.getKey();
            j=top.getValue()+1;
            if (i >= 0 && i < r && j >= 0 && j < c) {
                if(min[i][j] > min[top.getKey()][top.getValue()] + matrix[i][j]) {
                    if(min[i][j]!=Integer.MAX_VALUE)
                        list.remove(new Pair<>(i, j));
                    min[i][j]=min[top.getKey()][top.getValue()]+matrix[i][j];
                    list.add(new Pair<>(i, j));
                }
            }
        }
        return min[r-1][c-1];
    }

    public static void main(String[] args) {
        Scanner Reader=new Scanner(System.in);
        int t=Reader.nextInt();
        for(int y=0;y<t;y++) {
            int r=Reader.nextInt();
            int c=Reader.nextInt();
            int[][] matrix=new int[r][c];
            int[][] min=new int[r][c];
//            int[][] dp=new int[r][c];
//            int[][] visited=new int[r][c];
            for (int i=0;i<r;i++) {
                for(int j=0;j<c;j++) {
                    matrix[i][j]=Reader.nextInt();
//                    dp[i][j]=-1;
                    min[i][j]=Integer.MAX_VALUE;
                }
            }
            System.out.println(djikstra(matrix, min, r, c));
//            System.out.println(dp(matrix, dp, visited, 0, 0, r-1, c-1));
        }
    }
}
