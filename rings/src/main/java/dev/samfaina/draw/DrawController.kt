package dev.samfaina.draw

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.Log
import dev.samfaina.model.Chart
import dev.samfaina.model.Ring
import dev.samfaina.utils.AngleValues.Companion.EMPTY_ANGLE
import dev.samfaina.utils.AngleValues.Companion.START_ANGLE


class DrawController(private var chart: Chart) {

    private var textPaint: Paint = Paint()
    private var ringPaint: Paint = Paint()
    private var auxRect: Rect = Rect()


    init {
        // Ring paint
        ringPaint.isAntiAlias = true
        ringPaint.style = Paint.Style.STROKE
        ringPaint.strokeCap = Paint.Cap.ROUND

        // Text Paint
        textPaint.textSize = chart.attrs.textSize
        textPaint.isAntiAlias = true
    }


    fun drawRings(canvas: Canvas) {
        if (chart.dataSet.isNullOrEmpty()) {
            return
        }
        chart.dataSet.forEach { ring ->
            drawBackgroundRing(canvas, ring)
            setupTextRect(ring)
            updatePainters(ring)

            canvas.drawText(ring.text, chart.viewWidth / 2f + chart.attrs.textMarginLeft, ring.ringRect.bottom + chart.attrs.innerStrokeWidth / 2, textPaint)
            canvas.drawArc(ring.ringRect, START_ANGLE, ring.progressAnimValue, false, ringPaint)
        }
    }


    private fun drawBackgroundRing(canvas: Canvas, ring: Ring) {
        ringPaint.color = chart.attrs.ringUnfinishedColor
        ringPaint.strokeWidth = chart.attrs.outerStrokeWidthUnfinished
        canvas.drawArc(ring.ringRect, START_ANGLE, EMPTY_ANGLE, false, ringPaint)
    }

    private fun setupTextRect(ring: Ring) {
        textPaint.getTextBounds(ring.text, 0, ring.text.length, auxRect)
        ring.ringTextRect.set(
                chart.viewWidth / 2 + chart.attrs.textMarginLeft,
                ring.ringRect.bottom - chart.attrs.textSize,
                chart.viewWidth / 2 + auxRect.width() + chart.attrs.textMarginLeft,
                ring.ringRect.bottom + chart.attrs.innerStrokeWidth
        )
    }

    private fun updatePainters(ring: Ring) {
        textPaint.color = ring.filledColor
        ringPaint.color = ring.filledColor

        if (ring.highlighted) {
            ringPaint.strokeWidth = chart.attrs.highlightWidth
        } else {
            ringPaint.strokeWidth = chart.attrs.innerStrokeWidth
        }
    }
}