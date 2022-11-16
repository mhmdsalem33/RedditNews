package com.example.redditnews.data.realmdb


import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class ArticleDatabaseExplorer(
    @PrimaryKey
    var primaryKey : Long = 0,
    var id: String ? = null,
    var allow_live_comments: Boolean  ? = null,
    var approved_at_utc: String ? = null,
    var approved_by: String ? = null,
    var archived: Boolean ? = null,
    var author: String ? = null,
    var author_flair_background_color: String ? = null,
    var author_flair_css_class: String ? = null,
    var author_flair_text: String ? = null,
    var author_flair_text_color: String ? = null,
    var author_flair_type: String ? = null,
    var author_fullname: String ? = null,
    var author_is_blocked: Boolean ? = null,
    var author_patreon_flair: Boolean ? = null,
    var author_premium: Boolean ? = null,
    var banned_at_utc: String ? = null,
    var banned_by: String ? = null,
    var can_gild: Boolean ? = null,
    var can_mod_post: Boolean ? = null,
    var category: String ? = null,
    var clicked: Boolean ? = null,
    var content_categories: String ? = null,
    var contest_mode: Boolean ? = null,
    var created: Int ? = null,
    var created_utc: Int ? = null,
    var discussion_type: String ? = null,
    var distinguished: String ? = null,
    var domain: String ? = null,
    var downs: Int ? = null,
    var gilded: Int ? = null,
    var hidden: Boolean ? = null,
    var hide_score: Boolean ? = null,
    var is_created_from_ads_ui: Boolean ? = null,
    var is_crosspostable: Boolean ? = null,
    var is_meta: Boolean ? = null,
    var is_original_content: Boolean ? = null,
    var is_reddit_media_domain: Boolean ? = null,
    var is_robot_indexable: Boolean ? = null,
    var is_self: Boolean ? = null,
    var is_video: Boolean ? = null,
    var likes: String ? = null,
    var link_flair_background_color: String ? = null,
    var link_flair_css_class: String ? = null,
    var link_flair_text: String ? = null,
    var link_flair_text_color: String ? = null,
    var link_flair_type: String ? = null,
    var locked: Boolean ? = null,
    var media_only: Boolean ? = null,
    var mod_note: String ? = null,
    var mod_reason_by: String ? = null,
    var mod_reason_title: String ? = null,
    var name: String ? = null,
    var no_follow: Boolean ? = null,
    var num_comments: Int ? = null,
    var num_crossposts: Int ? = null,
    var num_reports: String ? = null,
    var over_18: Boolean ? = null,
    var parent_whitelist_status: String ? = null,
    var permalink: String ? = null,
    var pinned: Boolean ? = null,
    var pwls: Int ? = null,
    var quarantine: Boolean ? = null,
    var removal_reason: String ? = null,
    var removed_by: String ? = null,
    var removed_by_category: String ? = null,
    var report_reasons: String ? = null,
    var saved: Boolean ? = null,
    var score: Int ? = null,
    var send_replies: Boolean ? = null,
    var spoiler: Boolean ? = null,
    var stickied: Boolean ? = null,
    var subreddit: String ? = null,
    var subreddit_id: String ? = null,
    var subreddit_name_prefixed: String ? = null,
    var subreddit_subscribers: Int ? = null,
    var subreddit_type: String ? = null,
    var suggested_sort: String ? = null,
    var thumbnail: String ? = null,
    var title: String ? = null,
    var top_awarded_type: String ? = null,
    var total_awards_received: Int ? = null,
    var ups: Int ? = null,
    var upvote_ratio: Double ? = null,
    var url: String ? = null,
    var view_count: String ? = null,
    var visited: Boolean ? = null,
    var whitelist_status: String ? = null,
    var wls: Int ? = null
):RealmObject()