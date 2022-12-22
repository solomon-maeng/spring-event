package com.rebwon.springevent.server

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.AsyncConfigurer
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import java.util.concurrent.Executor

@Configuration
@EnableAsync
class AsyncConfig : AsyncConfigurer {

    private val log: Logger = LoggerFactory.getLogger(AsyncConfig::class.java)

    override fun getAsyncExecutor(): Executor? {
        val executor = ThreadPoolTaskExecutor()
        val processors = Runtime.getRuntime().availableProcessors()
        log.info("processors count {}", processors)
        executor.setCorePoolSize(processors)
        executor.setMaxPoolSize(processors * 2)
        executor.setQueueCapacity(50)
        executor.setKeepAliveSeconds(60)
        executor.setThreadNamePrefix("AsyncExecutor-")
        executor.initialize()
        return executor
    }
}