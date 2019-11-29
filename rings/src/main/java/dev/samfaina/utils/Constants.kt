package dev.samfaina.utils

import android.graphics.Color




class AngleValues {
    companion object{
        const val START_ANGLE = 90f
        const val EMPTY_ANGLE = 270f
    }
}

class DefaultValues {
    companion object{
        const val DEFAULT_TEXT_SIZE: Float = 18f
        const val DEFAULT_TEXT_MARGIN: Float = 10f
        const val DEFAULT_INNER_STROKE_WIDTH: Float = 10f
        const val DEFAULT_INNER_STROKE_UNFINISHED_WIDTH: Float = 10f
        const val DEFAULT_OUTER_STROKE_WIDTH: Float = 12f
        const val DEFAULT_OUTER_STROKE_UNFINISHED_WIDTH: Float = 10f
        var DEFAULT_RING_UNFINISHED_COLOR: Int = Color.GRAY
        const val DEFAULT_RING_COLOR: Int = Color.DKGRAY
    }
}


class AnimationValues {
    companion object{
        const val ANIM_DURATION: Long = 350
        const val ANIM_NAME = "RING_PROGRESS"
    }
}