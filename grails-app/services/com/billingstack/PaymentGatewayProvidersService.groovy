package com.billingstack

class PaymentGatewayProvidersService {
	
	def map(paymentGatewayProvider) {
		[
			id : paymentGatewayProvider.id
		]
	}

	def list() {
		PaymentGatewayProvider.list().collect { map(it) }
	}

	def create(entity) {
		println entity
		def paymentGatewayProvider = PaymentGatewayProvider.newInstance(
			name : entity.name,
			title : entity.title,
			description : entity.description
		)
		map(paymentGatewayProvider.save(failOnError : true))
		
	}

	def show(String paymentGatewayProviderId) {
		map(PaymentGatewayProvider.get(paymentGatewayProviderId))
	}

	def update(entity) {
		def paymentGatewayProvider = PaymentGatewayProvider.get(entity.id)
		map(paymentGatewayProvider)
	}

	def delete(String paymentGatewayProviderId) {
		PaymentGatewayProvider.load(paymentGatewayProviderId).delete(flush : true)
	}

}
