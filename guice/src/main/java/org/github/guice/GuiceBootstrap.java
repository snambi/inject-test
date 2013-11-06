package org.github.guice;

import com.google.inject.Binding;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.TypeLiteral;
import com.google.inject.spi.InjectionListener;
import com.google.inject.spi.TypeConverterBinding;

import javax.inject.Singleton;

public class GuiceBootstrap {
	
	public static void main( String[] args ){

        GuiceModule module = new GuiceModule();
		Injector injector = Guice.createInjector(module);

        System.out.println("\n\nguice injector ready\n\n");


		INotifierService notifier = injector.getInstance(NotifierServiceImpl.class);
		notifier.notify("hello world");
		
	}
}
