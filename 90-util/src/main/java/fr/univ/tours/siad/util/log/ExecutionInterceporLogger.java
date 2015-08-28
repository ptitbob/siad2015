package fr.univ.tours.siad.util.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

/**
 * Created by francois on 10/06/15.
 */
@Interceptor @Traceable
@Priority(Interceptor.Priority.LIBRARY_BEFORE + 10)
public class ExecutionInterceporLogger {

    private static final Logger LOGGER_EXECUTION_TIME = LogManager.getLogger("EXECUTION");
    private static final Logger LOGGER = LogManager.getLogger(ExecutionInterceporLogger.class);

    @AroundInvoke
    public Object executionLog(InvocationContext invocationContext) throws Exception {
        if (LOGGER_EXECUTION_TIME.isDebugEnabled()) {
            LOGGER.entry(invocationContext.getTarget().toString(), invocationContext.getMethod().getName());
            LOGGER_EXECUTION_TIME.trace("[BEGIN]\t" + invocationContext.getTarget().getClass().getSimpleName() + "." + invocationContext.getMethod().getName());
            long time = System.currentTimeMillis();
            try {
                return invocationContext.proceed();
            } finally {
                time = System.currentTimeMillis() - time;
                LOGGER_EXECUTION_TIME.trace("[END]  \t" + invocationContext.getTarget().getClass().getSimpleName() + "." + invocationContext.getMethod().getName() + "\t(execution: " + time + " ms)");
                LOGGER.exit();
            }
        } else {
            return invocationContext.proceed();
        }
    }

}
