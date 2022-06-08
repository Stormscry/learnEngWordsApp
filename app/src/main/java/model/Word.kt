package model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Word(
    val id: Int,
    val word_eng: String,
    val word_ru: String
) : Parcelable