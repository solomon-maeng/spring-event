package com.rebwon.springevent.management.campaign

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CampaignApi(
    private val service: CampaignService,
) {

    @PostMapping("/campaigns")
    fun registerCampaign(
        @RequestBody request: RegisterCampaignRequest
    ): ResponseEntity<Any> {
        service.registerCampaignWithEventSameTransaction(request)
        return ResponseEntity.ok(null)
    }

    @PostMapping("/campaigns-event-same-transaction")
    fun registerCampaignWithEventSameTransaction(
        @RequestBody request: RegisterCampaignRequest
    ): ResponseEntity<Any> {
        service.registerCampaignWithEventSameTransaction(request)
        return ResponseEntity.ok(null)
    }

    @PostMapping("/campaigns-event-not-same-transaction")
    fun registerCampaignWithEventNotSameTransaction(
        @RequestBody request: RegisterCampaignRequest
    ): ResponseEntity<Any> {
        service.registerCampaignWithEventNotSameTransaction(request)
        return ResponseEntity.ok(null)
    }

    @PostMapping("/campaigns-service")
    fun registerCampaignWithService(
        @RequestBody request: RegisterCampaignRequest
    ): ResponseEntity<Any> {
        service.registerCampaignWithService(request)
        return ResponseEntity.ok(null)
    }
}