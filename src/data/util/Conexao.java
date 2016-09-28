package data.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

public class Conexao {
    
    private Connection connection;
    private ResultSet resultado;
    private PreparedStatement stmt;

    public Conexao() throws ClassNotFoundException, SQLException {
        this.connection = getConnection(); 
        connection.setAutoCommit(false);
    }
    
    private Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost/prova","root","lectra125");
    }
    
    public void setQuery(String query) throws SQLException {
        stmt = connection.prepareStatement(query);
    }
    
    public void setDate(int i, Date param) throws SQLException {
		Calendar cal = Calendar.getInstance();
		cal.setTime(param);
		java.sql.Date dataAux = new java.sql.Date(cal.getTimeInMillis());
        stmt.setDate(i, dataAux);
    }
    
    public void setInt(int i, int param) throws SQLException {
        stmt.setInt(i, param);
    }
    
    public void setFloat(int i, String param) throws SQLException {
        stmt.setString(i, param);
    }
    
    public void executeQuery() throws SQLException {
    	resultado = stmt.executeQuery();
    }
    
    public void execute() throws SQLException {
        stmt.execute();
    }
    
    public int executeUpdate() throws SQLException {
        return stmt.executeUpdate();
    }
    
    public ResultSet getResultSet() {
        return resultado;
    }
    
    public boolean next() throws SQLException {
        return resultado.next();
    }
    
    public String getString(String field) throws SQLException {
        return resultado.getString(field);
    }
    
    public int getInt(String field) throws SQLException {
        return resultado.getInt(field);
    }
    
    public Date getDate(String field) throws SQLException {
        Calendar cal = Calendar.getInstance();
		cal.setTime(resultado.getDate(field));
		return new Date(cal.getTimeInMillis());
    }
    
    public void close() throws SQLException {
        stmt.close();
        connection.close();
    }
    public void commit() throws SQLException {
        connection.commit();
    }

	public void setFloat(int i, float longitude) {		
	}
}
