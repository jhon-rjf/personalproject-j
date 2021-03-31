import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTest2 {

	public static void main(String[] args) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
    	try {
        	// 1. ����̹� �ε�
            Class.forName("org.mariadb.jdbc.Driver");
            System.out.println("Driver Loading Success!!");
            
            // 2. DB���� ==> Connction ���
           // String url = "jdbc:mariadb://127.0.0.1:3309/test";
           //String user = "root", pwd = "dygks0917"; // ���� DB ��й�ȣ �Է�
           con = DriverManager.getConnection(
        		   "jdbc:mariadb://127.0.0.1:3309/test"
        		   "root"
        		   "dygks0917"
        		   );
        		   
           pstmt = con.prepareStatement("select * from his_bus_voltage");
           rs = pstmt.excuteQuery();
           // Connection con = DriverManager.getConnection(url, user, pwd);
            System.out.println("DB ���� ����!!");
            
            // 3. SQL�� �ۼ�
            String sql = "INSERT INTO MEMO_TABLE";
            	   sql += " VALUES ('������', DEFAULT, 'data')";
            
            // 4. sql���� �����Ű������ Statement��ü ������   
      	    Statement stmt=con.createStatement();	  
            
            // 5. Statement�� execute(String sql)�޼ҵ带 �̿��ؼ� sql�� ����
            stmt.execute(sql);
            System.out.println("������ insert ����!!");
            
            // 6. DB���� �ڿ� �ݳ�
	    if(stmt != null) stmt.close();
		
	    if(con != null) con.close();
            
        } catch(ClassNotFoundException e) {
		System.out.println("����̹� �ε� ����!!");
	} catch(SQLException e) {
		e.printStackTrace();
	} 
            
    }
}