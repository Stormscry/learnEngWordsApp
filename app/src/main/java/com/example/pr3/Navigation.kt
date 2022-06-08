package com.example.pr3

import androidx.fragment.app.Fragment
import model.Word
import model.WordService

fun Fragment.navigation(): Navigation = requireActivity() as Navigation

interface Navigation {
    val wordService: WordService

    fun launchWordDetailedFragment(word: Word)

    fun launchAddWordFragment()
}