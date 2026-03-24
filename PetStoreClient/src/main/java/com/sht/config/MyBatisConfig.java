package com.sht.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;

@org.springframework.context.annotation.Configuration
@MapperScan(basePackages = "com.sht.mapper")
@EnableTransactionManagement
@PropertySource("classpath:db.properties")
public class MyBatisConfig {

    @Value("${db.url}")
    private String url;

    @Value("${db.username}")
    private String username;

    @Value("${db.password}")
    private String password;

    @Value("${db.driverClassName}")
    private String driverClassName;

    @Value("${db.pool.initialSize}")
    private int initialSize;

    @Value("${db.pool.maxActive}")
    private int maxActive;

    @Value("${db.pool.maxWait}")
    private long maxWait;

    @Value("${db.pool.filters}")
    private String filters;

    // 1. 配置Druid数据源
    @Bean(name = "dataSource")
    public DataSource druidDataSource() throws SQLException {
        DruidDataSource ds = new DruidDataSource();
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        ds.setDriverClassName(driverClassName);
        ds.setInitialSize(initialSize);
        ds.setMaxActive(maxActive);
        ds.setMaxWait(maxWait);
        ds.setFilters(filters);
        return ds;
    }

    // 2. 配置SqlSessionFactory（MyBatis核心，替代mybatis-config.xml）
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource); // 关联数据源

        // MyBatis全局配置（替代mybatis-config.xml中的<configuration>）
        Configuration mybatisConfig = new Configuration();
        mybatisConfig.setMapUnderscoreToCamelCase(true); // 驼峰命名转换
        mybatisConfig.setLogImpl(org.apache.ibatis.logging.stdout.StdOutImpl.class); // 控制台打印SQL
        factoryBean.setConfiguration(mybatisConfig);

        // 实体类别名包（简化Mapper中类型写法）
        factoryBean.setTypeAliasesPackage("com.sht.pojo");

        // 加载 resources/mappers/ 下的所有 XML 映射文件
        factoryBean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath:mappers/*.xml")
        );

        return factoryBean.getObject();
    }

    // 3. 配置事务管理器（Service层@Transactional需要）
    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}