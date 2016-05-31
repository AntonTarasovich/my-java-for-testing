package ua.projects.javatests.addressbook;

import org.testng.annotations.Test;

public class GroupDeletionTests extends TestBase{

    @Test
    public void testGroupDeletion() {
        goToGroupCreation();
        selectGroup();
        deleteSelectedGroups();
        returnToGroupPage();
    }

}
