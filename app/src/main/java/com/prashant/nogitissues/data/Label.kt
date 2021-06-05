package com.prashant.nogitissues.data


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Label(
    @SerialName("color")
    val color: String? = "",
    @SerialName("default")
    val default: Boolean? = false,
    @SerialName("description")
    val description: String? = "",
    @SerialName("id")
    val id: Int? = 0,
    @SerialName("name")
    val name: String? = "",
    @SerialName("node_id")
    val nodeId: String? = "",
    @SerialName("url")
    val url: String? = ""
)