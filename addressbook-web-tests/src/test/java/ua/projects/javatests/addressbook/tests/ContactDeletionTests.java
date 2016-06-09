package ua.projects.javatests.addressbook.tests;

import org.testng.annotations.Test;
import ua.projects.javatests.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion() {
        app.getContactHelper().selectContact();
        if (! app.getContactHelper().isContactExist()) {
            app.getContactHelper().createContact(new ContactData("Anton", "Tarasovich", "Hammer", "MGID", "555-55-55", "anton.tarasovich@mgid.com", "test1"), true);
        }
        app.getContactHelper().deleteSelectedContact();
        app.alertAccept();
        app.getNavigationHelper().returnToHomePage();
    }
}
