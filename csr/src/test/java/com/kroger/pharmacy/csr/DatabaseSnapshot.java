package com.kroger.pharmacy.csr;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dbunit.Assertion;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.DatabaseDataSourceConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.SortedTable;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * The DatabaseSnapshot class is a utility dbunit class to load and unload data
 * into the database. Customize to the applications needs.
 */
public class DatabaseSnapshot
{
    /** The LOG. */
    public static Log          LOG                = LogFactory
                                                      .getLog("com.kroger.databasesnapshot");

    /** The Constant DATASOURCE_CONTEXT. */
    public static final String DATASOURCE_CONTEXT = "applicationContext-dataSource-test.xml";

    /**
     * The main program saves snapshot.
     * 
     * @param args the args
     */
    public static void main(String[] args)
    {
        try
        {
            String applicationContext = DATASOURCE_CONTEXT;
            if (args.length == 1)
                applicationContext = args[0];
            DatabaseSnapshot databaseSnapshot = new DatabaseSnapshot(
                applicationContext);
            databaseSnapshot.saveDatabaseSnapshot();
            LOG.info("Complete.");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /** The data source. */
    private final DataSource dataSource;

    /** The connection. */
    private final Connection connection;

    /**
     * Instantiates a new database snapshot.
     */
    public DatabaseSnapshot()
    {
        this(DATASOURCE_CONTEXT);
    }

    /**
     * Instantiates a new database snapshot.
     * 
     * @param applicationContext the applicationContext filename
     */
    public DatabaseSnapshot(String applicationContext)
    {
        ApplicationContext ctx = new ClassPathXmlApplicationContext(
            applicationContext);
        DataSource dataSource = (DataSource) ctx.getBean("dataSource");
        this.dataSource = dataSource;
        this.connection = null;
    }

    /**
     * Instantiates a new database snapshot.
     * 
     * @param dataSource the data source
     */
    public DatabaseSnapshot(DataSource dataSource)
    {
        this.dataSource = dataSource;
        this.connection = null;
    }

    /**
     * Instantiates a new database snapshot.
     * 
     * @param connection the database connection
     */
    public DatabaseSnapshot(Connection connection)
    {
        this.dataSource = null;
        this.connection = connection;
    }

    /**
     * Get the DbUnit Connection to the database.
     * 
     * @return the database connection
     * 
     * @throws SQLException the SQL exception
     */
    private IDatabaseConnection getDatabaseConnection() throws SQLException
    {
        IDatabaseConnection connection = null;
        if (dataSource != null)
        {
            connection = new DatabaseDataSourceConnection(dataSource);
        }
        else
        {
            connection = new DatabaseConnection(this.connection);
        }
        return connection;
    }

    /**
     * Save a snapshot of the database to a set of files.
     * 
     * @throws SQLException *
     * @throws DataSetException *
     * @throws FileNotFoundException *
     * @throws IOException *
     * @throws Exception the exception
     */
    public void saveDatabaseSnapshot() throws Exception
    {
        // saveQuery(
        // "facgenrl",
        // "FACGENRL",
        // "SELECT * FROM FACGENRL WHERE MGT_DIV_NO IN ('060')", false);

        saveTable("preference", "PREFERENCE", false);
        saveTable("preference_value", "PREFERENCE_VALUE", false);
    }

    /**
     * Save table.
     * 
     * @param filename the filename
     * @param tableName the table name
     * @param isCompressed the compressed flag
     * 
     * @throws Exception the exception
     */
    public void saveTable(String filename, String tableName,
        boolean isCompressed) throws Exception
    {
        LOG.info("Saving file: "
            + filename);
        IDatabaseConnection connection = getDatabaseConnection();
        QueryDataSet dataSet = new QueryDataSet(connection);
        dataSet.addTable(tableName);
        OutputStream out = getOutputStream(filename, isCompressed);
        FlatXmlDataSet.write(dataSet, out);
        out.close();
    }

    /**
     * Save query.
     * 
     * @param filename the filename
     * @param tableName the table name
     * @param query the query
     * @param isCompressed the compressed flag
     * 
     * @throws Exception the exception
     */
    public void saveQuery(String filename, String tableName, String query,
        boolean isCompressed) throws Exception
    {
        LOG.info("Saving query: "
            + filename);
        IDatabaseConnection connection = getDatabaseConnection();
        QueryDataSet dataSet = new QueryDataSet(connection);
        dataSet.addTable(tableName, query);
        OutputStream out = getOutputStream(filename, isCompressed);
        FlatXmlDataSet.write(dataSet, out);
        out.close();
    }

    /**
     * Gets the output stream.
     * 
     * @param filename the filename
     * @param isCompressed the compressed flag
     * 
     * @return the output stream
     * 
     * @throws Exception the exception
     */
    private OutputStream getOutputStream(String filename, boolean isCompressed)
        throws Exception
    {
        OutputStream out = null;
        if (isCompressed)
        {
            out = new BufferedOutputStream(new GZIPOutputStream(

            new FileOutputStream("src/test/resources/dbunit/"
                + filename + ".zml")));
        }
        else
        {
            out = new BufferedOutputStream(new FileOutputStream(
                "src/test/resources/dbunit/"
                    + filename + ".xml"));
        }
        return out;
    }

    /**
     * Restore all the files.
     * 
     * @throws Exception the exception
     */
    public void restoreDatabaseSnapshot() throws Exception
    {
        restoreFile("preference", false);
        restoreFile("preference_value", false);
    }

    /**
     * Restore file.
     * 
     * @param filename the filename
     * @param isCompressed the compressed flag
     * 
     * @throws Exception the exception
     */
    public void restoreFile(String filename, boolean isCompressed)
        throws Exception
    {
        LOG.info("Restoring file: "
            + filename);
        IDatabaseConnection connection = getDatabaseConnection();
        InputStream in = getInputStream(filename, isCompressed);
        IDataSet dataSet = new FlatXmlDataSet(in);
        try
        {
            DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);
        }
        finally
        {
            connection.close();
            in.close();
        }
    }

    /**
     * Refresh all the files.
     * 
     * @throws Exception the exception
     */
    public void refreshDatabaseSnapshot() throws Exception
    {
        refreshFile("preference", false);
        refreshFile("preference_value", false);
    }

    /**
     * Restore file.
     * 
     * @param filename the filename
     * @param isCompressed the compressed flag
     * 
     * @throws Exception the exception
     */
    public void refreshFile(String filename, boolean isCompressed)
        throws Exception
    {
        LOG.info("Refreshing file: "
            + filename);
        IDatabaseConnection connection = getDatabaseConnection();
        InputStream in = getInputStream(filename, isCompressed);
        IDataSet dataSet = new FlatXmlDataSet(in);
        try
        {
            DatabaseOperation.REFRESH.execute(connection, dataSet);
        }
        finally
        {
            connection.close();
            in.close();
        }
    }

    /**
     * Test if table equals file.
     * 
     * @param tableName the table name
     * @param filename the filename
     * @param isCompressed the compressed flag
     * 
     * @throws Exception the exception
     */
    public void assertTableEquals(String tableName, String filename,
        boolean isCompressed) throws Exception
    {
        LOG.info("Testing against file: "
            + filename);

        // Fetch database data after executing your code
        IDatabaseConnection connection = getDatabaseConnection();
        IDataSet databaseDataSet = connection.createDataSet();
        ITable actualTable = databaseDataSet.getTable(tableName);

        // Load expected data from an XML dataset
        IDataSet expectedDataSet = new FlatXmlDataSet(getInputStream(filename,
            isCompressed));
        ITable expectedTable = expectedDataSet.getTable(tableName);

        // Assert actual database table match expected table
        Assertion.assertEquals(new SortedTable(expectedTable), new SortedTable(
            actualTable));
    }

    /**
     * Gets the input stream.
     * 
     * @param filename the filename
     * @param isCompressed the compressed flag
     * 
     * @return the input stream
     * 
     * @throws Exception the exception
     */
    private BufferedInputStream getInputStream(String filename,
        boolean isCompressed) throws Exception
    {
        BufferedInputStream in = null;
        if (isCompressed)
        {
            in = new BufferedInputStream(new GZIPInputStream(
                new FileInputStream("src/test/resources/dbunit/"
                    + filename + ".zml")));
        }
        else
        {
            in = new BufferedInputStream(new FileInputStream(
                "src/test/resources/dbunit/"
                    + filename + ".xml"));
        }
        return in;
    }
}
