package ua.projects.javatests.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ua.projects.javatests.addressbook.model.ContactData;
import ua.projects.javatests.addressbook.model.Contacts;
import ua.projects.javatests.addressbook.model.GroupData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContactsFromXml() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")))) {
            String xml = "";
            String line = reader.readLine();
            while (line != null) {
                xml += line;
                line = reader.readLine();
            }
            XStream xstream = new XStream();
            xstream.processAnnotations(ContactData.class);
            List<ContactData> contacts = (List<ContactData>) xstream.fromXML(xml);
            return contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
        }
    }

    @DataProvider
    public Iterator<Object[]> validContactsFromJson() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))) {
            String json = "";
            String line = reader.readLine();
            while (line != null) {
                json += line;
                line = reader.readLine();
            }
            Gson gson = new Gson();
            List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>(){}.getType());
            return contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
        }
    }
    
    @Test (priority = 1, dataProvider = "validContactsFromJson")
    public void testContactCreation(ContactData contact) {
        app.goTo().goToHomePage();
        Contacts before = app.db().contacts();
        /*File photo = new File("src/test/resources/batman.jpg");
        ContactData contact = new ContactData().withFirstName("Anton").withLastName("Tarasovich").withNickname("Hammer").withWorkPlace("MGID")
                .withAddress("Kiev, Dovzhenko str. 3, app. 21").withHomePhone("111-11-11").withMobilePhone("222-22-22").withWorkPhone("333-33-33")
                .withFirstEmail("anton.tarasovich@mgid.com").withSecondEmail("vasya111@mail.ru").withThirdEmail("petya72@meta.ua").withGroup("test1")
                .withPhoto(photo);*/
        app.contact().create(contact);
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.db().contacts();

        assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

    @Test (priority = 2)
    public void testBadContactCreation() {
        app.goTo().goToHomePage();
        Contacts before = app.db().contacts();
        ContactData contact = new ContactData().withFirstName("Anton'");
        app.contact().create(contact);
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.db().contacts();

        assertThat(after, equalTo(before));
    }
}
