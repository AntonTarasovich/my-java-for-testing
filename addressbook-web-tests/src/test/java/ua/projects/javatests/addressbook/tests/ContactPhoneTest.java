package ua.projects.javatests.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.Test;

import org.testng.annotations.BeforeMethod;
import ua.projects.javatests.addressbook.model.ContactData;

public class ContactPhoneTest extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData().withFirstName("Anton").withLastName("Tarasovich").withNickname("Hammer").withWorkPlace("MGID")
                    .withHomePhone("111-11-11").withMobilePhone("222-22-22").withWorkPhone("333-33-33").withEmail("anton.tarasovich@mgid.com").withGroup("test1"));
        }
    }

    @Test
    public void testContactPhones() {
        app.goTo().goToHomePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        MatcherAssert.assertThat(contact, CoreMatchers.equalTo(contactInfoFromEditForm));
    }
}
