
public class Response {
	private String tag;
	private String response;
	
	
	
	public Response(String tag) {
		this.tag = ""+tag;
	}

	public String get_tag() {
		return this.tag;
	}
	
	public String get_response() {
		return this.response;
	}

}
