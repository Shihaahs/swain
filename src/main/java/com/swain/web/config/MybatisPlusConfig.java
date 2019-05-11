/** 
 * All rights Reserved, Designed By www.maihaoche.com
 *
 * @Package com.mhc.pandora.core.base.config
 * @author: 三帝（sandi@maihaoche.com）
 * @date: 2018-12-18 11:00:45
 * @Copyright: 2017-2020 www.maihaoche.com Inc. All rights reserved.
 * 注意：本内容仅限于卖好车内部传阅，禁止外泄以及用于其他的商业目
 */
package com.swain.web.config;

import com.baomidou.mybatisplus.mapper.ISqlInjector;
import com.baomidou.mybatisplus.mapper.LogicSqlInjector;
import com.baomidou.mybatisplus.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@MapperScan("com.swain.dal.dao*")
public class MybatisPlusConfig {
	/**
	 * mybatis-plus SQL执行效率插件【生产环境可以关闭】
	 */
	@Bean
	public PerformanceInterceptor performanceInterceptor() {
		return new PerformanceInterceptor();
	}

	/**
	 * mybatis-plus分页插件<br>
	 * 文档：http://mp.baomidou.com<br>
	 */
	@Bean
	public PaginationInterceptor paginationInterceptor() {
		PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
		// 开启 PageHelper 的支持
		paginationInterceptor.setLocalPage(true);
		return paginationInterceptor;
	}

	/**  
	 * <p> 乐观锁插件配置 </p>
	 * 
	 * @return
	 */
	@Bean
	public OptimisticLockerInterceptor optimisticLockerInterceptor() {
		return new OptimisticLockerInterceptor();
	}

	/**
     * 注入sql注入器
     */
    @Bean
    public ISqlInjector sqlInjector(){
        return new LogicSqlInjector();
    }

}
