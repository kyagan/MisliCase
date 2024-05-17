package com.kaanyagan.mislicase.data.api.model

data class ListItem (val data: Data, val listItemType:ListItemType)

enum class ListItemType(val type:Int){
    HEADER(0),
    MATCH(1)
}