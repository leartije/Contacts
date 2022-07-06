package contacts.repository;

import contacts.entity.Contact;

import java.util.ArrayList;
import java.util.List;

public class DataBase {

    private final List<Contact> MOCK_DB;

    public DataBase() {
        this.MOCK_DB = new ArrayList<>();
    }

    public List<Contact> getMOCK_DB() {
        return MOCK_DB;
    }
}
