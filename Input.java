import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class Input {
	
	public Input(){
		
	}
	
	public void readTxt(LinkedList<Client> clients) throws IOException{
		BufferedReader in = new BufferedReader(new FileReader("client_requests.txt"));
		
		in.readLine(); //to get rid of first line in txt 
		String line;
		while((line = in.readLine()) != null)
		{
			String delims = "[,]";
        	String[] tokens = line.split(delims);
        	Request req = new Request(tokens[1],tokens[2]);
        	clients.get(Integer.parseInt(tokens[0]) - 1).newRequest(req);
		}
		in.close();
	}

}
