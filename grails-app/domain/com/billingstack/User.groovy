package com.billingstack

class User {
	
	String id
	
	String username
	
	String password
	
	String apiKey
	String secretKey

	static belongsTo = [
		merchant : Merchant,
		customer : Customer
	]
	
	static mapping = {
		id generator : "uuid", type : "string"
	}

	static constraints = {
		username(unique : ['merchant','customer'])
		password()
		apiKey(nullable : true)
		secretKey(nullable : true)
		merchant(nullable : true)
		customer(nullable : true)
	}

}
