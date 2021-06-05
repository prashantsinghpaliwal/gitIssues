package com.prashant.nogitissues.data


import androidx.annotation.NonNull
import androidx.recyclerview.widget.DiffUtil
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class IssueItem(


    @SerialName("body")
    val body: String? = "",

    @SerialName("comments")
    val comments: Int? = 0,
    @SerialName("comments_url")
    val commentsUrl: String? = "",
    @SerialName("created_at")
    val createdAt: String? = "",
    @SerialName("events_url")
    val eventsUrl: String? = "",
    @SerialName("html_url")
    val htmlUrl: String? = "",
    @SerialName("id")
    val id: Int? = 0,
    @SerialName("labels")
    val labels: List<Label>? = listOf(),
    @SerialName("labels_url")
    val labelsUrl: String? = "",
    @SerialName("locked")
    val locked: Boolean? = false,

    @SerialName("node_id")
    val nodeId: String? = "",
    @SerialName("number")
    val number: Int? = 0,


    @SerialName("pull_request")
    val pullRequest: PullRequest? = PullRequest(),
    @SerialName("repository_url")
    val repositoryUrl: String? = "",
    @SerialName("state")
    val state: String? = "",
    @SerialName("title")
    val title: String? = "",
    @SerialName("updated_at")
    val updatedAt: String? = "",
    @SerialName("url")
    val url: String? = "",
    @SerialName("user")
    val user: User? = User()
) {

    companion object {
        val CALLBACK: DiffUtil.ItemCallback<IssueItem> =
            object : DiffUtil.ItemCallback<IssueItem>() {
                override fun areItemsTheSame(
                    @NonNull items: IssueItem,
                    @NonNull t1: IssueItem
                ): Boolean {
                    return items.id === t1.id
                }

                override fun areContentsTheSame(
                    @NonNull items: IssueItem,
                    @NonNull t1: IssueItem
                ): Boolean {
                    return true
                }
            }
    }


}