package org.github.guice;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

@Singleton
@Named
public class NotifierServiceImpl implements INotifierService {

	private IEmailer service;

    @PostConstruct
    public void startup(){
        System.out.println("starting " + NotifierServiceImpl.class.getCanonicalName());
    }

    @PreDestroy
    public void shutdown(){
        System.out.println("shutting down " + NotifierServiceImpl.class.getCanonicalName());
    }
	
	@Inject
	public NotifierServiceImpl( IEmailer emailer ){
		this.service = emailer;
	}
	
	public void notify(String msg) {
		service.send(msg);
	}
	
	public static void main(String[] args){
		System.out.println("hello world!");
	}

}
