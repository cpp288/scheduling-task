package com.cpp.scheduling.elastic.lite.config;

import com.cpp.scheduling.elastic.lite.job.CppSimpleJob;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 调度任务配置
 *
 * @author chenjian
 * @date 2018-12-20 11:06
 */
@Configuration
public class JobConfig {

    @Autowired
    private CoordinatorRegistryCenter registryCenter;

    /**
     * @see CppSimpleJob
     */
    @Bean(initMethod = "init")
    public JobScheduler cppSimpleJobScheduler(CppSimpleJob cppSimpleJob,
                                              @Value("${elasticjob.cppSimpleJob.corn}") final String cron,
                                              @Value("${elasticjob.cppSimpleJob.shardingTotalCount}") final int shardingTotalCount,
                                              @Value("${elasticjob.cppSimpleJob.shardingItemParameters}") final String shardingItemParameters) {
        return new SpringJobScheduler(cppSimpleJob, registryCenter,
                JobConfigUtil.getSimpleJobConfiguration(cppSimpleJob.getClass(), cron, shardingTotalCount, shardingItemParameters));
    }
}
