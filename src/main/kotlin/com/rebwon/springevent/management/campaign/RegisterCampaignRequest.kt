package com.rebwon.springevent.management.campaign

data class RegisterCampaignRequest(
    val name: String,
    val writer: String,
    val campaignType: String,
)