package dev.samfaina.model

import android.graphics.Color
import android.graphics.RectF
import dev.samfaina.utils.getArcProgress


class Ring {
    var text: String = ""
    var progress: Float = 0f
        set(value) {
            field = getArcProgress(value)
        }


    var filledColor: Int = Color.parseColor("#E6E6E6")
    var unfinishedColor: Int = Color.GRAY
    var highlighted: Boolean = false
    var ringRect: RectF = RectF()
    var ringTextRect: RectF = RectF()
    var progressAnimValue: Float = 0f


    override fun toString(): String {
        return "Ring(text='$text', progress=$progress, filledColor=$filledColor, unfinishedColor=$unfinishedColor, highlighted=$highlighted, ringRect=$ringRect, ringTextRect=$ringTextRect, progressAnimValue=$progressAnimValue)"
    }


}


class RingBuilder {
    private var ring: Ring = Ring()

    fun build(): Ring {
        return ring
    }

    fun setText(text: String): RingBuilder {
        ring.text = text
        return this
    }

    fun setProgress(progress: Float): RingBuilder {
        ring.progress = progress
        return this
    }

    fun setFilledColor(color: Int): RingBuilder {
        ring.filledColor = color
        return this
    }

    fun setUnfinishedColor(color: Int): RingBuilder {
        ring.unfinishedColor = color
        return this
    }

    fun setHighlighted(highlighted: Boolean): RingBuilder {
        ring.highlighted = highlighted
        return this
    }

}