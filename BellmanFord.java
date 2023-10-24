import java.util.*;
public class BellmanFord {
	int n, m;
	ArrayList<Edge> edges;
	int INF = 1000000000;
	boolean cy =false;
	ArrayList<Integer>cycle;
	public BellmanFord(int n , int m , ArrayList<Edge>edges){
		this.n = n;
		this.m = m;
		this.edges = edges;
		cycle=new ArrayList<>();
	}
	boolean hasNegativeCycle() {
		return this.cy;
	}
	ArrayList<Integer>getCycle(){
		return this.cycle;
	}
	void run()
	{
		double[]d = new double[n];
		int[]p = new int[n];
		Arrays.fill(p, -1);
	    int x=-1;
	    for (int i = 0; i < n; ++i) {
	        x = -1;
	        for (Edge e : edges) {
	            if (d[e.a] + e.cost < d[e.b]) {
	                d[e.b] = d[e.a] + e.cost;
	                p[e.b] = e.a;
	                x = e.b;
	            }
	        }
	    }

	    if (x == -1) {
	        return;
	    } else {
	    	cy =true;
	        for (int i = 0; i < n; ++i)
	            x = p[x];
	        
	        for (int v = x;; v = p[v]) {
	            cycle.add(v);
	            if (v == x && cycle.size() > 1)
	                break;
	        }
	        Collections.reverse(cycle);
	        return ;
	    }
	}
}
