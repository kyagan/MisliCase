package com.kaanyagan.mislicase.data.api.model

data class ListItem (val data: Data, val listItemType:ListItemType, var isFavorite: Boolean = false)

enum class ListItemType(val type:Int){
    LEAGUE(0),
    MATCH(1)
}