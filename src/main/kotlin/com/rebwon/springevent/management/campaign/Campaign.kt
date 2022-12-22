package com.rebwon.springevent.management.campaign

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Campaign(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val name: String,
    val active: Boolean = false,
    @Enumerated(EnumType.STRING)
    val type: CampaignType,
    val writer: String,
    @CreationTimestamp
    val createdAt: LocalDateTime? = null,
    @UpdateTimestamp
    val updatedAt: LocalDateTime? = null
) {

}