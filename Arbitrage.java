import java.util.*;
public class Arbitrage {

    // this class cannot be instantiated
    private Arbitrage() { }

    /**
     *  nexts the currency exchange table from standard input and
     *  prints an arbitrage opportunity to standard output (if one exists).
     *
     * @param args the command-line arguments
     */
    
    public static void main(String[] args) {
    	Scanner in = new Scanner (System.in);
        // V currencies
        int V = in.nextInt();
        
        String[] name = new String[V];
        ArrayList<Edge>gr =new ArrayList<>();
        
        double[][]adjm =new double[V][V];
        for (int v = 0; v < V; v++) {
            name[v] = in.next();
            for (int w = 0; w < V; w++) {
                double rate = in.nextDouble();
                adjm[v][w] =rate;
                Edge e1 = new Edge(v,w,-Math.log(rate));
                gr.add(e1);
            }
        }
        
        BellmanFord bf =new BellmanFord(V, V*V, gr);
        bf.run();
        
        
        if(bf.hasNegativeCycle()) {
        	double stake = 1000.0;
        	ArrayList<Integer>cy = bf.getCycle();
        	
        	for (int i= 0;i<cy.size()-1;i++) {
                System.out.printf("%10.5f %s ", stake, name[cy.get(i)]);
                stake *= adjm[cy.get(i)][cy.get(i+1)];
                System.out.printf("= %10.5f %s\n", stake, name[cy.get(i+1)]);
            }
        	
        }
        else {
        	System.out.println("No arbitrage opportunity");
        }
    }

}
