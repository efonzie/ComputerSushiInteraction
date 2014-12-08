package com.hci_420_620.ComputerSushiInteraction;

import java.util.ArrayList;

import android.R.color;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class OrderAdapter extends ArrayAdapter<MenuItem> {

	private Context context;
	private int layoutResourceId;
	private MenuItem _orderItems[];

	public OrderAdapter(Context context) {
		super(context, R.layout.order_item);
		this.context = context;
		this.layoutResourceId = R.layout.order_item;
	}

	@Override
	public void add(MenuItem object) {
		// TODO Auto-generated method stub
		super.add(object);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView==null){
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId, parent, false);
		}
        
		// set current order text views
        MenuItem menuItem = getItem(position);
        String itemName = menuItem.getName();
        String itemPrice = menuItem.getPriceString();
        TextView itemNameView = (TextView) convertView.findViewById(R.id.orderItemName);
        TextView itemPriceView = (TextView) convertView.findViewById(R.id.orderItemPrice);
        itemNameView.setText(itemName);
        itemPriceView.setText(itemPrice);
        
        // set background color for even rows
        if(position % 2 == 1){
        	convertView.setBackgroundColor(color.holo_blue_light);
        }else{
        	convertView.setBackgroundColor(color.white);
        }
        
		return convertView;
	}

	@Override
	public MenuItem getItem(int position) {
		// TODO Auto-generated method stub
		return super.getItem(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	@Override
	public boolean hasStableIds() {
		return true;
	}
	

}
