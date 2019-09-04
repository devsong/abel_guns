package com.stylefeng.guns.core.shiro;

import org.apache.shiro.util.Destroyable;
import org.apache.shiro.util.Initializable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

@Component
public class LifecycleBeanPostProcessor implements DestructionAwareBeanPostProcessor, PriorityOrdered {
    /**
     * Private internal class log instance.
     */
    private static final Logger log = LoggerFactory.getLogger(LifecycleBeanPostProcessor.class);

    /**
     * Order value of this BeanPostProcessor.
     */
    private int order;

    /**
     * Default Constructor.
     */
    public LifecycleBeanPostProcessor() {
        this(LOWEST_PRECEDENCE);
    }

    /**
     * Constructor with definable {@link #getOrder() order value}.
     *
     * @param order order value of this BeanPostProcessor.
     */
    public LifecycleBeanPostProcessor(int order) {
        this.order = order;
    }

    /**
     * Calls the <tt>init()</tt> methods on the bean if it implements {@link org.apache.shiro.util.Initializable}
     *
     * @param object the object being initialized.
     * @param name   the name of the bean being initialized.
     * @return the initialized bean.
     * @throws BeansException if any exception is thrown during initialization.
     */
    public Object postProcessBeforeInitialization(Object object, String name) throws BeansException {
        if (object instanceof Initializable) {
            try {
                if (log.isDebugEnabled()) {
                    log.debug("Initializing bean [" + name + "]...");
                }

                ((Initializable) object).init();
            } catch (Exception e) {
                throw new FatalBeanException("Error initializing bean [" + name + "]", e);
            }
        }
        return object;
    }

    /**
     * Does nothing - merely returns the object argument immediately.
     */
    public Object postProcessAfterInitialization(Object object, String name) throws BeansException {
        // Does nothing after initialization
        return object;
    }

    /**
     * Calls the <tt>destroy()</tt> methods on the bean if it implements {@link org.apache.shiro.util.Destroyable}
     *
     * @param object the object being initialized.
     * @param name   the name of the bean being initialized.
     * @throws BeansException if any exception is thrown during initialization.
     */
    public void postProcessBeforeDestruction(Object object, String name) throws BeansException {
        if (object instanceof Destroyable) {
            try {
                if (log.isDebugEnabled()) {
                    log.debug("Destroying bean [" + name + "]...");
                }

                ((Destroyable) object).destroy();
            } catch (Exception e) {
                throw new FatalBeanException("Error destroying bean [" + name + "]", e);
            }
        }
    }

    /**
     * Order value of this BeanPostProcessor.
     *
     * @return order value.
     */
    public int getOrder() {
        // LifecycleBeanPostProcessor needs Order. See https://issues.apache.org/jira/browse/SHIRO-222
        return order;
    }

    /**
     * Return true only if <code>bean</code> implements Destroyable.
     * @param bean bean to check if requires destruction.
     * @return true only if <code>bean</code> implements Destroyable.
     * @since 1.4
     */
    public boolean requiresDestruction(Object bean) {
        return (bean instanceof Destroyable);
    }
}
