package com.example.dogedex.model

import android.app.Activity
import android.content.Context

data class User (val id: Long, val email: String, val authenticationToken: String){
    companion object {
        private const val AUTH_PREFS = "auth_pref"
        private const val EMAIL_KEY = "email_key"
        private const val AUTH_TOKEN_KEY = "auth_token_key"
        private const val ID_KEY = "id"

        fun setLoggedInUser(activity: Activity, user: User) {
            activity.getSharedPreferences(AUTH_PREFS, Context.MODE_PRIVATE).also {
                it.edit().putLong(ID_KEY, user.id)
                    .putString(EMAIL_KEY, user.email)
                    .putString(AUTH_TOKEN_KEY, user.authenticationToken)
                    .apply()
            }
        }


        fun getLoggedInUser(activity: Activity, ): User? {
            val pref =
                activity.getSharedPreferences(AUTH_PREFS, Context.MODE_PRIVATE) ?: return null
            val userId = pref.getLong(ID_KEY, 0)
            if (userId == 0L) {
                return null
            }
            return User(
                userId,
                pref.getString(EMAIL_KEY, "") ?: "",
                pref.getString(AUTH_TOKEN_KEY, "") ?: ""
            )

        }

        fun logout(activity: Activity){
            activity.getSharedPreferences(AUTH_PREFS, Context.MODE_PRIVATE).also{
                it.edit().clear().apply()
            }
        }
    }
}