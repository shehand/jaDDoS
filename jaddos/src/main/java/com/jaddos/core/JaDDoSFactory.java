/**
 * 
 */
package com.jaddos.core;

import com.jaddos.protocol.JaDDoSTCPProtocol;
import com.jaddos.protocol.JaDDoSUDPProtocol;

/**
 * @author SonoD
 *
 */
public class JaDDoSFactory {

	/**
	 * Factory method which is responsible for creating the corresponding DDOS
	 * subclass.
	 * 
	 * @param ddosPattern
	 * @return a subclass of DDOS
	 */
	public static JaDDoS createDDOS(JaDDoSPattern ddosPattern) {
		switch (ddosPattern.getProtocol().toLowerCase()) {
		case "tcp":
			return new JaDDoSTCPProtocol(ddosPattern);
		case "udp":
			return new JaDDoSUDPProtocol(ddosPattern);
		}
		return new JaDDoSTCPProtocol(ddosPattern);
	}
}
