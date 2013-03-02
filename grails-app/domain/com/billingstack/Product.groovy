package com.billingstack

class Product extends BillingStackEntity {
	
	String name
	
	String title
	
	String description
	
	String provider
	
	static belongsTo = [
		merchant : Merchant
	]

	static constraints = {
		name()
		title(nullable : true)
		description(nullable : true)
		provider(nullable : true)
	}

}
