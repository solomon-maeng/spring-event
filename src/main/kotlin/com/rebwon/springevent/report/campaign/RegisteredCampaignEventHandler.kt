package com.rebwon.springevent.report.campaign

import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class RegisteredCampaignEventHandler(
    private val repository: CampaignReportInformationRepository,
) {

    @EventListener
    @Transactional
    fun handle(event: RegisteredCampaignEvent) {
        repository.save(CampaignReportInformation.create(event.campaignId))
    }
}