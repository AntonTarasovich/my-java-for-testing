package ua.projects.javatests.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.lanwen.verbalregex.VerbalExpression;
import ua.projects.javatests.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class RegistrationHelper extends HelperBase {

    public RegistrationHelper(ApplicationManager app) {
        super(app);
    }



    public void start(String username, String email) {
        wd.get(app.getProperty("web.baseUrl") + "signup_page.php");
        type(By.name("username"), username);
        type(By.name("email"), email);
        click(By.cssSelector("input[value='Signup']"));
    }

    public void finish(String confirmationLink, String password) {
        wd.get(confirmationLink);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.cssSelector("input[value='Update User']"));
    }

    public String userRegistration() throws IOException, MessagingException {
        long now = System.currentTimeMillis();
        String email = String.format("user%s@localhost.localdomain", now);
        String user = String.format("user%s", now);
        String password = "password";
        //app.james().createUser(user, password);
        app.registration().start(user, email);
        List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
        //List<MailMessage> mailMessages = app.james().waitForMail(user, password, 60000);
        String confirmationLink = findConfirmationLink(mailMessages, email);
        app.registration().finish(confirmationLink, password);
        return user;
    }

    public void userPasswordChangingConfirmation(String user) throws IOException, MessagingException {
        String email = user + "@localhost.localdomain";
        List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
        String confirmationLink = findPasswordChangingLink(mailMessages);
        wd.get(confirmationLink);
        click(By.cssSelector("input[value='Update User']"));
    }

    public void changePassword(String newPassword) {
        WebElement dynamicElement = (new WebDriverWait(wd, 10)).until(ExpectedConditions.presenceOfElementLocated(By.name("password")));
        type(By.name("password"), newPassword);
        type(By.name("password_confirm"), newPassword);
        click(By.cssSelector("input[value='Update User']"));
    }

    public String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> (m.to.equals(email))).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        regex.getText(mailMessage.text);
        return regex.getText(mailMessage.text);
    }

    public String findPasswordChangingLink(List<MailMessage> mailMessages) {
        MailMessage mailMessage = mailMessages.stream().collect(Collectors.toList()).get(2);
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        regex.getText(mailMessage.text);
        return regex.getText(mailMessage.text);
    }

}
