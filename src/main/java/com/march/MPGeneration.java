package com.march;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * @description: Mybatis Plus代码生成器
 * @author: tangcan
 * @create: 2018-11-27 15:08
 **/
 public class MPGeneration {

    public static void main(String[] args) {
        // 代码生成路径
        //String outPutDir = System.getProperty("user.dir") + "\\src\\main\\java";
        String outPutDir = "C:\\Users\\Demons\\Desktop";
        // 数据库地址
        String dbUrl = "jdbc:mysql://127.0.0.1:3306/exam_online?useSSL=false&useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai&allowMultiQueries=true&rewriteBatchedStatements=true";
        // 数据库账号
        String dbUsername = "root";
        // 数据库密码
        String dbPassword = "";
        // 添加表单前缀，不需要则留空
        String[] tablePrefix = {};
        // 需要生成代码的表单

        String[] tables = {"user", "exam", "question",
                "exam_question", "exam_record", "question_answer",
                "question_category", "question_option", "question_type",
                "record_answer", "record_option", "role"};
        //String[] tables = {"class", "class_stu"};
        //自动生成时，主键设置了自增长才会加上@TableId注解

        // 作者
        String author = "March";

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setSwagger2(true)
                .setOutputDir(outPutDir)
                .setFileOverride(false)      // 是否覆盖原文件
                .setActiveRecord(true)      // 不需要ActiveRecord特性的请改为false
                .setEnableCache(false)      // XML 二级缓存
                .setBaseResultMap(true)     // XML ResultMap
                .setBaseColumnList(false)   // XML columList
                .setControllerName("%sController")
                .setServiceName("%sService")
                .setServiceImplName("%sServiceImpl")
                .setMapperName("%sMapper")
                .setXmlName("%sMapper")
                .setAuthor(author);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.march")
                .setController("controller")
                .setService("service")
                .setServiceImpl("service.impl")
                .setMapper("dao")
                .setEntity("entity")
                .setXml("mapper");

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL)
                .setDriverName("com.mysql.cj.jdbc.Driver")
                .setUrl(dbUrl)
                .setUsername(dbUsername)
                .setPassword(dbPassword);


        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel)   // 表名生成策略
                // 需要生成的表
                .setInclude(tables)
                .setTablePrefix(tablePrefix)
                .setRestControllerStyle(true)
                .setEntityLombokModel(true);

        AutoGenerator mpg = new AutoGenerator();
        mpg.setPackageInfo(pc)
                .setGlobalConfig(gc)
                .setDataSource(dsc)
                .setStrategy(strategy);
        // 执行生成
        mpg.execute();
    }
}
