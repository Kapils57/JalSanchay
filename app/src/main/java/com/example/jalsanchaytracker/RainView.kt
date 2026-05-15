package com.example.jalsanchaytracker

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random

class RainView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val drops = mutableListOf<RainDrop>()
    private val dropCount = 60
    private var initialized = false

    // Background gradient colors
    private val bgPaint = Paint()

    init {
        bgPaint.color = Color.parseColor("#0D47A1")
    }

    data class RainDrop(
        var x: Float,
        var y: Float,
        var speed: Float,
        var length: Float,
        var alpha: Int
    )

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        if (!initialized && w > 0 && h > 0) {
            repeat(dropCount) {
                drops.add(
                    RainDrop(
                        x = Random.nextFloat() * w,
                        y = Random.nextFloat() * h,
                        speed = Random.nextFloat() * 8f + 4f,
                        length = Random.nextFloat() * 30f + 15f,
                        alpha = Random.nextInt(80, 200)
                    )
                )
            }
            initialized = true
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Draw dark blue background
        canvas.drawColor(Color.parseColor("#81D4FA"))

        // Draw rain drops
        paint.strokeWidth = 2f
        paint.style = Paint.Style.STROKE

        drops.forEach { drop ->
            paint.color = Color.argb(drop.alpha, 2, 136, 209)
            // Draw angled raindrop line
            canvas.drawLine(
                drop.x, drop.y,
                drop.x - 2f, drop.y + drop.length,
                paint
            )

            // Move drop down
            drop.y += drop.speed
            drop.x -= 1f

            // Reset drop to top when it goes off screen
            if (drop.y > height) {
                drop.y = -drop.length
                drop.x = Random.nextFloat() * width
            }
        }

        // Keep animating
        postInvalidateOnAnimation()
    }
}