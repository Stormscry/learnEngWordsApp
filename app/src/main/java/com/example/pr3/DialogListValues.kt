package com.example.pr3

class DialogListValues(
    val valuesList: List<Int>,
    val currentIndex: Int
) {
    companion object {
        fun createListValues(currItem: Int): DialogListValues {
            val valuesList = (0..10).toList()
            val index = valuesList.indexOf(currItem)
            return if (index != -1) {
                DialogListValues(valuesList, index)
            } else{
                DialogListValues(valuesList, 0)
            }
        }
    }
}