package com.hci_420_620.ComputerSushiInteraction;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

public class OrderFragment extends Fragment{
	
	OrderAdapter listAdapter;
	ListView listView;
	//List<MenuItem> currentOrder;  //current items that haven't been sent to the kitchen yet
	List<MenuItem> totalOrder; //current items that HAVE been sent to the kitchen
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		
		View view = inflater.inflate(R.layout.fragment_order, container, false);
		
		//currentOrder = new ArrayList<MenuItem>();
		totalOrder = new ArrayList<MenuItem>();
		
		listAdapter = new OrderAdapter(this.getActivity());
		
		listView = (ListView)view.findViewById(R.id.CurrentOrderListView);
		listView.setAdapter(listAdapter);
		
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
}
