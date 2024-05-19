package com.kaanyagan.mislicase.data.api.model

// ListItem is a class used for data to be displayed in RecyclerView
// data: Where the data is located
// listItemType: Specifies the type of data (League or Match)
// isFavorite: Used to indicate whether the match has been added to favorites or not.
data class ListItem (val data: Data, val listItemType:ListItemType, var isFavorite: Boolean = false)

// ListItemType is an enum class used to specify the types of data in the RecyclerView
enum class ListItemType(val type:Int){
    LEAGUE(0),
    MATCH(1)
}