package com.hci_420_620.ComputerSushiInteraction;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class OrderPagerAdapter extends FragmentPagerAdapter{
	private static int NUM_ITEMS = 2;
	
	public OrderPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    // Returns total number of pages
    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: // Fragment # 0 - This will show FirstFragment
                return OrderFragment.newInstance(0, "Current Order");
            case 1: // Fragment # 0 - This will show FirstFragment different title
                return OrderFragment.newInstance(1, "Total Order");
            default:
                return null;
        }
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
    	 switch (position) {
             case 0: // Fragment # 0 - This will show FirstFragment
                 return "Current Order";
             case 1: // Fragment # 0 - This will show FirstFragment different title
                 return "Total Order";
             default:
            	 return null;
    	 }
    }
    
    public static String GetFragmentTag(int viewID, int index){
    	return "android:switcher:" + viewID + ":" + index;
    }

}