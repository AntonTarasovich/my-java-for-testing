package ua.projects.javatests.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ua.projects.javatests.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion() {

        if (! app.getContactHelper().isContactExist()) {
            app.getContactHelper().createContact(new ContactData("Anton", "Tarasovich", "Hammer", "MGID", "555-55-55", "anton.tarasovich@mgid.com", "test1"));
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().deleteSelectedContact();
        app.alertAccept();
        app.getNavigationHelper().returnToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);
        before.remove(before.size() - 1);
        Assert.assertEquals(after, before);
    }
}
