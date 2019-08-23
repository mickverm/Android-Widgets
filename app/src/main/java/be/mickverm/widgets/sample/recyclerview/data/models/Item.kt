package be.mickverm.widgets.sample.recyclerview.data.models

import android.graphics.Color
import androidx.annotation.ColorInt
import kotlin.random.Random

data class Item(
    val id: Int,
    val text: String,
    @ColorInt val color: Int
) {
    companion object {
        fun random(random: Random, id: Int): Item {

            val first = (random.nextInt(25) + 65).toChar()
            val second = (random.nextInt(25) + 65).toChar()
            val third = (random.nextInt(25) + 65).toChar()

            return Item(
                id,
                String(charArrayOf(first, second, third)),
                Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256))
            )
        }
    }
}
