package org.github.guice;

import javax.inject.Inject;

public class NotifierServiceImpl implements INotifierService {

	private IEmailer service;
	
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
