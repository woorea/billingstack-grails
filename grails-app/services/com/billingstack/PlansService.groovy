package com.billingstack

class PlansService {
	
	def planItemsService

	def map(plan) {
		[
			id : plan.id,
			merchant_id : plan.merchant.id,
			name : plan.name,
			title : plan.title,
			description : plan.description
		]
	}

	def list() {
		Plan.list().collect { map(it) }
	}

	def create(merchantId, entity) {
		def plan = Plan.newInstance(
			merchant : Merchant.load(merchantId),
			name : entity.name,
			title : entity.title,
			description : entity.description
		).save(flush : true, failOnError : true)
		entity.items.each {
			planItemsService.create(plan.id, it)
		}
		map(plan)
	}

	def show(String planId) {
		map(Plan.get(planId))
	}

	def update(entity) {
		def plan = Plan.get(entity.id)
		map(plan)
	}

	def delete(String planId) {
		Plan.load(planId).delete(flush : true)
	}

}
