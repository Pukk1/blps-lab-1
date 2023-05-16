package com.iver.blpslab1.config

import com.iver.blpslab1.service.item.ItemService
import org.quartz.Job
import org.quartz.JobExecutionContext
import org.quartz.JobExecutionException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AsyncScheduling : Job {

    @Autowired
    private lateinit var itemService: ItemService

    @Throws(JobExecutionException::class)
    override fun execute(context: JobExecutionContext?) {
        itemService.orderNewItemsMessage()
    }
}
