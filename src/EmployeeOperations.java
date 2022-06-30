
public class EmployeeOperations {
    
    public EmployeeOperations() {
    }


    public String addEmployee(Integer id, String name,String email){
        return "insert into employeetable VALUES ( "+ id + ", '" + name + "', '" + email + "')";

    }
    public String listEmployees(){
        return "select * from employeetable";
    }
    public String deleteEmployee(int id){
        return "delete from employeetable where id = " + id;
    }

    
    
}
