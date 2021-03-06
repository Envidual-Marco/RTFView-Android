package com.envidual.rtfview.components

import android.graphics.drawable.Drawable
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.appcompat.widget.LinearLayoutCompat.HORIZONTAL
import androidx.core.view.MarginLayoutParamsCompat
import com.envidual.rtfview.callback.RTFCallback
import com.envidual.rtfview.common.toPx
import com.envidual.rtfview.core.RTFBuild
import com.envidual.rtfview.model.Token

class ButtonWrapper(
    private val callback: RTFCallback,
    private val label: RTFBuild,
    private val drawable: Drawable
): RTFBuild {

    override fun build(tokens: List<Token>): View {
        val view = label.build(tokens)
        val layout = LinearLayoutCompat(view.context)
        val imageView = AppCompatImageView(view.context)

        view.layoutParams = view.layoutParams.let {
            when (it) {
                null -> LinearLayoutCompat.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
                is ViewGroup.MarginLayoutParams ->  LinearLayoutCompat.LayoutParams(it)
                else -> LinearLayoutCompat.LayoutParams(it)
            }
        }.apply { weight = 1f }

        imageView.setImageDrawable(drawable)
        imageView.layoutParams = LinearLayoutCompat.LayoutParams(24.toPx(), 24.toPx())

        layout.gravity = Gravity.CENTER
        layout.orientation = HORIZONTAL
        layout.addView(view)
        layout.addView(imageView)
        layout.setOnClickListener { callback.event(tokens.first(), view) }

        return layout
    }

}