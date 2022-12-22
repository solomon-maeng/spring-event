package com.rebwon.springevent.server

import com.rebwon.springevent.management.campaign.CampaignReportInformationCreator
import com.rebwon.springevent.report.campaign.CampaignReportInformationService
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class CampaignReportInformationCreatorImpl(
    private val service: CampaignReportInformationService
) : CampaignReportInformationCreator {

    @Transactional
    override fun create(campaignId: Long) {
        service.create(campaignId)
    }
}