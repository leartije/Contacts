package contacts.entity;

import java.time.LocalDateTime;

public abstract class Contact {

    private String number;
    private LocalDateTime create;
    private LocalDateTime updated;

    public Contact() {
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDateTime getCreate() {
        return create;
    }

    public void setCreate(LocalDateTime create) {
        this.create = create.withSecond(0).withNano(0);
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated.withSecond(0).withNano(0);
    }

    public abstract String forList();

    @Override
    public String toString() {
        return String.format("Number: %s%n" +
                "Time created: %s%n" +
                "Time last edit: %s%n", getNumber(), getCreate().toString(), getUpdated().toString());
    }
}
