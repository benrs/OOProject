package ca.qc.johnabbott.cs603;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;

public class startupActivity extends ActionBarActivity {
    private SlidingTabLayout mSlidingTabLayout;
    private ViewPager mViewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);

        mViewPager = (ViewPager) findViewById(R.id.startupPager);
        mViewPager.setAdapter(new StartupAdapter(getSupportFragmentManager()));
        mSlidingTabLayout = (SlidingTabLayout) findViewById(R.id.sliding_tabs);
        mSlidingTabLayout.setDistributeEvenly(true);
        mSlidingTabLayout.setViewPager(mViewPager);

        //setting indicator and divider color
        mSlidingTabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.colorSecondary);    //define any color in xml resources and set it here, I have used white
            }
        });
    }

    public class StartupAdapter extends FragmentStatePagerAdapter {
        private String[] titles = {"Login", "Register" };

        public StartupAdapter(FragmentManager fm) { super(fm); }

        @Override
        public Fragment getItem(int i) {
            switch (i) {
                case 0:
                    return new LoginFragment();
                case 1:
                    return new CreateFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }
}
