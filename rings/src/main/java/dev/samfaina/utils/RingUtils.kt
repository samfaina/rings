package dev.samfaina.utils

import android.graphics.RectF
import android.view.MotionEvent
import kotlin.math.abs
import kotlin.math.atan2
import kotlin.math.pow
import kotlin.math.sqrt


fun isOnRing(event: MotionEvent, bounds: RectF, strokeWidth: Float): Boolean { // Figure the distance from center point to touch point.
    val distance = distance(event.x, event.y,
            bounds.centerX(), bounds.centerY())
    // Assuming square bounds to figure the radius.
    val radius = bounds.width() / 2f
    // The Paint stroke is centered on the circumference,
    // so the tolerance is half its width.
    val halfStrokeWidth = strokeWidth / 2f
    // Compare the difference to the tolerance.
    return abs(distance - radius) <= halfStrokeWidth
}

fun distance(x1: Float, y1: Float, x2: Float, y2: Float): Float {
    return sqrt((x1 - x2.toDouble()).pow(2.0) + (y1 - y2.toDouble()).pow(2.0)).toFloat()
}

fun isInSweep(event: MotionEvent, bounds: RectF): Boolean { // Figure atan2 angle.
    val at = Math.toDegrees(atan2(event.y - bounds.centerY().toDouble(), event.x - bounds.centerX().toDouble())).toFloat()
    // Convert from atan2 to standard angle.
    val angle = (at + 360) % 360
    // Check if in sweep.
    return angle >= AngleValues.START_ANGLE && angle <= AngleValues.START_ANGLE + AngleValues.EMPTY_ANGLE
}


fun getArcProgress(progress: Float): Float {
    return (AngleValues.EMPTY_ANGLE / 100f) * progress
}
