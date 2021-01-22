package com.anujan.mydocassignment.entity

import java.io.Serializable


data class Results (
    val title : String,
    val description : String,
    val contributor : String,
    val author : String,
    val contributor_note : String,
    val price : Double,
    val age_group : String,
    val publisher : String,
    val isbns : List<Isbns>,
    val ranks_history : List<RanksHistory>,
    val reviews : List<Reviews>
): Serializable