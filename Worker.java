
public class Worker extends Thread {
	private int worker_id;
	private Request currentTask = null;
	private Response response = null;
	private boolean ready = false;
	
	public Worker(int id){
		this.worker_id = id;
	}
	
	public int getWorkerId(){
		return worker_id;
	}
	
	/* worker node init is an initialisation function that gives your worker imple-
	 * mentation the opportunity to setup any required data structures (if you need any). */
	public void worker_node_init(Request params) {
		this.currentTask = params;
	}
	
	/* worker handle request accepts as input a request object (e.g. a dictionary) de-
	 * scribing a job. The worker must execute the job, and must send a response to the
	 * master. It will do so using the following two library functions. */
	public void worker_handle_request(Request req) {
		this.currentTask = req;
	}
	
	
	/* execute work is a black-box library function that interprets a request, executes
	 * the required work in the calling thread of control and populates a response. */
	public void execute_work(Request req, Response resp) {
		System.out.println("Worker (ID: "+ worker_id + ") is starting work on: " + currentTask.get_arg(Request.METHOD));
		/*currentTask = null;
		ready = true;*/
	}
	
	/* Your worker code is then responsible for sending this response back to the master using
	 * worker send response. */
	public void worker_send_response(Response resp) {
		
	}
	
	public void run(){
		try {
			System.out.println("Booting new worker.");
			sleep(1000);
			System.out.println("New worker (ID: "+ worker_id + ") is now available.");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		ready = true;
		while(true){
			//System.out.println("test");
			//System.out.println("Worker (ID: "+ worker_id + ") is starting work on: ");
			if(currentTask != null && ready){
				ready = false;
				Response resp = new Response(currentTask.get_arg(Request.ID));
				execute_work(currentTask, resp);
			}
		}
	}
	
	public boolean isReady(){
			return ready;
		
	}

}
