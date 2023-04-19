package com.iver.blpslab1.config

//import com.atomikos.icatch.config.UserTransactionServiceImp
import com.atomikos.icatch.jta.UserTransactionImp
import com.atomikos.icatch.jta.UserTransactionManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.jta.JtaTransactionManager
import javax.transaction.TransactionManager
import javax.transaction.UserTransaction

@Configuration
class AtomikosTransactionManagerConfig {

//    @Bean(initMethod = "init", destroyMethod = "shutdownForce")
//    fun userTransactionService(): UserTransactionServiceImp {
//        return UserTransactionServiceImp()
//    }

    @Bean
    fun userTransaction(): UserTransactionImp =
        UserTransactionImp()

    @Bean(initMethod = "init", destroyMethod = "close")
    fun atomikosTransactionManager(): UserTransactionManager {
        val userTransactionManager = UserTransactionManager()
        userTransactionManager.forceShutdown = false
        return userTransactionManager
    }

    @Bean
    fun transactionManager(userTransaction: UserTransaction, transactionManager: TransactionManager): JtaTransactionManager {
        return JtaTransactionManager(userTransaction, transactionManager)
    }
}