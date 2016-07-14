package ua.projects.javatests.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ua.projects.javatests.addressbook.model.ContactData;
import ua.projects.javatests.addressbook.model.Contacts;
import ua.projects.javatests.addressbook.model.GroupData;

import java.io.File;

public class InsertingContactInGroupTests extends TestBase{

    @BeforeMethod
    public void ensureContactPreconditions() {
        if (app.db().contacts().size() == 0) {
            File photo = new File("src/test/resources/batman.jpg");
            app.contact().create(new ContactData().withFirstName("Anton").withLastName("Tarasovich").withNickname("Hammer").withWorkPlace("MGID")
                    .withAddress("Kiev, Dovzhenko str. 3, app. 21").withHomePhone("111-11-11").withMobilePhone("222-22-22").withWorkPhone("333-33-33")
                    .withFirstEmail("anton.tarasovich@mgid.com").withSecondEmail("vasya111@mail.ru").withThirdEmail("petya72@meta.ua").withPhoto(photo));
        }
    }

    @BeforeMethod
    public void ensureGroupPreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1"));
        }
    }

    @Test
    public void testInsertingContactInGroup() {
        Contacts before = app.db().contacts();
        ContactData insertingContact = before.iterator().next();
        app.contact().insert(insertingContact);
    }
}
