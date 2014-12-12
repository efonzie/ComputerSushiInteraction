package com.hci_420_620.ComputerSushiInteraction;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

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
		
		
		
		listAdapter = new OrderAdapter(this.getActivity(), false);
		for(MenuItem item : order){
			listAdapter.add(item);
		}
		
		listView = (ListView)view.findViewById(R.id.orderList);
		listView.setAdapter(listAdapter);
		
		double total = listAdapter.getTotal();
		TextView totalTextView = (TextView) view.findViewById(R.id.checkout_totalPrice);
		String totalText = String.format("$%.2f",total);
		totalTextView.setText(totalText);
		
		View radioGroupView = view.findViewById(R.id.radioGroup_payment);
		RadioGroup radioGroup = (RadioGroup) radioGroupView;
		setRadioButtonEvents(radioGroup);
		
		Button selectPaymentButton = (Button) view.findViewById(R.id.selectPayment);
		//selectPaymentButton.setEnabled(false);
		selectPaymentButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
				builder.setTitle("Thank you!");
				builder.setMessage("Thank you for your payment!  Have a great day!");
				builder.setCancelable(false);
				builder.setPositiveButton("Goodbye", new DialogInterface.OnClickListener(){

					@Override
					public void onClick(DialogInterface dialog, int which) {
						MenuActivity activity = (MenuActivity) getActivity();
						activity.backToLaunch();
						
					}
					
				});
				builder.show();
			}
			
		});
		
		
		return view;
		
	}
	
	public void setRadioButtonEvents(RadioGroup radioGroup){
		
		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener(){
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				Button selectPaymentButton = (Button) getView().findViewById(R.id.selectPayment);
				
				
				TextView paymentText = (TextView) getView().findViewById(R.id.checkoutPaymentDetails);
				String paymentString = "";
				
				switch(checkedId){
				case R.id.radio_credit:
					paymentString = "Swipe your Credit Card";

					break;
				case R.id.radio_cash:

					paymentString = "The waitress will come to collect your cash shortly";
					break;
				case R.id.radio_check:
					
					paymentString = "The waitress will come to collect your check shortly";
					break;
				}
				paymentText.setText(paymentString);
				
			}
			
		});
		
	}
	
	@Override
	public void onStart(){
		super.onStart();
		
		getDialog().getWindow().setLayout(1500, 1000);
		
	}

}
