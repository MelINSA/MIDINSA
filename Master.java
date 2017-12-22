
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue; 

public class Master extends Thread{
	
	private LinkedList<Worker> workers;
	private LinkedList<Client> clients;
	private LinkedList<Request> requests;
	private LinkedList<int[]> clientNoForRequest; //Pair of ID and Client number
	private static BlockingQueue<Integer> queue;
	
    public Master(LinkedList<Worker> workers){
    	this.queue = new ArrayBlockingQueue<>(10);
    	this.workers = workers;

    } 
    
    /*
     * As this was not a networks course I had the Client/Server communication very simple and have all requests sent in the beginning.
     * As they were mapped to their clients in the beginning, the order in which they are received
     * by the Master node/our server the requests are not ordered like in the TXT file but ordered by client 
     */
    public void readRequests(LinkedList<Client> clients){
    	this.clients = clients;
    	//this.requests = requests;
    	this.requests = new LinkedList<Request>();
    	this.clientNoForRequest = new LinkedList<int[]>();
    	int counter = 0;
    	for (int i = 0; i < clients.size(); i++){
    		for (int j = 0; j < clients.get(i).getRequests().size(); j++) {
    			Request req = clients.get(i).getRequests().get(j);
    			requests.add(req);
    			requests.getLast().setID(counter);
    			int[] mapping = {counter, i};
    			clientNoForRequest.add(mapping);
    			counter++;
    		}
    	}
    	System.out.println(""+ counter + " requests read.");
    	
    }
	   

	@Override
	public void run() {
        while (!requests.isEmpty() || !queue.isEmpty()) {
    		int workersAvailable = 0;
        	for (Worker w : workers){
        		if(w.isReady()){
        			workersAvailable++;
        			
        		}
        	}
    		System.out.println("Workers available: "+workersAvailable);

        	
        	if(workersAvailable > 0 && !requests.isEmpty()){

        		Request toProcess = requests.getFirst();
        		int clientID = Integer.parseInt(toProcess.get_arg(Request.ID));
        		handle_client_request(getClientFromReqID(clientID), toProcess);
        		//requests.removeFirst();
        	}
        	
        	
        	
        }
    }
	
	public Client getClientFromReqID(int id){
		for (int[] pair : clientNoForRequest){
			if(pair[0] == id){
				return clients.get(pair[1]);
			}
		}
		return null; //not found
	}
	

	/* handle client request is called by the master whenever a new client request
 * arrives at the web server. A request is provided to your code as a dictionary (a list
 * of key-value pairs: req.get_arg(key)). Your implementation will need to inspect
 * the request and make decisions about how to service the request using worker nodes
 * in the worker pool. client has a unique identifier for this request to your server.
 * It is guaranteed to be a unique identifier for all outstanding requests. */
	public void handle_client_request(Client client, Request req) {
    	for (Worker w : workers){
    		if(w.isReady()){
    			w.worker_node_init(req);    			
    			break;
    		}
    		
    	}
    	

		;
	}
	
	/* handle worker response is called whenever a worker node 
	 * reports results back to the master. The response of a worker consists of a tag and a
	 * string (resp.get_tag() and resp.get_response()). The responding worker node
	 * is identified by worker_id (each worker node is given a unique id which your master
	 * learns about in handle new worker online { see \Elasticity" section below.). */
	public void handle_worker_response(Worker worker, Response resp) {
		;
	}
	
	
	/* send client response sends the provided response to the specified client.
	 *  This client handle should match the client handle provided in the
	 *  initial call to handle client request. */
	public void send_client_response(Client client, Response resp) {
	}
	
	/* sends the job described by the key-value pairs in the
	 * request object to a worker. Assuming that you have implemented your worker node
	 * code properly, the master, after calling send request to worker should expect to
	 * see a handle worker response event in the future. */
	public void send_request_to_worker(Worker worker, Request req) {
	}

	
	

}
