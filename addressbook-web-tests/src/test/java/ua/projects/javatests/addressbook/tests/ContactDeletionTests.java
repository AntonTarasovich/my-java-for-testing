package ua.projects.javatests.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion() {
        app.getContactHelper().selectGroup();
        app.getContactHelper().deleteSelectedGroup();
        app.alertAccept();
        app.getNavigationHelper().returnToHomePage();
    }
}
