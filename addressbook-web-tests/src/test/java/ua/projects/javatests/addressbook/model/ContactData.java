package ua.projects.javatests.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;

@Entity
@Table (name = "addressbook")
@XStreamAlias("contact")
public class ContactData {

    @Id
    @Column (name = "id")
    @XStreamOmitField
    private int id;

    @Column (name = "firstname")
    @Expose
    private String firstName;

    @Column (name = "lastname")
    @Expose
    private String lastName;

    @Expose
    private String nickname;

    @Column (name = "company")
    @Expose
    private String workPlace;

    @Expose
    @Type(type = "text")
    private String address;

    @Column (name = "home")
    @Type(type = "text")
    @Expose
    private String homePhone;

    @Column (name = "mobile")
    @Type(type = "text")
    private String mobilePhone;

    @Column (name = "work")
    @Type(type = "text")
    private String workPhone;

    @Transient
    private String allPhones;

    @Transient
    private String allEmails;

    @Column (name = "email")
    @Type(type = "text")
    @Expose
    private String firstEmail;

    @Column (name = "email2")
    @Type(type = "text")
    private String secondEmail;

    @Column (name = "email3")
    @Type(type = "text")
    private String thirdEmail;

    @Transient
    private String group;

    @Transient
    private String info;

    @Column (name = "photo")
    @Type(type = "text")
    private String photo = "src/test/resources/batman.jpg";

    public int getId() {
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

    public String getAddress() {
        return address;
    }

    public String getAllPhones() {
        return allPhones;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public String getFirstEmail() {
        return firstEmail;
    }

    public String getSecondEmail() {
        return secondEmail;
    }

    public String getThirdEmail() {
        return thirdEmail;
    }

    public String getAllEmais() {
        return allEmails;
    }

    public String getGroup() {
        return group;
    }

    public String getInfo() {
        return info;
    }

    public File getPhoto() {
        return new File(photo);
    }

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public ContactData withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ContactData withWorkPlace(String workPlace) {
        this.workPlace = workPlace;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactData withNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public ContactData withHomePhone(String homePhone) {
        this.homePhone = homePhone;
        return this;
    }

    public ContactData withMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }

    public ContactData withWorkPhone(String workPhone) {
        this.workPhone = workPhone;
        return this;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public ContactData withFirstEmail(String firstEmail) {
        this.firstEmail = firstEmail;
        return this;
    }

    public ContactData withSecondEmail(String secondEmail) {
        this.secondEmail = secondEmail;
        return this;
    }

    public ContactData withThirdEmail(String thirdEmail) {
        this.thirdEmail = thirdEmail;
        return this;
    }

    public ContactData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }

    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }

    public ContactData withInfo(String info) {
        this.info = info;
        return this;
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != that.id) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (nickname != null ? !nickname.equals(that.nickname) : that.nickname != null) return false;
        if (workPlace != null ? !workPlace.equals(that.workPlace) : that.workPlace != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (homePhone != null ? !homePhone.equals(that.homePhone) : that.homePhone != null) return false;
        return firstEmail != null ? firstEmail.equals(that.firstEmail) : that.firstEmail == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (nickname != null ? nickname.hashCode() : 0);
        result = 31 * result + (workPlace != null ? workPlace.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (homePhone != null ? homePhone.hashCode() : 0);
        result = 31 * result + (firstEmail != null ? firstEmail.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "firstName='" + firstName + '\'' +
                ", id=" + id +
                ", lastName='" + lastName + '\'' +
                ", nickname='" + nickname + '\'' +
                ", workPlace='" + workPlace + '\'' +
                ", address='" + address + '\'' +
                ", homePhone='" + homePhone + '\'' +
                ", firstEmail='" + firstEmail + '\'' +
                '}';
    }

}
