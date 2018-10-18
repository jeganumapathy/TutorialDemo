package hackit.com.tutorial

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.mikepenz.materialdrawer.AccountHeader
import com.mikepenz.materialdrawer.AccountHeaderBuilder
import com.mikepenz.materialdrawer.DrawerBuilder
import com.mikepenz.materialdrawer.model.interfaces.Nameable


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar.setTitle("Tutorial")
        var header = AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.header)
                .addProfiles(
                        // ProfileDrawerItem().withName("Mike Penz").withEmail("mikepenz@gmail.com").withIcon(resources.getDrawable(R.drawable.profile))
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
                        when ((drawerItem as Nameable<*>).name.text) {
                            (getString(R.string.Movies)) ->
                                setLayout(MoviesFragment())
                            (getString(R.string.top_trends)) ->
                                setLayout(TopTrendsFragment())
                            (getString(R.string.tv_shows)) ->
                                setLayout(TvShowsFragment())
                            (getString(R.string.favorites)) ->
                                setLayout(FavoritesFragment())
                        }
                    }
                    false
                }.build()
        setLayout(MoviesFragment())
    }

    private fun setLayout(fragment: Fragment) {
        val ft = supportFragmentManager.beginTransaction()
        ft.add(R.id.frame_container, fragment)
        ft.addToBackStack(null)
        ft.commit()
    }

}
