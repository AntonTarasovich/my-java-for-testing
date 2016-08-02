package ua.projects.javatests.rest;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

public class RestTests extends TestBase{

    @Test
    public void testCreateIssue() throws IOException {
        Set<Issue> oldIssues = getRestIssues();
        Issue newIssue = new Issue().withSubject("Test issue").withtDescription("New test issue");
        int issueId = createRestIssue(newIssue);
        Set<Issue> newIssues = getRestIssues();
        oldIssues.add(newIssue.withId(issueId));
        Assert.assertEquals(newIssues, oldIssues);
    }



}
