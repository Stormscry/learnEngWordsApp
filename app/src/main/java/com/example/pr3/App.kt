package com.example.pr3

import android.app.Application
import model.WordService

class App: Application() {
    val wordService = WordService()
}