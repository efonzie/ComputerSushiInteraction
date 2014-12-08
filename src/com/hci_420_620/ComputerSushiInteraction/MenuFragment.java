package com.hci_420_620.ComputerSushiInteraction;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.TextView;
import android.widget.Button;



public class MenuFragment extends Fragment{
	
	MenuAdapter listAdapter;
	ExpandableListView expListView;
	ArrayList<MenuSection> menuSections;
	MenuItem selectedItem;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		
		View view = inflater.inflate(R.layout.fragment_menu, container, false);
		
		prepareMenu();
		
		listAdapter = new MenuAdapter(this.getActivity(), menuSections);
		
		expListView = (ExpandableListView)view.findViewById(R.id.menuList);
		expListView.setAdapter(listAdapter);

		expListView.setOnChildClickListener(new OnChildClickListener() {

		    @Override
		    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
		    	
		    	DetailFragment detailFrag = (DetailFragment) getActivity().getFragmentManager().findFragmentById(R.id.detailFragment);
		    	if(detailFrag != null){
		    		MenuItem item = (MenuItem)listAdapter.getChild(groupPosition, childPosition);
		    		selectedItem = item;
		    		detailFrag.updateDetails(item);
		    	}
		    	
		    	return false;
		    }
		});  
		
		return view;
	}
	
	public void prepareMenu() {
    	menuSections = new ArrayList<MenuSection>();
    	
    	
    	MenuSection nigiri = new MenuSection("Nigiri");
    	nigiri.addItem(new MenuItem("Yellowtail", "Yellowtail served over rice", 3.50));
    	nigiri.getItem(0).setImageId(R.drawable.nigiri_hamachi);
    	nigiri.addItem(new MenuItem("Tuna", "Tuna served over rice", 3.50));
    	nigiri.getItem(1).setImageId(R.drawable.nigiri_maguro);
    	nigiri.addItem(new MenuItem("Albacore", "Albacore served over rice", 3.50));
    	nigiri.getItem(2).setImageId(R.drawable.nigiri_albacore);
    	
    	MenuSection rolls = new MenuSection("Long Rolls and Hand Rolls");
    	rolls.addItem(new MenuItem("Roll 1", "Roll 1 description", 7.50));
    	rolls.addItem(new MenuItem("Roll 2", "Roll 2 description", 8.50));
    	rolls.addItem(new MenuItem("Roll 3", "Roll 3 description", 9.50));
    	
    	MenuSection beverages = new MenuSection("Beverages");
    	beverages.addItem(new MenuItem("Water", "A cold refreshing glass of water", 0.00));
    	beverages.addItem(new MenuItem("Tea", "A glass of iced tea", 1.50));
    	beverages.addItem(new MenuItem("Sake", "A small hot bottle of sake", 3.00));
    	
    	MenuSection dessert = new MenuSection("Desserts");
    	dessert.addItem(new MenuItem("Ice Cream", "A scoop of ice cream.  Available flavors are vanilla, chocolate, and green tea.", 3.50));
    	dessert.addItem(new MenuItem("Mochi", "Ball of ice cream wrapped in rice dough.  Available in flavors vanilla, chocolate, mango, strawberry, and green tea ice cream.", 3.50));
    	
    	menuSections.add(nigiri);
    	menuSections.add(rolls);
    	menuSections.add(beverages);
    	menuSections.add(dessert);
    	
    }
	
}