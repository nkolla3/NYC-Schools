package com.nyc.schools

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nyc.schools.ui.main.SchoolsListFragment

class SchoolsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.schools_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, SchoolsListFragment.newInstance())
                .commitNow()
        }
    }
}