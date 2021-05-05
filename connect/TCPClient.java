package connect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;


public class TCPClient {
	 public static void main(String[] args) {
		 
	        // 클라이언트 소켓 생성
	 
	        Socket socket = new Socket();
	        Scanner sc = new Scanner(System.in);
	 
	        InputStream is = null; // 바이트 단위로 데이터읽기
	        InputStreamReader isr = null; // 읽은 데이터를 길게 나타냄
	        BufferedReader br = null; // 틀
	 
	        OutputStream os = null; // 데이터 보내기
	        OutputStreamWriter osw = null; // 보낼 데이터를 보냄
	        PrintWriter pw = null; // 화면에 나오게 하는애
	 
	        // new InetSocketAddress(InetAddress.getLocalHost() 6077
	 
	        try {
	            socket.connect(new InetSocketAddress(InetAddress.getLocalHost(), 6077));
	            System.out.println("[client] 서버와 연결되었습니다.");
	 
	            while (true) {
	 
	                is = socket.getInputStream();
	                isr = new InputStreamReader(is, "UTF-8");
	                br = new BufferedReader(isr);
	 
	                os = socket.getOutputStream();
	                osw = new OutputStreamWriter(os, "UTF-8");
	                pw = new PrintWriter(osw, true);
	 
	                // 읽는거
	                System.out.print(">>");
	                String data = sc.nextLine();
	                //System.out.print(data+"\n");
	                if ("exit".equals(data))
	                    break;
	 
	                pw.println(data);
	 
	                data = br.readLine();
	                System.out.println("<< " + data);
	 
	            }
	 
	        } catch (IOException e) {
	           
	            e.printStackTrace();
	        } finally {
	            try {
	                if (socket != null && !socket.isClosed()) {
	                    socket.close();
	                }
	            } catch (IOException e) {
	               
	                e.printStackTrace();
	            }
	 
	            sc.close();
	 
	        }
	 
	    }
	 
}
