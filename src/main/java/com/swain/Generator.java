package com.swain;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;

import java.util.ArrayList;
import java.util.List;

public class Generator {

    public static final String LINK = "jdbc:mysql://106.13.87.50:3306/db_swain?useUnicode=true&characterEncoding=utf-8";
    public static final String ACCOUNT = "root";
    public static final String PASSWORD = "922900";

    public static String[] tables = {
            "machine",
            "product",
            "material",
            "user",
            "repair",
    };

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        String projectPath = System.getProperty("user.dir");
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(projectPath + "/src/main/java/")
                .setAuthor("Generator")
                .setOpen(false)
                .setFileOverride(true)
                // 不需要ActiveRecord特性的请改为false
                .setActiveRecord(true)
                // XML 二级缓存
                .setEnableCache(false)
                // XML ResultMap
                .setBaseResultMap(true)
                // XML columList
                .setBaseColumnList(true)
                .setMapperName("%sDao")
                .setServiceName("%sManager")
                .setServiceImplName("%sManagerImpl");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(LINK);
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername(ACCOUNT);
        dsc.setPassword(PASSWORD);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig packageConfig = new PackageConfig()
                .setParent("com.swain.core.dal")
                .setEntity("domain")
                .setMapper("dao")
                .setService("manager")
                .setServiceImpl("manager.impl");

        mpg.setPackageInfo(packageConfig);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig("/mybatis-templates/xDaoMapper.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return projectPath + "/src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper.xml";
            }
        });

        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        //模板设置
        TemplateConfig templateConfig = new TemplateConfig()
                .setEntity("/mybatis-templates/xDomain")
                .setMapper("/mybatis-templates/xDao")
                .setService("/mybatis-templates/xManager")
                .setServiceImpl("/mybatis-templates/xManagerImpl")
                .setXml(null)
                .setController(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setSuperEntityClass("com.baomidou.ant.common.BaseEntity");
        strategy.setEntityLombokModel(true);
        strategy.setInclude(tables);
        strategy.setSuperEntityColumns("id");
        strategy.setTablePrefix(packageConfig.getModuleName() + "_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new VelocityTemplateEngine());
        mpg.execute();
    }
}
