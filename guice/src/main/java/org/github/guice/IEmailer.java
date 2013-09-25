package org.github.guice;

//@ImplementedBy(EmailerImpl.class)
public interface IEmailer {
	
	public void send( String msg );

}
