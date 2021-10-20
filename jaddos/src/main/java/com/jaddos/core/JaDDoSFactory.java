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

package com.jaddos.core;

import com.jaddos.protocol.JaDDoSTCPProtocol;
import com.jaddos.protocol.JaDDoSUDPProtocol;

/**
 * Factory class of DoS types
 * 
 * @author ShehanD
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
