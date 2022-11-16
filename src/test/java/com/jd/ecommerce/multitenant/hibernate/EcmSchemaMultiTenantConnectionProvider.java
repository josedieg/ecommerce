package com.jd.ecommerce.multitenant.hibernate;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.HibernateException;
import org.hibernate.engine.config.spi.ConfigurationService;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.hibernate.service.spi.ServiceRegistryAwareService;
import org.hibernate.service.spi.ServiceRegistryImplementor;

import com.mysql.cj.jdbc.MysqlDataSource;

public class EcmSchemaMultiTenantConnectionProvider
		implements MultiTenantConnectionProvider, ServiceRegistryAwareService {

	private static final long serialVersionUID = 1L;
	private static final String TENANT_SUPPORTED = "SCHEMA";
	private DataSource dataSource;
	private String typeTenancy;

	@Override
	public boolean supportsAggressiveRelease() {
		return false;
	}

	@Override
	public void injectServices(ServiceRegistryImplementor serviceRegistry) {
		typeTenancy = (String) ((ConfigurationService) serviceRegistry.getService(ConfigurationService.class))
				.getSettings().get("hibernate.multiTenancy");
		
		InputStream is = getClass().getClassLoader().getResourceAsStream("db.properties");
		
		this.dataSource = getMySQLDataSource(is);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean isUnwrappableAs(Class clazz) {
		return false;
	}

	@Override
	public <T> T unwrap(Class<T> clazz) {
		return null;
	}

	@Override
	public Connection getAnyConnection() throws SQLException {
		final Connection connection = dataSource.getConnection();
		return connection;
	}

	@Override
	public Connection getConnection(String tenantIdentifier) throws SQLException {
		if (TENANT_SUPPORTED.equals(typeTenancy)) {
			try {
				final Connection connection = getAnyConnection();
				connection.createStatement().execute("use " + tenantIdentifier);
				return connection;
			} catch (final SQLException e) {
				throw new HibernateException("Error trying to alter schema [" + tenantIdentifier + "]", e);
			}
		}

		return getAnyConnection();
	}

	@Override
	public void releaseAnyConnection(Connection connection) throws SQLException {
		connection.close();
	}

	private void resetConnection(Connection connection) {
		if (TENANT_SUPPORTED.equals(typeTenancy)) {
			try {
				connection.createStatement().execute("SET SCHEMA 'public'");
			} catch (final SQLException e) {
				throw new HibernateException("Error trying to alter schema [public]", e);
			}
		}
	}

	@Override
	public void releaseConnection(String tenantIdentifier, Connection connection) throws SQLException {
		releaseAnyConnection(connection);
	}
	
	public static DataSource getMySQLDataSource(InputStream is) {
		Properties props = new Properties();
		MysqlDataSource mysqlDS = null;
		try {
			props.load(is);
			mysqlDS = new MysqlDataSource();
			mysqlDS.setURL(props.getProperty("MYSQL_DB_URL"));
			mysqlDS.setUser(props.getProperty("MYSQL_DB_USERNAME"));
			mysqlDS.setPassword(props.getProperty("MYSQL_DB_PASSWORD"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mysqlDS;
	}
}