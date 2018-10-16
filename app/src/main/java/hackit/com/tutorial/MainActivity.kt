package hackit.com.tutorial

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.mikepenz.materialdrawer.AccountHeader
import com.mikepenz.materialdrawer.AccountHeaderBuilder
import com.mikepenz.materialdrawer.DrawerBuilder
import com.mikepenz.materialdrawer.model.ProfileDrawerItem
import com.mikepenz.materialdrawer.model.interfaces.Nameable



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar.setTitle("Tutorial")
        var header =  AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.header)
                .addProfiles(
                        ProfileDrawerItem().withName("Mike Penz").withEmail("mikepenz@gmail.com").withIcon(resources.getDrawable(R.drawable.profile))
                )
                .withOnAccountHeaderListener(AccountHeader.OnAccountHeaderListener { view, profile, currentProfile -> false })
                .build()
        //Create the drawer
         DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar).
                 withAccountHeader(header)
                .inflateMenu(R.menu.example_menu)
                .withOnDrawerItemClickListener { view, position, drawerItem ->
                    if (drawerItem is Nameable<*>) {
                        Toast.makeText(this@MainActivity, (drawerItem as Nameable<*>).name.getText(this@MainActivity), Toast.LENGTH_SHORT).show()
                    }
                    false
                }.build()
        createProfile()

    }

    fun createProfile() {
    }

}
