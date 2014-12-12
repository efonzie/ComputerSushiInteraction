package com.hci_420_620.ComputerSushiInteraction;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class CheckoutDialogFragment extends DialogFragment {
	
	OrderAdapter listAdapter;
	ListView listView;
	ArrayList<MenuItem> order;
	
	
	public CheckoutDialogFragment(ArrayList<MenuItem> inorder){
		order = inorder;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		View view = inflater.inflate(R.layout.fragment_checkout, container);
		getDialog().setTitle("Checkout");
		
		
		
		listAdapter = new OrderAdapter(this.getActivity());
		for(MenuItem item : order){
			listAdapter.add(item);
		}
		
		listView = (ListView)view.findViewById(R.id.orderList);
		listView.setAdapter(listAdapter);
		
		return view;
		
	}
	
	@Override
	public void onStart(){
		super.onStart();
		
		getDialog().getWindow().setLayout(1500, 1000);
		
	}

}
