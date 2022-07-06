package contacts.services;

import contacts.entity.Person;

import java.time.LocalDate;

import static contacts.util.Msg.*;

public class PersonService implements UtilService {

    public static final String MALE = "M";
    public static final String FEMALE = "F";

    public void addPerson() {
        Person person = new Person();
        person.setFirstName(getStringWithoutValidation(ENTER_NAME));
        person.setSurname(getStringWithoutValidation(ENTER_SURNAME));
        person.setBirthDate(getBirthDate());
        person.setGender(getGender());
        person.setNumber(getNumber());
        person.setCreate(getTimeStump());
        person.setUpdated(getTimeStump());
        getDataBase().getMOCK_DB().add(person);
        System.out.println(THE_RECORD_ADDED);
    }

    public void editPerson(Person person) {
        System.out.print(EDIT_MENU_PERSON);
        switch (getField()) {
            case NAME:
                person.setFirstName(getStringWithoutValidation(ENTER_NAME));
                break;
            case SURNAME:
                person.setSurname(getStringWithoutValidation(ENTER_SURNAME));
                break;
            case BIRTH:
                person.setBirthDate(getBirthDate());
                break;
            case GENDER:
                person.setGender(getGender());
                break;
            case NUMBER:
                person.setNumber(getNumber());
                break;
            case ERROR:
                return;
        }

        person.setUpdated(getTimeStump());
        System.out.println(THE_RECORD_UPDATED);
    }

    //util
    private String getGender() {
        System.out.print(ENTER_GENDER);
        String gender = SCANNER.nextLine().strip().toUpperCase();
        if (!gender.isEmpty() && (MALE.equals(gender) || FEMALE.equals(gender))) {
            return gender;
        }
        System.out.println(BAD_GENDER);
        return NO_DATA;
    }

    private LocalDate getBirthDate() {
        System.out.print(ENTER_BIRTH_DATE);
        LocalDate birth = getLocalDate(SCANNER.nextLine());
        if (birth != null) {
            return birth;
        }
        System.out.println(BAD_BIRTH_DATE);
        return null;
    }

}
