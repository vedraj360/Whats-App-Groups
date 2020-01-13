package infinitives.whatsgroups;

import android.app.Fragment;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;


public class MainActivity extends AppCompatActivity implements Groups.OnFragmentInteractionListener, aboutus.OnFragmentInteractionListener, addgroups.OnFragmentInteractionListener, moreapps.OnFragmentInteractionListener {

    private ActionBar toolbar;
    Groups groups;
    addgroups addgroups;
    moreapps moreapps;
    aboutus aboutus;

    MenuItem prevMenuItem;
    ViewPager viewPager;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.bgBottomNavigation));
        }
        viewPager = findViewById(R.id.viewpager);
        toolbar = getSupportActionBar();
        toolbar.setTitle("Groups");

        bottomNavigationView = findViewById(R.id.navigations);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.navigation_grp:
                                viewPager.setCurrentItem(0);
                                break;
                            case R.id.navigation_add:
                                viewPager.setCurrentItem(1);
                                return true;
                            case R.id.navigation_mor:
                                viewPager.setCurrentItem(2);
                                break;
                            case R.id.navigation_abt:
                                viewPager.setCurrentItem(3);
                                break;
                        }
                        return false;
                    }
                });
        /*
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) bottomNavigationView.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationBehavior());
*/
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                switch (position){
                    case 0:
                        toolbar.setTitle("Groups");
                        break;
                    case 1:
                        toolbar.setTitle("Add Group");
                        break;
                    case 2:
                        toolbar.setTitle("More Apps");
                        break;
                    case 3:
                        toolbar.setTitle("About Us");
                        break;

                        default:

                }

            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                } else {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                Log.d("page", "onPageSelected: " + position);
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                prevMenuItem = bottomNavigationView.getMenu().getItem(position);
                Log.d("prev", "page " + prevMenuItem);


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

       /*  //Disable ViewPager Swipe

       viewPager.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                return true;
            }
        });

        */

        setupViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewpageAdapter adapter = new ViewpageAdapter(getSupportFragmentManager());
        groups = new Groups();
        addgroups = new addgroups();
        moreapps = new moreapps();
        aboutus = new aboutus();
        adapter.addfragment(groups);
        adapter.addfragment(addgroups);
        adapter.addfragment(moreapps);
        adapter.addfragment(aboutus);
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
