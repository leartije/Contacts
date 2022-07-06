package contacts.services;

import contacts.entity.Contact;
import contacts.repository.DataBase;
import contacts.util.Fields;
import contacts.util.Msg;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public interface UtilService {

    Scanner SCANNER = new Scanner(System.in);
    DataBase dataBase = new DataBase();

    default String getStringWithoutValidation(String msg) {
        System.out.print(msg);
        return SCANNER.nextLine();
    }

    default String getNumber() {
        System.out.print(Msg.ENTER_NUMBER);
        String number = SCANNER.nextLine();
        if (isValidNumber(number)) {
            return number;
        }
        System.out.println(Msg.WRONG_NUMBER_FORMAT);
        return Msg.NO_DATA;
    }

    default LocalDateTime getTimeStump() {
        return LocalDateTime.now();
    }

    default DataBase getDataBase() {
        return dataBase;
    }

    default int getDBSize() {
        return getDataBase().getMOCK_DB().size();
    }

    default LocalDate getLocalDate(String date) {
        try {
            return LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    default Fields getField() {
        try {
            return Fields.valueOf(SCANNER.nextLine().toUpperCase());
        } catch (IllegalArgumentException e) {
            return Fields.ERROR;
        }
    }

    default void printList() {
        for (int i = 0; i < getDBSize(); i++) {
            System.out.println((i + 1) + ". " + getDataBase().getMOCK_DB().get(i).forList());
        }
    }

    default List<Contact> search(String query) {
        return getDataBase().getMOCK_DB().stream()
                .filter(contact -> contact.toString().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }


    //util

    private boolean isValidNumber(String number) {
        String regex = "^\\+?(\\(\\w+\\)|\\w+[ -]\\(\\w{2,}\\)|\\w+)([ -]\\w{2,})*";
        return number.matches(regex);
    }

}
