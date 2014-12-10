package com.hci_420_620.ComputerSushiInteraction;

import android.app.Fragment;
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
		Button b = (Button)view.findViewById(R.id.addItemToOrder);
		b.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View v){
				addToOrderButton(v);
			}
			
		});
		
		return view;
	}
	
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
	
	
	public void addToOrderButton(View view){
		OrderFragment orderFrag = (OrderFragment) getActivity().getFragmentManager().findFragmentById(R.id.orderFragment);
    	if(orderFrag != null){
    		orderFrag.addItemToOrder(selectedItem);
    	}

    }
}
