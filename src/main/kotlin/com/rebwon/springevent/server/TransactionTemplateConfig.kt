package com.rebwon.springevent.server

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.TransactionDefinition
import org.springframework.transaction.support.TransactionTemplate

@Configuration
class TransactionTemplateConfig {

    @Bean
    fun transactionTemplate(platformTransactionManager: PlatformTransactionManager): TransactionTemplate {
        val transactionTemplate = TransactionTemplate()
        transactionTemplate.transactionManager = platformTransactionManager
        transactionTemplate.isolationLevel = TransactionDefinition.ISOLATION_DEFAULT
        transactionTemplate.propagationBehavior = TransactionDefinition.PROPAGATION_REQUIRED
        transactionTemplate.timeout = TransactionDefinition.TIMEOUT_DEFAULT
        return transactionTemplate
    }
}