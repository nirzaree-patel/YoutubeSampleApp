package com.app.youtubemusic.utils

import android.content.Context
import android.content.SharedPreferences


//typealias PreferenceObserver<T> = (key: String, value: T) -> Unit
public class Pref {


    object PreferenceHelper {
        fun customPref(context: Context, name: String): SharedPreferences =
            context.getSharedPreferences(name, Context.MODE_PRIVATE)
    }

    companion object {
        var MyPREFERENCES = "YoutubeApp"
        private lateinit var pref: SharedPreferences
        private lateinit var key: String
        private var defaultValue: Any? = null
        //    private var listener: PreferenceObserver<T>? = null

        fun getValue(context: Context, key: String, defaultValue: Any?): Any? {
            this.pref = PreferenceHelper.customPref(context, MyPREFERENCES)
            return pref[key, defaultValue]
        }

        fun setValue(context: Context, key: String, value: Any?) {
            this.pref = PreferenceHelper.customPref(context, MyPREFERENCES)
            pref[key] = value
//        listener?.invoke(key, value)

        }

        fun removeValue(context: Context, key: String) {
            this.pref = PreferenceHelper.customPref(context, MyPREFERENCES)
            val editor = this.pref.edit()
            editor.remove(key)
            editor.apply()

        }

        fun clearAll(context: Context) {
            this.pref = PreferenceHelper.customPref(context, MyPREFERENCES)
            val editor = this.pref.edit()
            editor.clear()
            editor.apply()
        }
    }
}

private inline fun SharedPreferences.edit(task: (SharedPreferences.Editor) -> Unit) {
    val editor = this.edit()
    task(editor)
    editor.apply()
}

operator fun SharedPreferences.set(key: String, value: Any?) {
    when (value) {
        is String? -> edit { it.putString(key, value) }
        is Int -> edit { it.putInt(key, value) }
        is Boolean -> edit { it.putBoolean(key, value) }
        is Float -> edit { it.putFloat(key, value) }
        is Long -> edit { it.putLong(key, value) }
        else -> throw UnsupportedOperationException("No implementation")
    }
}

@Suppress("UNCHECKED_CAST")
operator fun <T> SharedPreferences.get(key: String, defaultValue: T? = null): T {
    return when (defaultValue) {
        is String -> getString(key, defaultValue as? String) as T
        is Int -> getInt(key, defaultValue as? Int ?: -1) as T
        is Boolean -> getBoolean(key, defaultValue as? Boolean ?: false) as T
        is Float -> getFloat(key, defaultValue as? Float ?: -1f) as T
        is Long -> getLong(key, defaultValue as? Long ?: -1) as T
        else -> throw UnsupportedOperationException("No implementation")
    }
}
