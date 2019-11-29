package dev.samfaina.animation

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.util.Log
import android.view.animation.AccelerateDecelerateInterpolator
import dev.samfaina.model.Chart
import dev.samfaina.model.Ring
import dev.samfaina.utils.AnimationValues.Companion.ANIM_DURATION
import dev.samfaina.utils.AnimationValues.Companion.ANIM_NAME


class AnimationManager(var chart: Chart, var listener: AnimationListener?) {


    private val animatorSet: AnimatorSet = AnimatorSet()
    private var lastValue: Float = -1f

    interface AnimationListener {
        fun onAnimationUpdated(value: Float)
    }

    fun animate() {
        animatorSet.playSequentially(createAnimatorList())
        animatorSet.start()
    }

    private fun createAnimatorList(): List<Animator>? {
        return chart.dataSet.map { createAnimator(it) }

    }

    private fun createAnimator(ring: Ring): ValueAnimator {
        val ringProgressValue: PropertyValuesHolder = PropertyValuesHolder.ofFloat(ANIM_NAME, 0f, ring.progress)
        val animator = ValueAnimator()
        animator.setValues(ringProgressValue)
        animator.duration = ANIM_DURATION
        animator.interpolator = AccelerateDecelerateInterpolator()
        animator.addUpdateListener { valueAnimator -> this@AnimationManager.onAnimationUpdate(ring, valueAnimator) }
        return animator
    }


    private fun onAnimationUpdate(ring: Ring, valueAnimator: ValueAnimator?) {

        if (valueAnimator == null || listener == null) {
            return
        }
        val progress = valueAnimator.getAnimatedValue(ANIM_NAME) as Float
        ring.progressAnimValue = progress
        Log.d(ring.text, progress.toString())
        lastValue = progress
        listener?.onAnimationUpdated(progress)
    }

}

