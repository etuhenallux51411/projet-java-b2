package main.businessPackage;

import main.dataAccessPackage.CommunityDAO;

import main.dataAccessPackage.CommunityDAOImpl;
import main.exceptionPackage.ConnectionDataAccessException;
import main.modelPackage.CommunityModel;
import main.modelPackage.MemberModel;

import javax.naming.CommunicationException;
import java.util.List;

public class CommunityManager implements CommunityDAO {

    private CommunityDAO communityDAO;

    public CommunityManager() throws ConnectionDataAccessException {
        setCommunityDAO(new CommunityDAOImpl());
    }

    public void setCommunityDAO(CommunityDAO communityDAO) {this.communityDAO = communityDAO;}

    public List<CommunityModel> getAllCommunities() throws CommunicationException {
        return communityDAO.getAllCommunities();
    }

    public List<MemberModel> getCommunityById(int id) throws CommunicationException {
        return communityDAO.getCommunityById(id);
    }
}
