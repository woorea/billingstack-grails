package com.billingstack

class PaymentMethod extends BillingStackEntity {
	
	String name

	static belongsTo = [
		provider : PaymentGatewayProvider
	]

	static constraints = {
		name()
	}

}
