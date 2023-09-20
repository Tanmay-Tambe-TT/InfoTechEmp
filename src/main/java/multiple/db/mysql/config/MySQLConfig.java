package multiple.db.mysql.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactoryMySQL", basePackages = {
		"multiple.db.mysql.mysqlrepo" }, transactionManagerRef = "transactionManagerMySQL")
public class MySQLConfig {

	@Autowired
	private Environment environment;

	// datasource
	@Primary
	@Bean(name = "mySQLdataSource")
	public DataSource mySQLdataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl(environment.getProperty("spring.datasource.url"));
		dataSource.setDriverClassName(environment.getProperty("spring.datasource.driver-class-name"));
		dataSource.setUsername(environment.getProperty("spring.datasource.username"));
		dataSource.setPassword(environment.getProperty("spring.datasource.password"));
		return dataSource;
	}

	// entityManageFactory
	@Primary
	@Bean(name = "entityManagerFactoryMySQL")
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryMySQL() {
		LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
		bean.setDataSource(mySQLdataSource());
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		bean.setJpaVendorAdapter(vendorAdapter);
		Map<String, String> props = new HashMap<>();
		props.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		props.put("hibernate.show_sql", "true");
		props.put("hibernate.hbm2ddl.auto", "update");
		bean.setJpaPropertyMap(props);
		bean.setPackagesToScan("multiple.db.mysql.mysqlentity");
		System.out.println("helloMYsql");
		return bean;

	}

	@Primary
	@Bean(name = "transactionManagerMySQL")
	public PlatformTransactionManager transactionManagerMySQL() {
		System.out.println("helloMYsql2");
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactoryMySQL().getObject());
		return transactionManager;
	}

}
