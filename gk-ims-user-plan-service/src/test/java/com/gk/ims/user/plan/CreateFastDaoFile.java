package com.gk.ims.user.plan;

import com.fast.code.TableFileCreateUtils;
import com.fast.code.bean.FileCreateConfig;

/**
 * 文件创建
 */
public class CreateFastDaoFile {
    public static void main(String[] args) {
        FileCreateConfig config = new FileCreateConfig();
        //数据库连接
        config.setDBInfo("jdbc:mysql://kaifa.mysql.guo-kai.com:3306/gk-ims?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai&useInformationSchema=true","gkims-kaifa","PGrsByizeD357ajR","com.mysql.cj.jdbc.Driver");
        //选择生成的文件
        config.setNeedModules(FileCreateConfig.CodeCreateModule.Base);
        //是否生成表前缀
        config.setPrefix(false,false,null);
        //是否使用lombok插件
        config.setUseLombok(true);
        //是否下划线转大小写,默认true
        config.setUnderline2CamelStr(true);
        config.setUseDTOSwagger2(true);
        //是否覆盖原文件,默认false
        config.setReplaceFile(true);
        //文件生成的包路径
        config.setBasePackage("com.gk.ims.user.plan");
        //项目多模块空间
        config.setChildModuleName("gk-ims-user-plan-service");
        //需要生成的表名 (可选值,具体表名"tab1","tab2"或all)
        config.setCreateTables("search_datasource","search_datasource_relation");
        //生成代码
        TableFileCreateUtils.create(config);
    }

}
