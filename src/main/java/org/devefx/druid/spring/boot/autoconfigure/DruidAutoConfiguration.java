/*
 * Copyright 2016-2017, Youqian Yue (devefx@163.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.devefx.druid.spring.boot.autoconfigure;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
@EnableConfigurationProperties(DruidProperties.class)
@AutoConfigureBefore(DataSourceAutoConfiguration.class)
public class DruidAutoConfiguration {
	
	private final DruidProperties properties;
	
	public DruidAutoConfiguration(DruidProperties properties) {
		this.properties = properties;
	}
	
	@Bean
	public DataSource dataSource() throws SQLException {
		DruidDataSource ds = new DruidDataSource();
		ds.setName(properties.getName());
		ds.setUrl(properties.getUrl());
		ds.setUsername(properties.getUsername());
		ds.setPassword(properties.getPassword());
		ds.setDriverClassName(properties.getDriverClassName());
		ds.setInitialSize(properties.getInitialSize());
		ds.setMaxActive(properties.getMaxActive());
		ds.setMaxWait(properties.getMaxWait());
		ds.setPoolPreparedStatements(properties.isPoolPreparedStatements());
		ds.setMaxPoolPreparedStatementPerConnectionSize(properties.getMaxPoolPreparedStatementPerConnectionSize());
		ds.setValidationQuery(properties.getValidationQuery());
		ds.setValidationQueryTimeout(properties.getValidationQueryTimeout());
		ds.setTestOnBorrow(properties.isTestOnBorrow());
		ds.setTestOnReturn(properties.isTestOnReturn());
		ds.setTestWhileIdle(properties.isTestWhileIdle());
		ds.setKeepAlive(properties.isKeepAlive());
		ds.setTimeBetweenEvictionRunsMillis(properties.getTimeBetweenEvictionRunsMillis());	
		ds.setMinEvictableIdleTimeMillis(properties.getMinEvictableIdleTimeMillis());
		ds.setConnectionInitSqls(properties.getConnectionInitSqls());
		ds.setExceptionSorter(properties.getExceptionSorter());
		ds.setFilters(properties.getFilters());
		if (properties.getConnectionProperties() != null) {
			ds.setConnectionProperties(properties.getConnectionProperties());
		}
		if (properties.getConnectProperties() != null) {
			ds.setConnectProperties(properties.getConnectProperties());
		}
		
		ds.init();
		return ds;
	}
}
