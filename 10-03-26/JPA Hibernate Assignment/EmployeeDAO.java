package cg.demo.jpahibernate.dao;

import cg.demo.jpahibernate.entities.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

import java.util.List;


public class EmployeeDAO {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA-PU");
    EntityManager em = emf.createEntityManager();

    public Employee insertEmployee(Employee employee) {
        em.getTransaction().begin();
        em.persist(employee);
        em.getTransaction().commit();
        return employee;
    }

    public Employee updateEmployee(int id, Employee updatedData) {
        Employee employee = em.find(Employee.class, id);
        if (employee != null) {
            em.getTransaction().begin();
            employee.setName(updatedData.getName());
            employee.setSalary(updatedData.getSalary());
            employee.setDepartment(updatedData.getDepartment());
            employee.setPhoneno(updatedData.getPhoneno());
            em.getTransaction().commit();
        }
        return employee;
    }

    public Employee deleteEmployee(int id) {
        Employee employee = em.find(Employee.class, id);
        if (employee != null) {
            em.getTransaction().begin();
            em.remove(employee);
            em.getTransaction().commit();
        }
        return employee;
    }

    public List<Employee> getAllEmployees() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
        cq.from(Employee.class);
        return em.createQuery(cq).getResultList();
    }

    public void close() {
        em.close();
        emf.close();
    }
}
