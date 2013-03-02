package com.billingstack

import grails.converters.JSON

class PlanItemsController {

		def planItemsService

		def list() {
			try {
				render planItemsService.list() as JSON
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def create(String planId) {
			try {
				render planItemsService.create(planId, planItem) as JSON
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def show(String planItemId) {
			try {
				return planItemsService.show(planItemId) as JSON
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def update() {
			try {
				render planItemsService.update(request.JSON) as JSON
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def delete(String planItemId) {
			try {
				planItemsService.delete(planItemId)
				render(status : 204)
			} catch(e) {
				render onError(e) as JSON
			}
		}
		
		private onError(e) {
			log.error(e.message,e)
			response.status = 500
			["error":e.message]
		}

}
