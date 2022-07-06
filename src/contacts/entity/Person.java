package contacts.entity;

import contacts.util.Msg;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Person extends Contact {

    private String firstName;
    private String surname;
    private LocalDate birthDate;
    private String gender;

    public Person() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String forList() {
        return getFirstName() + " " + getSurname();
    }

    @Override
    public String toString() {
        return String.format("Name: %s%n" +
                        "Surname: %s%n" +
                        "Birth date: %s%n" +
                        "Gender: %s%n" +
                        super.toString(), getFirstName(), getSurname(),
                getBirthDate() != null ? getBirthDate() : Msg.NO_DATA, getGender());
    }
}
