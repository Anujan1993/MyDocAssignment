package com.anujan.mydocassignment.entity

import java.io.Serializable

data class RanksHistory (

	val primary_isbn10 : String,
	val primary_isbn13 : String,
	val rank : Int,
	val list_name : String,
	val display_name : String,
	val published_date : String,
	val bestsellers_date : String,
	val weeks_on_list : Int,
	val ranks_last_week : String,
	val asterisk : Int,
	 val dagger : Int
): Serializable