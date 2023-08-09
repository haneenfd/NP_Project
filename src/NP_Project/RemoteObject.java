package NP_Project;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class RemoteObject extends UnicastRemoteObject implements RMI {
    static final String DB_URL = "jdbc:mysql://localhost/test";
    static final String USER = "root";
    static final String PASS = "haneen";
    
    public RemoteObject() throws RemoteException {
        super();
    }

    @Override
    public Connection getConnection() throws RemoteException, SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
        return connection;
    }
    
    @Override
     public String delete(String id) throws RemoteException, SQLException {
    try {
        Connection conn = this.getConnection();
        String sql = "UPDATE emolyees SET status=0 WHERE id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, id);
        int rowsUpdated = stmt.executeUpdate();
        if (rowsUpdated > 0) {
            return "Status updated successfully";
        } else {
            return "Employee not found";
        }
    } catch (Exception ex) {
        return ex.getMessage();
    }
}


    @Override
    public String update(String id, String name, String gender, String dep, String age, String year) throws RemoteException, SQLException {
        StringBuilder sqlBuilder = new StringBuilder("UPDATE emolyees SET");
        boolean hasUpdates = false;
        
        if (!name.isEmpty()) {
            sqlBuilder.append(" name = ?,");
            hasUpdates = true;
        }
        
        if (!gender.isEmpty()) {
            sqlBuilder.append(" gender = ?,");
            hasUpdates = true;
        }
        
        if (!dep.isEmpty()) {
            sqlBuilder.append(" dep = ?,");
            hasUpdates = true;
        }
        
        if (!age.isEmpty()) {
            sqlBuilder.append(" age = ?,");
            hasUpdates = true;
        }
        
        if (!year.isEmpty()) {
            sqlBuilder.append(" year = ?,");
            hasUpdates = true;
        }
        
        if (!hasUpdates) {
            return "nothings to updat -_-";
        }
        
        sqlBuilder.deleteCharAt(sqlBuilder.length() - 1);
        sqlBuilder.append(" WHERE id = ?");
        String sql = sqlBuilder.toString();

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            
            int parameterIndex = 1;
            
            if (!name.isEmpty()) {
                statement.setString(parameterIndex, name);
                parameterIndex++;
            }
            
            if (!gender.isEmpty()) {
                statement.setString(parameterIndex, gender);
                parameterIndex++;
            }
            
            if (!dep.isEmpty()) {
                statement.setString(parameterIndex, dep);
                parameterIndex++;
            }
            
            if (!age.isEmpty()) {
                statement.setString(parameterIndex, age);
                parameterIndex++;
            }
            
            if (!year.isEmpty()) {
                statement.setString(parameterIndex,year);
                parameterIndex++;
            }
            
            statement.setString(parameterIndex, id);
            
            int rowsUpdated = statement.executeUpdate();
            
            if (rowsUpdated > 0) {
               return "data updated successfully";
            } else {
                return "No rows found with the specified ID.";
            }
        } catch (Exception e) {
            return e.toString();
        }
    }

    @Override
    public String insert(String id, String name, String gender, String dep, String age, String year) throws RemoteException, SQLException {
        
        try {
            Connection  conn = this.getConnection();
            String sql = "INSERT INTO emolyees (name, id, gender, dep, age, year, status) VALUES (?, ?, ?, ?, ?, ?, 1)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, id);
            statement.setString(3, gender);
            statement.setString(4, dep);
            statement.setString(5, age);
            statement.setString(6, year);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
            return "record inserted :)";
            }else{
                return "something went wrong";
            }
        } catch (Exception ex) {
            return ex.getMessage();
        }
          
    }
}
