package org.github.spring;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Named;
import javax.inject.Singleton;

@Singleton
@Named
public class EmailerImpl implements IEmailer {
	
	@PostConstruct
	public void startup(){
		System.out.println("starting " + EmailerImpl.class.getCanonicalName());
	}
	
	@PreDestroy
	public void shutdown(){
		System.out.println("shutting down " + EmailerImpl.class.getCanonicalName());
	}

	public void send(String msg) {
		System.out.print("Sending mail [");
		System.out.print(msg);
		System.out.println("]");
	}

}
