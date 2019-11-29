package dev.samfaina.model

import android.content.Context
import android.content.res.Resources
import android.content.res.TypedArray
import com.samfaina.rings.R
import dev.samfaina.attrs.Attrs
import dev.samfaina.utils.DefaultValues

class Chart(context: Context, attributes: TypedArray) {

    var dataSet: List<Ring> = listOf()
    var attrs: Attrs = getAttrsModel(context, attributes)
    var viewWidth: Int = 0


    private fun getAttrsModel(context: Context, attributes: TypedArray): Attrs {
        return Attrs(
                attributes.getDimension(R.styleable.RingsView_rings_text_size, sp2px(context.resources, DefaultValues.DEFAULT_TEXT_SIZE)),
                attributes.getDimension(R.styleable.RingsView_rings_text_margin_left, sp2px(context.resources, DefaultValues.DEFAULT_TEXT_MARGIN)),
                attributes.getDimension(R.styleable.RingsView_rings_inner_stroke_width, sp2px(context.resources, DefaultValues.DEFAULT_INNER_STROKE_WIDTH)),
                attributes.getDimension(R.styleable.RingsView_rings_inner_stroke_width_unfinished, sp2px(context.resources, DefaultValues.DEFAULT_INNER_STROKE_UNFINISHED_WIDTH)),
                attributes.getDimension(R.styleable.RingsView_rings_outer_stroke_width, sp2px(context.resources, DefaultValues.DEFAULT_OUTER_STROKE_WIDTH)),
                attributes.getDimension(R.styleable.RingsView_rings_outer_stroke_width_unfinished, sp2px(context.resources, DefaultValues.DEFAULT_OUTER_STROKE_UNFINISHED_WIDTH)),
                attributes.getColor(R.styleable.RingsView_rings_unfinished_color, DefaultValues.DEFAULT_RING_UNFINISHED_COLOR),
                attributes.getColor(R.styleable.RingsView_rings_default_filled_color, DefaultValues.DEFAULT_RING_COLOR)
        )
    }


    /**
     * Convert the specified dimen value to pixels, used for fonts, not views.
     *
     * @param resources An instance of Android [Resources]
     * @param sp        A dimen value specified in sp
     * @return A float value corresponding to the specified sp converted to pixels
     */

    private fun sp2px(resources: Resources, sp: Float): Float {
        val scale = resources.displayMetrics.scaledDensity
        return sp * scale
    }


}