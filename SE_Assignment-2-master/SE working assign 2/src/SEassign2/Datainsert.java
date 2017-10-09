package SEassign2;

import java.sql.*;

public class Datainsert {
	
	public static String checkUserExists(String user, String password) {
		
		Connection con = null;
		try {		
			con  = DBcon.getConnection();			

			// Get a prepared SQL statement
			String sql = "SELECT * FROM register WHERE userid=? and password=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, user);
			st.setString(2, password);
			
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				return rs.getString("name");
			}

		} catch (Exception e) {
			System.err.println("Login Exception: " + e.getMessage());
		} finally {
			DBcon.close(con);
		}
		return null;
	}
	
	public static boolean insertUser(String name, String emailAddress, String user, String password) {
		Connection con = null;
		try {
			con  = DBcon.getConnection();	

			// Get a prepared SQL statement
			String sql = "INSERT INTO register (name,emailAddress,userID,password) VALUES(?,?,?,?)";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, name);
			st.setString(2, emailAddress);
			st.setString(3, user);
			st.setString(4, password);
			
			//execute insert SQL Statement
			st.executeUpdate();
			return true;

		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
		} finally {
			DBcon.close(con);
		}
		return false;
	}
	
}
