package ua.projects.javatests.rest;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import io.restassured.RestAssured;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

public class TestBase {

    String state = null;

    @BeforeClass
    public void init() {
        RestAssured.authentication = RestAssured.basic("LSGjeU4yP1X493ud1hNniA==", "");
    }

    public Set<Issue> getRestAssuredIssues() throws IOException {
        String json = RestAssured.get("http://demo.bugify.com/api/issues.json").asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        return new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
    }

    public int createRestAssuredIssue(Issue newIssue) throws IOException {
        String json = RestAssured.given().param("subject", newIssue.getSubject()).param("description", newIssue.getDescription())
                .post("http://demo.bugify.com/api/issues.json").asString();
        JsonElement parsed = new JsonParser().parse(json);
        return parsed.getAsJsonObject().get("issue_id").getAsInt();
    }

    public Set<Issue> getRestIssues() throws IOException {
        String json = getExecutor().execute(Request.Get("http://demo.bugify.com/api/issues.json")).returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        return new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
    }

    public Executor getExecutor() {
        return Executor.newInstance().auth("LSGjeU4yP1X493ud1hNniA==", "");
    }

    public int createRestIssue(Issue newIssue) throws IOException {
        String json = getExecutor().execute(Request.Post("http://demo.bugify.com/api/issues.json")
                .bodyForm(new BasicNameValuePair("subject", newIssue.getSubject()), new BasicNameValuePair("description", newIssue.getDescription())))
                .returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        return parsed.getAsJsonObject().get("issue_id").getAsInt();
    }


    public boolean isIssueOpen(int issueId) throws IOException {

        String json = RestAssured.get("http://demo.bugify.com/api/issues/" + issueId + ".json").getBody().asString();
        JsonObject parsed = new JsonParser().parse(json).getAsJsonObject();
        JsonArray issues = parsed.getAsJsonArray("issues");
        for (JsonElement state_name : issues) {
            JsonObject myObject = state_name.getAsJsonObject();
            state = myObject.get("state_name").toString().replaceAll("\"", "");
        }
        return !state.equals("Resolved");
    }

    public void skipIfNotFixed(int issueId) throws IOException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

}
