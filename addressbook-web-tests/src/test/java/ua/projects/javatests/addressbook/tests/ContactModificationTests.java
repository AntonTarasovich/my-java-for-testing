package ua.projects.javatests.addressbook.tests;

import org.testng.annotations.Test;
import ua.projects.javatests.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase{

    @Test
    public void testContactModification() {
        if (! app.getContactHelper().isContactExist()) {
            app.getContactHelper().newContactCreation();
            app.getContactHelper().createContact(new ContactData("Anton", "Tarasovich", "Hammer", "MGID", "555-55-55", "anton.tarasovich@mgid.com", "test1"));
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("Vasya", "Petrov", "Tachka", "FBI", "666-66-66", "vasya.petrov@fbi.com", null), false);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().returnToHomePage();
    }
}