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
       DBcon DB = new DBcon();
      
        
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
           
                	
                
                if ("!command".equals(data2)) {
                	 DB.Msg = buffer;
                	DB.run();
                	//쿼리 다른것 추가 필요
                }
            }
   
            // 3.accept(클라이언트로 부터 연결요청을 기다림)
            // .. blocking 되면서 기다리는중, connect가 들어오면 block이 풀림
        } catch (IOException e) { //입출력에 관련한 오류가 났을때
            e.printStackTrace();//리턴값이 없다.
        } finally {
 
            try {
 
                if (serverSocket != null && !serverSocket.isClosed()) //소캣이 닫혀있지 않을 때 
                    serverSocket.close();
 
            } catch (Exception e) {
                e.printStackTrace();
            }
 
            sc.close();
            try {
               DB.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
           
            DB.interrupt();
            System.out.println("Thread closed");
        }
 
        	
      
        	}


    }
 



