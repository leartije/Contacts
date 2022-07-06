package contacts.services;

import contacts.entity.Organization;
import contacts.util.Fields;
import contacts.util.Msg;

public class OrganizationService implements UtilService {

    public void addOrg() {
        Organization organization = new Organization();
        organization.setName(getStringWithoutValidation(Msg.ENTER_ORG_NAME));
        organization.setAddress(getStringWithoutValidation(Msg.ENTER_ADDRESS));
        organization.setNumber(getNumber());
        organization.setCreate(getTimeStump());
        organization.setUpdated(getTimeStump());
        getDataBase().getMOCK_DB().add(organization);
        System.out.println(Msg.THE_RECORD_ADDED);
    }

    public void editOrg(Organization contact) {
        System.out.print(Msg.EDIT_MENU_ORGANISATION);
        Fields field = getField();
        switch (field) {
            case NAME:
                contact.setName(getStringWithoutValidation(Msg.ENTER_ORG_NAME));
                break;
            case ADDRESS:
                contact.setAddress(getStringWithoutValidation(Msg.ENTER_ADDRESS));
                break;
            case NUMBER:
                contact.setNumber(getNumber());
                break;
            case ERROR:
                return;
        }

        contact.setUpdated(getTimeStump());
        System.out.println(Msg.THE_RECORD_UPDATED);

    }

}
