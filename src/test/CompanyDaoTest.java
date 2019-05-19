package test;

import dao.company.CompanyDaoImpl;
import lib.exceptions.UniqueValueException;
import model.Company;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CompanyDaoTest {
    @Test
    void createNewCompanyInDb() throws UniqueValueException, SQLException {
        CompanyDaoImpl companyDao = new CompanyDaoImpl();
        Company company = new Company( 0, "Osem", "12345", "company@osem.com" );

        int id = companyDao.create( company );
        assertNotEquals( -1, id, "Company id should not be -1" );
        companyDao.delete( id );
    }

    @Test
    void createSameCompanyTwice() {
        CompanyDaoImpl companyDao = new CompanyDaoImpl();
        Company company = new Company( 0, UUID.randomUUID().toString(), "12345", "company@osem.com" );

        assertThrows( UniqueValueException.class, () -> {
            int id = companyDao.create( company );
            try {
                companyDao.create( company );
            } catch ( Exception e ) {
                companyDao.delete( id );
            }
        } );
    }
}
