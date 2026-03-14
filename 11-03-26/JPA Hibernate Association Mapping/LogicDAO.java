package cg.demo.orm.dao;

import cg.demo.orm.entities.Department;
import cg.demo.orm.entities.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class LogicDAO {
    private final EntityManagerFactory emf;
    private final EntityManager em;

    public LogicDAO() {
        this.emf = Persistence.createEntityManagerFactory("JPA-PU");
        this.em = emf.createEntityManager();
    }

    public Employee insertEmployee(Employee employee) {
        em.getTransaction().begin();
        em.persist(employee);
        em.getTransaction().commit();
        return employee;
    }

    public List<Employee> getEmployeesWithDepartmentAndManager() {
        return em.createQuery("select e from Employee e JOIN FETCH e.department", Employee.class).getResultList();
    }

    public List<Object[]> getNumberOfEmployeesInEachDepartment() {
        return em.createQuery("select d.name, count(e) from Department d JOIN d.employees e group by d.name", Object[].class).getResultList();
    }

    public List<Employee> getEmployeeByDepartment(String name) {
        return em.createQuery("select e from Employee e JOIN e.department d where d.name = :name ", Employee.class)
                .setParameter("name", name)
                .getResultList();
    }

    public List<Object[]> getDetailsByMobileNumber(long mobileNumber) {
        return em.createQuery(
                        "SELECT e.employeeId, e.name, d FROM Employee e JOIN e.mobileNumber m JOIN e.department d WHERE m = :mobileNumber",
                        Object[].class
                )
                .setParameter("mobileNumber", mobileNumber)
                .getResultList();
    }

    public Department findDepartmentByName(String name) {
        return em.createQuery("select d from Department d where d.name = :name", Department.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    public void close() {
        em.close();
        emf.close();
    }
}
