package JSONTransmitProtocol.newReader;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import JSONTransmitProtocol.newReader.query.Response;


public class JSONReader{
	/** transform json file to String */
	private String jsonString;
	private String type;
	private Info info;
	private Submission submission;
	private Login login;
	private Query query;
	public JSONReader(String jsonString) {
		this.jsonString = jsonString;
		System.out.println(this.jsonString);
		init();
	}
	
	/**
	 * Parse the json file
	 */
	private void init() {
		try {
			// init json object and get total type
			JSONObject jsonObject = new JSONObject(jsonString);
			type = jsonObject.getString("type");
			
			// get info first
			JSONObject infoJsonObject = jsonObject.getJSONObject("info");
			info = new Info(infoJsonObject.getInt("UID"));
			info.setUsername(infoJsonObject.getString("username"));
			info.setIP(infoJsonObject.getString("IP"));
			info.setPID(infoJsonObject.getInt("PID"));
			info.setTime(infoJsonObject.getString("time"));
			
			// handle other type
			if (type.equals("submission")) {
				JSONObject submissionJsonObject = jsonObject.getJSONObject("submission");
				String submissionType = submissionJsonObject.getString("type");
				submission = new Submission(submissionType);
				if (submissionType.equals("sent")) {
					JSONObject sentJsonObject = submissionJsonObject.getJSONObject("sent");
					submission.setLanguage(sentJsonObject.getString("language"));
					submission.setCode(sentJsonObject.getString("code"));
				}
				else if (submissionType.equals("response")) {
					JSONObject responseJsonObject = submissionJsonObject.getJSONObject("response");
					submission.setUID(responseJsonObject.getInt("UID"));
					submission.setPID(responseJsonObject.getInt("PID"));
					submission.setUsername(responseJsonObject.getString("username"));
					submission.setSubmissionID(responseJsonObject.getInt("submissionID"));
					submission.setResult(responseJsonObject.getString("result"));
					submission.setTime(responseJsonObject.getString("time"));
				}
				
			}
			else if (type.equals("login")) {
				JSONObject loginJsonObject = jsonObject.getJSONObject("login");
				String loginType = loginJsonObject.getString("type");
				login = new Login(loginType);
				if (loginType.equals("login")) {
					login.setPassword(loginJsonObject.getString("password"));
				}
				else if (loginType.equals("check")) {
					JSONObject checkJsonObject = loginJsonObject.getJSONObject("check");
					login.setUID(checkJsonObject.getInt("UID"));
					login.setStatement(checkJsonObject.getString("statement"));
				}
			}
			else if (type.equals("query")) {
				JSONObject queryJsonObject = jsonObject.getJSONObject("query");
				String queryType = queryJsonObject.getString("type");

				query = new Query(queryType);
				if (queryType.equals("question")) {
					JSONObject questionJsonObject = queryJsonObject.getJSONObject("question");
					String questionType = questionJsonObject.getString("type");
					query.getQuestion().setType(questionType);
					if (questionType.equals("rank")) {
						query.getQuestion().setRank(questionJsonObject.getInt("rank"));
					}
					else if (questionType.equals("problemrate")) {
						query.getQuestion().setProblemrate(questionJsonObject.getInt("problemrate"));
					}
					else if (questionType.equals("statistics")) {
						query.getQuestion().setStatistics(questionJsonObject.getString("statistics"));
					}
					else if (questionType.equals("friendlist")) {
						// do nothing, type is already set
					}
				}
				else if (queryType.equals("response")) {
					JSONObject responseJsonObject = queryJsonObject.getJSONObject("response");
					String responseType = responseJsonObject.getString("type");
					query.setResponse(new Response(responseType));
					if (responseType.equals("rank")) {
						JSONArray rankJsonArray = responseJsonObject.getJSONArray("rank");
						for (int i = 0; i < rankJsonArray.length(); i++) {
							String username;
							int solved;
							JSONObject rankPair = rankJsonArray.getJSONObject(i);
							username = rankPair.getString("username");
							solved = rankPair.getInt("solved");
							query.getResponse().getRank().AddRank(username, solved);
						}
					}
					else if (responseType.equals("problemrate")) {
						JSONObject problemrateJsonObject = responseJsonObject.getJSONObject("problemrate");
						query.getResponse().getProblemRate().setPID(problemrateJsonObject.getInt("PID"));
						query.getResponse().getProblemRate().setTotalSubmission(problemrateJsonObject.getInt("TotalSubmission"));
						query.getResponse().getProblemRate().setAccept(problemrateJsonObject.getInt("Accept"));
						query.getResponse().getProblemRate().setWrongAnswer(problemrateJsonObject.getInt("WrongAnswer"));
						query.getResponse().getProblemRate().setRuntimeError(problemrateJsonObject.getInt("RuntimeError"));
						query.getResponse().getProblemRate().setTimeLimitExceeded(problemrateJsonObject.getInt("TimeLimitExceeded"));
						query.getResponse().getProblemRate().setMemoryLimitExceeded(problemrateJsonObject.getInt("MemoryLimitExceeded"));
						query.getResponse().getProblemRate().setCompileError(problemrateJsonObject.getInt("CompileError"));
					}
					else if (responseType.equals("friendlist")){
						JSONArray friendlistJsonArray = responseJsonObject.getJSONArray("friendlist");
						for (int i = 0; i < friendlistJsonArray.length(); i++) {
							query.getResponse().getFriendList().addFriend(friendlistJsonArray.getString(i));
						}
					}
					else if (responseType.equals("statistics")) {
						JSONArray statisticsJsonArray = responseJsonObject.getJSONArray("statistics");
						for (int i = 0; i < statisticsJsonArray.length(); i++) {
							JSONObject problemtypeAndSolvedJsonObject = statisticsJsonArray.getJSONObject(i);
							String problemtype = problemtypeAndSolvedJsonObject.getString("problemtype");
							int solved = problemtypeAndSolvedJsonObject.getInt("solved");
							query.getResponse().getStatistics().AddStatistics(problemtype, solved);
						}
					}
				}
			}
			else {
				System.out.println("Unknown Error!");
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getType() {
		return type;
	}
	public Info getInfo() {
		return info;
	}
	public Login getLogin() {
		return login;
	}
	public Query getQuery() {
		return query;
	}
	public Submission getSubmission() {
		return submission;
	}
}
