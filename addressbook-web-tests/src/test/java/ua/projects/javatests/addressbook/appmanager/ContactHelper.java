package ua.projects.javatests.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ua.projects.javatests.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitNewContact() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("nickname"), contactData.getNickname());
        type(By.name("company"), contactData.getWorkPlace());
        type(By.name("home"), contactData.getTelephoneNumber());
        type(By.name("email"), contactData.getEmail());
    }

    public void newContactCreation() {
        click(By.linkText("add new"));
    }

    public void selectGroup() {
        click(By.name("selected[]"));
    }

    public void deleteSelectedGroup() {
        click(By.cssSelector(".left>input[value='Delete']"));
    }

    public void initContactModification() {
        click(By.cssSelector(".center>a>img[title='Edit']"));
    }

    public void submitContactModification() {
        click(By.name("update"));
    }
}
