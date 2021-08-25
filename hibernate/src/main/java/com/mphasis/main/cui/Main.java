package com.mphasis.main.cui;

import com.mphasis.entities.Employee;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main {

    private static SessionFactory factory;

    public static void main(String[] args){
        try {
            factory = new Configuration().configure().buildSessionFactory();

            /* Add few employee records in database */
            Main ME = new Main();
            Integer empID1 = ME.addEmployee("Zara", "Ali", 1000);
            Integer empID2 = ME.addEmployee("Daisy", "Das", 5000);
            Integer empID3 = ME.addEmployee("John", "Paul", 10000);

            System.out.println(empID1);
            System.out.println(empID2);
            System.out.println(empID3);

        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }


    }

    /* Method to CREATE an employee in the database */
    public Integer addEmployee(String fname, String lname, int salary){
        Session session = factory.openSession();
        Transaction tx = null;
        Integer employeeID = null;

        try {
            tx = session.beginTransaction();
            Employee employee = new Employee(fname, lname, salary);
            employeeID = (Integer) session.save(employee);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return employeeID;
    }

}
