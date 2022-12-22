package com.rebwon.springevent.report.campaign

import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class RegisteredCampaignAsyncEventHandler(
    private val repository: CampaignReportInformationRepository,
) {

    @EventListener
    @Transactional
    fun handle(event: RegisteredCampaignAsyncEvent) {
        repository.save(CampaignReportInformation.create(event.campaignId))
    }
}