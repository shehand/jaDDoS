/**
 * 
 */
package com.jaddos.portscanner;

/**
 * @author SonoD
 *
 */
public class PortScanResult implements IPortScannerResult {

	private int port;
	private boolean isOpened;

	public PortScanResult(int port, boolean isOpened) {
		this.port = port;
		this.isOpened = isOpened;
	}

	@Override
	public int getPort() {
		return port;
	}

	@Override
	public void setPort(int port) {
		this.port = port;

	}

	@Override
	public boolean isOpened() {
		return isOpened;
	}

	@Override
	public void setIsOpened(boolean isOpened) {
		this.isOpened = isOpened;

	}

}
