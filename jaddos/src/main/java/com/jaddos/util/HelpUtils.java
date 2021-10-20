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

import java.util.ArrayList;
import java.util.List;

/**
 * Util class for help implementation
 * 
 * @author ShehanD
 *
 */
public class HelpUtils {

	/** Instance for help functions */
	private List<String> helpFunctions = new ArrayList<>();

	/** Instance for class object */
	private static HelpUtils helpUtilsInstance = new HelpUtils();

	/** Constructor */
	private HelpUtils() {
		helpFunctions.add("EXAMPLES:");
		helpFunctions.add("from terminal:  java -jar <path to jar file> --host example.com --port 8080 --protocol UDP");
		helpFunctions.add("");
		helpFunctions.add("PARAMETERS:");
		helpFunctions.add("--help					Print this help summary page");
		helpFunctions.add("--host / -h				REQUIRED specify IP or HOSTNAME");
		helpFunctions.add(
				"--protocol / -pc			REQUIRED specify the DoS format. Only support for TCP or UDP for the moment.");
		helpFunctions.add(
				"--port / -p				OPTIONAL should be an opened port that can create a socket listen to it. If not provided program will use a sequence of threads to identify opened ports in the given host. This may required some additional time since this is an I/O related task.");
		helpFunctions.add(
				"--threads / -t			OPTIONAL number of threads that should concurrenlty send packets to the defined host. larger the number, larger the affect");
		helpFunctions
				.add("--message / -m			OPTIONAL a message string that should send with the request packet");
		helpFunctions.add("--timeout / -tm			OPTIONAL timeout between two threads");
		helpFunctions.add("--sockettimeout / -scm	OPTIONAL timeout for socket to send requests");
		helpFunctions.add(
				"--duration / -d			OPTIONAL a time duration that attack should happened. Time should be given in HH:MM:SS format seperating each with colon element. All three segments should present in the given parameter.");
		helpFunctions.add("");
	}

	/**
	 * Method to print the help usage
	 */
	public void printUsageFunctions() {
		for (String s : helpFunctions) {
			System.out.println(s);
		}
	}

	/**
	 * Method to return the HelpUtils instance.
	 * 
	 * @return HelpUtils
	 */
	public static HelpUtils getInstance() {
		return helpUtilsInstance;
	}
}
