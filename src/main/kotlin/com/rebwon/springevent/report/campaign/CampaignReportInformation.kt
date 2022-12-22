package com.rebwon.springevent.report.campaign

import java.time.LocalDate
import java.util.*
import java.util.concurrent.ThreadLocalRandom
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class CampaignReportInformation(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val campaignId: Long,
    val accessId: String,
    val password: String,
    val expireDate: LocalDate? = null,
    val description: String? = null,
    val visibleForToday: Boolean = false
) {

    companion object {

        fun create(campaignId: Long) : CampaignReportInformation {
            val accessId = UUID.randomUUID().toString()
            val password = UUID.randomUUID().toString()
            return CampaignReportInformation(
                campaignId = campaignId,
                accessId = accessId,
                password = password
            )
        }
    }
}