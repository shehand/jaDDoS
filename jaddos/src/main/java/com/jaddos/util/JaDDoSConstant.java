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

/**
 * Constants for JaDDoS implementation
 * 
 * @author ShehanD
 * 
 *
 */
public class JaDDoSConstant {

	// Current version
	public static final double DDOS_VERSION = 0.2;

	// MD5 Password to be used when the script is executed from the webserver,
	// default is "apple"
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

	// For attack vector
	public static final int DEFAULT_TIMEOUT_IN_MILLISECONDS = 1000;
	public static final int DEFAULT_ATTACK_THREAD_COUNT = 10;
}
