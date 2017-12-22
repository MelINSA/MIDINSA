import java.util.ArrayList;

public class Client extends Thread{
	
	private int succesfullRequests;	
	private int id;
	private ArrayList<Request> requests;
	
	public Client(int id) {
		this.id = id;
		this.succesfullRequests = 0;
		this.requests = new ArrayList<Request>();
	}
	
	
	public void newRequest(Request req){
		this.requests.add(req);	
	}
	
	public ArrayList<Request> getRequests() {
		return this.requests;
	}

	public int getThreadId() {
		return this.id;
	}
	
	public void incrementSuccesfullRequests(){
		this.succesfullRequests++;
	}

}
