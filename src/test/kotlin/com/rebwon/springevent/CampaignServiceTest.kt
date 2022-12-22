package com.rebwon.springevent

import com.rebwon.springevent.management.campaign.CampaignRepository
import com.rebwon.springevent.management.campaign.CampaignService
import com.rebwon.springevent.management.campaign.RegisterCampaignRequest
import com.rebwon.springevent.management.campaign.RegisteredCampaignEvent
import com.rebwon.springevent.report.campaign.CampaignReportInformationRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.event.ApplicationEvents
import org.springframework.test.context.event.RecordApplicationEvents

@SpringBootTest
@RecordApplicationEvents
class CampaignServiceTest {

    @Autowired lateinit var service: CampaignService
    @Autowired lateinit var recoredEvents: ApplicationEvents
    @Autowired lateinit var campaignRepository: CampaignRepository
    @Autowired lateinit var campaignReportInformationRepository: CampaignReportInformationRepository

    @AfterEach
    fun tearDown() {
        campaignRepository.deleteAll()
        campaignReportInformationRepository.deleteAll()
    }

    @Test
    fun `동일 트랜잭션에서 이벤트로 캠페인과 캠페인 리포트 정보를 생성`() {
        val request = RegisterCampaignRequest(name = "테스트 캠페인", writer = "김키티", "COSTED")

        service.registerCampaignWithEventSameTransaction(request)

        val campaignEvent = recoredEvents.stream(RegisteredCampaignEvent::class.java).count()
        val reportEvent = recoredEvents.stream(com.rebwon.springevent.report.campaign.RegisteredCampaignEvent::class.java).count()

        assertThat(campaignEvent).isEqualTo(1)
        assertThat(reportEvent).isEqualTo(1)
        assertThatSuccessfullySaveCampaignAndCampaignReportInformation()
    }

    @Test
    fun `동일하지 않은 트랜잭션에서 이벤트로 캠페인과 캠페인 리포트 정보를 생성`() {
        val request = RegisterCampaignRequest(name = "테스트 캠페인", writer = "김키티", "COSTED")

        service.registerCampaignWithEventNotSameTransaction(request)

        val campaignEvent = recoredEvents.stream(RegisteredCampaignEvent::class.java).count()
        val reportEvent = recoredEvents.stream(com.rebwon.springevent.report.campaign.RegisteredCampaignEvent::class.java).count()

        assertThat(campaignEvent).isEqualTo(1)
        assertThat(reportEvent).isEqualTo(1)
    }

    @Test
    fun `동일 트랜잭션에서 서비스로 캠페인과 캠페인 리포트 정보를 생성`() {
        val request = RegisterCampaignRequest(name = "테스트 캠페인", writer = "김키티", "COSTED")

        service.registerCampaignWithService(request)

        assertThatSuccessfullySaveCampaignAndCampaignReportInformation()
    }

    private fun assertThatSuccessfullySaveCampaignAndCampaignReportInformation() {
        assertThat(campaignRepository.findAll().count()).isEqualTo(1)
        assertThat(campaignReportInformationRepository.findAll().count()).isEqualTo(1)
    }
}