/**
 * 
 */
package com.jaddos.core;

import java.net.SocketAddress;
import java.net.SocketException;

/**
 * @author SonoD
 *
 */
public abstract class JaDDoS implements IJaDDoS {

	private JaDDoSPattern ddosPattern;
    private SocketAddress address;
    
    public JaDDoS() {
        
    }
    
    public JaDDoS(JaDDoSPattern ddosPattern) {
        this.ddosPattern = ddosPattern;
    }

    /**
     * This method writes something (Protocol dependent) to the socket.
     * @param message 
     */
    public abstract void writeLineToSocket(String message);
    
    /**
     * Creates the socket. (Protocol dependent)
     */
    protected abstract void createSocket();
    
    /**
     * Connect to the socket. (Protocol dependent)
     */
    protected abstract void connectToSocket();
    
    /**
     * Close the socket (Protocol dependent)
     */
    protected abstract void closeSocket();

    /**
     * Get the DDOS pattern.
     * @return the pattern of the current DDOS
     */
    public JaDDoSPattern getDdosPattern() {
        return ddosPattern;
    }

    /**
     * Set the DDOS pattern.
     * @param ddosPattern pattern of the current DDOS
     */
    public void setDdosPattern(JaDDoSPattern ddosPattern) {
        this.ddosPattern = ddosPattern;
    }

    /**
     * Get the address of the victim
     * @return 
     */
    public SocketAddress getAddress() {
        return address;
    }

    /**
     * Set the address of the victim
     * @param address 
     */
    public void setAddress(SocketAddress address) {
        this.address = address;
    }

}
