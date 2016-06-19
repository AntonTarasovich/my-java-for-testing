package ua.projects.javatests.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ua.projects.javatests.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

public class GroupHelper extends HelperBase
{

    NavigationHelper nh = new NavigationHelper(wd);

    public GroupHelper(WebDriver wd) {
        super(wd);
    }

    public void submitGroupCreation() {
        click(By.name("submit"));
    }

    public void fillGroupForm(GroupData groupData) {
        type(By.name("group_name"), groupData.getName());
        type(By.name("group_header"), groupData.getHeader());
        type(By.name("group_footer"), groupData.getFooter());
    }

    public void newGroupCreation() {
        click(By.name("new"));
    }

    public void deleteSelectedGroups() {
        click(By.name("delete"));
    }

    public void selectGroup(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void initGroupModification() {
        click(By.name("edit"));
    }

    public void submitGroupModification() {
        click(By.name("update"));
    }

    public void create(GroupData group) {
        newGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        nh.groupPage();
    }


    public void delete(int index) {
        selectGroup(index);
        deleteSelectedGroups();
        nh.groupPage();
    }

    public void modify(int index, GroupData group) {
        selectGroup(index);
        initGroupModification();
        fillGroupForm(group);
        submitGroupModification();
        nh.groupPage();
    }

    public boolean isGroupExist() {
        return isElementPresent((By.name("selected[]")));
    }

    public int getGroupCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public List<GroupData> list() {
        List<GroupData> groups = new ArrayList<GroupData>();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements) {
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            groups.add(new GroupData().withId(id).withName(name));
        }
        return groups;
    }
}
