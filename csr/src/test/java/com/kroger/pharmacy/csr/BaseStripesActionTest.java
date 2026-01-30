package com.kroger.pharmacy.csr;

import java.util.HashMap;
import java.util.Map;

import org.junit.BeforeClass;

import net.sf.ehcache.CacheManager;
import net.sourceforge.stripes.controller.DispatcherServlet;
import net.sourceforge.stripes.controller.StripesFilter;
import net.sourceforge.stripes.mock.MockServletContext;

/**
 * Base class for Stripes Test Cases
 */
public abstract class BaseStripesActionTest extends AbstractApplicationTestCase
{
    /** The mock servlet context. */
    protected static MockServletContext mockServletContext;

    /**
     * Sets the up mock servlet context.
     */
    @BeforeClass
    public static void setUpMockServletContext()
    {
        mockServletContext = new MockServletContext("test");

        // Add the Stripes Filter
        Map<String, String> filterParams = new HashMap<String, String>();
        filterParams.put("ActionResolver.Packages",
        		 "com.kroger.FIXME,com.kroger.commons.web.stripes.");
        filterParams.put("ActionBeanContext.Class",
            "com.kroger.commons.web.stripes.ActionBeanContext");
        filterParams
            .put(
                "Interceptor.Classes",
                "com.kroger.commons.web.stripes.SpringTestInterceptor,"
                    + "net.sourceforge.stripes.controller.BeforeAfterMethodInterceptor,"
                    + "com.kroger.commons.web.stripes.ValidationErrorMessageInterceptor");
        mockServletContext.addFilter(StripesFilter.class, "StripesFilter",
            filterParams);

        // Add the Stripes Dispatcher
        mockServletContext.setServlet(DispatcherServlet.class,
            "StripesDispatcher", null);
    }

    static protected void clearCaches()
    {
        for (Object mgr : CacheManager.ALL_CACHE_MANAGERS)
        {
            ((CacheManager)mgr).clearAll();
        }
    }

}
