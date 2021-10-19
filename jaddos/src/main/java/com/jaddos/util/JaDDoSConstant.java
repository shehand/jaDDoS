package com.jaddos.util;

/**
 * @author SonoD
 *
 */
public class JaDDoSConstant {

	// Current version
	public static final double DDOS_VERSION = 0.2;

	// MD5 Password to be used when the script is executed from the webserver, default is "apple"
	public static final String DDOS_PASSWORD = "1f3870be274f6c49b3e31a0c6728957f";

	// Script max execution time
	public static final int DDOS_MAX_EXECUTION_TIME = 0;

	// Default and max packets size
	public static final int DDOS_DEFAULT_PACKET_SIZE = 65000;
	public static final int DDOS_MAX_PACKET_SIZE = 65000;

	// Default byte to send
	public static final String DDOS_DEFAULT_BYTE = "\\x00";

	// Loggin functions
	public static final int DDOS_LOG_DEBUG = 4;
	public static final int DDOS_LOG_INFO = 3;
	public static final int DDOS_LOG_NOTICE = 2;
	public static final int DDOS_LOG_WARNING = 1;
	public static final int DDOS_LOG_ERROR = 0;

	// Output formats
	public static final String DDOS_OUTPUT_FORMAT_JSON = "json";
	public static final String DDOS_OUTPUT_FORMAT_TEXT = "text";
	public static final String DDOS_OUTPUT_FORMAT_XML = "xml";

	// Output status
	public static final String DDOS_OUTPUT_STATUS_ERROR = "error";
	public static final String DDOS_OUTPUT_STATUS_SUCCESS = "success";
	
	public static final int DEFAULT_TIMEOUT_IN_MILLISECONDS = 1000;
}
