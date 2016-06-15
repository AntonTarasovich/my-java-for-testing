package ua.projects.javatests.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ua.projects.javatests.addressbook.model.ContactData;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase{

    @Test
    public void testContactModification() {
        if (! app.getContactHelper().isContactExist()) {
            app.getContactHelper().newContactCreation();
            app.getContactHelper().createContact(new ContactData("Anton", "Tarasovich", "Hammer", "MGID", "555-55-55", "anton.tarasovich@mgid.com", "test1"));
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().initContactModification();
        ContactData contact = new ContactData(before.get(before.size() - 1).getId(), "Vasya", "Petrov", "Tachka", "FBI", "666-66-66", "vasya.petrov@fbi.com", null);
        app.getContactHelper().fillContactForm(contact, false);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().returnToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(contact);
        Assert.assertEquals(new HashSet<Object>(after), new HashSet<Object>(before));
    }
}