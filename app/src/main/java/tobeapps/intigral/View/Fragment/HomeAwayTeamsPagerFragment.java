package tobeapps.intigral.View.Fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import tobeapps.intigral.R;


public class HomeAwayTeamsPagerFragment extends Fragment {
    private Adapter adapter;
    private TabLayout tabs;
    private ViewPager viewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_home_away_pager, container, false);
        tabs = view.findViewById(R.id.result_tabs);
        viewPager = view.findViewById(R.id.viewpager);
        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {

            }
        });

        initilize();
        return view;

    }

    private void initilize() {
        setupViewPager(viewPager);
        tabs.setupWithViewPager(viewPager);
        ViewGroup vg = (ViewGroup) tabs.getChildAt(0);
        int tabsCount = vg.getChildCount();

    }

    // Add Fragments to Tabs
    private void setupViewPager(ViewPager viewPager) {

        adapter = new Adapter(getChildFragmentManager());
        adapter.addFragment(new AwayTeamFragment(), getResources().getString(R.string.away_team_title));
        adapter.addFragment(new HomeTeamFragment(), getResources().getString(R.string.home_team_title));
        viewPager.setAdapter(adapter);


    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public Adapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {

            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);

            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}
