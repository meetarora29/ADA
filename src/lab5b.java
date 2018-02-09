import java.util.Scanner;

class Point {
    double x, y;

    Point(double x, double y) {
        this.x=x;
        this.y=y;
    }
}



public class lab5b {
    public static double distance(Point one, Point two) {
        return Math.sqrt((one.x-two.x)*(one.x-two.x) + (one.y-two.y)*(one.y-two.y));
    }

    public static int dfs_matching(int[][] graph, int i, int[] visited, int[] matching, int p, int q) {
        if(i<0)
            return -1;

        for(int j=0;j<q;j++) {
            if(graph[i][j]==1 && visited[j]==0) {
                visited[j]=1;

                if(matching[j]<0 || dfs_matching(graph, matching[j], visited, matching, p, q)==1) {
                    matching[j]=i;
                    return 1;
                }
            }
        }
        return -1;
    }

    public static int match(int[][] graph, int p, int q, int[] matching, int[] visited) {
        int result=0;
        for(int i=0;i<p;i++ ) {
            if(dfs_matching(graph, i, visited, matching, p, q)==1)
                result++;
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner Reader=new Scanner(System.in);
        int p=Reader.nextInt();
        int q=Reader.nextInt();
        int t=Reader.nextInt();
        int v=Reader.nextInt();
        Point[] squirrels=new Point[p];
        Point[] holes=new Point[q];
        int[][] graph=new int[p][q];
        for(int i=0;i<p;i++) {
            squirrels[i]=new Point(Reader.nextDouble(), Reader.nextDouble());
        }
        for(int i=0;i<q;i++) {
            holes[i]=new Point(Reader.nextDouble(), Reader.nextDouble());
        }
        double d=t*v;
        for(int i=0;i<p;i++) {
            for(int j=0;j<q;j++) {
                if(distance(squirrels[i], holes[j])<=d)
                    graph[i][j]=1;
            }
        }

        int[] visited=new int[q];
        int[] matching=new int[q];

        for(int i=0;i<q;i++)
            matching[i]=-1;

        for(int i=0;i<p;i++) {
            dfs_matching(graph, i, visited, matching, p, q);
        }

        int sum=0;
        for(int i=0;i<q;i++) {
            if(matching[i]!=-1)
                sum+=1;
        }
        System.out.println(p-sum);
    }
}