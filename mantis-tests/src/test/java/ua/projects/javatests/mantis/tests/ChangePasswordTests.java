package ua.projects.javatests.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.mail.MessagingException;
import java.io.IOException;

import static org.testng.Assert.assertTrue;

public class ChangePasswordTests extends TestBase{

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testChangePassword() throws IOException, MessagingException {
        String user = app.registration().userRegistration();
        app.navigation().login(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
        app.navigation().changeUserPassword(user);
        app.registration().userPasswordChangingConfirmation(user);
        app.registration().changePassword(app.getProperty("web.newPassword"));
        assertTrue(app.newSession().login(user, app.getProperty("web.newPassword")));
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }

}
