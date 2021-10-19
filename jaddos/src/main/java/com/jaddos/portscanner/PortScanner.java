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

import org.apache.log4j.Logger;

/**
 * @author SonoD
 *
 */
public class PortScanner {

	private String dns;
	private List<Future<PortScanResult>> resultSet;
	private Logger logger = Logger.getLogger(PortScanner.class);

	public PortScanner(String dns) {
		this.dns = dns;
	}

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

	private void searchForOpenPorts(String ipAddress) {
		final ExecutorService executorService = Executors.newFixedThreadPool(20);
		final List<Future<PortScanResult>> futures = new ArrayList<>();

		for (int port = 1; port <= 65535; port++) {
			logger.info("Scanning port : " + port);
			futures.add(portIsOpen(executorService, ipAddress, port));
		}

		executorService.shutdown();
		resultSet = futures;
	}

	public List<Future<PortScanResult>> returnOpenPortAndIp() throws UnknownHostException {
		InetAddress hostAddress = InetAddress.getByName(dns);
		searchForOpenPorts(hostAddress.getHostAddress());
		return resultSet;
	}
}
