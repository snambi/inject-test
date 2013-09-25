package org.github.guice;

import com.google.inject.Binder;

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
    }
}
