package com.cpp.scheduling.elastic.lite.config;

import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 作业任务注册中心配置
 * 判断是否配置了zookeeper 地址
 *
 * @author chenjian
 * @date 2018-12-20 10:03
 */
@Configuration
@ConditionalOnExpression("'${spring.elasticjob.serverLists}'.length() > 0")
public class JobRegistryCenterConfig {

    @Value("${spring.elasticjob.serverLists}")
    private String serverLists;
    @Value("${spring.elasticjob.namespace}")
    private String namespace;

    @Bean(initMethod = "init")
    public CoordinatorRegistryCenter registryCenter() {
        // Zookeeper 配置（还可以配置其它参数）
        ZookeeperConfiguration zookeeperConfiguration = new ZookeeperConfiguration(serverLists, namespace);
        return new ZookeeperRegistryCenter(zookeeperConfiguration);
    }
}
