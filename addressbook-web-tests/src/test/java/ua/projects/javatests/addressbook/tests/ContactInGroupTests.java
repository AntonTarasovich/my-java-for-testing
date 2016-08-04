package ua.projects.javatests.addressbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ua.projects.javatests.addressbook.model.ContactData;
import ua.projects.javatests.addressbook.model.GroupData;
import ua.projects.javatests.addressbook.model.Groups;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactInGroupTests extends TestBase{

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

    @Test(priority = 1)
    public void testInsertContactInGroup() {
        app.goTo().goToHomePage();
        if  (app.db().contacts().size() == 0) {
            File photo = new File("src/test/resources/batman.jpg");
            Groups groups = app.db().groups();
            ContactData newContact = new ContactData().withFirstName("Anton").withLastName("Tarasovich").withNickname("Hammer").withWorkPlace("MGID")
                    .withAddress("Kiev, Dovzhenko str. 3, app. 21").withHomePhone("111-11-11").withMobilePhone("222-22-22").withWorkPhone("333-33-33")
                    .withFirstEmail("anton.tarasovich@mgid.com").withSecondEmail("vasya111@mail.ru").withThirdEmail("petya72@meta.ua").withPhoto(photo)
                    .inGroup(groups.iterator().next());
            app.goTo().goToHomePage();
            app.contact().create(newContact);
        }
        ContactData contact = app.db().contacts().iterator().next();
        int contactId = contact.getId();
        Groups before = app.db().contactInGroup(contactId);
        app.contact().selectContactById(contactId);
        if (contactIsInAllGroups(contact)) {
            GroupData group = app.contact().createNewGroupForContact();
            app.contact().selectContactById(contactId);
            app.contact().addSelectedContactToGroup(group.getName());
        }
        GroupData group = app.db().groups().iterator().next();
        app.contact().addSelectedContactToGroup(group.getName());
        Groups after = app.db().contactInGroup(contactId);
        assertThat(after, equalTo(before.withAdded(group)));
    }


    @Test(priority = 2)
    public void testDeleteContactFromGroup() {
        app.goTo().goToHomePage();
        GroupData group = app.db().groupWithContact();
        String groupName = group.getName();
        app.group().selectGroupWithContact(groupName);
        int contactId = app.contact().getContactId();
        Groups before = app.db().contactInGroup(contactId);
        app.group().removeFromGroup();
        Groups after = app.db().contactInGroup(contactId);
        app.goTo().goToHomePage();
        app.group().selectAllGroups();
        assertThat(after, equalTo(before.withoutAdded(group)));
    }

    public boolean contactIsInAllGroups(ContactData contact) {
        Groups groups = app.db().groups();
        Groups contactGroups = contact.getGroups();
        return groups.equals(contactGroups);
    }

    @Test
    public String groupWithContact() {
        String name = "";
        Groups groups = app.db().groups();
        for (GroupData group : groups) {
            if (group.getContacts().size() > 0) {
                name = group.getName();
                break;
            }
        }
        System.out.println(name);
        return name;
    }

}
