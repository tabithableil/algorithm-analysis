import java.io.*;
import java.util.*;


class Edge{
	
	public Vertex dest; 
	public double time; 
	
	public Edge(Vertex dest, double time){
		this.dest = dest;
		this.time = time;
	}
	
	public Vertex getDest(){
		return dest;
	}
	
	public double getTime(){
		return time;
	}
	
	public String toString(){
		return dest.getName() + " " + time;
	}
	
	public void setTime(double time){
		this.time = time;
	}
}


class Vertex{
	
    public String name; 
    public LinkedList<Edge> adj; 
    public double time; 
    public Vertex previous;
    
    public Vertex(String name){
    	this.name = name;
    	adj = new LinkedList<Edge>();
    	time = Double.MAX_VALUE;
    	previous = null;
	}
    
    public String getName(){
    	return name;
    } 
}


class Graph{
	
	public static final int INFINITY = Integer.MAX_VALUE;
	private Map<String, Vertex> vertexMap = new HashMap<String, Vertex>();
	
	// add edge between two vertices
	public void addEdge(String sourceName, String destName, double time){
		Vertex v = getVertex(sourceName); 
		Vertex w = getVertex(destName); 
	
		v.adj.add(new Edge(w, time)); 
		w.adj.add(new Edge(v, time));
	}
	
	// delete an edge between two vertices
	public void deleteEdge(String sourceName, String destName){
		Vertex v = getVertex(sourceName); 
		Vertex w = getVertex(destName);
		ListIterator<Edge> itr = v.adj.listIterator(); 
		while(itr.hasNext()){
			Edge current = itr.next();
			if(current.getDest() == w){
				itr.remove();
			}
		}	
	}
	
	private Vertex getVertex(String vertName){
		Vertex v = vertexMap.get(vertName);
		// if vertex does not exist, create a vertex
		if(v == null){
			v = new Vertex(vertName);
			vertexMap.put(vertName, v);
		}
		return v;
	}
	 
	public void getAdj(String vertName){
    	Vertex v = vertexMap.get(vertName);
    	ListIterator<Edge> itr = v.adj.listIterator();
    	while(itr.hasNext()){
    		Edge current = itr.next();
    		System.out.println(current.toString());
    	}
    }

	// Dijkstra's algorithm to find shortest path
	public void dijkstras(String vertName){
		PriorityQueue<Vertex> q = new PriorityQueue<Vertex>();
		Vertex source = vertexMap.get(vertName);
		source.time = 0;

		while(!q.isEmpty()){
			Vertex v = q.poll();
			// compare times to find the shortest path
			for(Edge edge: v.adj){
				Vertex x = edge.dest;
				double wt = edge.time;
				
				if(x.time > v.time + wt){
					q.remove(x);
					x.time = v.time + wt;
					x.previous = x;
					q.add(x);	
				}
			}
		}		
	}	
}


class project3{
	
	 public static void main(String[ ] args){
	        Graph g = new Graph();
	        try
	        {
	            FileReader fin = new FileReader(args[0]);
	            Scanner graphFile = new Scanner(fin);
	            String line;
				
	            while(graphFile.hasNextLine()){
	                line = graphFile.nextLine();
	                StringTokenizer st = new StringTokenizer(line);
					
	                try
	                {
	                	// if there aren't 3 tokens on a line, skip line 
	                    if(st.countTokens() != 3){
	                        System.err.println("Skipping ill-formatted line " + line);
	                        continue;
	                    }
	                    
	                    // otherwise, take in the two vertices and traversal time
	                    String source  = st.nextToken();
	                    String dest    = st.nextToken();
	                    double time = Double.parseDouble(st.nextToken());
	                    g.addEdge(source, dest, time);
	                }
	                catch( NumberFormatException e )
						{ System.err.println( "Skipping ill-formatted line " + line ); }
	             }
	         }
	         catch( IOException e )
	           { System.err.println( e ); }    
	    }
	}



