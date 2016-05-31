package ua.projects.javatests.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NavigationHelper {

    private FirefoxDriver wd;

    public NavigationHelper(FirefoxDriver wd) {
        this.wd = wd;
    }

    public void returnToHomePage() {
        wd.findElement(By.linkText("home page")).click();
    }

    public void goToGroupCreation() {
        wd.findElement(By.linkText("groups")).click();
    }
}
