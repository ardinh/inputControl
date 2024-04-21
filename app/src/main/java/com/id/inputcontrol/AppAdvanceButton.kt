package com.id.inputcontrol

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible

class AppAdvanceButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0
): LinearLayout(context, attrs, defStyle, defStyleRes) {

    var drawableStartView: ImageView
    var drawableEndView: ImageView
    var labelView: TextView
    var valueView: TextView
    var containerView: ConstraintLayout

    var textValue: String? = null
        set(value) {
            field = value
            valueView.text = field
        }

    override fun setEnabled(enabled: Boolean) {
        if (enabled) {
            containerView.background = ContextCompat.getDrawable(context, R.drawable.bg_blue_20_border_8)
        } else {
            containerView.background = ContextCompat.getDrawable(context, R.drawable.bg_grey_60_border_8)
        }
        super.setEnabled(enabled)
    }


    init {
        LayoutInflater.from(context).inflate(R.layout.template_advance_button, this, true)
        drawableStartView = findViewById(R.id.ic_start)
        drawableEndView = findViewById(R.id.ic_end)
        labelView = findViewById(R.id.tv_label)
        labelView.setTextColor(ContextCompat.getColor(context, R.color.black))
        valueView = findViewById(R.id.tv_value)
        containerView = findViewById(R.id.container_view)

        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it,
                R.styleable.AppAdvanceButton, 0, 0)

            try {
                val label = resources.getText(typedArray
                    .getResourceId(R.styleable.AppAdvanceButton_label, R.string.empty))

                val hint = resources.getText(typedArray
                    .getResourceId(R.styleable.AppAdvanceButton_hint, R.string.empty))

                val value = resources.getText(typedArray
                    .getResourceId(R.styleable.AppAdvanceButton_text, R.string.empty))

                val enabled = typedArray.getBoolean(R.styleable.AppAdvanceButton_enabled, true)

                val tint = resources.getColor(typedArray
                                    .getResourceId(R.styleable.AppAdvanceButton_drawableTint, R.color.blue_100), null)

                val tintEnd = resources.getColor(typedArray
                                    .getResourceId(R.styleable.AppAdvanceButton_drawableTintEnd, R.color.blue_100), null)

                drawableStartView.setColorFilter(tint)
                drawableEndView.setColorFilter(tintEnd)
                val drawableStart = typedArray.getDrawable(R.styleable.AppAdvanceButton_drawableStart)
                val drawableEnd = typedArray.getDrawable(R.styleable.AppAdvanceButton_drawableEnd)

                if (enabled) {
                    containerView.background = ContextCompat.getDrawable(context, R.drawable.bg_blue_20_border_8)
                } else {
                    containerView.background = ContextCompat.getDrawable(context, R.drawable.bg_grey_60_border_8)
                }
                setDrawableEnd(drawableEnd)
                setDrawableStart(drawableStart)

                labelView.text = label
                valueView.hint = hint
                textValue = value.toString()

            } finally {
                typedArray.recycle()
            }
        }
    }

    fun setDrawableStart(drawable: Drawable?) {
        if (drawable != null) {
            drawableStartView.isVisible = true
            drawableStartView.setImageDrawable(drawable)
        }
    }

    fun setDrawableEnd(drawable: Drawable?) {
        if (drawable != null) {
            drawableEndView.isVisible = true
            drawableEndView.setImageDrawable(drawable)
        }
    }

    fun setHint(text: String) {
        valueView.hint = text
    }

    fun setLabel(text: String) {
        labelView.text = text
    }
}