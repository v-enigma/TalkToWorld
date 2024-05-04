package com.example.talktoworld

import android.app.Application
import com.example.talktoworld.data.AppContainer
import com.example.talktoworld.data.DefaultAppContainer

class TalkToWorldApplication: Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}