package JSONTransmitProtocol.newReader;

import JSONTransmitProtocol.newReader.query.Question;
import JSONTransmitProtocol.newReader.query.Response;

public class Query {
	private String type;
	private Question question;
	private Response response;
	
	@SuppressWarnings("unused")
	private Query() {}
	public Query(String type) {
		this.type = type;
		question = new Question();
		response = new Response();
	}
	
	public Question getQuestion() {
		return question;
	}
	public Response getResponse() {
		return response;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	public void setResponse(Response response) {
		this.response = response;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
