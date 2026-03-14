package cg.demo.orm.dao;

import cg.demo.orm.entities.Department;
import cg.demo.orm.entities.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;

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
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);

        Root<Employee> employee = cq.from(Employee.class);
        employee.fetch("department");

        cq.select(employee);

        return em.createQuery(cq).getResultList();
    }

    public List<Object[]> getNumberOfEmployeesInEachDepartment() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);

        Root<Department> department = cq.from(Department.class);
        Join<Department, Employee> employee = department.join("employees");

        cq.multiselect(
                department.get("name"),
                cb.count(employee)
        );

        cq.groupBy(department.get("name"));

        return em.createQuery(cq).getResultList();
    }

    public List<Employee> getEmployeeByDepartment(String name) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);

        Root<Employee> employee = cq.from(Employee.class);
        Join<Employee, Department> department = employee.join("department");

        cq.select(employee).where(cb.equal(department.get("name"), name));

        return em.createQuery(cq).getResultList();
    }

    public List<Object[]> getDetailsByMobileNumber(long mobileNumber) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);

        Root<Employee> employee = cq.from(Employee.class);

        Join<Employee, Long> mobile = employee.join("mobileNumber");
        Join<Employee, Department> department = employee.join("department");

        cq.multiselect(
                employee.get("employeeId"),
                employee.get("name"),
                department
        ).where(cb.equal(mobile, mobileNumber));

        return em.createQuery(cq).getResultList();
    }

    public Department findDepartmentByName(String name) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Department> cq = cb.createQuery(Department.class);

        Root<Department> department = cq.from(Department.class);

        cq.select(department)
                .where(cb.equal(department.get("name"), name));

        return em.createQuery(cq).getSingleResult();
    }

    public List<Object[]> getEmployeeAboveSalary(int salary) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);

        Root<Employee> employee = cq.from(Employee.class);
        Join<Employee, Department> department = employee.join("department");

        cq.multiselect(
                employee.get("name"),
                employee.get("salary"),
                department.get("name")
        ).where(cb.greaterThan(employee.get("salary"), salary));

        return em.createQuery(cq).getResultList();
    }

    public void close() {
        em.close();
        emf.close();
    }
}

