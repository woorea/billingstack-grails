package com.billingstack

class Usage extends BillingStackEntity {
	
	Subscription subscription

	static belongsTo = [
		subscription : Subscription
	]

	static constraints = {
		subscription()
	}

}
