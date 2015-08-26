package fr.univ.tours.siad.util.log;

import org.slf4j.Logger;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

/**
 * Created by francois on 10/06/15.
 */
@Interceptor @Traceable
public class ExecutionInterceporLogger {

    @Inject
    private Logger logger;

    @AroundInvoke
    public Object executionLog(InvocationContext invocationContext) throws Exception {
        if (logger.isTraceEnabled()) {
            logger.trace("[BEGIN]\t" + invocationContext.getTarget().getClass().getSimpleName() + "." + invocationContext.getMethod().getName());
            long time = System.currentTimeMillis();
            try {
                return invocationContext.proceed();
            } finally {
                time = System.currentTimeMillis() - time;
                logger.trace("[END]  \t" + invocationContext.getTarget().getClass().getSimpleName() + "." + invocationContext.getMethod().getName() + "\t(execution: " + time + " ms)");
            }
        } else {
            return invocationContext.proceed();
        }
    }

}
