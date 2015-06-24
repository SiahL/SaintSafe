package edu.css.slyimo.saintsafe;

import java.util.ArrayList;
import java.util.Locale;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity implements ActionBar.TabListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        // Set up the action bar.
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // When swiping between different sections, select the corresponding
        // tab. We can also use ActionBar.Tab#select() to do this if we have
        // a reference to the Tab.
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by
            // the adapter. Also specify this Activity object, which implements
            // the TabListener interface, as the callback (listener) for when
            // this tab is selected.
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in
        // the ViewPager.
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).

            if (position == 0)
                return PlaceholderFragment.newInstance(position + 1);
            else if (position == 1)
                return PlaceholderFragment1.newInstance(position + 1);
            else if (position == 2)
                return PlaceholderFragment2.newInstance(position + 1);
            else
                return null;

        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);
                case 2:
                    return getString(R.string.title_section3).toUpperCase(l);
            }
            return null;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);


            //final ArrayList<GetUsers> searchResult = GetUsers();

            //final ListView lv = (ListView) rootView.findViewById(R.id.mainListView);
            //lv.setAdapter(new CustomBaseAdapter(getActivity(), searchResult));

           final ArrayList<GetUsers> searchResult = GetUsersList();
            Log.i("Array Size", " " + searchResult.size());

            ListView lv = (ListView) rootView.findViewById(R.id.mainListView);
            CustomBaseAdapter adapter = new CustomBaseAdapter(getActivity(), searchResult);
            lv.setAdapter(adapter);



            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String phoneNumber = searchResult.get(position).getPhone();
                    Intent dailIntent = new Intent(Intent.ACTION_DIAL);
                   // dailIntent.setData(Uri.parse("sms:"+phoneNumber));
                    dailIntent.setData(Uri.parse("tel: phoneNumber"));
                    startActivity(dailIntent);
                }

            });



            return rootView;
        }


    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment1 extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment1 newInstance(int sectionNumber) {
            PlaceholderFragment1 fragment = new PlaceholderFragment1();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment1() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_report, container, false);



        final ArrayList<GetReportTypes> searchResults = GetReportList();
        Log.i("Array Size", " " + searchResults.size());

        ListView lv = (ListView) rootView.findViewById(R.id.srListView);

       MyCustomBaseAdapter adapter = new MyCustomBaseAdapter(getActivity(), searchResults);
        /* Assign adapter to lv */
       /* lv.setAdapter(adapter);*/
       lv.setAdapter(adapter);

            return rootView;

        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment2 extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment2 newInstance(int sectionNumber) {
            PlaceholderFragment2 fragment = new PlaceholderFragment2();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment2() {

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_assist, container, false);

            Button emergency = (Button) rootView.findViewById(R.id.btMain);
            emergency.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Intent profile = new Intent(getActivity(), ProfileActivity.class);
                    //startActivity(profile);


                    sendSMSAlert("2187908058", "Emergency assistance requested");
                }
            });

            return rootView;
        }

        public void sendSMSAlert(String phoneNumber, String message) {
            ArrayList<PendingIntent> sentPendingIntents = new ArrayList<PendingIntent>();
            ArrayList<PendingIntent> deliveredPendingIntents = new ArrayList<PendingIntent>();
            PendingIntent sentPI = PendingIntent.getBroadcast(getActivity(), 0,
                    new Intent(getActivity(), SmsSentReceiver.class), 0);
            PendingIntent deliveredPI = PendingIntent.getBroadcast(getActivity(), 0,
                    new Intent(getActivity(), SmsDeliveredReceiver.class), 0);
            Toast.makeText(getActivity(), "SMS sending -delivered :)", Toast.LENGTH_SHORT).show();
            try {
                SmsManager sms = SmsManager.getDefault();
                ArrayList<String> mSMSMessage = sms.divideMessage(message);
                for (int i = 0; i < mSMSMessage.size(); i++) {
                    sentPendingIntents.add(i, sentPI);
                    deliveredPendingIntents.add(i, deliveredPI);
                }
                sms.sendMultipartTextMessage(phoneNumber, null, mSMSMessage,
                        sentPendingIntents, deliveredPendingIntents);

            } catch (Exception e) {

                e.printStackTrace();
                Toast.makeText(getActivity(), "SMS sending failed :(...", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public class SmsSentReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            switch (getResultCode()) {
                case Activity.RESULT_OK:
                    Toast.makeText(getApplicationContext(), "SMS delivered", Toast.LENGTH_SHORT).show();
                    break;
                case Activity.RESULT_CANCELED:
                    Toast.makeText(context, "SMS not delivered", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

    public class SmsDeliveredReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            switch (getResultCode()) {
                case Activity.RESULT_OK:
                    Toast.makeText(context.getApplicationContext(), "SMS Sent", Toast.LENGTH_SHORT).show();

                    break;
                case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                    Toast.makeText(context, "SMS generic failure", Toast.LENGTH_SHORT)
                            .show();

                    break;
                case SmsManager.RESULT_ERROR_NO_SERVICE:
                    Toast.makeText(context, "SMS no service", Toast.LENGTH_SHORT)
                            .show();
                    break;
                case SmsManager.RESULT_ERROR_NULL_PDU:
                    Toast.makeText(context, "SMS null PDU", Toast.LENGTH_SHORT).show();
                    break;
                case SmsManager.RESULT_ERROR_RADIO_OFF:
                    Toast.makeText(context, "SMS radio off", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }


    private static ArrayList GetReportList() {


        ArrayList results = new ArrayList();

        GetReportTypes sr1 = new GetReportTypes();
        sr1.setReportTitle("Jumpstart");

        sr1.setIcon(R.mipmap.jumpstart);
        results.add(sr1);

        sr1 = new GetReportTypes();
        sr1.setReportTitle("Noise Complaint");
        sr1.setIcon(R.mipmap.noise_complaint);
        results.add(sr1);

        sr1 = new GetReportTypes();
        sr1.setReportTitle("Room Lockout");
        sr1.setIcon(R.mipmap.room_lockout);
        results.add(sr1);

        sr1 = new GetReportTypes();
        sr1.setReportTitle("Emergency");
        sr1.setIcon(R.mipmap.call911);
        results.add(sr1);

        sr1 = new GetReportTypes();
        sr1.setReportTitle("Escort");
        sr1.setIcon(R.mipmap.escort);
        results.add(sr1);

        return results;


    }
    private static ArrayList GetUsersList() {


        ArrayList result = new ArrayList();

        GetUsers sr1 = new GetUsers();
        sr1.setName("General");
        sr1.setPhone("218-447-5444");
        sr1.setIcon(R.mipmap.contacts);
        result.add(sr1);

        sr1 = new GetUsers();
        sr1.setName("Security");
        sr1.setPhone("218-723-5937");
        sr1.setIcon(R.mipmap.contacts);
        result.add(sr1);

        return result;
    }
}
