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
            Class.forName(driver); //����̹� ��üȭ
            con = DriverManager.getConnection( //db�� ����
                    "jdbc:mariadb://127.0.0.1:3309/test",
                    "root",
                    "dygks0917");
        	 }
            
            if( con != null ) {
                System.out.println("DB ���� ����");
                
                pstmt = con.prepareStatement("INSERT INTO CHAT VALUES('admin',DEFAULT,?)");//�غ�Ϸ�
                pstmt.setString(1, Msg);
            
                System.out.println(Msg);
                pstmt.executeQuery();//�������� �־��
                Msg = null;
            }
        
           
            
        } catch (ClassNotFoundException e) { 
            System.out.println("����̹� �ε� ����");
        } catch (SQLException e) {
            System.out.println("DB ���� ����");
            e.printStackTrace();
        }
         try {
 			
 			System.out.println("��������");
 		}catch(Exception e) {
 			e.printStackTrace();
 			System.out.println("��������");
 		}
    	}      
    
   
    
   
   
    
    public static void main(String[] args){
        DBcon DBcon    = new DBcon();
    }
}
