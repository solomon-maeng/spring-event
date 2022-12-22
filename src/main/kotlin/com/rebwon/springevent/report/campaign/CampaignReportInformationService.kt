package com.rebwon.springevent.report.campaign

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CampaignReportInformationService(
    private val repository: CampaignReportInformationRepository,
) {

    @Transactional
    fun create(campaignId: Long) {
        repository.save(CampaignReportInformation.create(campaignId))
    }
}