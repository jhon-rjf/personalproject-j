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
        	// 1. 드라이버 로딩
            Class.forName("org.mariadb.jdbc.Driver");
            System.out.println("Driver Loading Success!!");
            
            // 2. DB연결 ==> Connction 얻기
           // String url = "jdbc:mariadb://127.0.0.1:3309/test";
           //String user = "root", pwd = "dygks0917"; // 각자 DB 비밀번호 입력
           con = DriverManager.getConnection(
        		   "jdbc:mariadb://127.0.0.1:3309/test"
        		   "root"
        		   "dygks0917"
        		   );
        		   
           pstmt = con.prepareStatement("select * from his_bus_voltage");
           rs = pstmt.excuteQuery();
           // Connection con = DriverManager.getConnection(url, user, pwd);
            System.out.println("DB 연결 성공!!");
            
            // 3. SQL문 작성
            String sql = "INSERT INTO MEMO_TABLE";
            	   sql += " VALUES ('정윤걸', DEFAULT, 'data')";
            
            // 4. sql문을 실행시키기위한 Statement객체 얻어오기   
      	    Statement stmt=con.createStatement();	  
            
            // 5. Statement의 execute(String sql)메소드를 이용해서 sql문 실행
            stmt.execute(sql);
            System.out.println("데이터 insert 성공!!");
            
            // 6. DB연결 자원 반납
	    if(stmt != null) stmt.close();
		
	    if(con != null) con.close();
            
        } catch(ClassNotFoundException e) {
		System.out.println("드라이버 로딩 실패!!");
	} catch(SQLException e) {
		e.printStackTrace();
	} 
            
    }
}