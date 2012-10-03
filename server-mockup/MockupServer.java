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

            while (true) {
                Socket c = serv.accept();
                // Accept connections from the gateway
                
                BufferedInputStream input;
                input = new BufferedInputStream(c.getInputStream());
                
                boolean read = true ;
                
                while (read){
                    
                   byte[] cellId = new byte[4];
                   input.read(cellId,0,4);
                   
                   byte[] nodeId = new byte[4];
                   input.read(nodeId,0,4);
                   
                   byte[] sideRoad = new byte[1];
                   input.read(sideRoad,0,1);
                   
                   byte[] timeStamp = new byte[8];
                   input.read(timeStamp,0,8);
                   
                   byte[] size = new byte[4];
                   input.read(size,0,4);
                   
                   //Get the size of the row data that we need to read.
                               
                   ByteBuffer wrapped = ByteBuffer.wrap(size); //Big-endian by default
                   int rawDataSize = wrapped.getInt(); 
                   
                }
                
                c.close();
                // Close connection with the gateway
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}