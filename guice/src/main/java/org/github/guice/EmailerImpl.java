package org.github.guice;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Named;
import javax.inject.Singleton;

@Singleton
@Named
public class EmailerImpl implements IEmailer {

    private String version;

    @PostConstruct
    public void startup(){
        System.out.println("starting " + EmailerImpl.class.getCanonicalName());
    }

    @PreDestroy
    public void shutdown(){
        System.out.println("shutting down " + EmailerImpl.class.getCanonicalName());
    }

	public void send(String msg) {
		System.out.println("Sending mail [");
		System.out.println(msg);
		System.out.println("]");
	}

}
