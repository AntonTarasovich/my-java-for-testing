package ua.projects.javatests.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {
    
    @Test
    public void testContactCreation() {
        login("admin", "secret");
        newContactCreation();
        fillContactForm(new ContactData("Anton", "Tarasovich", "Hammer", "MGID", "555-55-55", "anton.tarasovich@mgid.com"));
        submitNewContact();
        returnToHomePage();
    }
}
