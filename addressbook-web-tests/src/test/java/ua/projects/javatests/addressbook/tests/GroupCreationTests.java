package ua.projects.javatests.addressbook.tests;

import org.testng.annotations.Test;
import ua.projects.javatests.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        app.getNavigationHelper().goToGroupCreation();
        app.getGroupHelper().newGroupCreation();
        app.getGroupHelper().fillGroupForm(new GroupData("test1", null, null));
        app.getGroupHelper().submitGroupCreation();
        app.getNavigationHelper().returnToGroupPage();
    }

}
