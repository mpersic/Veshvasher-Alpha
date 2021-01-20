package matejpersic_orwima_proj.ferit.veshwasher;

public class Employee {
    public int id ;
    public String employee_name;
    public String employee_age;
    public String employee_salary;

    public Employee(int id, String employee_name, String employee_age, String employee_salary) {
        this.id = id;
        this.employee_name = employee_name;
        this.employee_age = employee_age;
        this.employee_salary=employee_salary;
    }

    public String getEmployee_salary() {
        return employee_salary;
    }

    public int getId() {
        return id;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public String getEmployee_age() {
        return employee_age;
    }
}
