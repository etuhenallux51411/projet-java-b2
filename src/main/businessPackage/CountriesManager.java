package main.businessPackage;

import main.dataAccessPackage.CountriesDAO;
import main.dataAccessPackage.CountriesDAOImpl;
import main.exceptionPackage.ConnectionDataAccessException;
import main.exceptionPackage.CountriesDAOException;

import java.util.List;

public class CountriesManager {
    private CountriesDAO countriesDAO;

    public CountriesManager() throws ConnectionDataAccessException {
        setCountriesDAO(new CountriesDAOImpl());
    }

    public void setCountriesDAO(CountriesDAO countriesDAO) {
        this.countriesDAO = countriesDAO;
    }

    public List<String> getCountries() throws CountriesDAOException {
        return countriesDAO.getCountries();
    }

}
