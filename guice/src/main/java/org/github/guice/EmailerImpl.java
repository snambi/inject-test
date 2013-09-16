package org.github.guice;

import javax.inject.Singleton;


public class EmailerImpl implements IEmailer {

	public void send(String msg) {
		System.out.println("Sending mail [");
		System.out.println(msg);
		System.out.println("]");
	}

}
