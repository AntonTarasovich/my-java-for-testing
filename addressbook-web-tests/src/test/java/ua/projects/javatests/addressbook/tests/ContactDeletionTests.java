package ua.projects.javatests.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ua.projects.javatests.addressbook.model.ContactData;
import ua.projects.javatests.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            File photo = new File("src/test/resources/batman.jpg");
            app.contact().create(new ContactData().withFirstName("Anton").withLastName("Tarasovich").withNickname("Hammer").withWorkPlace("MGID")
                    .withAddress("Kiev, Dovzhenko str. 3, app. 21").withHomePhone("111-11-11").withMobilePhone("222-22-22").withWorkPhone("333-33-33")
                    .withFirstEmail("anton.tarasovich@mgid.com").withSecondEmail("vasya111@mail.ru").withThirdEmail("petya72@meta.ua").withGroup("test1")
                    .withPhoto(photo));
        }
    }

    @Test
    public void testContactDeletion() {
        Contacts before = app.db().contacts();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        app.alertAccept();
        app.goTo().goToHomePage();
        assertThat(app.contact().count(), equalTo(before.size() - 1));
        Contacts after = app.db().contacts();

        assertThat(after, equalTo(before.without(deletedContact)));
    }

    @Test(enabled = false)
    public void testAllContactDeletion() {
        app.goTo().goToHomePage();
        app.contact().deleteAllContacts();
        app.alertAccept();
        app.goTo().goToHomePage();
        assertThat(app.contact().count(), equalTo(0));
    }
}
