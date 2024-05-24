package main.dataAccessPackage;

import main.modelPackage.CommunityModel;
import main.modelPackage.MemberModel;

import javax.naming.CommunicationException;
import java.util.List;

public interface CommunityDAO {

    public List<CommunityModel> getAllCommunities() throws CommunicationException;

    public List<MemberModel> getCommunityById(int id) throws CommunicationException;
}
