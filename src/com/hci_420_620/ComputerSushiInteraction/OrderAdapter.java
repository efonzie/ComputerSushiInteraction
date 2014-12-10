package com.hci_420_620.ComputerSushiInteraction;

import java.util.ArrayList;

import android.R.color;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.internal.widget.AdapterViewCompat.OnItemClickListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class OrderAdapter extends ArrayAdapter<MenuItem> {

	private Context context;
	private int layoutResourceId;
	private ArrayList<MenuItem> _orderItems;;

	public OrderAdapter(Context context) {
		super(context, R.layout.fragment_order_item);
		this.context = context;
		this.layoutResourceId = R.layout.fragment_order_item;
		this._orderItems = new ArrayList<MenuItem>();
	}

	@Override
	public void add(MenuItem object) {
		_orderItems.add(object);
		super.add(object);
	}
	
	@Override
	public void remove(MenuItem object) {
		super.remove(object);
		notifyDataSetChanged();
	}
	
	public void remove(int index) {
		MenuItem item = _orderItems.remove(index);
		super.remove(item);
		notifyDataSetChanged();
	}
	
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		final OrderItemViewHolder holder;
		if(convertView==null){
			holder = new OrderItemViewHolder();
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId, parent, false);
            
            holder.itemName = (TextView) convertView.findViewById(R.id.orderItemName);
            holder.itemPrice = (TextView) convertView.findViewById(R.id.orderItemPrice);
            holder.deleteItem = (ImageButton)convertView.findViewById(R.id.removeItemFromOrderButton);
            
            convertView.setTag(holder);
		}else{
			holder = (OrderItemViewHolder) convertView.getTag();
		}
		
		holder.itemName.setTag(position);
		holder.itemPrice.setTag(position);
		holder.deleteItem.setTag(position);
		
		// set current order text views
        MenuItem menuItem = getItem(position);
        String itemName = menuItem.getName();
        String itemPrice = menuItem.getPriceString();
        holder.itemName.setText(itemName);
        holder.itemPrice.setText(itemPrice);
        
        holder.deleteItem.setOnClickListener(new View.OnClickListener() {
        	
        	@Override
        	public void onClick(View v) {
        		int tag = (int) v.getTag();
        		if(tag < _orderItems.size()){
        			remove(tag);
        			notifyDataSetChanged();
        		}
        	}
        });
        // set background color for even rows
        if(position % 2 == 1){
        	convertView.setBackgroundColor(Color.LTGRAY);
        }else{
        	convertView.setBackgroundColor(Color.TRANSPARENT);
        }
        
        
		return convertView;
	}

	@Override
	public MenuItem getItem(int position) {
		return _orderItems.get(position);
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


