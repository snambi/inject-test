package org.github.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class GuiceBootstrap {
	
	public static void main( String[] args ){
		
		Injector injector = Guice.createInjector();
		INotifierService notifier = injector.getInstance(NotifierServiceImpl.class);
		
		notifier.notify("hello world");
	}

}
