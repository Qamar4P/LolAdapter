package com.qamar4p.loladapterexample

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View


/**
 * @author Qamar4P
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun setSupportActionBar(toolbar: Toolbar?) {
        super.setSupportActionBar(toolbar)
        //do something common in activities
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
