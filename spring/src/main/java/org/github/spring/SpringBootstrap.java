package org.github.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class SpringBootstrap {
	
	public static void main( String[] args ){
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("org.github");
		context.refresh();
		
		IEmailer emailer = context.getBean(IEmailer.class);
		emailer.send("sending email");
		
		INotifierService notifier = context.getBean(INotifierService.class);
		notifier.notify("sending notification");
		
		context.destroy();
	}

}
