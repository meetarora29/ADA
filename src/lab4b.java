import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

class Pair2<T1, T2> implements Comparable<Pair2<T1, T2>> {
    T1 key;
    T2 value;

    Pair2(T1 key, T2 value) {
        this.key=key;
        this.value=value;
    }

    T1 getKey() {
        return key;
    }

    T2 getValue() {
        return value;
    }

    public int compareTo(Pair2 p) {
        return Integer.compare((Integer)this.value, (Integer) p.value);
    }
}

public class lab4b {
    public static int _wait(int f1, int f2, int time) {
        int distance=Math.abs(f1-f2);
        int remainder=-1;
        distance*=10;

        if(f1<f2) {
            remainder=time%distance;
            return (distance-remainder)%distance;
        }
        return time%distance;
    }

    public static int dijkstra(ArrayList<ArrayList<Pair2<Integer, Integer>>> graph ,int[] visited, int top) {
        PriorityQueue<Pair2<Integer, Integer>> pq=new PriorityQueue<>();
        pq.add(new Pair2<>(0, 0));
        while (!pq.isEmpty()) {
            Pair2 pair=pq.poll();
            
            if((Integer)pair.key==top)
                return (Integer)pair.value;

            if(visited[(Integer)pair.key]==1)
                continue;
            
            visited[(Integer) pair.key]=1;
            
            for(int i=0;i<graph.get((Integer)pair.key).size();++i) {
                Pair2 temp=graph.get((Integer)pair.key).get(i);
                pq.add(new Pair2<>((Integer)temp.key, (Integer)pair.value + _wait((Integer)pair.key, (Integer)temp.key, (Integer)pair.value)+(Integer)temp.value));
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int u=in.nextInt();
        int n=in.nextInt();
        ArrayList<ArrayList<Pair2<Integer, Integer>>> graph=new ArrayList<ArrayList<Pair2<Integer, Integer>>>();

        int[] visited=new int[u];
        for(int i=0;i<u;i++)
            graph.add(new ArrayList<Pair2<Integer, Integer>>());

        for(int i=0;i<n;i++) {
            int f1=in.nextInt()-1;
            int f2=in.nextInt()-1;
            graph.get(f1).add(new Pair2<>(f2, (f2-f1)*5));
            graph.get(f2).add(new Pair2<>(f1, (f2-f1)*5));
        }

        System.out.println(dijkstra(graph, visited, u-1));
    }
}
