package com.anujan.mydocassignment.entity

import java.io.Serializable

data class BestSellers (
	val status : String,
	val copyright : String,
	val num_results : Int,
	val results : List<Results>
): Serializable