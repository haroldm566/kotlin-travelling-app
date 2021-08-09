package op.mobile.app.dev.mandha1.travelling

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar) // Find the View in activity_main.xml with the id toolbar
        setSupportActionBar(toolbar) // Set toolbar as the entire application's action bar

        val btmNavView: BottomNavigationView = findViewById(R.id.btm_nav_view)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        btmNavView.setupWithNavController(navHostFragment.navController)

    }
}