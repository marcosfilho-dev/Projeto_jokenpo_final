package com.example.projetojokenp

import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

@Suppress("UNREACHABLE_CODE")
class CustomObserver : DefaultLifecycleObserver, LifecycleEventObserver {
    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        Log.d("Lifecycle", "observer ${owner.toString()} - onResume")
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {

        Log.d("Lifecycle", "Observer ${source.toString()} event: ${event.toString()}")
    }
}