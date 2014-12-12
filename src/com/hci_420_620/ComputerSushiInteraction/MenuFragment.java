package com.hci_420_620.ComputerSushiInteraction;

import java.util.ArrayList;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.TextView;
import android.widget.Button;
import android.widget.TextView.OnEditorActionListener;

public class MenuFragment extends Fragment {

	MenuAdapter listAdapter;
	ExpandableListView expListView;
	ArrayList<MenuSection> menuSections;
	ArrayList<MenuSection> currentDisplay;
	MenuItem selectedItem;

	public int selectedGroupID = -1; // group ID of the selected item
	public int selectedChildID = -1; // child id of the selected item

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_menu, container, false);

		prepareMenu();

		listAdapter = new MenuAdapter(this.getActivity(), menuSections);

		expListView = (ExpandableListView) view.findViewById(R.id.menuList);
		expListView.setAdapter(listAdapter);

		expListView.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {

				DetailFragment detailFrag = (DetailFragment) getActivity()
						.getSupportFragmentManager().findFragmentById(
								R.id.detailFragment);
				if (detailFrag != null) {
					MenuItem item = (MenuItem) listAdapter.getChild(
							groupPosition, childPosition);
					selectedItem = item;

					int index = parent.getFlatListPosition(ExpandableListView
							.getPackedPositionForChild(groupPosition,
									childPosition));
					parent.setItemChecked(index, true);

					selectedGroupID = groupPosition;
					selectedChildID = childPosition;

					detailFrag.updateDetails(item);
				}

				return false;
			}
		});

		expListView.setOnGroupClickListener(new OnGroupClickListener() {
			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
				if (parent.isGroupExpanded(groupPosition)) {

					parent.collapseGroup(groupPosition);
					if (selectedGroupID == groupPosition) {
						parent.clearChoices();
					}
				} else {

					parent.expandGroup(groupPosition);

					if (selectedGroupID == groupPosition) {
						long packedChildPos = ExpandableListView
								.getPackedPositionForChild(selectedGroupID,
										selectedChildID);
						int index = parent.getFlatListPosition(packedChildPos);
						parent.setItemChecked(index, true);
					}

				}

				if (selectedChildID != -1 && selectedGroupID != -1
						&& selectedGroupID != groupPosition) {
					if (parent.isGroupExpanded(selectedGroupID)) {
						long packedChildPos = ExpandableListView
								.getPackedPositionForChild(selectedGroupID,
										selectedChildID);
						int index = parent.getFlatListPosition(packedChildPos);
						parent.setItemChecked(index, true);
						return true;
					}
				}

				return true;

			}
		});

		// set up listener to respond to text changed events
		EditText search = (EditText) view.findViewById(R.id.menuSearch);
		search.addTextChangedListener(new TextWatcher() {
			@Override
			public void afterTextChanged(Editable s) {
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				filterMenu();
			}

		});

		Button clearSearch = (Button) view.findViewById(R.id.clearMenuSearch);
		clearSearch.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				EditText search = (EditText) getView().findViewById(
						R.id.menuSearch);
				search.setText("");
				filterMenu();
			}
		});

		return view;
	}

	public void filterMenu() {
		String searchText = ((EditText) getView().findViewById(R.id.menuSearch))
				.getText().toString();

		currentDisplay = new ArrayList<MenuSection>();

		for (MenuSection section : menuSections) {

			MenuSection toAdd = new MenuSection(section.sectionName);

			for (MenuItem item : section.itemList) {
				if (item.name.toLowerCase().contains(searchText.toLowerCase())) {
					toAdd.addItem(item);
				}
			}

			if (toAdd.itemList.size() > 0) {
				currentDisplay.add(toAdd);
			}
		}

		if (currentDisplay.size() == 0) {
			currentDisplay.add(new MenuSection("No items found"));
		}

		listAdapter = new MenuAdapter(this.getActivity(), currentDisplay);
		expListView.setAdapter(listAdapter);

		// if there are only 1 sections, automatically expand that section
		if (currentDisplay.size() == 1) {
			expListView.expandGroup(0);
		}

	}

	public void prepareMenu() {
		menuSections = new ArrayList<MenuSection>();
		// -----------------------------------------Bianca
		// Menu---------------------------------------------

		// Appetizers section
		MenuSection appetizers = new MenuSection("Appetizers");
		appetizers.addItem(new MenuItem("Gyoza",
				"Pan fried or deep fried meat and vegetable dumplings ", 5.95));
		appetizers.getItem(0).setImageId(R.drawable.appetizer_gyoza);
		appetizers.addItem(new MenuItem("Shumai",
				"Steamed shrimp or pork dumplings", 5.50));
		appetizers.getItem(1).setImageId(R.drawable.appetizer_shumai);
		appetizers.addItem(new MenuItem("Edamame",
				"Steamed green soybean sprinkled with salt", 3.99));
		appetizers.getItem(2).setImageId(R.drawable.appetizer_edamame);
		appetizers.addItem(new MenuItem("Yaki Tori",
				"Grilled white meat chicken on skewers with sauce", 5.50));
		appetizers.getItem(3).setImageId(R.drawable.appetizer_yakitori);
		appetizers.addItem(new MenuItem("Beef Kushiyaki",
				"Grilled beef on skewers with sauce", 6.95));
		appetizers.getItem(4).setImageId(R.drawable.appetizer_beef_kushiyaki);
		appetizers.addItem(new MenuItem("Pork Kushiyaki",
				"Grilled pork on skewers with sauce", 5.95));
		appetizers.getItem(5).setImageId(R.drawable.appetizer_pork_kushiyaki);
		appetizers.addItem(new MenuItem("Tatsuta Age",
				"Japanese style ginger battered fried chicken", 6.50));
		appetizers.getItem(6).setImageId(R.drawable.appetizer_tatsuta);
		appetizers.addItem(new MenuItem("Age Tuna",
				"Deep fried tuna with spicy sauce", 6.50));
		appetizers.getItem(7).setImageId(R.drawable.appetizer_age_tuna);
		appetizers.addItem(new MenuItem("Scallop Kushi Katsu",
				"Deep fried breaded scallop", 7.99));
		appetizers.getItem(8).setImageId(
				R.drawable.appetizer_scallop_kushikatsu);
		appetizers.addItem(new MenuItem("Sake-Shioyaki",
				"Lightly salted grill salmon served with scallion ponsu sauce",
				6.99));
		appetizers.getItem(9).setImageId(R.drawable.appetizer_sake_shioyaki);

		// Soup and Salad section
		MenuSection spsl = new MenuSection("Soup and Salad");
		spsl.addItem(new MenuItem("Miso soup",
				"Stock into which softened miso paste is mixed", 2.50));
		spsl.getItem(0).setImageId(R.drawable.spsl_miso_soup);
		spsl.addItem(new MenuItem("Wakame seaweed salad",
				"Sea vegetable, or edible seaweed", 5.50));
		spsl.getItem(1).setImageId(R.drawable.spsl_wakame_seaweed);
		spsl.addItem(new MenuItem("Chef salad",
				"Salad with vibrant orange-carrot dressing", 3.50));
		spsl.getItem(2).setImageId(R.drawable.spsl_chef_salad);

		// Rolls section
		MenuSection rolls = new MenuSection("Long Rolls and Hand Rolls");
		rolls.addItem(new MenuItem(
				"Alaska Maki",
				"A variant of the California roll with raw salmon on the inside, or layered on the outside",
				4.50));
		rolls.getItem(0).setImageId(R.drawable.rolls_alaska);
		rolls.addItem(new MenuItem(
				"Avocado Maki",
				"Lower in calories than most other rolls, these savory treats provide healthy, monounsaturated fat from avocados to help you feel satisfied",
				3.50));
		rolls.getItem(1).setImageId(R.drawable.rolls_avocado);
		rolls.addItem(new MenuItem(
				"Boston Maki",
				"An uramaki California roll with poached shrimp instead of imitation crab",
				5.50));
		rolls.getItem(2).setImageId(R.drawable.rolls_boston);
		rolls.addItem(new MenuItem(
				"California Maki",
				"A kind of sushi roll, usually made inside-out, containing cucumber, crab meat or imitation crab, and avocado.",
				7.50));
		rolls.getItem(3).setImageId(R.drawable.rolls_california);
		rolls.addItem(new MenuItem("Ebi Maki", "Ebi is actually jumbo shrim",
				8.50));
		rolls.getItem(4).setImageId(R.drawable.rolls_ebi);
		rolls.addItem(new MenuItem(
				"Kappa Maki",
				"This is by far the easiest sushi roll to make. Not only is it vegetarian, it is also vegan, so just about anyone can eat it!",
				6.50));
		rolls.getItem(5).setImageId(R.drawable.rolls_kappa);
		rolls.addItem(new MenuItem(
				"Oshinko Maki",
				"Oshinko is a Japanese pickled radish that has a strong flavor, very tasty!",
				7.80));
		rolls.getItem(6).setImageId(R.drawable.rolls_oshinko);
		rolls.addItem(new MenuItem(
				"Saba Maki",
				"It is not served completely raw, but is one of those fish that is cured in rice vinegar with some salt",
				8.50));
		rolls.getItem(7).setImageId(R.drawable.rolls_saba);
		rolls.addItem(new MenuItem("Tekka Maki", "A tuna roll.", 6.90));
		rolls.getItem(8).setImageId(R.drawable.rolls_tekka);
		rolls.addItem(new MenuItem("Ume Maki",
				"It is a delicious maki made with ume", 9.50));
		rolls.getItem(9).setImageId(R.drawable.rolls_ume);

		// Sushi section
		MenuSection sushi = new MenuSection("Sushi A La Carte");
		sushi.addItem(new MenuItem("Ebi", "Cooked shrimp ", 4.75));
		sushi.getItem(0).setImageId(R.drawable.sushi_ebi);
		sushi.addItem(new MenuItem("Hamachi", "Young Yellowtail ", 3.50));
		sushi.getItem(1).setImageId(R.drawable.sushi_hamachi);
		sushi.addItem(new MenuItem("Ikura", "Salmon roe", 4.50));
		sushi.getItem(2).setImageId(R.drawable.sushi_ikura);
		sushi.addItem(new MenuItem("Ika", "Squid ", 4.00));
		sushi.getItem(3).setImageId(R.drawable.sushi_ika);
		sushi.addItem(new MenuItem(
				"Inari",
				"Inarizushi is a simple and inexpensive type of sushi, in which sushi rice is filled into aburaage (deep fried tofu) bags.",
				5.50));
		sushi.getItem(4).setImageId(R.drawable.sushi_inari);
		sushi.addItem(new MenuItem("Maguro", "A lean cut of tuna", 4.50));
		sushi.getItem(5).setImageId(R.drawable.sushi_maguro);
		sushi.addItem(new MenuItem("Saba", "Mackerel ", 7.50));
		sushi.getItem(6).setImageId(R.drawable.sushi_saba);
		sushi.addItem(new MenuItem("Sake", "Salmon", 4.00));
		sushi.getItem(7).setImageId(R.drawable.sushi_sake);
		sushi.addItem(new MenuItem("Tako", "Octopus ", 3.50));
		sushi.getItem(8).setImageId(R.drawable.sushi_tako);
		sushi.addItem(new MenuItem(
				"Tamago",
				"Tamago egg is classic Japanese folded omelet sometimes called tamagoyaki.",
				4.50));
		sushi.getItem(9).setImageId(R.drawable.sushi_tamago);
		sushi.addItem(new MenuItem("Tobiko", "Flying fish roe ", 4.80));
		sushi.getItem(10).setImageId(R.drawable.sushi_tobiko);
		sushi.addItem(new MenuItem("Unagi",
				"Freshwater eel broiled with a sweet sauce. ", 4.70));
		sushi.getItem(11).setImageId(R.drawable.sushi_unagi);
		sushi.addItem(new MenuItem("Uni", "Gonad of Sea urchin", 5.50));
		sushi.getItem(12).setImageId(R.drawable.sushi_uni);

		// Beverages section
		MenuSection beverages = new MenuSection("Beverages");
		beverages.addItem(new MenuItem("Water",
				"A cold refreshing glass of water", 0.00));
		beverages.getItem(0).setImageId(R.drawable.beverages_water);
		beverages.addItem(new MenuItem("Tea", "A glass of iced tea", 1.50));
		beverages.getItem(1).setImageId(R.drawable.beverages_tea);
		beverages.addItem(new MenuItem("Sake", "A small hot bottle of sake",
				3.00));
		beverages.getItem(2).setImageId(R.drawable.beverages_sake);
		beverages.addItem(new MenuItem("Orange Juice",
				"A cold refreshing glass of orange juice", 5.00));
		beverages.getItem(3).setImageId(R.drawable.beverages_juice);
		beverages.addItem(new MenuItem("Coke",
				"A cold refreshing glass of Coke", 3.00));
		beverages.getItem(4).setImageId(R.drawable.beverages_coke);

		// Dessert section
		MenuSection dessert = new MenuSection("Desserts");
		dessert.addItem(new MenuItem("Apple Pie",
				"A delicious piece of apple pie.", 4.50));
		dessert.getItem(0).setImageId(R.drawable.dessert_apple_pie);
		dessert.addItem(new MenuItem("Chocolate Pie",
				"A delicious piece of chocolate pie.", 4.50));
		dessert.getItem(1).setImageId(R.drawable.dessert_chocolate_pie);
		dessert.addItem(new MenuItem(
				"Ice Cream",
				"A scoop of ice cream.  Available flavors are vanilla, chocolate, and green tea.",
				3.50));
		dessert.getItem(2).setImageId(R.drawable.dessert_ice_cream);
		dessert.addItem(new MenuItem(
				"Mochi",
				"Ball of ice cream wrapped in rice dough.  Available in flavors vanilla, chocolate, mango, strawberry, and green tea ice cream.",
				3.50));
		dessert.getItem(3).setImageId(R.drawable.dessert_mochi);
		dessert.addItem(new MenuItem("Ume Pie",
				"A delicious piece of ume pie.", 4.50));
		dessert.getItem(4).setImageId(R.drawable.dessert_ume_pie);

		menuSections.add(appetizers);
		menuSections.add(spsl);
		menuSections.add(rolls);
		menuSections.add(sushi);
		menuSections.add(beverages);
		menuSections.add(dessert);
		/*
		 * MenuSection nigiri = new MenuSection("Nigiri"); nigiri.addItem(new
		 * MenuItem("Yellowtail", "Yellowtail served over rice", 3.50));
		 * nigiri.getItem(0).setImageId(R.drawable.nigiri_hamachi);
		 * nigiri.addItem(new MenuItem("Tuna", "Tuna served over rice", 3.50));
		 * nigiri.getItem(1).setImageId(R.drawable.nigiri_maguro);
		 * nigiri.addItem(new MenuItem("Albacore", "Albacore served over rice",
		 * 3.50)); nigiri.getItem(2).setImageId(R.drawable.nigiri_albacore);
		 * 
		 * MenuSection rolls = new MenuSection("Long Rolls and Hand Rolls");
		 * rolls.addItem(new MenuItem("Roll 1", "Roll 1 description", 7.50));
		 * rolls.addItem(new MenuItem("Roll 2", "Roll 2 description", 8.50));
		 * rolls.addItem(new MenuItem("Roll 3", "Roll 3 description", 9.50));
		 * 
		 * MenuSection beverages = new MenuSection("Beverages");
		 * beverages.addItem(new MenuItem("Water",
		 * "A cold refreshing glass of water", 0.00)); beverages.addItem(new
		 * MenuItem("Tea", "A glass of iced tea", 1.50)); beverages.addItem(new
		 * MenuItem("Sake", "A small hot bottle of sake", 3.00));
		 * 
		 * MenuSection dessert = new MenuSection("Desserts");
		 * dessert.addItem(new MenuItem("Ice Cream",
		 * "A scoop of ice cream.  Available flavors are vanilla, chocolate, and green tea."
		 * , 3.50)); dessert.addItem(new MenuItem("Mochi",
		 * "Ball of ice cream wrapped in rice dough.  Available in flavors vanilla, chocolate, mango, strawberry, and green tea ice cream."
		 * , 3.50));
		 * 
		 * menuSections.add(nigiri); menuSections.add(rolls);
		 * menuSections.add(beverages); menuSections.add(dessert);
		 */
	}

}
