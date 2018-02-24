package bus.yywang.com.myapplication;


import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends FragmentActivity implements GameFragment.OnFragmentInteractionListener {

    private TextView mTextMessage;
    ViewPager viewPager;
    private TabFragmentPagerAdapter adapter;
    private static String CURRENT_ITEM = "current_item";
    BottomNavigationView navigation;
    HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Log.i(TAG,"itemid="+item.getItemId()+"order="+item.getOrder());


            switch (item.getItemId()) {
                case R.id.navigation_home:
                    viewPager.setCurrentItem(0);

                    return true;
                case R.id.navigation_dashboard:
                    viewPager.setCurrentItem(1);

                    return true;
                case R.id.navigation_notifications:
                    viewPager.setCurrentItem(2);

                    return true;

            }
            return false;
        }
    };
    private String TAG ="demo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        viewPager =(ViewPager) findViewById(R.id.myViewPager);
        adapter = new TabFragmentPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new MyOnPageChangeListener() );
        int currentitem =savedInstanceState ==null?0: savedInstanceState.getInt(CURRENT_ITEM) ;
        Log.i(TAG,"currentitem="+currentitem);
        viewPager.setCurrentItem(currentitem);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        map.put(0,R.id.navigation_home);
        map.put(1,R.id.navigation_dashboard);
        map.put(2,R.id.navigation_notifications);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(CURRENT_ITEM,viewPager.getCurrentItem());
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


    private class MyOnPageChangeListener implements ViewPager.OnPageChangeListener{

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


            Log.i(TAG,"$onPageScrolled position="+position + ",positionOffset="+positionOffset);
        }

        @Override
        public void onPageSelected(int position) {
            Log.i(TAG,"$onPageSelected position="+position);
            navigation.setSelectedItemId(map.get(position));
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            Log.i(TAG,"$onPageScrollStateChanged state="+state);
        }

    }

    private class TabFragmentPagerAdapter extends FragmentPagerAdapter{
    ArrayList<android.support.v4.app.Fragment> list;
    public TabFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        list = new ArrayList<Fragment>();
        list.add(new HomeFragment());
        list.add(new GameFragment());
        list.add(new ToolFragment());

    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}

}
