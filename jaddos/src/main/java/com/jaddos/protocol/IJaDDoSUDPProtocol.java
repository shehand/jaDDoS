/**
 * 
 */
package com.jaddos.protocol;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * @author SonoD
 *
 */
public interface IJaDDoSUDPProtocol {

	 /**
     * Get the Socket
     * @return 
     */
    public DatagramSocket getSocket();

    /**
     * Set the socket
     * @param socket 
     */
    public void setSocket(DatagramSocket socket);


    /**
     * Get the packet
     * @return 
     */
    public DatagramPacket getPacket();

    /**
     * Set the packet
     * @param packet 
     */
    public void setPacket(DatagramPacket packet);
}
