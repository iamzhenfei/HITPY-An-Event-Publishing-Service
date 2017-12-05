package xyz.hitpy.seproject.model;

public class CheckPreview {
    protected String joiner;
    protected String reason;
    protected String contact;
    protected int id;

    public CheckPreview(String joiner, String reason, String contact, int id) {
        this.joiner = joiner;
        this.reason = reason;
        this.contact = contact;
        this.id = id;
    }

    public String getJoiner() {
        return joiner;
    }

    public void setJoiner(String joiner) {
        this.joiner = joiner;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
