import java.sql.*;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        int cont = 1;
        EmployeeOperations operations = new EmployeeOperations();
        while( cont != 0){
            try {
                Connection myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/newhospital" , "student", "yarkin-1303");
                Statement stmt = myConnection.createStatement();
                if( !tableExistsSQL(myConnection, "employeeTable")){
                    String openTable = "create table employeeTable (id int, name varchar(150),email varchar(150))";
                    stmt.executeUpdate(openTable);
                }
                System.out.println("Welcome to employee management system");
                System.out.print("Enter 0 to stop, 1 to add new employee, 2 to remove employee, 3 to list them: ");
                Scanner scan = new Scanner(System.in);
                cont = scan.nextInt();
                if( cont == 1){
                    System.out.print("Id of the employee: " );
                    int id = scan.nextInt();
                    ResultSet set = stmt.executeQuery("select * from employeeTable where id = " + id);
                    if( set.next()){
                        System.out.println("Id is taken");
                    }
                    else{
                        System.out.print("Name of the employee: ");
                        String name = scan.next();
                        System.out.print("Email of the employee: ");
                        String email = scan.next();
                        stmt.executeUpdate(operations.addEmployee(id, name, email));
                    }
                    


                }
                else if(cont == 2){
                    System.out.print("Id of the employee: " );
                    int id = scan.nextInt();
                    ResultSet set = stmt.executeQuery("select * from employeeTable where id = " + id);
                    if( !set.next()){
                        System.out.println("Employee with the specified id doesn't exist.");
                    }
                    else{
                        stmt.executeUpdate(operations.deleteEmployee(id));

                    }
                    

                }
                else if( cont == 3){
                    ResultSet set = stmt.executeQuery(operations.listEmployees());	
                    while(set.next()){
                        System.out.println("Id: " + set.getString("id") + "\nName: " + set.getString("name")
                        + "\nEmail: " + set.getString("email")+ "\n");
    
                   } 

                }
                
        
                   
                   
            
                
                
                    
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
       
    }
    static boolean tableExistsSQL(Connection connection, String tableName) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT count(*) "
          + "FROM information_schema.tables "
          + "WHERE table_name = ?"
          + "LIMIT 1;");
        preparedStatement.setString(1, tableName);
    
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getInt(1) != 0;
    }
}
