package com.jaddos.core;

import java.net.SocketAddress;

/**
 * @author SonoD
 *
 */
public interface IJaDDoS extends Runnable {
    /**
     * Get the DDOS pattern.
     * @return the pattern of the current DDOS
     */
    public JaDDoSPattern getDdosPattern();

    /**
     * Set the DDOS pattern.
     * @param ddosPattern pattern of the current DDOS
     */
    public void setDdosPattern(JaDDoSPattern ddosPattern);

    /**
     * Get the address of the victim
     * @return 
     */
    public SocketAddress getAddress();

    /**
     * Set the address of the victim
     * @param address 
     */
    public void setAddress(SocketAddress address);
}
