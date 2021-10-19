package com.jaddos.core;

import java.io.Serializable;

/**
 * @author SonoD
 *
 */
public class JaDDoSPattern implements Serializable {

	/**
	 * Default serialization id
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The name of the protocol (tcp, udp etc.)
	 */
	private String protocol;

	/**
	 * The target host. This host is the victim.
	 */
	private String host;

	/**
	 * Target port.
	 */
	private int port;

	/**
	 * The number of generated attackers.
	 */
	private int threads;

	/**
	 * The message to send to the victim. Over the specified protocol.
	 */
	private String message;

	/**
	 * The duration in hours. Must be greater equal 0.
	 */
	private int hours;

	/**
	 * The duration in minutes. Must be greater equal 0.
	 */
	private int minutes;

	/**
	 * The duration in seconds. Must be greater equal 0.
	 */
	private int seconds;

	/**
	 * The timeout between an attack. This attribute specifies the break between
	 * every attack in milliseconds.
	 */
	private int timeout;

	/**
	 * The socket timeout. The timeout how long a socket should wait until the
	 * connection aborts in milliseconds.
	 */
	private int socketTimeout;

	/**
	 * Get the procol name.
	 * 
	 * @return the protocolname (e.g tcp, udp ...)
	 */
	public String getProtocol() {
		return protocol;
	}

	/**
	 * Set the protocol name.
	 * 
	 * @param protocol the name of the protocol (tcp, udp ...).
	 */
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	/**
	 * Get the target host (victim).
	 * 
	 * @return
	 */
	public String getHost() {
		return host;
	}

	/**
	 * Set the target host (victim).
	 * 
	 * @param host
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * Get the target port.
	 * 
	 * @return
	 */
	public int getPort() {
		return port;
	}

	/**
	 * Set the target port.
	 * 
	 * @param port
	 */
	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * Get the number of attacker threads.
	 * 
	 * @return
	 */
	public int getThreads() {
		return threads;
	}

	/**
	 * Set the number of attacker threads.
	 * 
	 * @param threads
	 */
	public void setThreads(int threads) {
		this.threads = threads;
	}

	/**
	 * Get the message to send to the victim.
	 * 
	 * @return message to send to the victim
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Set the message to send to the victim.
	 * 
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * The duration time in hours. NOTE that this is not the whole duration time
	 * converted in hours
	 * 
	 * @return
	 */
	public int getHours() {
		return hours;
	}

	/**
	 * The duration time in hours. NOTE that this is not the whole duration time
	 * converted in hours.
	 * 
	 * @param hours
	 */
	public void setHours(int hours) {
		this.hours = hours;
	}

	/**
	 * The duration time in minutes. NOTE that this is not the whole duration time
	 * converted in minutes
	 * 
	 * @return
	 */
	public int getMinutes() {
		return minutes;
	}

	/**
	 * Get the duration time in minutes. NOTE that this is not the whole duration
	 * time converted in minutes
	 * 
	 * @param minutes
	 */
	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	/**
	 * Duration time in seconds. NOTE that this is not the whole duration time
	 * converted in seconds
	 * 
	 * @return duration time in seconds
	 */
	public int getSeconds() {
		return seconds;
	}

	/**
	 * Set the duration time in seconds. NOTE that this is not the whole duration
	 * time converted in seconds
	 * 
	 * @param seconds
	 */
	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}

	/**
	 * Get the timeout between every attack.
	 * 
	 * @return the time between every attack in milliseconds
	 */
	public int getTimeout() {
		return timeout;
	}

	/**
	 * Set the timeout between every attack.
	 * 
	 * @param timeout time between every attack in milliseconds
	 */
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	/**
	 * Get the timeout until the socket times out.
	 * 
	 * @return timeout until the socket times out in milliseconds
	 */
	public int getSocketTimeout() {
		return socketTimeout;
	}

	/**
	 * Set the timeout until the socket times out.
	 * 
	 * @param socketTimeout timeout until the socket times out in milliseconds
	 */
	public void setSocketTimeout(int socketTimeout) {
		this.socketTimeout = socketTimeout;
	}

}
