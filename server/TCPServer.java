package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;


public class TCPServer {
	public static final int PORT = 6077;
	 
    public static void main(String[] args) {
 
        ServerSocket serverSocket = null;
 
        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
 
        OutputStream os = null;
        OutputStreamWriter osw = null;
        PrintWriter pw = null;
        Scanner sc = new Scanner(System.in);
       
        
        
        try {
            // 1. Server Socket 생성
            serverSocket = new ServerSocket();
 
            // 2. Binding : Socket에 SocketAddress(IpAddress + Port) 바인딩 함
 
            InetAddress inetAddress = InetAddress.getLocalHost();
            String localhost = inetAddress.getHostAddress();
 
            serverSocket.bind(new InetSocketAddress(localhost, PORT));
 
            System.out.println("[server] binding " + localhost);
 
            // 3. accept(클라이언트로 부터 연결요청을 기다림)
 
            Socket socket = serverSocket.accept();
            InetSocketAddress socketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
 
            System.out.println("[server] connected by client"+"\n"+"프로그램을 종료하시려면 exit를 입력하세요");
            System.out.println("[server] Connect with " + socketAddress.getHostString() + " " + socket.getPort());
 
            while (true) {
 
                // inputStream 가져와서 (주 스트림) StreamReader와 BufferedReader로 감싸준다 (보조 스트림)
                is = socket.getInputStream(); //적은값
                isr = new InputStreamReader(is, "UTF-8"); //형식변환
                br = new BufferedReader(isr); //보내준다
 
                // outputStream 가져와서 (주 스트림) StreamWriter와 PrintWriter로 감싸준다 (보조 스트림)
                os = socket.getOutputStream();
                osw = new OutputStreamWriter(os, "UTF-8");
                pw = new PrintWriter(osw, true);
 
                String buffer = null;
                buffer = br.readLine(); // Blocking
                if (buffer == null) {
 
                    // 정상종료 : remote socket close()
                    // 메소드를 통해서 정상적으로 소켓을 닫은 경우
                    System.out.println("[server] closed by client");
                    break;
 
                }
 
                System.out.println("[server] recived : " + buffer);
               
                System.out.print(">>");
                String data2 = sc.nextLine();
                
                pw.println(data2);
         
              
            }
 
            // 3.accept(클라이언트로 부터 연결요청을 기다림)
            // .. blocking 되면서 기다리는중, connect가 들어오면 block이 풀림
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
 
            try {
 
                if (serverSocket != null && !serverSocket.isClosed())
                    serverSocket.close();
 
            } catch (Exception e) {
                e.printStackTrace();
            }
 
            sc.close();
 
        }
 
        	
      
        	}
 // class DBcon 쓰레드 활성화
   // while문으로 계속 돌리기
    // pstmt.setString을 활용하여 채팅 데이터 쿼리문으로 옮기기
    
    public class DBcon extends Thread{
    		@Override
    		public void run() {
    			
    			String driver = "org.mariadb.jdbc.Driver";
    		    Connection con;
    		    PreparedStatement pstmt;
    		    
    		   
    		    	
    		         try {
    		            Class.forName(driver); //드라이버 객체화
    		            con = DriverManager.getConnection( //db와 연결
    		                    "jdbc:mariadb://127.0.0.1:3309/test",
    		                    "root",
    		                    "dygks0917");
    		            
    		            if( con != null ) {
    		            	
    		                System.out.println("DB 접속 성공");
    		                //INSERT INTO chat VALUES("정윤걸", DEFAULT, "data");
    		                pstmt = con.prepareStatement("INSERT INTO CHAT VALUES('admin',DEFAULT,'talk')");//준비완료
    		             //   pstmt.setString(1, name);
    		              //  pstmt.setString(1, buffer);
    		                
    		                pstmt.executeQuery();//쿼리문을 넣어라
    		               // System.out.println(buffer);
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
    }

    }
 



