package com.rebwon.springevent

import com.fasterxml.jackson.databind.ObjectMapper
import com.rebwon.springevent.management.campaign.CampaignRepository
import com.rebwon.springevent.management.campaign.RegisterCampaignRequest
import com.rebwon.springevent.report.campaign.CampaignReportInformationRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post

@SpringBootTest
@AutoConfigureMockMvc
class CampaignApiTest {

    @Autowired lateinit var mockMvc: MockMvc
    @Autowired lateinit var objectMapper: ObjectMapper
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

        mockMvc.post("/campaigns-event-same-transaction") {
            content = objectMapper.writeValueAsString(request)
            contentType = MediaType.APPLICATION_JSON
        }
            .andDo { print() }
            .andExpect { status { isOk() } }
    }

    @Test
    fun `동일하지 않은 트랜잭션에서 이벤트로 캠페인과 캠페인 리포트 정보를 생성`() {
        val request = RegisterCampaignRequest(name = "테스트 캠페인", writer = "김키티", "COSTED")

        mockMvc.post("/campaigns-event-not-same-transaction") {
            content = objectMapper.writeValueAsString(request)
            contentType = MediaType.APPLICATION_JSON
        }
            .andDo { print() }
            .andExpect { status { isOk() } }
    }

    @Test
    fun `동일 트랜잭션에서 서비스로 캠페인과 캠페인 리포트 정보를 생성`() {
        val request = RegisterCampaignRequest(name = "테스트 캠페인", writer = "김키티", "COSTED")

        mockMvc.post("/campaigns-service") {
            content = objectMapper.writeValueAsString(request)
            contentType = MediaType.APPLICATION_JSON
        }
            .andDo { print() }
            .andExpect { status { isOk() } }
    }

}
