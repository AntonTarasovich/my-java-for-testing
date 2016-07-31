package ua.projects.javatests.mantis.appmanager;

import org.openqa.selenium.By;

public class NavigationHelper extends  HelperBase{

    public NavigationHelper(ApplicationManager app) {
        super(app);
    }

    public void goToMainPage() {
        wd.get(app.getProperty("web.baseUrl"));

    }

    public void login(String username, String password) {
        wd.get(app.getProperty("web.baseUrl"));
        type(By.name("username"), username);
        type(By.name("password"), password);
        click(By.cssSelector("input[value='Login']"));
    }

    public void goToUsersManage() {
        click(By.linkText("Manage Users"));
    }

    public void selectUser(String name) {
        click(By.linkText(name));
    }

    public void resetPassword() {
        click(By.cssSelector("input[value='Reset Password']"));
    }

    public void changeUserPassword (String name) {
        goToUsersManage();
        selectUser(name);
        resetPassword();
    }
}
