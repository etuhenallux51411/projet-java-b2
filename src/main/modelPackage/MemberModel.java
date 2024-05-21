package main.modelPackage;

public class MemberModel {

    private CommunityModel community;

    private UserModel user;


    public MemberModel(CommunityModel community, UserModel user) {
        this.community = community;
        this.user = user;
    }

    public CommunityModel getCommunity() {
        return community;
    }

    public void setCommunity(CommunityModel community) {
        this.community = community;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }
}
