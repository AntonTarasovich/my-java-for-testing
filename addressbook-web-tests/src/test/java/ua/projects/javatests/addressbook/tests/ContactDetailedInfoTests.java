package ua.projects.javatests.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ua.projects.javatests.addressbook.model.ContactData;
import ua.projects.javatests.addressbook.model.Contacts;

import java.io.File;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.testng.Assert;

public class ContactDetailedInfoTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData().withFirstName("Anton").withLastName("Tarasovich").withNickname("Hammer").withWorkPlace("MGID")
                    .withAddress("Kiev, Dovzhenko str. 3, app. 21").withHomePhone("111-11-11").withMobilePhone("222-22-22").withWorkPhone("333-33-33")
                    .withFirstEmail("anton.tarasovich@mgid.com").withSecondEmail("vasya111@mail.ru").withThirdEmail("petya72@meta.ua").withGroup("test1"));
        }
    }

    @Test
    public void testContactDetailedInfo() {
        app.goTo().goToHomePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactDetailedInfoForm = app.contact().detailedInfoForm(contact);
        app.goTo().goToHomePage();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        app.goTo().goToHomePage();

        assertThat(contactDetailedInfoForm.getInfo(), equalTo(mergeInfo(contactInfoFromEditForm)));
    }

    @Test
    public void testPhotoInDetailedInfo() {
        File photo = new File("src/test/resources/batman.jpg");
        ContactData contact = new ContactData().withFirstName("Anton").withPhoto(photo);
        app.goTo().goToHomePage();
        Contacts before = app.contact().all();
        app.contact().create(contact);
        app.goTo().goToDetailedInfoPage(app.contact().maxId());
        Assert.assertEquals(app.contact().isPhotoExist(), true);
        app.goTo().goToHomePage();
        app.contact().delete(contact.withId(app.contact().maxId()));
        app.alertAccept();
        app.goTo().goToHomePage();
        Contacts after = app.contact().all();

        assertThat(after, equalTo(before));
    }

    public static String mergeInfo(ContactData contact) {
        String info = Arrays.asList(contact.getFirstName() + " " + contact.getLastName(), contact.getNickname(), contact.getWorkPlace(), contact.getAddress(), "",
                phoneWithHomePrefix(contact.getHomePhone()), phoneWithMobilePrefix(contact.getMobilePhone()), phoneWithWorkPrefix(contact.getWorkPhone()), "",
                emailWithDomain(contact.getFirstEmail()), emailWithDomain(contact.getSecondEmail()), emailWithDomain(contact.getThirdEmail())).stream()
                .filter((s) -> ! s.equals("")).filter((s) -> ! s.equals("")).filter((s) -> ! s.equals("\n")).filter((s) -> ! s.equals(" "))
                .collect(Collectors.joining(" "));
        return info;
    }

    public static String emailWithDomain(String email) {
        String domain = email.substring(email.indexOf('@') + 1, email.length());
        if (email.equals("")) {
            return "";
        }
        else if (domain.length() != 0) {
            return email + " (www." + domain + ")";
        }
        return email;
    }

    public static String phoneWithHomePrefix(String phone) {
        if (phone.equals("")) {
            return "";
        }
        return "H: " + phone;
    }

    public static String phoneWithMobilePrefix(String phone) {
        if (phone.equals("")) {
            return "";
        }
        return "M: " + phone;
    }

    public static String phoneWithWorkPrefix(String phone) {
        if (phone.equals("")) {
            return "";
        }
        return "W: " + phone;
    }
}
