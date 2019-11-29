package dev.samfaina

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.samfaina.rings.R
import dev.samfaina.model.Chart
import dev.samfaina.model.Ring
import dev.samfaina.utils.isInSweep
import dev.samfaina.utils.isOnRing


class RingsView(context: Context, attrs: AttributeSet) : View(context, attrs), ChartManager.AnimationListener {

    private var chartManager: ChartManager
    private var chart: Chart
    private var listener: OnHighlightListener? = null


    /**
     * Set all the rings clickable.
     */
    private var areRingsClickable = true


    init {
        val attributes = context.theme.obtainStyledAttributes(attrs, R.styleable.RingsView, 0, 0)
        chart = Chart(context, attributes)
        attributes.recycle()
        chartManager = ChartManager(chart, this)
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec)

        for ((index, ring) in chart.dataSet.withIndex()) {
            setupRingDimensions(index, ring, widthMeasureSpec, heightMeasureSpec)
        }

    }


    override fun onDraw(canvas: Canvas) {
        chart.viewWidth = width
        chartManager.drawer.drawRings(canvas)
    }


    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (areRingsClickable) {

            when (event.action) {
                MotionEvent.ACTION_UP -> {
                    for (ring in chart.dataSet) {
                        ring.highlighted = isOnRing(event, ring.ringRect, chart.attrs.outerStrokeWidth) && isInSweep(event, ring.ringRect)
                                || ring.ringTextRect.contains(event.x, event.y)
                        if (ring.highlighted) {
                            listener?.onHighlight(ring)
                        }

                    }

                    invalidate()
                }
            }
            return true
        }
        return false
    }

    private fun setupRingDimensions(index: Int, ring: Ring, widthMeasureSpec: Int, heightMeasureSpec: Int) {
        if (index == 0) {
            ring.ringRect.set(
                    chart.attrs.outerStrokeWidth / 2f,
                    chart.attrs.outerStrokeWidth / 2f,
                    MeasureSpec.getSize(widthMeasureSpec) - (chart.attrs.outerStrokeWidth / 2f),
                    MeasureSpec.getSize(heightMeasureSpec) - (chart.attrs.outerStrokeWidth / 2f)
            )
        } else {
            ring.ringRect.set(
                    chart.dataSet[index - 1].ringRect.left + (chart.attrs.outerStrokeWidth + chart.attrs.innerStrokeWidth),
                    chart.dataSet[index - 1].ringRect.top + (chart.attrs.outerStrokeWidth + chart.attrs.innerStrokeWidth),
                    chart.dataSet[index - 1].ringRect.right - (chart.attrs.outerStrokeWidth + chart.attrs.innerStrokeWidth),
                    chart.dataSet[index - 1].ringRect.bottom - (chart.attrs.outerStrokeWidth + chart.attrs.innerStrokeWidth)
            )
        }
    }


    override fun onAnimationUpdated() {
        invalidate()
    }

    fun setDataset(rings: List<Ring>) {
        chart.dataSet = rings
        post {
            chartManager.animate()
        }
    }

    fun addOnHighlightListener(listener: OnHighlightListener) {
        this.listener = listener
    }


    interface OnHighlightListener {
        fun onHighlight(ring: Ring) = Unit
    }

}