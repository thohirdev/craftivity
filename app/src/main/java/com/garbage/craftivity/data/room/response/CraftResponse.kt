package com.garbage.craftivity.data.room.response

import com.google.gson.annotations.SerializedName

data class CraftResponse (
    @SerializedName("id_carft")
    val craftId: Int,

    @SerializedName("thumbnail_craft")
    val image: String,

    @SerializedName("slug_craft")
    val title: String,

    @SerializedName("author_craft")
    val author: String,
)
