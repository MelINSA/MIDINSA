import java.io.IOException;
import java.util.LinkedList;

public class test {

	public static void main(String[] args) throws IOException, InterruptedException {
		LinkedList<Client> clients;
		clients = new LinkedList<Client>();
		for (int i = 0; i < 10; i++){
			clients.add(new Client(i+1));
		}
		
		Input input = new Input();
		input.readTxt(clients);
		
		LoadBalancer loadbalancer = new LoadBalancer(clients);
		loadbalancer.master_node_init(10, 1);
		
        Master master = new Master(loadbalancer.getWorkers());
        

	}

}
