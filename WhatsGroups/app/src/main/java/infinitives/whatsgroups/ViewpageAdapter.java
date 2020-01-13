package infinitives.whatsgroups;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ved&Div on 29-12-2017.
 */

public class ViewpageAdapter extends FragmentPagerAdapter {
    private final List<Fragment> flist = new ArrayList<>();

    public ViewpageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
               return flist.get(position);
    }

    @Override
    public int getCount() {
        return flist.size();
    }

    public void addfragment(Fragment fragment)
    {
        flist.add(fragment);
    }
}
