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

package com.jaddos.util;

import org.apache.log4j.Logger;

/**
 * TimeChecker implementation
 * 
 * @author ShehanD
 *
 */
public class TimeChecker extends Thread {

	/** Logger instance */
	private Logger logger = Logger.getLogger(TimeChecker.class);

	/** Boolean variable to indicate the state */
	private boolean stopThread;

	/** Duration in seconds */
	private int duration;

	/** Constructor */
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
