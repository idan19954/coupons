import dao.company.CompanyDaoImpl;
import lib.exceptions.UniqueValueException;
import model.Company;


public class MainApplication {
    public static void main( String[] args ) {
        CompanyDaoImpl companyDao = new CompanyDaoImpl();

        Company company = new Company( 0, "edrfger", "12345", "company@osem.com" );

        try {
            companyDao.create( company );
        } catch ( UniqueValueException e ) {
            e.printStackTrace();
        }
    }
}