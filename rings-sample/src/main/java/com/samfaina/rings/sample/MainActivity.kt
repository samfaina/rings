package com.samfaina.rings.sample

import android.app.Activity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.util.Log
import dev.samfaina.model.Ring
import dev.samfaina.model.RingBuilder
import dev.samfaina.RingsView

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val ringsView = findViewById<RingsView>(R.id.rings)

        ringsView.setDataset(mutableListOf(
                RingBuilder().setText("Javascript").setProgress(85f).setFilledColor(ContextCompat.getColor(this,R.color.colorAccent)).build(),
                RingBuilder().setText("PHP").setProgress(12f).setFilledColor(ContextCompat.getColor(this,R.color.colorPrimary)).build(),
                RingBuilder().setText("Java").setProgress(54f).setFilledColor(ContextCompat.getColor(this,R.color.colorPrimaryDark)).build()
        ))

        ringsView.addOnHighlightListener(object : RingsView.OnHighlightListener {
            override fun onHighlight(ring: Ring) {
                Log.d("RING", ring.toString())
            }

        })
    }
}