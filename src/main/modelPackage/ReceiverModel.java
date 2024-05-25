package main.modelPackage;

public class ReceiverModel {

    private DirectMessageModel directMessage;

    private UserModel user;

    public ReceiverModel(DirectMessageModel directMessage, UserModel user) {
        this.directMessage = directMessage;
        this.user = user;
    }

    public DirectMessageModel getDirectMessage() {
        return directMessage;
    }

    public void setDirectMessage(DirectMessageModel directMessage) {
        this.directMessage = directMessage;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }
}
