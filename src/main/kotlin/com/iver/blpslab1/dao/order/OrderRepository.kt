package com.iver.blpslab1.dao.order

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository : CrudRepository<OrderEntity, OrderId>
