import dao.company.CompanyDao;
import dao.company.CompanyDaoImpl;
import dao.coupon.CouponDao;
import dao.coupon.CouponDaoImpl;
import dao.customer.CustomerDao;
import dao.customer.CustomerDaoImpl;
import facade.admin.AdminFacade;
import facade.admin.AdminFacadeImpl;
import facade.company.CompanyFacadeImpl;
import facade.customer.CustomerFacadeImpl;
import lib.UserType;
import lib.exceptions.UniqueValueException;
import model.Company;
import model.Coupon;
import model.Customer;
import model.utils.CouponType;

import java.sql.Date;
import java.util.Scanner;

public class MainApplication {
    public static void main(String[] args) throws Exception {
        CouponSystem couponSystem = null;

        String password = null;
        String userName = null;

        try {
            // coupon system loaded
            couponSystem = CouponSystem.getInstance();

            // all the 3 facades can login
            AdminFacadeImpl adminFacade = (AdminFacadeImpl) couponSystem.login("admin", "1234", UserType.valueOf("ADMIN"));
           adminFacade.addNewCompany(new Company(1,"TOYOTA","3456","TOYOTA@GMAIL.COM"));
           adminFacade.addNewCompany(new Company(2,"coca cola","6543","cocaCola@GMAIL.COM"));
           adminFacade.getOneCompany(1);
           adminFacade.updateCompany(new Company(2,"coca cola","4567","cocaCola1@gmail.com"));
           adminFacade.getAllCompanies();
           adminFacade.deleteCompany(1);
           adminFacade.addNewCustomer(new Customer(3,"yona","7654"));
           adminFacade.addNewCustomer(new Customer(4,"tova","6789"));
           adminFacade.getOneCustomer(4);
           adminFacade.updateCustomer(new Customer(4,"tova","6789"));
           adminFacade.getAllCustomers();
           adminFacade.deleteCustomer(3);

           CompanyFacadeImpl companyFacade = (CompanyFacadeImpl) couponSystem.login("Toyota", "zzz", UserType.valueOf("COMPANY"));


            CustomerFacadeImpl customerFacade = (CustomerFacadeImpl) couponSystem.login("Hana", "yosefyosef1",
                    UserType.valueOf("CUSTOMER"));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();



            }

        }
    }
}