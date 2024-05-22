package main.businessPackage;

import main.dataAccessPackage.LikeDAO;
import main.dataAccessPackage.LikeDAOimpl;
import main.exceptionPackage.ConnectionDataAccessException;
import main.exceptionPackage.LikeSearchException;
import main.modelPackage.LikeModel;

import java.sql.Date;
import java.util.List;

public class LikeManager implements LikeDAO {

    private LikeDAO likeDAO;

    public LikeManager() throws ConnectionDataAccessException {
        likeDAO = new LikeDAOimpl();
    }

    public void setLikeDAO(LikeDAO likeDAO) {
        this.likeDAO = likeDAO;
    }

    public List<LikeModel> getLikesBetween(Date startDate, Date endDate) throws LikeSearchException {
        return likeDAO.getLikesBetween(startDate, endDate);
    }
}
