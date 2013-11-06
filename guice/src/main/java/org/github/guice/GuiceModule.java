package org.github.guice;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.TypeLiteral;
import com.google.inject.matcher.Matcher;
import com.google.inject.spi.InjectionListener;
import com.google.inject.spi.TypeEncounter;
import com.google.inject.spi.TypeListener;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: nsankaran
 * Date: 9/24/13
 * Time: 9:25 PM
 */
@SuppressWarnings("restriction")
public class GuiceModule implements Module{
	
	static Set<Class> singletons = Collections.synchronizedSet( new HashSet<Class>() );


	public void configure(Binder binder) {
		
        binder.bind(IEmailer.class).to(EmailerImpl.class);
        binder.bind(INotifierService.class).to(NotifierServiceImpl.class);

        Matcher<Object> matcher = new Matcher<Object>() {
            public boolean matches(Object o) {
                boolean result = false;
                if( o != null ){
                	
                	if( o instanceof com.google.inject.TypeLiteral ){
                		TypeLiteral<Object> typeliteral = (TypeLiteral<Object>) o;
                		
                		Class clz = typeliteral.getRawType();
                		//System.out.println("matching " + typeliteral.getRawType() );
                		if( clz.isAnnotationPresent(Singleton.class) ){
                			System.out.println("found class with @singleton " + clz.getCanonicalName() );
                			result = true;
                		}
                	}
                }
                return result;
            }

			public Matcher<Object> and(Matcher<? super Object> other) {
				return null;
			}

			public Matcher<Object> or(Matcher<? super Object> other) {
				return null;
			}
        };

        TypeListener typeListener = new TypeListener() {
            public <I> void hear(TypeLiteral<I> type, TypeEncounter<I> encounter) {
            	if( Singleton.class.isAssignableFrom(type.getRawType()) ){
            		TypeEncounter<Singleton> singleton = (TypeEncounter<Singleton>) encounter;
            		System.out.println("type = " + type.toString() );
            	}
            	
            }
        };

        binder.bindListener( matcher, typeListener );
        
/*       
 * 		ProvisionListener provisionListener = new ProvisionListener() {
			
			public <T> void onProvision(ProvisionInvocation<T> provision) {
				System.out.println("provision = " + provision.provision() );
			}
		};
		
		binder.bindListener(matcher, provisionListener);*/
		
		InjectionListener<?> injectionListener = new InjectionListener<Object>() {

			public void afterInjection(Object injectee) {
				System.out.println("called after injection");
			}
		};
		binder.bindListener(matcher, new TypeListener() {
			
			public <I> void hear(TypeLiteral<I> type, TypeEncounter<I> encounter) {
				encounter.register(new InjectionListener() {

					public void afterInjection(Object injectee) {
						System.out.println("Called after injection "+ injectee.getClass().getCanonicalName());
						// call the method annotated with @PostConstruct
						Method[] methods = injectee.getClass().getMethods();
						if( methods != null && methods.length > 0 ){
							for( Method m : methods ){
								if( m.isAnnotationPresent((Class<? extends Annotation>) PostConstruct.class) ){
									try {
										m.invoke(injectee);
									} catch (IllegalArgumentException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} catch (IllegalAccessException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} catch (InvocationTargetException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
							}
						}
					}
				});
			}
		});
	}
}
