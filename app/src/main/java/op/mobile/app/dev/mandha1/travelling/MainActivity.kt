package op.mobile.app.dev.mandha1.travelling

import android.content.Context
import android.content.SharedPreferences
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    private var NightMode: Int = 0
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

//    val NightMode: Int
//    val sharedPreferences: SharedPreferences
//    var editor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        //  Allows the bottom navigation to work - going between home, dashboard and notifications fragments
//        val btmNavView: BottomNavigationView = findViewById(R.id.btm_nav_view)
//        btmNavView.setupWithNavController(navController)

        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        //  Testing that the API key is being shown successfully
        //  from the local.properties file
        // Add YANDEX_API_KEY=<KEY> to local.properties first
//        val ai: ApplicationInfo = applicationContext.packageManager
//            .getApplicationInfo(applicationContext.packageName, PackageManager.GET_META_DATA)
//        val value = ai.metaData["keyValue"]
//        val key = value.toString()
//        Log.d("APIKEY", key)

        sharedPreferences = getSharedPreferences("SharedPrefs", MODE_PRIVATE);
        NightMode = sharedPreferences.getInt("NightModeInt", 1);
        AppCompatDelegate.setDefaultNightMode(NightMode);
    }

    override fun onSupportNavigateUp(): Boolean {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    //  Allows set theme to persist even when app's closed
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        NightMode = AppCompatDelegate.getDefaultNightMode()
        sharedPreferences = getSharedPreferences("SharedPrefs", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
        editor.putInt("NightModeInt", NightMode)
        editor.apply()
    }
}