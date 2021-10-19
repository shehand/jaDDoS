/**
 * 
 */
package com.jaddos.portscanner;

/**
 * @author SonoD
 *
 */
public interface IPortScannerResult {

	public int getPort();
	
	public void setPort(int port);
	
	public boolean isOpened();
	
	public void setIsOpened(boolean isOpened);
}
