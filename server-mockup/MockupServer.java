/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mockup.server;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;

/**
 *
 * @author onir
 */
public class MockupServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        int port = Integer.parseInt(args[0]);
        
        try {
            
            ServerSocket serv = new ServerSocket(port);
            System.out.println("Server created at port" + port + ".");
            
            ByteBuffer wrap;

            while (true) {
                Socket c = serv.accept();
                // Accept connections from the gateway
                
                BufferedInputStream input;
                input = new BufferedInputStream(c.getInputStream());
                
                boolean read = true ;
                
                
                
                while (read){
                 
                   byte[] cellId = new byte[4];
                   input.read(cellId,0,4);
                   
                   System.out.println("Begin of packet");
                   
                   int cellIdNumber = ByteBuffer.wrap(cellId).getInt();
                   System.out.println("Celld Id:" + cellIdNumber);
                   
                   byte[] nodeId = new byte[4];
                   input.read(nodeId,0,4);
                   
                   int nodeIdNumber = ByteBuffer.wrap(nodeId).getInt();
                   System.out.println("Node Id:" + nodeIdNumber);
                   
                   byte[] sideRoad = new byte[1];
                   input.read(sideRoad,0,1);
                   
                   byte sideRoadNumber = ByteBuffer.wrap(sideRoad).get();
                   System.out.println("Side Road:" + sideRoadNumber);
                   
                   byte[] timeStamp = new byte[8];
                   input.read(timeStamp,0,8);
                   
                   long timeStampNumber = ByteBuffer.wrap(timeStamp).getLong();
                   System.out.println("Time Stamp:" + timeStamp);
                   
                   byte[] size = new byte[4];
                   input.read(size,0,4);
                   
                   int rawDataSize = ByteBuffer.wrap(size).getInt(); 
                   System.out.println("Raw Data Size:" + size);
                   
                   byte[] rawData = new byte[rawDataSize];
                   input.read(rawData,0, rawDataSize);
                   
                   for (byte i : rawData)
                       System.out.print(i);
                   
                   System.out.println("");
                   System.out.println("End of packet");
                   
                }
                
                c.close();
                // Close connection with the gateway
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}