package com.iver.blpslab1.api.v1.delegate

import com.iver.blpslab1.api.v1.http.requests.CreateItemRequest
import com.iver.blpslab1.api.v1.http.requests.UpdateItemRequest
import com.iver.blpslab1.api.v1.http.views.toView
import com.iver.blpslab1.service.item.ItemService
import org.camunda.bpm.engine.delegate.DelegateExecution
import org.camunda.bpm.engine.delegate.JavaDelegate
import org.springframework.stereotype.Component
import javax.inject.Named

@Component
@Named("createItem")
class CreateItemDelegate(
    private val itemService: ItemService,
) : JavaDelegate {
    override fun execute(execution: DelegateExecution) {
        val createItemRequest = CreateItemRequest(
            fullName = execution.getVariable("fullName") as String,
            inStock = execution.getVariable("inStock") as Boolean,
            price = execution.getVariable("price") as Int,
            warrantyPeriod = execution.getVariable("warrantyPeriod") as Int,
            country = execution.getVariable("country") as String,
            definition = execution.getVariable("definition") as String,
        )
        execution.setVariable("item", itemService.createItem(createItemRequest).toView())
    }
}

@Component
@Named("getItem")
class GetItemDelegate(
    private val itemService: ItemService,
) : JavaDelegate {
    override fun execute(execution: DelegateExecution) {
        val itemId: Long = execution.getVariable("itemId") as Long
        execution.setVariable("item", itemService.getItemById(itemId).toView())
    }
}

@Component
@Named("updateItem")
class UpdateItemDelegate(
    private val itemService: ItemService,
) : JavaDelegate {
    override fun execute(execution: DelegateExecution) {
        val itemId: Long = execution.getVariable("itemId") as Long
        val updateItemRequest = UpdateItemRequest(
            fullName = execution.getVariable("fullName") as String,
            inStock = execution.getVariable("inStock") as Boolean,
            price = execution.getVariable("price") as Int,
            warrantyPeriod = execution.getVariable("warrantyPeriod") as Int,
            country = execution.getVariable("country") as String,
            definition = execution.getVariable("definition") as String,
        )
        execution.setVariable("item", itemService.updateItem(itemId, updateItemRequest).toView())
    }
}

@Component
@Named("deleteItem")
class DeleteItemDelegate(
    private val itemService: ItemService,
) : JavaDelegate {
    override fun execute(execution: DelegateExecution) {
        val itemId: Long = execution.getVariable("itemId") as Long
        itemService.deleteItem(itemId)
    }
}