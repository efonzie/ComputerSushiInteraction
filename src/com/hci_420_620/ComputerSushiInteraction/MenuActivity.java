package com.hci_420_620.ComputerSushiInteraction;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;
import android.widget.Toast;

public class MenuActivity extends FragmentActivity {

	String strCurrentOrder = "Current Order";
	String strTotalOrder = "Total Order";
	
	FragmentPagerAdapter adapterViewPager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		
		//This initializes the OrderPager to handle the "current" list and the "total" lists
		ViewPager vpPager = (ViewPager) findViewById(R.id.orderViewPager);
		adapterViewPager = new OrderPagerAdapter(getSupportFragmentManager());
		vpPager.setAdapter(adapterViewPager);
		
		vpPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int pos) {
				updateTabText(pos);
				
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				
			}
			
			
		});
		
		TextView currentOrder = (TextView) this.findViewById(R.id.currentOrderTab);
		currentOrder.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				goToCurrentOrder();
				return false;
			}
		});
		
		TextView totalOrder = (TextView) this.findViewById(R.id.totalOrderTab);
		totalOrder.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				goToTotalOrder();
				return false;
			}
		});
		
	}
	
	public void goToCurrentOrder(){
		ViewPager vpPager = (ViewPager) findViewById(R.id.orderViewPager);
		vpPager.setCurrentItem(0);
	}
	
	public void goToTotalOrder(){
		ViewPager vpPager = (ViewPager) findViewById(R.id.orderViewPager);
		vpPager.setCurrentItem(1);
	}
	
	public void updateTabText(int pos){
		TextView currentOrder = (TextView) this.findViewById(R.id.currentOrderTab);
		TextView totalOrder = (TextView) this.findViewById(R.id.totalOrderTab);
		
		int col2 = 0xFF999999;
		int col1 = 0xFF000000;
		
		if(pos==1){
			int a = col1;
			col1=col2;
			col2=a;
		}
		currentOrder.setTextColor(col1);
		totalOrder.setTextColor(col2);
	}
	
	public void checkout(ArrayList<MenuItem> items){
		
	}
	
	
	
}
