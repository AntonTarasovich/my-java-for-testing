package ua.projects.javatests.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ua.projects.javatests.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.contact().list().size() == 0) {
            app.contact().create(new ContactData().withFirstName("Anton").withLastName("Tarasovich").withNickname("Hammer").withWorkPlace("MGID")
                    .withTelephoneNumber("555-55-55").withEmail("anton.tarasovich@mgid.com").withGroup("test1"));
        }
    }

    @Test
    public void testContactModification() {
        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;
        ContactData contact = new ContactData().withId(before.get(index).getId()).withFirstName("Vasya").withLastName("Petrov").withNickname("Tachka")
                .withWorkPlace("FBI").withTelephoneNumber("666-66-66").withEmail("vasya.petrov@fbi.com");
        app.contact().modify(index, contact);
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(contact);
        Comparator<? super ContactData> ById = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(ById);
        after.sort(ById);
        Assert.assertEquals(before, after);
    }

}