package com.rebwon.springevent.management.campaign

import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.transaction.support.TransactionTemplate
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException

@Service
class CampaignService(
    private val campaignRepository: CampaignRepository,
    private val creator: CampaignReportInformationCreator,
    private val publisher: ApplicationEventPublisher,
    private val transaction: TransactionTemplate,
) {
    @Transactional
    fun registerCampaignWithEventSameTransaction(request: RegisterCampaignRequest) {
        val campaign = campaignRepository.save(
            Campaign(
                name = request.name,
                type = CampaignType.valueOf(request.campaignType),
                writer = request.writer
            )
        )
        publisher.publishEvent(RegisteredCampaignEvent(campaignId = campaign.id!!))
    }

    fun registerCampaignWithEventNotSameTransaction(request: RegisterCampaignRequest) {
        val campaign = transaction.execute {
            campaignRepository.save(
                Campaign(
                    name = request.name,
                    type = CampaignType.valueOf(request.campaignType),
                    writer = request.writer
                )
            )
        } ?: throw IllegalStateException("DB 에러")
        publisher.publishEvent(RegisteredCampaignEvent(campaignId = campaign.id!!))
    }

    @Transactional
    fun registerCampaignWithService(request: RegisterCampaignRequest) {
        val campaign = campaignRepository.save(
            Campaign(
                name = request.name,
                type = CampaignType.valueOf(request.campaignType),
                writer = request.writer
            )
        )
        creator.create(campaignId = campaign.id!!)
    }
}