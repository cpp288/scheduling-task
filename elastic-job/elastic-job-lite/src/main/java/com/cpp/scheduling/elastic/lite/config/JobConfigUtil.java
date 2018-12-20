package com.cpp.scheduling.elastic.lite.config;

import com.dangdang.ddframe.job.api.ElasticJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.JobTypeConfiguration;
import com.dangdang.ddframe.job.config.dataflow.DataflowJobConfiguration;
import com.dangdang.ddframe.job.config.script.ScriptJobConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;

/**
 * 调度任务配置工具类
 *
 * @author chenjian
 * @date 2018-12-20 10:39
 */
public class JobConfigUtil {

    /**
     * 获取 simple job configuration
     *
     * @param jobClass
     * @param cron
     * @param shardingTotalCount
     * @param shardingItemParameters
     * @return
     */
    public static LiteJobConfiguration getSimpleJobConfiguration(final Class<? extends ElasticJob> jobClass,
                                                                 final String cron,
                                                                 final int shardingTotalCount,
                                                                 final String shardingItemParameters) {
        JobTypeConfiguration jobTypeConfiguration = new SimpleJobConfiguration(
                getJobCoreConfiguration(jobClass, cron, shardingTotalCount, shardingItemParameters), jobClass.getCanonicalName());

        return LiteJobConfiguration
                .newBuilder(jobTypeConfiguration)
                .overwrite(true)
                .build();
    }

    /**
     * 获取 data flow job configuration
     *
     * @param jobClass
     * @param cron
     * @param shardingTotalCount
     * @param shardingItemParameters
     * @return
     */
    public static LiteJobConfiguration getDataFlowJobConfiguration(final Class<? extends ElasticJob> jobClass,
                                                                   final String cron,
                                                                   final int shardingTotalCount,
                                                                   final String shardingItemParameters) {
        JobTypeConfiguration jobTypeConfiguration = new DataflowJobConfiguration(
                getJobCoreConfiguration(jobClass, cron, shardingTotalCount, shardingItemParameters),
                jobClass.getCanonicalName(), true);

        return LiteJobConfiguration
                .newBuilder(jobTypeConfiguration)
                .overwrite(true)
                .build();
    }

    /**
     * 获取 script job configuration
     *
     * @param jobClass
     * @param cron
     * @param shardingTotalCount
     * @param shardingItemParameters
     * @param scriptCommandLine
     * @return
     */
    public static LiteJobConfiguration getScriptJobConfiguration(final Class<? extends ElasticJob> jobClass,
                                                                 final String cron,
                                                                 final int shardingTotalCount,
                                                                 final String shardingItemParameters,
                                                                 final String scriptCommandLine) {
        JobTypeConfiguration jobTypeConfiguration = new ScriptJobConfiguration(
                getJobCoreConfiguration(jobClass, cron, shardingTotalCount, shardingItemParameters), scriptCommandLine);

        return LiteJobConfiguration
                .newBuilder(jobTypeConfiguration)
                .overwrite(true)
                .build();
    }

    /**
     * 获取 JobCoreConfiguration
     *
     * @param jobClass               继承 ElasticJob Class
     * @param cron                   cron 表达式
     * @param shardingTotalCount     分片数量
     * @param shardingItemParameters 任务分片携带的参数
     * @return
     */
    private static JobCoreConfiguration getJobCoreConfiguration(final Class<? extends ElasticJob> jobClass,
                                                                final String cron,
                                                                final int shardingTotalCount,
                                                                final String shardingItemParameters) {
        return JobCoreConfiguration
                .newBuilder(jobClass.getName(), cron, shardingTotalCount)
                .shardingItemParameters(shardingItemParameters)
                .build();
    }
}
