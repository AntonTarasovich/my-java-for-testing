package ua.projects.javatests.addressbook.model;

public class ContactData {
    private final String firstName;
    private final String lastName;
    private final String nickname;
    private final String workPlace;
    private final String telephoneNumber;
    private final String email;
    private String group;

    public ContactData(String firstName, String lastName, String nickname, String workPlace, String telephoneNumber, String email, String group) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickname = nickname;
        this.workPlace = workPlace;
        this.telephoneNumber = telephoneNumber;
        this.email = email;
        this.group = group;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNickname() {
        return nickname;
    }

    public String getWorkPlace() {
        return workPlace;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getGroup() {
        return group;
    }
}
