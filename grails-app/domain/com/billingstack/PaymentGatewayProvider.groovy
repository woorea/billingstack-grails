package com.billingstack

class PaymentGatewayProvider extends BillingStackEntity {
	
	String name
	
	String title
	
	String description
	
	static hasMany = [
		methods : PaymentMethod
	]

	static constraints = {
		name()
		title(nullable : true)
		description(nullable :true)
	}

}
