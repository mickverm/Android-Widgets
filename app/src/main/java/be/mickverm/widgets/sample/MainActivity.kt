package be.mickverm.widgets.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import be.mickverm.widgets.sample.recyclerview.ui.sectioned.SectionedItemsFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, SectionedItemsFragment.newInstance())
                .commitNow()
        }
    }
}
