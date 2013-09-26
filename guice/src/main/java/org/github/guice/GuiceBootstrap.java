package org.github.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class GuiceBootstrap {
	
	public static void main( String[] args ){

        GuiceModule module = new GuiceModule();
		Injector injector = Guice.createInjector(module);

		INotifierService notifier = injector.getInstance(NotifierServiceImpl.class);
		notifier.notify("hello world");

        String t1 = "hello";
        String t2 = "hello";
        String t3 = t1;

        System.out.println("t1 " + t1.hashCode());
        System.out.println("t2 " + t2.hashCode());
        System.out.println("t3 " + t3.hashCode());

        t3 = "hello2";
        System.out.println("t3 " + t3.hashCode());
	}

}
