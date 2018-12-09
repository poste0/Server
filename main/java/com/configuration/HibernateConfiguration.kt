package com.configuration;

import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.Database
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import java.util.*
import javax.persistence.EntityManagerFactory
import javax.sql.DataSource

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = ["com"])
open class HibernateConfiguration(private val environment: Environment) {

    @Bean
    open fun dataSource(): DataSource {
        return DataSourceBuilder
                .create()
                .url(environment.getProperty("spring.datasource.url"))
                .driverClassName("org.postgresql.Driver")
                .username(environment.getProperty("spring.datasource.username"))
                .password(environment.getProperty("spring.datasource.password"))
                .build()
    }

    @Bean
    open fun exceptionTranslation(): PersistenceExceptionTranslationPostProcessor {
        return PersistenceExceptionTranslationPostProcessor()
    }

    @Bean
    open fun entityManagerFactory(): LocalContainerEntityManagerFactoryBean {

        val vendorAdapter = HibernateJpaVendorAdapter()
        vendorAdapter.setDatabase(Database.POSTGRESQL)
        vendorAdapter.setGenerateDdl(false)

        val em = LocalContainerEntityManagerFactoryBean()
        em.dataSource = dataSource()
        em.setPackagesToScan("com.entities")
        em.jpaVendorAdapter = vendorAdapter
        em.setJpaProperties(hibernateProperties())

        return em
    }

    @Bean
    open fun transactionManager(emf: EntityManagerFactory): PlatformTransactionManager {
        val jpaTransactionManager = JpaTransactionManager()
        jpaTransactionManager.entityManagerFactory = emf
        return jpaTransactionManager
    }

    private fun hibernateProperties(): Properties {
        val properties = Properties()
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL9Dialect")
        properties.setProperty("hibernate.connection.pool_size", "10")
        properties.setProperty("hibernate.show_sql", "true")
        return properties
    }
}
