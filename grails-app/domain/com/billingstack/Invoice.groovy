package com.billingstack

class Invoice extends BillingStackEntity {
	
	Customer customer
	
	String identifier
	
	Date due
	
	BigDecimal subTotal
	BigDecimal taxPercentage
	BigDecimal taxTotal
	BigDecimal total
	
	String status
	
	Currency currency
	
	Transaction transaction

	static belongsTo = [
		merchant : Merchant
	]
	
	static hasMany = [
		lines : InvoiceLine
	]

	static constraints = {
		customer()
		identifier()
		due()
		subTotal()
		taxPercentage()
		taxTotal()
		total()
		status()
		currency()
		transaction(nullable : true)
	}

}
