package ua.projects.javatests.addressbook.tests;

import org.testng.annotations.Test;
import ua.projects.javatests.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {
    
    @Test
    public void testContactCreation() {
        app.getContactHelper().newContactCreation();
        app.getContactHelper().fillContactForm(new ContactData("Anton", "Tarasovich", "Hammer", "MGID", "555-55-55", "anton.tarasovich@mgid.com"));
        app.getContactHelper().submitNewContact();
        app.getNavigationHelper().returnToHomePage();
    }
}
