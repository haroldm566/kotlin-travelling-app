package op.mobile.app.dev.mandha1.travelling

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val toolbar: Toolbar = findViewById(R.id.toolbar) // Find the View in activity_main.xml with the id toolbar
        setSupportActionBar(toolbar) // Set toolbar as the entire application's action bar
    }
}