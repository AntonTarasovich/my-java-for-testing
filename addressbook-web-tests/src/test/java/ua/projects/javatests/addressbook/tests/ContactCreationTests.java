package ua.projects.javatests.addressbook.tests;

import org.testng.annotations.Test;
import ua.projects.javatests.addressbook.model.ContactData;
import ua.projects.javatests.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {
    
    @Test
    public void testContactCreation() {
        Contacts before = app.contact().all();
        ContactData contact = new ContactData().withFirstName("Anton").withLastName("Tarasovich").withNickname("Hammer").withWorkPlace("MGID")
                .withHomePhone("111-11-11").withMobilePhone("222-22-22").withWorkPhone("333-33-33").withEmail("anton.tarasovich@mgid.com").withGroup("test1");
        app.contact().create(contact);
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.contact().all();

        assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

    @Test
    public void testBsdContactCreation() {
        Contacts before = app.contact().all();
        ContactData contact = new ContactData().withFirstName("Anton'");
        app.contact().create(contact);
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.contact().all();

        assertThat(after, equalTo(before));
    }
}
