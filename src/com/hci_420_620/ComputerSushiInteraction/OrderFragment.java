package com.hci_420_620.ComputerSushiInteraction;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

public class OrderFragment extends Fragment{
	
	OrderAdapter listAdapter;
	ListView listView;
	List<MenuItem> order;  //current items that are shown in this order fragment

	int orderPage = 0;
	String orderTitle = "Current";
	
	public static OrderFragment newInstance(int page, String title){
		OrderFragment orderFrag = new OrderFragment();
		orderFrag.orderPage = page;
		orderFrag.orderTitle = title;
		return orderFrag;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		
		View view = inflater.inflate(R.layout.fragment_order, container, false);
		
		//currentOrder = new ArrayList<MenuItem>();
		order = new ArrayList<MenuItem>();
		
		listAdapter = new OrderAdapter(this.getActivity());
		
		listView = (ListView)view.findViewById(R.id.currentOrderListView);
		listView.setAdapter(listAdapter);
		
		//Register a datasetobserver to check and see when the data changes.
		listAdapter.registerDataSetObserver(new DataSetObserver(){
			public void onChanged(){
				updateTotal();
			}
		});

		Button submitButton = (Button)view.findViewById(R.id.submitOrderButton);
		
		if(orderTitle == "Total Order"){
			submitButton.setText("Checkout");
		}
		
		submitButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				submitOrder();
			}
		});
		
		return view;
	}
	
	public void addItemToOrder(MenuItem item){
		if(item != null){
			listAdapter.add(item);
			String itemName = item.getName();
			String toastString = "'" + itemName + "' has been added to your order.";
			showToast(toastString);
			
		}else{
			showToast("Please select an item to add to the order.");
		}
	 }
	 
	public void showToast(String toastString){
    	Toast itemAddedToast = Toast.makeText(getActivity().getApplicationContext(), toastString, Toast.LENGTH_SHORT);
    	itemAddedToast.show();
	}
	
	public void updateTotal(){
		//update total text:
		String total = String.format("$%.2f",listAdapter.getTotal());
		TextView totalText = (TextView) getView().findViewById(R.id.currentOrderPrice);
		totalText.setText(total);
	}
	
	public void submitOrder(){
		if(orderTitle == "Current Order"){
			FragmentManager fragMan = getActivity().getSupportFragmentManager();
			String totalOrderTag = OrderPagerAdapter.GetFragmentTag(R.id.orderViewPager, 1);
			OrderFragment orderFrag = (OrderFragment) fragMan.findFragmentByTag(totalOrderTag);
			
			for(MenuItem item : listAdapter._orderItems){
				orderFrag.addItemToOrder(item);
			}
			
			listAdapter.clear();
			listAdapter._orderItems.clear();
			listAdapter.notifyDataSetChanged();
			
			orderFrag.listAdapter.notifyDataSetChanged();
			
			MenuActivity menu = (MenuActivity) getActivity();
			menu.goToTotalOrder();
		}
		else if(orderTitle == "Total Order"){
			MenuActivity menu = (MenuActivity) getActivity();

			menu.checkout(listAdapter._orderItems);
		}
		
	}
}
