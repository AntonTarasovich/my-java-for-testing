package ua.projects.javatests.addressbook.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.Test;
import ua.projects.javatests.addressbook.model.ContactData;
import ua.projects.javatests.addressbook.model.Contacts;
import ua.projects.javatests.addressbook.model.GroupData;
import ua.projects.javatests.addressbook.model.Groups;

import java.util.List;

public class DbHelper {

    private final SessionFactory sessionFactory;

    public DbHelper() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();

        sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
    }

    public Groups groups() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<GroupData> result = session.createQuery("from GroupData").list();
        session.getTransaction().commit();
        session.close();
        return new Groups(result);
    }

    public Contacts contacts()  {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<ContactData> result = session.createQuery("from ContactData where deprecated = '0000-00-00'").list();
        session.getTransaction().commit();
        session.close();
        return new Contacts(result);
    }

    public List<String> contactGroups() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<String> result = session.createQuery("select name from GroupData").list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public void groupsContacts() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<GroupData> result = session.createQuery("from GroupData").list();
        for (GroupData group : result) {
            System.out.println(group);
            System.out.println(group.getContacts().size());
        }
        session.getTransaction().commit();
        session.close();
    }

    public GroupData groupWithContact() {
        GroupData group = null;
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<GroupData> result = session.createQuery("from GroupData").list();
        for (GroupData groupWithContact : result) {
            if (groupWithContact.getContacts().size() > 0) {
                group = groupWithContact;
                break;
            }
        }
        session.getTransaction().commit();
        session.close();
        return group;
    }

    public Groups contactInGroup(int contactId) {
        Groups groups = null;
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<ContactData> result = session.createQuery("from ContactData where id = " + contactId).list();
        for (ContactData contact : result) {
            System.out.println(contact.getGroups());
            groups = contact.getGroups();
        }
        session.getTransaction().commit();
        session.close();
        return groups;
    }

}
