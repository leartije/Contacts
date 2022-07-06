package contacts.entity;

public class Organization extends Contact {

    private String name;
    private String address;

    public Organization() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    @Override
    public String forList() {
        return getName();
    }


    @Override
    public String toString() {
        return String.format("Organization name: %s%n" +
                "Address: %s%n" +
                super.toString(), getName(), getAddress());
    }
}
