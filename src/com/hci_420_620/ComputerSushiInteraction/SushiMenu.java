package com.hci_420_620.ComputerSushiInteraction;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ExpandableListView;

public class SushiMenu extends ActionBarActivity {
	MenuAdapter listAdapter;
	ExpandableListView expListView;
	List<MenuSection> menuSections;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
				int imageId = item.getImageId();
				
				View menuImageView = findViewById(R.id.menuItemImage);
				
				menuImageView.setBackgroundResource(imageId);
				
				return false;
			}
		});
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
