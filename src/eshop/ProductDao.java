package eshop;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProductDao {
	
	private String dbUrl = "jdbc:mysql://localhost:3306/new_database?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private String dbUname = "root";
	private String dbPassword = "rootpassword";
	private String dbDriver = "com.mysql.cj.jdbc.Driver";
	
	public void loadDriver(String dbDriver)
	{
		try {
			Class.forName(dbDriver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Connection getConnection()
	{
		Connection con = null;
		try {
			con = DriverManager.getConnection(dbUrl, dbUname, dbPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	
	
	public String submit(Product product){
		loadDriver(dbDriver);
		Connection con = getConnection();
		String result = "Product submited successfully";
		String sql = "insert into new_database.products values(?,?,?,?)";
		
		PreparedStatement ps;
		try {
		ps = con.prepareStatement(sql);
		ps.setString(1, product.getName());
		ps.setString(2, product.getBarcode());
		ps.setString(3, product.getColor());
		ps.setString(4, product.getDescription());
		ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = "Product not submited(Existant Barcode)";
		}
		return result;
	}
	
	public List<Product> selectAllProducts() {

		List<Product> products = new ArrayList<>();
		loadDriver(dbDriver);
		Connection con = getConnection();
		String sql = "select * from new_database.products";
		
		PreparedStatement ps;
		try {
		ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			String  name = rs.getString("name");
			String barcode = rs.getString("barcode");
			String color = rs.getString("color");
			String description = rs.getString("description");
			products.add(new Product(barcode, name, color, description));
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return products;
	}
	
	

}
