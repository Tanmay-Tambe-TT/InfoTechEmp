package multiple.db.h2.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactoryH2DB", basePackages = {
		"multiple.db.h2.h2dbrepo" }, transactionManagerRef = "transactionManagerH2DB")
public class H2Config {

	@Autowired
	Environment environment;

	// datasource
	@Primary
	@Bean(name = "empDataSource")
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl(environment.getProperty("spring.first.datasource.url"));
		dataSource.setDriverClassName(environment.getProperty("spring.first.datasource.driver-class-name"));
		dataSource.setUsername(environment.getProperty("spring.first.datasource.username"));
		dataSource.setPassword(environment.getProperty("spring.first.datasource.password"));
		return dataSource;
	}

	// entityManageFactory
	@Primary
	@Bean(name = "entityManagerFactoryH2DB")
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryH2DB() {
		LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
		bean.setDataSource(dataSource());
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		bean.setJpaVendorAdapter(vendorAdapter);
		Map<String, String> props = new HashMap<>();
		props.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		props.put("hibernate.show_sql", "true");
		props.put("hibernate.hbm2ddl.auto", "update");
		bean.setJpaPropertyMap(props);
		bean.setPackagesToScan("multiple.db.h2.h2dbentity");
		System.out.println("Hello....H2..Database");
		return bean;
	}

	@Primary
	@Bean(name = "transactionManagerH2DB")
	public PlatformTransactionManager transactionManagerH2DB() {
		JpaTransactionManager manager = new JpaTransactionManager();
		manager.setEntityManagerFactory(entityManagerFactoryH2DB().getObject());
		return manager;
	}

}
