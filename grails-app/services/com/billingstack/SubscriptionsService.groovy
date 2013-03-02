package com.billingstack

class SubscriptionsService {

	def map(subscription) {
		[
			id : subscription.id,
			customer : subscription.customer.id,
			payment_method : subscription.paymentMethod.id,
			plan_id : subscription.plan.id,
			resource : subscription.resource,
			billing_day : subscription.billingDay
		]
	}

	def list() {
		Subscription.list().collect { map(it) }
	}

	def create(customerId, entity) {
		def subscription = Subscription.newInstance(
			customer : Customer.load(customerId),
			paymentMethod : CustomerPaymentMethod.load(entity.payment_method),
			plan : Plan.load(entity.plan_id),
			resource : entity.resource,
			billingDay : new Date().date
		)
		map(subscription.save(failOnError : true))
	}

	def show(String subscriptionId) {
		map(Subscription.get(subscriptionId))
	}

	def update(entity) {
		def subscription = Subscription.get(entity.id)
		map(subscription)
	}

	def delete(String subscriptionId) {
		Subscription.load(subscriptionId).delete(flush : true)
	}

}
