package com.test.config.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import java.util.concurrent.Executor;

@Configuration
@EnableAsync
@Slf4j
public class AsyncConfiguration {

    @Bean("profileExecutorPool")
    public Executor getProfileExecutor(@Value("${async.profilepool.coresize}") int coresize,
    @Value("${async.profilepool.maxsize}") int maxsize,
    @Value("${async.profilepool.queueCapacity}") int queueCapacity,
    @Value("${async.profilepool.prefix}") String prefix){
    return getExecutor(coresize,maxsize,queueCapacity,prefix);
    }

    private Executor getExecutor(int coresize,int maxsize,int queueCapacity,String prefix){
        log.info("Async Config Details: coreSize - {} maxsie - {} QueueCapacity - {} prefix - {}",coresize,maxsize,queueCapacity,prefix);
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(coresize);
        executor.setMaxPoolSize(maxsize);
        executor.setQueueCapacity(queueCapacity);
        executor.setThreadNamePrefix(prefix);
        executor.initialize();
        return executor;
    }
}
