package com.beardream;

import com.beardream.config.AddPermission;
import com.beardream.dao.MethodMapper;
import com.beardream.dao.ModuleMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@MapperScan("com.beardream.dao")
@SpringBootApplication
@ComponentScan
public class SpringbootApplication {

	@Component
	static class PermissionScanner implements CommandLineRunner {

		@Autowired
		private ModuleMapper moduleMap;

		@Autowired
		private MethodMapper methodMapper;

		@Override
		public void run(String... args) throws Exception {
			//扫描所有controller，注册权限模块和方法
			new AddPermission().scanner(moduleMap, methodMapper);
		}

	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}
}