package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBcon {
	String driver = "org.mariadb.jdbc.Driver";
    Connection con;
    PreparedStatement pstmt;
    ResultSet rs; //쿼리문을 실행한 값을 넣는값
 
    public DBcon() {
         try {
            Class.forName(driver); //드라이버 객체화
            con = DriverManager.getConnection( //db와 연결
                    "jdbc:mariadb://127.0.0.1:3309/test",
                    "root",
                    "dygks0917");
            
            if( con != null ) {
                System.out.println("DB 접속 성공");
                //INSERT INTO chat VALUES("정윤걸", DEFAULT, "data");
                pstmt = con.prepareStatement("INSERT INTO CHAT VALUES(?,DEFAULT,?)");//준비완료
                pstmt.setString(2, buffer);
                pstmt.executeQuery();//쿼리문을 넣어라
                System.out.println(buffer);
            }
        
           
            
        } catch (ClassNotFoundException e) { 
            System.out.println("드라이버 로드 실패");
        } catch (SQLException e) {
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
    
   
    
    public static void main(String[] args){
        DBcon dbcon    = new DBcon();
    }
}
