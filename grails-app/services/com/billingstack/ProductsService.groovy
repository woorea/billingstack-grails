package com.billingstack

class ProductsService {
	
	def map(product) {
		[
			id : product.id,
			merchant : product.merchant.id,
			name : product.name,
			title : product.title,
			description : product.description,
			provider : product.provider
		]
	}

	def list() {
		Product.list().collect { map(it) }
	}

	def create(merchantId, entity) {
		Product product = Product.newInstance(
			merchant : Merchant.load(merchantId),
			name : entity.name,
			title : entity.title,
			description : entity.description,
			provider : entity.provider
		)
		map(product.save(failOnError : true))
	}

	def show(String productId) {
		map(Product.get(productId))
	}

	def update(entity) {
		def product = Product.get(entity.id)
		map(product)
	}

	def delete(String productId) {
		Product.load(productId).delete(flush : true)
	}

}
