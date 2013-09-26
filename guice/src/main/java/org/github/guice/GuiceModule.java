package org.github.guice;

import com.google.inject.Binder;
import com.google.inject.TypeLiteral;
import com.google.inject.matcher.Matcher;
import com.google.inject.spi.TypeEncounter;
import com.google.inject.spi.TypeListener;

import javax.inject.Singleton;

/**
 * Created with IntelliJ IDEA.
 * User: nsankaran
 * Date: 9/24/13
 * Time: 9:25 PM
 */
public class GuiceModule implements com.google.inject.Module{

    @Override
    public void configure(Binder binder) {
        binder.bind(IEmailer.class).to(EmailerImpl.class);
        binder.bind(INotifierService.class).to(NotifierServiceImpl.class);

        Matcher matcher = new Matcher() {
            @Override
            public boolean matches(Object o) {
                boolean result = false;
                if( o != null ){
                    if( o.getClass().isAnnotationPresent(Singleton.class)){
                        result = true;
                    }
                }
                return result;
            }

            @Override
            public Matcher and(Matcher other) {
                return null;
            }

            @Override
            public Matcher or(Matcher other) {
                return null;
            }
        };

        TypeListener typeListener = new TypeListener() {
            @Override
            public <I> void hear(TypeLiteral<I> type, TypeEncounter<I> encounter) {

            }
        };

        binder.bindListener( matcher, typeListener );
    }
}
