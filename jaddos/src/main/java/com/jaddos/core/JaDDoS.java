/***
 * Copyright (c) 2021 Shehan Dhaleesha
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 * 
 */

package com.jaddos.core;

import java.net.SocketAddress;
import java.net.SocketException;

/**
 * Abstract class to hold the DoS Configurations
 * 
 * @author ShehanD
 *
 */
public abstract class JaDDoS implements IJaDDoS {

	/** To hold the DoS pattern */
	private JaDDoSPattern ddosPattern;

	/** To hold the socket address */
	private SocketAddress address;

	/**  Constructor */
	public JaDDoS() {

	}

	/**  Constructor */
	public JaDDoS(JaDDoSPattern ddosPattern) {
		this.ddosPattern = ddosPattern;
	}

	/**
	 * This method writes something (Protocol dependent) to the socket.
	 * 
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
	 * 
	 * @return the pattern of the current DDOS
	 */
	public JaDDoSPattern getDdosPattern() {
		return ddosPattern;
	}

	/**
	 * Set the DDOS pattern.
	 * 
	 * @param ddosPattern pattern of the current DDOS
	 */
	public void setDdosPattern(JaDDoSPattern ddosPattern) {
		this.ddosPattern = ddosPattern;
	}

	/**
	 * Get the address of the victim
	 * 
	 * @return
	 */
	public SocketAddress getAddress() {
		return address;
	}

	/**
	 * Set the address of the victim
	 * 
	 * @param address
	 */
	public void setAddress(SocketAddress address) {
		this.address = address;
	}

}
