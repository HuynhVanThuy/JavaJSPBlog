package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Tables.Category;
 

public class ConnectDB {
	
	Connection conn = null;
	String url = "jdbc:postgresql://localhost:5432/blog";
	String username = "postgres";
	String password = "654321";
	
	public Connection open(){
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(url, username, password); 
			if(conn != null) {
				return conn;
			} 
			else {
				return conn;
			}
		} 
		catch (ClassNotFoundException e) {
			System.out.println("Lỗi Driver database: ");
			e.printStackTrace();
		}
		catch (SQLException e) {
			System.out.println("Lỗi kết nối, kiểm tra lại thông tin database: ");
			e.printStackTrace();
		} 
		return null; 
	}
	
	public void close(){
		try {
			conn.close();
		} catch (SQLException e) {
			System.out.println("Lỗi đóng dữ liệu!");
			System.out.println(e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		ConnectDB con = new ConnectDB();  
		String query = "SELECT id, name, create_at, update_at FROM category";
		ArrayList<Category> listCategory = new ArrayList<>();
		try {
			con.open();
			Statement st = con.conn.createStatement();
			ResultSet rs = st.executeQuery(query);  
			while (rs.next()) {  
				Category category = new Category();
				category.setId(rs.getLong("id"));
				category.setName(rs.getString("name")); 
				category.setCreate_at(rs.getDate("create_at"));  
				category.setUpdate_at(rs.getDate("update_at"));  
				listCategory.add(category);
			}
			con.close();
			
		} catch (SQLException e) { 
			e.printStackTrace();
		} 
		for (Category ls : listCategory) {
			System.out.println(ls.getId());
			System.out.println(ls.getName());
			System.out.println(ls.getCreate_at());
			System.out.println(ls.getUpdate_at());
		}
	}

}
