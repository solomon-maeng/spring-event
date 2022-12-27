package com.rebwon.springevent

import com.rebwon.springevent.management.campaign.CampaignService
import com.rebwon.springevent.management.campaign.RegisterCampaignRequest
import com.rebwon.springevent.management.campaign.RegisteredCampaignEvent
import io.kotest.core.spec.style.StringSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.shouldBe
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestConstructor
import org.springframework.test.context.TestExecutionListeners
import org.springframework.test.context.event.ApplicationEvents
import org.springframework.test.context.event.ApplicationEventsTestExecutionListener
import org.springframework.test.context.event.RecordApplicationEvents

@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@RecordApplicationEvents
@TestExecutionListeners(value = [ApplicationEventsTestExecutionListener::class], mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS)
class CampaignServiceTestWithKotest : StringSpec() {

    override fun extensions() = listOf(SpringExtension)

    @Autowired lateinit var service: CampaignService
    @Autowired lateinit var recoredEvents: ApplicationEvents

    init {

        "ApplicationEvent 테스트" {
            val request = RegisterCampaignRequest(name = "테스트 캠페인", writer = "김키티", "COSTED")

            service.registerCampaignWithEventSameTransaction(request)

            val campaignEvent = recoredEvents.stream(RegisteredCampaignEvent::class.java).count()
            val reportEvent = recoredEvents.stream(com.rebwon.springevent.report.campaign.RegisteredCampaignEvent::class.java).count()

            campaignEvent shouldBe 1
            reportEvent shouldBe 1
        }
    }
}