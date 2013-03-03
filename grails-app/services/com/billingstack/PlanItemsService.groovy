package com.billingstack

class PlanItemsService {
	
	def pricingService

	def map(planItem) {
		def entity = [
			id : planItem.id,
			product_id : planItem.product.id,
		]
		if(planItem instanceof FixedPricingPlanItem) {
			entity.type = "fixed"
			entity.price = planItem.price
		} else {
			if(planItem instanceof VolumePricingPlanItem) {
				entity.type = "volume"
			} else {
				entity.type = "time"
			}
			entity.pricing = planItem.pricing.collect { pricingService.map(it) }
		}
		entity
	}

	def list() {
		PlanItem.list()
	}

	def create(planId, entity) {
		def planItem
		if("fixed".equals(entity.type)) {
			planItem = FixedPricingPlanItem.newInstance(
				plan : Plan.load(planId),
				product : Product.load(entity.product_id),
				price : entity.price
			)
			map(planItem.save(flush : true, failOnError : true))
		} else if("volume".equals(entity.type)) {
			planItem = VolumePricingPlanItem.newInstance(
				plan : Plan.load(planId),
				product : Product.load(entity.product_id),
			)
			planItem.save(flush : true, failOnError : true).id
			entity.pricing.each { range ->
				pricingService.create(planItem.id, range)
			}
			map(planItem)
		} else if("time".equals(entity.type)) {
			planItem = TimePricingPlanItem.newInstance(
				plan : Plan.load(planId),
				product : Product.load(entity.product_id),
			)
			planItem.save(flush : true, failOnError : true)
			entity.pricing.each { range ->
				pricingService.create(planItem.id, range)
			}
			map(planItem)
		} else {
			throw new UnsupportedOperationException("plan.item.not.supported")
		}
		
	}

	def show(String planItemId) {
		map(PlanItem.get(planItemId))
	}

	def update(entity) {
		def planItem = PlanItem.get(entity.id)
		map(planItem)
	}

	def delete(String planItemId) {
		PlanItem.load(planItemId).delete(flush : true)
	}

}
