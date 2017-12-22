public class Request extends Message {
	public static final int METHOD = 1;
	public static final int ARGUMENT = 2;
	public static final int ID = 3;
	private String req_method;
	private String req_argument;
	private String req_id;
	
	public Request(String content, String argument){
		this.req_method = content;
		this.req_argument = argument;
	}
	
	public String get_arg(int key) {
		switch(key){
			case METHOD : return this.req_method;			
			case ARGUMENT : return this.req_argument;
			case ID : return this.req_id;
			default : return null;
		
		}
	}	
	
	public void setID(int i){
		this.req_id = "" + i;
	}
	

}
