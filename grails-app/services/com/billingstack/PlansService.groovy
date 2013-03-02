package com.billingstack

class PlansService {

	def map(plan) {
		[
			id : plan.id,
			merchant : plan.merchant.id,
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
		)
		map(plan.save())
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
