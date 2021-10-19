package com.jaddos.util;

import org.apache.log4j.Logger;

/**
 * @author SonoD
 *
 */
public class TimeChecker extends Thread {

	private boolean stopThread;
	private int duration;
	private Logger logger = Logger.getLogger(TimeChecker.class);

	public TimeChecker(boolean stopThread, int duration) {
		this.stopThread = stopThread;
		this.duration = duration;
	}

	@Override
	public void run() {
		while (!isInterrupted() && !stopThread && duration > 0) {
			try {
				duration -= 1;
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				interrupt();
				stopThread = true;
				duration = 0;
				logger.error("Thread interrupted at:" + duration + " due to: " + e.getMessage(), e);
				break;
			}
		}
		interrupt();
	}
}
