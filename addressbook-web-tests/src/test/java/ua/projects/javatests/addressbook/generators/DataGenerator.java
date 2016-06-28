package ua.projects.javatests.addressbook.generators;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ua.projects.javatests.addressbook.model.ContactData;
import ua.projects.javatests.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;

import static ua.projects.javatests.addressbook.generators.DataGenerator.DataType.*;

public class DataGenerator {

    public enum DataType {GROUPS, CONTACTS}

    @Parameter(names = "-c", description = "Group count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    @Parameter(names = "-d", description = "Data format")
    public String format;

    public static void main(String[] args) throws IOException {
        DataGenerator generator = new DataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
        jCommander.parse(args);
        } catch (ParameterException ex) {
            jCommander.usage();
            return;
        }
        generator.run(CONTACTS);
    }

    private void run(DataType datatype) throws IOException {
        switch (datatype) {
            case GROUPS:
            List<GroupData> groups = generateGroups(count);
            if (format.equals("csv")) {
                saveGroupDataAsCsv(groups, new File(file));
            } else if (format.equals("xml")) {
                saveGroupDataAsXml(groups, new File(file));
            } else if (format.equals("json")) {
                saveGroupDataAsJson(groups, new File(file));
            } else {
                System.out.println("Unrecognized format : " + format);
            }
                break;

            case CONTACTS:
                List<ContactData> contacts = generateContacts(count);
                if (format.equals("csv")) {
                    saveContactDataAsCsv(contacts, new File(file));
                } else if (format.equals("xml")) {
                    saveContactDataAsXml(contacts, new File(file));
                } else if (format.equals("json")) {
                    saveContactDataAsJson(contacts, new File(file));
                } else {
                    System.out.println("Unrecognized format : " + format);
                }
                break;
        }
    }

    private void saveGroupDataAsJson(List<GroupData> groups, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(groups);
        Writer writer = new FileWriter(file);
        writer.write(json);
        writer.close();
    }

    private void saveGroupDataAsXml(List<GroupData> groups, File file) throws IOException {
        XStream xstream = new XStream();
        xstream.processAnnotations(GroupData.class);
        String xml = xstream.toXML(groups);
        Writer writer = new FileWriter(file);
        writer.write(xml);
        writer.close();
    }

    private void saveGroupDataAsCsv(List<GroupData> groups, File file) throws IOException {
        Writer writer = new FileWriter(file);
        for (GroupData group : groups) {
            writer.write(String.format("%s;%s;%s\n", group.getName(), group.getHeader(), group.getFooter()));
        }
        writer.close();
    }

    private void saveContactDataAsJson(List<ContactData> contacts, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        Writer writer = new FileWriter(file);
        String json = gson.toJson(contacts);
        writer.write(json);
        writer.close();
    }

    private void saveContactDataAsXml(List<ContactData> contacts, File file) throws IOException {
        XStream xstream = new XStream();
        xstream.processAnnotations(ContactData.class);
        String xml = xstream.toXML(contacts);
        Writer writer = new FileWriter(file);
        writer.write(xml);
        writer.close();
    }

    private void saveContactDataAsCsv(List<ContactData> contacts, File file) throws IOException {
        Writer writer = new FileWriter(file);
        for (ContactData contact : contacts) {
            writer.write(String.format("%s;%s;%s;%s;%s;%s;%s\n", contact.getFirstName(), contact.getLastName(), contact.getNickname(),
                    contact.getWorkPlace(), contact.getAddress(), contact.getHomePhone(), contact.getFirstEmail()));
        }
        writer.close();
    }

    private List<GroupData> generateGroups(int count) {
        List<GroupData> groups = new ArrayList<GroupData>();
        for (int i = 0; i < count; i++) {
            groups.add(new GroupData().withName(String.format("test %s", i)).withHeader(String.format("header %s", i))
                    .withFooter(String.format("footer %s", i)));
        }
        return groups;
    }

    private List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<ContactData>();
        for (int i = 0; i < count; i++) {
            contacts.add(new ContactData().withFirstName(String.format("name_%s", i)).withLastName(String.format("lastname _%s", i))
                    .withNickname(String.format("nickname_%s", i)).withWorkPlace(String.format("workplace_%s", i)).withAddress(String.format("address_%s", i))
                    .withHomePhone(String.format("homephone_%s", i)).withFirstEmail(String.format("email_%s", i)));
        }
        return contacts;
    }
}
