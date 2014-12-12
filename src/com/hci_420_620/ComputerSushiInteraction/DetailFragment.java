package com.hci_420_620.ComputerSushiInteraction;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DetailFragment extends Fragment{
	MenuItem selectedItem;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		
		View view = inflater.inflate(R.layout.fragment_detail, container, false);
		
		
		//Handle the button click, if you use android:onClick in the XML it will be sent to the activity
		Button addToOrderButton = (Button)view.findViewById(R.id.addItemToOrder);
		addToOrderButton.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View v){
				addToOrderButton(v);
			}
			
		});
		
		//Handle the button click, if you use android:onClick in the XML it will be sent to the activity
		Button drinkRefillButton = (Button)view.findViewById(R.id.drinkRefill);
		drinkRefillButton.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View v){
				drinkRefillButtonCLick(v);
			}
			
		});
		
		//Handle the button click, if you use android:onClick in the XML it will be sent to the activity
		Button callServerButton = (Button)view.findViewById(R.id.callServer);
		callServerButton.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View v){
				callServerButtonClick(v);
			}
			
		});
		
		return view;
	}
	
	// output message to indicate server is on their way
	protected void callServerButtonClick(View v) {
		Toast.makeText(getActivity(), "Your server will be with you shortly.", Toast.LENGTH_SHORT).show();
	}

	// output message to indicate server is coming to refill your drink
	protected void drinkRefillButtonCLick(View v) {
		Toast.makeText(getActivity(), "Your drink refill request has been sent to your server.", Toast.LENGTH_SHORT).show();
	}

	// update the menu details section for the newly selected item
	public void updateDetails(MenuItem item){
		selectedItem = item;
		int imageId = item.getImageId();
		if(imageId != -1){
			View itemImageView = getActivity().findViewById(R.id.menuDetailsItemImage);
			itemImageView.setBackgroundResource(imageId);
		}
		else{
			View itemImageView = getActivity().findViewById(R.id.menuDetailsItemImage);
			itemImageView.setBackgroundResource(0);
		}
		
		// set item name
		String itemName = item.getName();
		TextView itemNameView = (TextView) getActivity().findViewById(R.id.menuDetailsItemName);
		itemNameView.setText(itemName);
		
		// set item price
		String itemPrice = item.getPriceString();
		TextView itemPriceView = (TextView) getActivity().findViewById(R.id.menuDetailsItemPrice);
		itemPriceView.setText(itemPrice);
		
		// set item description
		String itemDesc = item.getDescription();
		TextView itemDescView = (TextView) getActivity().findViewById(R.id.menuDetailsItemDesc);
		itemDescView.setText(itemDesc);
	}
	
	// adds the currently selected item to the current order
	public void addToOrderButton(View view){
		FragmentManager fragMan = getActivity().getSupportFragmentManager();
		String currentOrderTag = OrderPagerAdapter.GetFragmentTag(R.id.orderViewPager, 0);
		OrderFragment orderFrag = (OrderFragment) fragMan.findFragmentByTag(currentOrderTag);
    	if(orderFrag != null){
    		orderFrag.addItemToOrder(selectedItem);
    	}
    	
    	MenuActivity menu = (MenuActivity)getActivity();
    	menu.goToCurrentOrder();

    }
}
