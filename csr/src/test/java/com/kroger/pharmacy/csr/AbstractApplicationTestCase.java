package com.kroger.pharmacy.csr;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import junit.framework.Assert;

import org.junit.Before;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.kroger.commons.security.spoofing.SpoofingUtil;
import com.kroger.pharmacy.csr.service.ICsrService;
import com.kroger.pharmacy.csr.service.IReportService;

/**
 * A base class to run transactional test cases. Please note that this class has
 * annotations and method that make the SpringFramework run JUnit Test Cases.
 * When Spring 2.1 is released, the annotations and method will no longer be
 * needed.
 */
@ContextConfiguration(locations = {
    "classpath:applicationContext-dataSource-test.xml",
    "classpath:applicationContext-entityManager-test.xml",
    "classpath:applicationContext-siteMinderSecurity.xml",
    "classpath:applicationContext-csr.xml"})
public abstract class AbstractApplicationTestCase
    extends AbstractTransactionalJUnit4SpringContextTests
{
    /** The database snapshot. */
    protected static DatabaseSnapshot databaseSnapshot;

    /** The data source. */
    protected DataSource              dataSource;

    /**
     * Sets the data source.
     * 
     * @param dataSource the new data source
     */
    public void setDataSource(DataSource dataSource)
    {
        databaseSnapshot = new DatabaseSnapshot(dataSource);
        this.dataSource = dataSource;
    }

    /** The entity manager factory. */
    protected EntityManagerFactory entityManagerFactory;

    /**
     * Sets the entity manager factory.
     * 
     * @param emf the new entity manager factory
     */
    public void setEntityManagerFactory(EntityManagerFactory emf)
    {
        this.entityManagerFactory = emf;
    }

    @Before
    public void allowSpoofing()
    {
        SpoofingUtil.setSpoofingAllowedForTesting(true);
    }
    
    protected IReportService reportService;
    protected ICsrService csrService;
    
    @Before
    public void getBeans() {
    	reportService = (IReportService)applicationContext.getBean("reportService");
    	csrService = (ICsrService)applicationContext.getBean("csrService");
    	
    	Assert.assertNotNull(reportService);
		Assert.assertNotNull(csrService);
    }
}
