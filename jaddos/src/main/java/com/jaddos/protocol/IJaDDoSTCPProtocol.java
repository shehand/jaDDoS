package com.jaddos.protocol;

import java.net.Socket;

/**
 * @author SonoD
 *
 */
public interface IJaDDoSTCPProtocol {

	/**
     * Get the socket
     * @return 
     */
    public Socket getSocket();

    /**
     * Set the socket
     * @param socket 
     */
    public void setSocket(Socket socket);
}
