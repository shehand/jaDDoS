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
		helpFunctions.add(
				"from terminal:  java -jar <path to jar file> host=TARGET port=PORT time=SECONDS packet=NUMBER bytes=NUMBER");
		helpFunctions.add("PARAMETERS:");
		helpFunctions.add("help		Print this help summary page");
		helpFunctions.add("host		REQUIRED specify IP or HOSTNAME");
		helpFunctions.add("pass		REQUIRED only if used from webserver");
		helpFunctions.add("port		OPTIONAL if not specified a random ports will be selected");
		helpFunctions.add("time		OPTIONAL seconds to keep the DDoS alive, required if packet is not used");
		helpFunctions.add("packet	OPTIONAL number of packets to send to the target, required if time is not used");
		helpFunctions
				.add("bytes		OPTIONAL size of the packet to send, defualt: " + JaDDoSConstant.DDOS_DEFAULT_PACKET_SIZE);
		helpFunctions.add("format	OPTIONAL output format, (text,json,xml), default: text");
		helpFunctions.add("output	OPTIONAL logfile, save the output to file");
		helpFunctions.add("verbose	OPTIONAL 0: debug, 1:info, 2:notice, 3:warning, 4:error, default: info");
		helpFunctions.add("");
		helpFunctions.add("Note: 	If both time and packet are specified, only time will be used");
	}

	public void printUsageFunctions() {
		for(String s: helpFunctions) {
			System.out.println(s);
		}
	}

	public static HelpUtils getInstance() {
		return helpUtilsInstance;
	}
}
