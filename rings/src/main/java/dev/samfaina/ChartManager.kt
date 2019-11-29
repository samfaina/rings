package dev.samfaina

import dev.samfaina.animation.AnimationManager
import dev.samfaina.draw.DrawController
import dev.samfaina.model.Chart

class ChartManager(var chart: Chart, var listener: AnimationListener?) : AnimationManager.AnimationListener {


    var drawer: DrawController = DrawController(chart)
    private var animationManager: AnimationManager = AnimationManager(chart, this)

    interface AnimationListener {
        fun onAnimationUpdated()
    }


    fun animate() {
        if (!chart.dataSet.isNullOrEmpty()) {
            animationManager.animate()
        }
    }


    override fun onAnimationUpdated(value: Float) {
        listener?.onAnimationUpdated()
    }

}