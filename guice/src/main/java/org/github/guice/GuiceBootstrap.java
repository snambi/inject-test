package org.github.guice;

import com.google.inject.Binding;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;

public class GuiceBootstrap {
	
	public static void main( String[] args ){

        GuiceModule module = new GuiceModule();
		//Injector injector = Guice.createInjector(module);
        //System.out.println("\n\nguice injector ready\n\n");


		Injector injector = Guice.createInjector(Stage.PRODUCTION, module);
		
		INotifierService notifier = injector.getInstance(NotifierServiceImpl.class);
		notifier.notify("hello world");
	}
}
