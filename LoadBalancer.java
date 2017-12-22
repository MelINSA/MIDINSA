import java.util.LinkedList;

public class LoadBalancer extends Thread {
	private LinkedList<Worker> workers;
	private LinkedList<Client> clients;
	private int tick_period;
	private int max_workers;
	
	public LoadBalancer(LinkedList<Client> clients){
		this.clients = clients;
	}
	
	public LinkedList<Worker> getWorkers(){
		return this.workers;
	}
	
	/* master node init allows to initialise your master implementation. The argu-
	 * ment max workers specifies the maximum number of worker nodes the master can
	 * use (requests beyond this limit will be denied). The system expects your code to
	 * provide a value for the argument tick period, which is the time interval (in sec-
	 * onds) at which you would like your function handle tick to be called during server
	 * operation. */
	public void master_node_init(int max_workers, int tick_period) throws InterruptedException {
		this.tick_period = tick_period;
		this.max_workers = max_workers;
		int start_workers = max_workers/2;
		
		this.workers = new LinkedList<Worker>(); //create Workers
		for(int i = 0; i < start_workers; i++){
			workers.add(new Worker(i));
		}
		
        Master master = new Master(workers);
        master.readRequests(clients);
        master.start();
        System.out.println("Master started.");
        
		for(int i = 0; i < start_workers; i++){
			workers.get(i).start();
		}
		
		
	}
	
	public void handle_tick() {
	}
	
	/* handle new worker online is called by the master process whenever a
	 * new worker node has booted as is ready to receive requests. Finally, your master
	 * implementation can request new worker nodes to be added to its pool (or request
	 * that worker nodes be removed from the pool). */
	public void handle_new_worker_online(Worker worker, int tag) {
	}
	
	/* After calling request new worker node, the system (at some point in the fu-
	 * ture) will notify your master that the new worker node is ready for requests by
	 * calling handle new worker online. Booting a worker is not instantaneous. The
	 * requested worker node will not become available to your server for about a second. */
	public void request_new_worker_node(Request req) {
	}
	
	/* In contrast, kill worker node will immediately kill a worker. The worker should
	 * not be sent further messages after this call, and any outstanding tasks assigned to
	 * the worker node are lost. THEREFORE, IT WOULD BE UNWISE TO KILL A
	 * WORKER NODE THAT HAS PENDING WORK */
	public void kill_worker_node(Worker worker) {
	}
	
	public void run(){
		while(true){
			try {
				wait(tick_period * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			handle_tick();
		}
	}

}
