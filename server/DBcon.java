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
    ResultSet rs; //�������� ������ ���� �ִ°�
 
    public DBcon() {
         try {
            Class.forName(driver); //����̹� ��üȭ
            con = DriverManager.getConnection( //db�� ����
                    "jdbc:mariadb://127.0.0.1:3309/test",
                    "root",
                    "dygks0917");
            
            if( con != null ) {
                System.out.println("DB ���� ����");
                //INSERT INTO chat VALUES("������", DEFAULT, "data");
                pstmt = con.prepareStatement("INSERT INTO CHAT VALUES(?,DEFAULT,?)");//�غ�Ϸ�
                pstmt.setString(2, buffer);
                pstmt.executeQuery();//�������� �־��
                System.out.println(buffer);
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
        DBcon dbcon    = new DBcon();
    }
}
