package contacts.business;

import contacts.services.ContactService;
import contacts.services.ContactServiceImpl;
import contacts.util.ContactType;
import contacts.util.Msg;
import contacts.util.Options;

import static contacts.services.UtilService.SCANNER;

public class PhoneBook {

    private final ContactService contactService;

    public PhoneBook() {
        contactService = new ContactServiceImpl();
    }

    public void start() {
        while (true) {
            System.out.print(Msg.MAIN_MENU);
            Options options = getOptions(SCANNER.nextLine());
            switch (options) {
                case ADD:
                    System.out.print(Msg.ENTER_THE_TYPE);
                    ContactType contactType = getContactType(SCANNER.nextLine());
                    contactService.addContact(contactType);
                    break;
                case LIST:
                    contactService.list();
                    break;
                case SEARCH:
                    contactService.search();
                    break;
                case COUNT:
                    contactService.count();
                    break;
                case EXIT:
                    System.exit(0);
                    break;
                case ERROR:
                    break;
            }
        }
    }

    //util

    private ContactType getContactType(String input) {
        try {
            return ContactType.valueOf(input.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.printf(Msg.NOT_VALID_OPTION, input);
            return ContactType.ERROR;
        }
    }

    private Options getOptions(String input) {
        try {
            return Options.valueOf(input.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.printf(Msg.NOT_VALID_OPTION, input);
            return Options.ERROR;
        }
    }

}
