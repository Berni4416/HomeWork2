package at.fh.swengb.windisch.homeexercise2

import android.content.Context
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.view.View
import android.preference.PreferenceManager
import at.fh.swengb.windisch.homeexercise2.MainActivity.PreferenceHelper.customPreference
import at.fh.swengb.windisch.homeexercise2.R.id.t_note
import at.fh.swengb.windisch.homeexercise2.R.id.t_title


class MainActivity : AppCompatActivity(), View.OnClickListener {

    val CUSTOM_PREF_NAME ="User_data"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Save.setOnClickListener(this)

    }
    override fun onClick(v: View?) {
        val prefs = customPreference(this, CUSTOM_PREF_NAME)
        when (v?.id) {
            R.id.Save -> {
                prefs.name = t_title.text.toString()
                prefs.age = t_note.text.toString().toInt()
            }
}
    }
    object PreferenceHelper {

        val USER_ID = "USER_ID"
        val USER_PASSWORD = "PASSWORD"

        fun defaultPreference(context: Context): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

        fun customPreference(context: Context, name: String): SharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE)

        inline fun SharedPreferences.editMe(operation: (SharedPreferences.Editor) -> Unit) {
            val editMe = edit()
            operation(editMe)
            editMe.apply()
        }
    var SharedPreferences.userId
        get() = getString(t_title, 0)
        set(value) {
            editMe {
                it.putInt(t_title, value)
            }
        }

    var SharedPreferences.password
        get() = getString(t_note, "")
        set(value) {
            editMe {
                it.putString(t_note, value)
            }
        }
}
