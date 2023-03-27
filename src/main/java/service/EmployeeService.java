package service;

import dbbinding.impl.EmployeeBinding;
import entity.Employee;
import repository.RepositoryException;
import repository.impl.SqlRepository;

import java.util.Objects;
import java.util.Optional;

public enum EmployeeService {
    INSTANCE;

    public boolean checkCredentials(Employee employee)
    {
        SqlRepository<Employee> repository = new SqlRepository<Employee>(new EmployeeBinding());

        try {
            int employeeId = repository.getEntityId(employee);
            String password = repository.getEntityById(employeeId).getPassword();

            return Objects.equals(password, employee.getPassword());
        } catch (RepositoryException e) {
            return false;
        }
    }

    public Optional<Employee> getByLogin(String login)
    {
        Employee employee = new Employee();
        employee.setLogin(login);

        try {
            SqlRepository<Employee> repository = new SqlRepository<Employee>(new EmployeeBinding());
            int employeeId = repository.getEntityId(employee);
            return Optional.of(repository.getEntityById(employeeId));
        } catch (RepositoryException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }
}
