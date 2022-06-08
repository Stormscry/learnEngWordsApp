package com.example.pr3

import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fragments.AddWordFragment
import kotlinx.android.parcel.Parcelize

class StateViewModel(): ViewModel() {
    val state: LiveData<AddWordFragmentState>
        get() = stateLiveData
    private val stateLiveData = MutableLiveData<AddWordFragmentState>()

    fun saveState(state: AddWordFragmentState){
        stateLiveData.value = state
    }

}

@Parcelize
data class AddWordFragmentState(
    val engWordInput: String,
    val ruWordInput: String,
) : Parcelable