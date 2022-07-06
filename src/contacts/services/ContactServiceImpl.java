package contacts.services;

import contacts.entity.Contact;
import contacts.entity.Organization;
import contacts.entity.Person;
import contacts.util.ContactType;
import contacts.util.Msg;
import contacts.util.Options;

import java.util.List;
import java.util.Locale;

public class ContactServiceImpl implements ContactService, UtilService {

    private final PersonService personService;
    private final OrganizationService organizationService;

    public ContactServiceImpl() {
        this.personService = new PersonService();
        this.organizationService = new OrganizationService();
    }

    @Override
    public void addContact(ContactType contactType) {
        switch (contactType) {
            case PERSON:
                personService.addPerson();
                break;
            case ORGANIZATION:
                organizationService.addOrg();
                break;
            case ERROR:
                break;

        }
    }

    @Override
    public void list() {
        if (getDBSize() == 0) {
            System.out.println(Msg.THE_LIST_IS_EMPTY);
            return;
        }
        printList();
        System.out.println();
        System.out.print(Msg.LIST_MENU);
        String input = SCANNER.nextLine();
        int index = isNumber(input);
        Contact contact;
        if (index > -1) {
            contact = info(index);
        } else {
            return;
        }

        while (true) {
            System.out.print(Msg.RECORD_MENU);
            Options options = getOptions(SCANNER.nextLine().toUpperCase(Locale.ROOT));
            switch (options) {
                case EDIT:
                    editContact(contact);
                    break;
                case DELETE:
                    removeContact(index);
                    break;
                case MENU:
                    return;
                case ERROR:
                    System.out.println("error, man");
                    break;
            }
        }
    }

    @Override
    public void search() {
        System.out.print(Msg.ENTER_SEARCH_QUERY);
        String query = SCANNER.nextLine();
        List<Contact> search = search(query);
        if (search.size() == 0) {
            System.out.println("Empty");
            return;
        }
        System.out.printf(Msg.FOUND_RESULTS, search.size());
        for (int i = 0; i < search.size(); i++) {
            System.out.println((i + 1) + ". " + search.get(i).forList());
        }
        System.out.println();
        System.out.print(Msg.SEARCH_MENU);
        String input = SCANNER.nextLine().toUpperCase(Locale.ROOT);
        int index = isNumber(input);
        Contact contact;
        if (index > -1) {
            contact = search.get(index - 1);
            System.out.println(contact.toString());
            while (true) {
                System.out.print(Msg.RECORD_MENU);
                Options options = getOptions(SCANNER.nextLine().toUpperCase(Locale.ROOT));
                switch (options) {
                    case EDIT:
                        editContact(contact);
                        break;
                    case DELETE:
                        removeContact(index);
                        break;
                    case MENU:
                        return;
                    case ERROR:
                        System.out.println("error, man");
                        break;
                }
            }
        } else {
            Options options = getOptions(input);
            switch (options) {
                case BACK:
                    return;
                case AGAIN:
                    search();
            }
        }

    }

    @Override
    public void count() {
        System.out.printf(Msg.PHONE_BOOK_SIZE, getDBSize());
    }



    //util

    public Contact info(int i) {
        if (i > 0 && i <= getDBSize()) {
            String string = getDataBase().getMOCK_DB().get(i - 1).toString();
            System.out.println(string);
            return getDataBase().getMOCK_DB().get(i - 1);
        }
        System.out.printf(Msg.NUMBER_IS_OUT_OF_BOUND, i);
        return null;
    }

    private int isNumber(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private Options getOptions(String input) {
        try {
            return Options.valueOf(input);
        } catch (IllegalArgumentException e) {
            return Options.ERROR;
        }
    }

    private void removeContact(int i) {
        if (i > 0 && i <= getDBSize()) {
            getDataBase().getMOCK_DB().remove(i - 1);
            System.out.println(Msg.THE_RECORD_REMOVED);
            return;
        }
        System.out.printf(Msg.NUMBER_IS_OUT_OF_BOUND, i);
    }


    private void editContact(Contact contact) {
        if (contact instanceof Person) {
            personService.editPerson((Person) contact);
            return;
        }
        if (contact instanceof Organization) {
            organizationService.editOrg((Organization) contact);
        }
    }


}
