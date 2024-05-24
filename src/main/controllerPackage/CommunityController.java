package main.controllerPackage;

import main.businessPackage.CommunityManager;
import main.exceptionPackage.ConnectionDataAccessException;
import main.modelPackage.CommunityModel;
import main.modelPackage.MemberModel;

import javax.naming.CommunicationException;
import java.util.List;

public class CommunityController {

    private CommunityManager communityManager;

    public CommunityController() throws ConnectionDataAccessException {
        communityManager = new CommunityManager();
    }

    public List<CommunityModel> getAllCommunities() throws CommunicationException {
        return communityManager.getAllCommunities();
    }

    public List<MemberModel> getCommunityById(int id) throws CommunicationException {
        return communityManager.getCommunityById(id);
    }
}
