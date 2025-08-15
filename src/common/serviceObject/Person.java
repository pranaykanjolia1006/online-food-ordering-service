package common.serviceObject;

public abstract class Person {
    private String userId;
    private String name;
    private String phoneNumnber;
    private String emailId;

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPhoneNumnber() {
        return phoneNumnber;
    }
    public void setPhoneNumnber(String phoneNumnber) {
        this.phoneNumnber = phoneNumnber;
    }
    public String getEmailId() {
        return emailId;
    }
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

}
