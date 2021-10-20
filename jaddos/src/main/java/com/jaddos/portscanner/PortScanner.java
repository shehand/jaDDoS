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

package com.jaddos.portscanner;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

/**
 * PortScanner class implementation
 * 
 * @author ShehanD
 *
 */
public class PortScanner {

	/** Variable to hold the host DSN record */
	private String dns;

	/** Future list that holds PortScannerResults objects */
	private List<Future<PortScanResult>> resultSet;

	/** Logger instance */
	private Logger logger = Logger.getLogger(PortScanner.class);

	/** Constructor */
	public PortScanner(String dns) {
		this.dns = dns;
	}

	/**
	 * Method to check whether the port is opened or not
	 * 
	 * @param ExecutorService
	 * @param ip
	 * @param port
	 * @return
	 */
	private static Future<PortScanResult> portIsOpen(final ExecutorService es, final String ip, final int port) {
		return es.submit(new Callable<PortScanResult>() {
			@Override
			public PortScanResult call() {
				try {
					Socket socket = new Socket();
					socket.connect(new InetSocketAddress(ip, port),
							ScannerConstants.PORT_SEARCHING_TIMEOUT_MILLISECONDS);
					socket.close();
					return new PortScanResult(port, true);
				} catch (Exception e) {
					return new PortScanResult(port, false);
				}
			}
		});
	}

	/**
	 * Method that will create a thread pool and pool the applicable ports that
	 * should be checked in order to check for opened ports. This is a concurrent
	 * process.
	 * 
	 * @param ipAddress
	 */
	private void searchForOpenPorts(String ipAddress) {
		final ExecutorService executorService = Executors.newFixedThreadPool(20);
		final List<Future<PortScanResult>> futures = new ArrayList<>();

		for (int port = 1; port <= 65535; port++) {
			logger.info("Scanning port : " + port);
			futures.add(portIsOpen(executorService, ipAddress, port));
		}

		executorService.shutdown();

		// Wait for finishing the jobs
		try {
			if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
				executorService.shutdownNow();
			}
		} catch (InterruptedException e) {
			executorService.shutdownNow();
			Thread.currentThread().interrupt();
		}

		resultSet = futures;
	}

	/**
	 * Publicly accessible method to search for a opened port
	 * 
	 * @return List<Future<PortScanResult>>
	 * @throws UnknownHostException
	 */
	public List<Future<PortScanResult>> returnOpenPortAndIp() throws UnknownHostException {
		InetAddress hostAddress = InetAddress.getByName(dns);
		searchForOpenPorts(hostAddress.getHostAddress());
		return resultSet;
	}
}
