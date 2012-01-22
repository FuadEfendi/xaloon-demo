package org.xaloon.wicket.demo.init;

import javax.inject.Inject;
import javax.interceptor.Interceptor;

import org.xaloon.core.api.classifier.dao.ClassifierDao;
import org.xaloon.core.api.interceptor.AbstractInterceptor;
import org.xaloon.core.api.interceptor.Signature;

/**
 * @author vytautas r.
 */
@Logged
@Interceptor
public class LoggingInterceptor extends AbstractInterceptor<Logged> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private ClassifierDao classifierDao;

	@Override
	protected Class<Logged> getAnnotationClass() {
		return Logged.class;
	}

	@Override
	protected void beforeProceed(Signature signature, Logged annotation) {
		System.out.println(annotation + "\t" + signature + "\t" + signature.getMethodName());
		System.out.println("Just check if inject is working. Total classifiers: " + classifierDao.getCount());
		System.out.println(String.format("This works in both: Java EE and Spring 3.x: %s, %s, %s. %s", 
			signature.getMethodName(), signature.getDeclaringType().toString(), signature.getDeclaringTypeName(), signature.getParameters()));
	}

	

}