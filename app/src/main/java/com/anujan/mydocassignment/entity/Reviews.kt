package com.anujan.mydocassignment.entity

import java.io.Serializable


data class Reviews (
	val book_review_link : String,
	val first_chapter_link : String,
	val sunday_review_link : String,
	val article_chapter_link : String
): Serializable