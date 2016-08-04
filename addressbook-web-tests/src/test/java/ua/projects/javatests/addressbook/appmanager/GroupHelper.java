package ua.projects.javatests.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ua.projects.javatests.addressbook.model.GroupData;
import ua.projects.javatests.addressbook.model.Groups;

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

    public void selectGroupById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
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

    public void delete(GroupData group) {
        selectGroupById(group.getId());
        deleteSelectedGroups();
        nh.groupPage();
    }

    public void modify(GroupData group) {
        selectGroupById(group.getId());
        initGroupModification();
        fillGroupForm(group);
        submitGroupModification();
        nh.groupPage();
    }

    public boolean isGroupExist() {
        return isElementPresent((By.name("selected[]")));
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public Groups all() {
        Groups groups = new Groups();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements) {
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            groups.add(new GroupData().withId(id).withName(name));
        }
        return groups;
    }

    public void selectAllGroups() {
        new Select(wd.findElement(By.name("group"))).selectByVisibleText("[all]");
    }


    public void deleteAllGroups() {
        List<WebElement> groupCheckboxes = wd.findElements(By.name("selected[]"));
        for (WebElement groupCheckbox : groupCheckboxes) {
            groupCheckbox.click();
        }
        click(By.name("delete"));
    }

    public void selectGroupWithContact(String groupName) {
        new Select(wd.findElement(By.name("group"))).selectByVisibleText(groupName);
    }

    public void removeFromGroup() {
        click(By.name("remove"));
    }
}
