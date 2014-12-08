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
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

public class OrderFragment extends Fragment{
	
	List<MenuItem> currentOrder;  //current items that haven't been sent to the kitchen yet
	List<MenuItem> totalOrder; //current items that HAVE been sent to the kitchen
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		
		View view = inflater.inflate(R.layout.fragment_order, container, false);
		
		currentOrder = new ArrayList<MenuItem>();
		totalOrder = new ArrayList<MenuItem>();
		
		
		
		return view;
	}
	
	 public void addItemToOrder(MenuItem item){
	    	this.currentOrder.add(item);
	    	String itemName = item.getName();
	    	String toastString = "'" + itemName + "' has been added to your order.";
	    	Toast itemAddedToast = Toast.makeText(getActivity().getApplicationContext(), toastString, Toast.LENGTH_SHORT);
	    	itemAddedToast.show();
	 }

}
