package ua.projects.javatests.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ua.projects.javatests.addressbook.model.ContactData;
import ua.projects.javatests.addressbook.model.Contacts;
import ua.projects.javatests.addressbook.model.GroupData;

import java.util.List;

public class ContactHelper extends HelperBase {

    NavigationHelper nh = new NavigationHelper(wd);

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
                    nh.groupPage();
                    gh.create(new GroupData().withName("test1"));
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

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void deleteSelectedContact() {
        click(By.cssSelector(".left>input[value='Delete']"));
    }

    public void initContactModification(int id) {
        wd.findElement(By.cssSelector("a[href='edit.php?id=" + id + "']")).click();
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public boolean isContactExist() {
        return isElementPresent(By.name("selected[]"));
    }

    public void create(ContactData contact) {
        newContactCreation();
        fillContactForm(contact, true);
        submitNewContact();
        nh.returnToHomePage();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectedContact();
    }


    public void modify(ContactData contact) {
        selectContactById(contact.getId());
        initContactModification(contact.getId());
        fillContactForm(contact, false);
        submitContactModification();
        nh.returnToHomePage();
    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public Contacts all() {
        Contacts contacts = new Contacts();
        List<WebElement> rows = wd.findElements(By.name("entry"));
        for (WebElement row : rows) {
            List <WebElement> cells = row.findElements(By.tagName("td"));
            String firstName = cells.get(2).getText();
            String lastName = cells.get(1).getText();
            int id = Integer.parseInt(row.findElement(By.tagName("input")).getAttribute("value"));
            contacts.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName));
        }
        return contacts;
    }

}
