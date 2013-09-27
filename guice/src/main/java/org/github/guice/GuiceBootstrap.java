package org.github.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.spi.InjectionListener;

public class GuiceBootstrap {
	
	public static void main( String[] args ){

        GuiceModule module = new GuiceModule();
		Injector injector = Guice.createInjector(module);
		
		

		
		

		INotifierService notifier = injector.getInstance(NotifierServiceImpl.class);
		notifier.notify("hello world");
		
	}
}
