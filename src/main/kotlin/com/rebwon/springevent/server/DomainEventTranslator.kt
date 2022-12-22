package com.rebwon.springevent.server

import com.rebwon.springevent.management.campaign.RegisteredCampaignEvent
import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionalEventListener

@Component
class DomainEventTranslator(
    private val publisher: ApplicationEventPublisher,
) {

//    @Async
//    @EventListener
//    fun handle(event: RegisteredCampaignEvent) {
//        publisher.publishEvent(com.rebwon.springevent.report.campaign.RegisteredCampaignEvent(event.campaignId))
//    }

    @EventListener
    fun handle(event: RegisteredCampaignEvent) {
        publisher.publishEvent(com.rebwon.springevent.report.campaign.RegisteredCampaignEvent(event.campaignId))
    }


//    @Async
//    @TransactionalEventListener
//    fun handle(event: RegisteredCampaignEvent) {
//        publisher.publishEvent(com.rebwon.springevent.report.campaign.RegisteredCampaignEvent(event.campaignId))
//    }
}