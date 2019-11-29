package dev.samfaina.attrs


/**
 * Style attributes holder
 */
data class Attrs(
        var textSize: Float = 0f,
        var textMarginLeft: Float = 0f,
        var innerStrokeWidth: Float = 0f,
        var innerStrokeWidthUnfinished: Float = 0f,
        var outerStrokeWidth: Float = 0f,
        var outerStrokeWidthUnfinished: Float = 0f,
        var ringUnfinishedColor: Int = 0,
        var ringFilledColor: Int = 0
)