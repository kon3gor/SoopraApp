package org.eshendo.soopra.ui.custom

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class CircularRatingView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var startColor = Color.parseColor("#2BED9C")
    private var endColor = Color.parseColor("#FFE600")

    private lateinit var gradient: SweepGradient
    private var gradientMatrix = Matrix()

    private val sWidth = 20f

    private val progressColor = Paint().apply {
        isDither = true
        style = Paint.Style.STROKE
        strokeCap = Paint.Cap.ROUND
        strokeWidth = sWidth
        isAntiAlias = true
    }


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        val middleX = (0f+width/2)
        val middleY = (0f+height)/2
        gradientMatrix.postRotate(-80f, middleX, middleY)
        gradient = SweepGradient(middleX, middleY, startColor, endColor).apply {
            setLocalMatrix(gradientMatrix)
        }
        progressColor.shader = gradient
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawArc(sWidth, sWidth, this.width.toFloat() - sWidth, this.height.toFloat() - sWidth,
            -90f, -270f, false, progressColor)

    }
}