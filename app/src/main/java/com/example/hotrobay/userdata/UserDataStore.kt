package com.example.hotrobay.userdata

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore

val Context.userDataStore: DataStore<User> by dataStore(
    fileName = "user.pb",
    serializer = UserSerializer
)