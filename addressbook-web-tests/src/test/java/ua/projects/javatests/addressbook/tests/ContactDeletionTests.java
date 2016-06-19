package ua.projects.javatests.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ua.projects.javatests.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.contact().list().size() == 0) {
            app.contact().create(new ContactData().withFirstName("Anton").withLastName("Tarasovich").withNickname("Hammer").withWorkPlace("MGID")
                    .withTelephoneNumber("555-55-55").withEmail("anton.tarasovich@mgid.com").withGroup("test1"));
        }
    }

    @Test
    public void testContactDeletion() {
        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;
        app.contact().delete(index);
        app.alertAccept();
        app.goTo().returnToHomePage();
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size() - 1);
        before.remove(index);
        Assert.assertEquals(after, before);
    }
}
