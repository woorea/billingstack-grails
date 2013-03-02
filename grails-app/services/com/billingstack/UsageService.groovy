package com.billingstack

class UsageService {

	def map(usage) {
		[
			id : usage.id,
			subscription : usage.subscription.id
		]
	}

	def list() {
		Usage.list().collect { map(it) }
	}

	def create(subscriptionId, entity) {
		def usage = Usage.newInstance(
			subscription : Subscription.load(subscriptionId)
		)
		map(usage.save())
	}

	def show(String usageId) {
		map(Usage.get(usageId))
	}

	def update(entity) {
		def usage = Usage.get(entity.id)
		map(usage)
	}

	def delete(String usageId) {
		Usage.load(usageId).delete(flush : true)
	}

}
