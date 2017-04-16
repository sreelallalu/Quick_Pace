package admin.nyesteveture.user.admin_case;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

/**
 * Created by lalu on 4/15/2017.
 */
public class DepartmentMAin extends AppCompatActivity {
    String _userId,_userName;
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.departmentmain);
        Intent io=getIntent();
        _userId=io.getStringExtra("iduser");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new DepartmentMAin.SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container1);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs1);
        tabLayout.setupWithViewPager(mViewPager);


    }
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment=null;

            if(position==0)
            {
                fragment=new ViewNotification();


            }else if(position==1)
            {
                fragment=new ViewRequest();

            }
            else if(position==2)
            {
                fragment=new ViewPhoto();

            }

            return fragment;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3 ;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "NOTIFICATION";
                case 1:
                    return "REQUEST";
                case 2:
                    return "VIEW PHOTO";

            }
            return null;
        }
    }
    boolean exit=false;
    @Override
    public void onBackPressed() {
        if (exit)
            System.exit(0);
        else {
            Toast.makeText(this, "Press Back again to Exit.",
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);

        }

    }
}
