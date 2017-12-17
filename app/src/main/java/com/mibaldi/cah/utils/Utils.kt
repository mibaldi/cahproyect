package com.mibaldi.cah.utils

import android.app.Activity
import com.mibaldi.cah.application.App

val Activity.app: App get() = application as App
