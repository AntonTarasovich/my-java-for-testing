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
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("work"), contactData.getWorkPhone());
        type(By.name("email"), contactData.getFirstEmail());
        type(By.name("email2"), contactData.getSecondEmail());
        type(By.name("email3"), contactData.getThirdEmail());
        attach(By.name("photo"), contactData.getPhoto());
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
                    type(By.name("address"), contactData.getAddress());
                    type(By.name("home"), contactData.getHomePhone());
                    type(By.name("mobile"), contactData.getMobilePhone());
                    type(By.name("work"), contactData.getWorkPhone());
                    type(By.name("email"), contactData.getFirstEmail());
                    type(By.name("email2"), contactData.getSecondEmail());
                    type(By.name("email3"), contactData.getThirdEmail());
                    attach(By.name("photo"), contactData.getPhoto());
                }
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText("test1");
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

    public void initContactModificationById(int id) {
        click(By.cssSelector("a[href='edit.php?id=" + id + "']"));
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
        nh.goToHomePage();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectedContact();
    }


    public void modify(ContactData contact) {
        selectContactById(contact.getId());
        initContactModificationById(contact.getId());
        fillContactForm(contact, false);
        submitContactModification();
        nh.goToHomePage();
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public Contacts all() {
        Contacts contacts = new Contacts();
        List<WebElement> rows = wd.findElements(By.name("entry"));
        for (WebElement row : rows) {
            List <WebElement> cells = row.findElements(By.tagName("td"));
            String firstName = cells.get(2).getText();
            String lastName = cells.get(1).getText();
            String address = cells.get(3).getText();
            String allPhones = cells.get(5).getText();
            String allEmails = cells.get(4).getText();
            int id = Integer.parseInt(row.findElement(By.tagName("input")).getAttribute("value"));
            contacts.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName).withAddress(address)
            .withAllPhones(allPhones).withAllEmails(allEmails));
        }
        return contacts;
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
        String nickName = wd.findElement(By.name("nickname")).getAttribute("value");
        String workPlace = wd.findElement(By.name("company")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String homePhone = wd.findElement(By.name("home")).getAttribute("value");
        String mobilePhone = wd.findElement(By.name("mobile")).getAttribute("value");
        String workPhone = wd.findElement(By.name("work")).getAttribute("value");
        String firstEmail = wd.findElement(By.name("email")).getAttribute("value");
        String secondEmail = wd.findElement(By.name("email2")).getAttribute("value");
        String thirdEmail = wd.findElement(By.name("email3")).getAttribute("value");
        return new ContactData().withId(contact.getId()).withFirstName(firstName).withLastName(lastName).withNickname(nickName).withWorkPlace(workPlace)
                .withAddress(address).withHomePhone(homePhone).withMobilePhone(mobilePhone).withWorkPhone(workPhone).withFirstEmail(firstEmail)
                .withSecondEmail(secondEmail).withThirdEmail(thirdEmail);
    }

    public ContactData detailedInfoForm(ContactData contact) {
        nh.goToDetailedInfoPage(contact.getId());
        String info = wd.findElement(By.id("content")).getText().replaceAll("\n", " ").replaceAll("[\\s]{2,}", " ");
        return contact.withInfo(info);
    }

    public boolean isPhotoExist() {
        return isElementPresent(By.cssSelector("img[alt='Embedded Image']"));
    }

    public int maxId() {
        Contacts contacts = all();
        return contacts.stream().mapToInt((g) -> g.getId()).max().getAsInt();
    }

    public void deleteAllContacts() {
        click(By.cssSelector("#MassCB"));
        deleteSelectedContact();
    }

    public void insert(ContactData contact) {
        selectContactById(contact.getId());
        new Select(wd.findElement(By.name("to_group"))).selectByVisibleText("test1");
        /*while (! isElementPresent(By.name("remove")))
                if (isElementPresent(By.tagName("input"))) {
                    group.click();
                }
                int id = Integer.parseInt(row.findElement(By.tagName("input")).getAttribute("value"));
            }
        }
        selectContactById(contact.getId());
    }*/
    }
}
