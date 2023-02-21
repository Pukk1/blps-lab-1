package com.iver.blpslab1.dao.order.repository

import com.iver.blpslab1.dao.order.entity.AddressEntity
import com.iver.blpslab1.dao.order.entity.AddressId
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AddressRepository : CrudRepository<AddressEntity, AddressId>