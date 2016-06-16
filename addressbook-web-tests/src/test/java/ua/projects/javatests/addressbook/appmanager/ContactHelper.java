package ua.projects.javatests.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ua.projects.javatests.addressbook.model.ContactData;
import ua.projects.javatests.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitNewContact() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("nickname"), contactData.getNickname());
        type(By.name("company"), contactData.getWorkPlace());
        type(By.name("home"), contactData.getTelephoneNumber());
        type(By.name("email"), contactData.getEmail());
            if (creation) {
                click(By.name("new_group"));
                    if (wd.findElements(By.cssSelector("select[name=new_group] option")).size() > 1) {
                    return;
                } else {
                    GroupHelper gh = new GroupHelper(wd);
                    NavigationHelper nh = new NavigationHelper(wd);
                    nh.goToGroupPage();
                    gh.createGroup(new GroupData("test1", null, null));
                    click(By.linkText("add new"));
                    type(By.name("firstname"), contactData.getFirstName());
                    type(By.name("lastname"), contactData.getLastName());
                    type(By.name("nickname"), contactData.getNickname());
                    type(By.name("company"), contactData.getWorkPlace());
                    type(By.name("home"), contactData.getTelephoneNumber());
                    type(By.name("email"), contactData.getEmail());
                }
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
            } else {
                Assert.assertFalse(isElementPresent(By.name("new_group")));
            }
    }

    public void newContactCreation() {
        click(By.linkText("add new"));
    }

    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void deleteSelectedContact() {
        click(By.cssSelector(".left>input[value='Delete']"));
    }

    public void initContactModification(int index) {
        wd.findElements(By.cssSelector(".center>a>img[title='Edit']")).get(index).click();
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public boolean isContactExist() {
        return isElementPresent(By.name("selected[]"));
    }

    public void createContact(ContactData contact) {
        fillContactForm(contact, true);
        submitNewContact();
        NavigationHelper nh = new NavigationHelper(wd);
        nh.returnToHomePage();
    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> rows = wd.findElements(By.name("entry"));
        for (WebElement row : rows) {
            List <WebElement> cells = row.findElements(By.tagName("td"));
            String firstName = cells.get(2).getText();
            String secondName = cells.get(1).getText();
            int id = Integer.parseInt(row.findElement(By.tagName("input")).getAttribute("value"));
            ContactData contact = new ContactData(id, firstName, secondName, null, null, null, null, null);
            contacts.add(contact);
        }
        return contacts;
    }
}
