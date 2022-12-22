package com.rebwon.springevent.management.campaign

import org.springframework.data.repository.CrudRepository

interface CampaignRepository : CrudRepository<Campaign, Long> {
}