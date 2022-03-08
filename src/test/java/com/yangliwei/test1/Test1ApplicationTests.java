package com.yangliwei.test1;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

@SpringBootTest
class Test1ApplicationTests {

    @Test
    void contextLoads() {

            FastAutoGenerator.create("jdbc:mysql://localhost:3306/test1?useUnicode=true&characterEncoding=utf8", "yangliwei", "123456")
                    .globalConfig(builder -> {
                        builder.author("yangliwei") // 设置作者
//                                .enableSwagger() // 开启 swagger 模式
                                .fileOverride() // 覆盖已生成文件
                                .outputDir("D://"); // 指定输出目录
                    })
                    .packageConfig(builder -> {
                        builder.parent("com.yangliwei.test1") // 设置父包名
                                .moduleName("generator") // 设置父包模块名
                                .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "D://")); // 设置mapperXml生成路径
                    })
                    .strategyConfig(builder -> {
                        builder.addInclude("phone"); // 设置需要生成的表名
//                                .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                    })
                    .templateEngine(new VelocityTemplateEngine())
                    // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                    .execute();
        }


}
