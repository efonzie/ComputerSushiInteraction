package com.hci_420_620.ComputerSushiInteraction;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

public class SushiMenu extends Activity {
	MenuAdapter listAdapter;
	ExpandableListView expListView;
	List<MenuSection> menuSections;
	MenuItem selectedItem;
	List<MenuItem> currentOrder;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentOrder = new ArrayList<MenuItem>();
        setContentView(R.layout.sushi_menu);
        expListView = (ExpandableListView) findViewById(R.id.menuList);
        prepareMenu();
        listAdapter = new MenuAdapter(this, menuSections);
        expListView.setAdapter(listAdapter);
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
			
			@Override
			public boolean onChildClick(ExpandableListView parent, View v,int groupPosition, int childPosition, long id) {
				MenuSection section = (MenuSection) parent.getAdapter().getItem(groupPosition);
				MenuItem item = section.getItem(childPosition);
				setSelectedItem(item);
				return false;
			}
		});
    }

    protected void setSelectedItem(MenuItem item) {
    	// set item
		selectedItem = item;
		
		// set item image
		int imageId = item.getImageId();
		View itemImageView = findViewById(R.id.menuDetailsItemImage);
		itemImageView.setBackgroundResource(imageId);
		
		// set item name
		String itemName = item.getName();
		TextView itemNameView = (TextView) findViewById(R.id.menuDetailsItemName);
		itemNameView.setText(itemName);
		
		// set item name
		String itemPrice = item.getPriceString();
		TextView itemPriceView = (TextView) findViewById(R.id.menuDetailsItemPrice);
		itemPriceView.setText(itemPrice);
		
		// set item name
		String itemDesc = item.getDescription();
		TextView itemDescView = (TextView) findViewById(R.id.menuDetailsItemDesc);
		itemDescView.setText(itemDesc);
	}
    
    public void addSelectedItemToOrder(View view){
    	this.currentOrder.add(this.selectedItem);
    	String itemName = selectedItem.getName();
    	String toastString = "'" + itemName + "' has been added to your order.";
    	addToast(toastString);
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

	public void addToast(String toastString){
    	Toast itemAddedToast = Toast.makeText(getApplicationContext(), toastString, Toast.LENGTH_SHORT);
    	itemAddedToast.show();
	}
}
