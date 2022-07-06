package contacts.business;

import contacts.services.ContactService;
import contacts.services.UtilService;
import contacts.util.Msg;
import contacts.util.Options;

import java.util.Locale;

public class SearchMenu implements UtilService {

    public void search(ContactService contactService) {
        while (true) {
            contactService.search();
            System.out.print(Msg.SEARCH_MENU);
            String input = UtilService.SCANNER.nextLine().toUpperCase(Locale.ROOT);
            if (isNumber(input)) {
                continue;
            }
            Options options = getOptions(input);
            switch (options) {
                case BACK:
                    return;
                case AGAIN:
                    continue;
                case ERROR:
                    System.out.println("Error");
                    break;
            }
        }
    }

    private boolean isNumber(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException  e) {
            return false;
        }
    }

    private Options getOptions(String input) {
        try {
            return Options.valueOf(input);
        } catch (IllegalArgumentException e) {
            return Options.ERROR;
        }
    }

}
