package ua.projects.javatests.rest;

import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

public class RestAssuredTests extends TestBase{

    @BeforeClass
    public void init() {
        RestAssured.authentication = RestAssured.basic("LSGjeU4yP1X493ud1hNniA==", "");
    }

    @Test
    public void testCreateIssue() throws IOException {
        skipIfNotFixed(1);
        Set<Issue> oldIssues = getRestAssuredIssues();
        Issue newIssue = new Issue().withSubject("Test issue").withtDescription("New test issue");
        int issueId = createRestAssuredIssue(newIssue);
        Set<Issue> newIssues = getRestAssuredIssues();
        oldIssues.add(newIssue.withId(issueId));
        Assert.assertEquals(newIssues, oldIssues);
    }

}
