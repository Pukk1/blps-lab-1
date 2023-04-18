package com.iver.blpslab1.service.utils

import org.springframework.transaction.support.TransactionTemplate

fun <T> doTransaction(transactionTemplate: TransactionTemplate, operations: () -> T): T {
    return transactionTemplate.execute {
        try {
            operations.invoke()
        } catch (e: Exception) {
            it.setRollbackOnly()
            throw e
        }
    }!!
}
