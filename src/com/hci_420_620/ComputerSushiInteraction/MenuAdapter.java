package com.hci_420_620.ComputerSushiInteraction;

import java.util.List;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class MenuAdapter extends BaseExpandableListAdapter {
	private Context _context;
	private List<MenuSection> _menuSections;

	public MenuAdapter(SushiMenu context, List<MenuSection> menuSections) {
		this._context = context;
		this._menuSections = menuSections;
	}

	@Override
	public int getGroupCount() {
		return this._menuSections.size();
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		MenuSection menuSection = this._menuSections.get(groupPosition);
		return menuSection.getItemCount();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return this._menuSections.get(groupPosition);
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return this._menuSections.get(groupPosition).getItem(childPosition);
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		
        MenuSection MenuSection = (MenuSection) getGroup(groupPosition);
		String sectionName = MenuSection.getSectionName();
		
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.menu_section, null);
        }
 
        TextView sectionView = (TextView) convertView.findViewById(R.id.SectionName);
        sectionView.setTypeface(null, Typeface.BOLD);
        sectionView.setText(sectionName);
 
        return convertView;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        MenuItem menuItem = (MenuItem) getChild(groupPosition, childPosition);
        String itemName = menuItem.getName();
        String itemDescription = menuItem.getDescription();
        Double itemPriceValue = menuItem.getPrice();
        String itemPrice = String.format("$%.3f",itemPriceValue);
 
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.menu_item, null);
        }
 
        TextView itemNameView = (TextView) convertView.findViewById(R.id.itemName);
        TextView itemDescView = (TextView) convertView.findViewById(R.id.itemDescription);
        TextView itemPriceView = (TextView) convertView.findViewById(R.id.itemPrice);
        
        itemNameView.setText(itemName);
        itemDescView.setText(itemDescription);
        itemPriceView.setText(itemPrice);
        
        
        return convertView;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

}
