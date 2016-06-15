package ua.projects.javatests.addressbook.model;

public class ContactData {
    private final String id;
    private final String firstName;
    private final String lastName;
    private final String nickname;
    private final String workPlace;
    private final String telephoneNumber;
    private final String email;
    private String group;

    public ContactData(String id, String firstName, String lastName, String nickname, String workPlace, String telephoneNumber, String email, String group) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickname = nickname;
        this.workPlace = workPlace;
        this.telephoneNumber = telephoneNumber;
        this.email = email;
        this.group = group;
    }

    public ContactData(String firstName, String lastName, String nickname, String workPlace, String telephoneNumber, String email, String group) {
        this.id = null;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickname = nickname;
        this.workPlace = workPlace;
        this.telephoneNumber = telephoneNumber;
        this.email = email;
        this.group = group;
    }

    public String getId() {
        return id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        return lastName != null ? lastName.equals(that.lastName) : that.lastName == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

}
