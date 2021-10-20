package com.jaddos.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author SonoD
 *
 */
public class HelpUtils {

	private List<String> helpFunctions = new ArrayList<>();
	private static HelpUtils helpUtilsInstance = new HelpUtils();

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

	public void printUsageFunctions() {
		for (String s : helpFunctions) {
			System.out.println(s);
		}
	}

	public static HelpUtils getInstance() {
		return helpUtilsInstance;
	}
}
