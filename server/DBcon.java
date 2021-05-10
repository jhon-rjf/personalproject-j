package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBcon extends Thread {
	
	String Msg = null;
	String driver = "org.mariadb.jdbc.Driver";
    Connection con;
    PreparedStatement pstmt;
    
    public void run() {
    	System.out.println("Thread start");
    	
    		
    	
         try {
        	 if(con==null) {
            Class.forName(driver); //드라이버 객체화
            con = DriverManager.getConnection( //db와 연결
                    "jdbc:mariadb://127.0.0.1:3309/test",
                    "root",
                    "dygks0917");
        	 }
            
            if( con != null ) {
                System.out.println("DB 접속 성공");
                
                pstmt = con.prepareStatement("INSERT INTO CHAT VALUES('admin',DEFAULT,?)");//준비완료
                pstmt.setString(1, Msg);
            
                System.out.println(Msg);
                pstmt.executeQuery();//쿼리문을 넣어라
                pstmt.close();
                
            }
        
           
            
        } catch (ClassNotFoundException e) {  //클래스를 문자열로 로딩을 시도하다가 클래스가 없는경우 발생
            System.out.println("드라이버 로드 실패");
        } catch (SQLException e) { // SQL Server에 대 한 .NET Framework Data Provider 서버에서 생성 된 오류가 발생할 때
            System.out.println("DB 접속 실패");
            e.printStackTrace();
        }
         try {
 			
 			System.out.println("쿼리성공");
 		}catch(Exception e) {
 			e.printStackTrace();
 			System.out.println("쿼리실패");
 		}
         
    	}      
}
