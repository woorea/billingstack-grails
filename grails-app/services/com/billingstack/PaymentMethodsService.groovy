package com.billingstack

class PaymentMethodsService {

	def map(paymentMethod) {
		[
			id : paymentMethod.id,
			provider_id : paymentMethod.provider.id
		]
	}

	def list() {
		PaymentMethod.list().collect { map(it) }
	}

	def create(paymentGatewayProviderId, entity) {
		def paymentMethod = PaymentMethod.newInstance(
			provider : PaymentGatewayProvider.load(paymentGatewayProviderId),
			name : entity.name
		)
		map(paymentMethod.save(failOnError : true))
	}

	def show(String paymentMethodId) {
		map(PaymentMethod.get(paymentMethodId))
	}

	def update(entity) {
		def paymentMethod = PaymentMethod.get(entity.id)
		map(paymentMethod)
	}

	def delete(String paymentMethodId) {
		PaymentMethod.load(paymentMethodId).delete(flush : true)
	}

}
