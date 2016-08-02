package ua.projects.javatests.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class GithubTests {

    @Test
    public void testCommits() throws IOException {
        Github github = new RtGithub("97f417249ba8ed935e02ca7e8e9e149a59a04088");
        RepoCommits commits = github.repos().get(new Coordinates.Simple("AntonTarasovich", "my-java-for-testing")).commits();
        for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build())) {
            System.out.println(new RepoCommit.Smart(commit).message());
        }
    }
}
