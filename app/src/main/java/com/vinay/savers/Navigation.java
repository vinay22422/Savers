package com.vinay.savers;

import android.app.SearchManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

import com.vinay.savers.Adapter.MyListAdapter;
import com.vinay.savers.diseases.Anemia;
import com.vinay.savers.diseases.Asthma;
import com.vinay.savers.diseases.BloodPressure;
import com.vinay.savers.diseases.BoneFracture;
import com.vinay.savers.diseases.Burns;
import com.vinay.savers.diseases.Cardiac;
import com.vinay.savers.diseases.Cyclone;
import com.vinay.savers.diseases.Ear;
import com.vinay.savers.diseases.Electric;
import com.vinay.savers.diseases.Flood;
import com.vinay.savers.diseases.FoodPoisoning;
import com.vinay.savers.diseases.Heart;
import com.vinay.savers.diseases.JointDislocation;
import com.vinay.savers.diseases.LungCancer;
import com.vinay.savers.diseases.Malaria;
import com.vinay.savers.diseases.NoseBleed;
import com.vinay.savers.diseases.Pneumonia;
import com.vinay.savers.diseases.Pox;
import com.vinay.savers.diseases.Rabies;
import com.vinay.savers.diseases.SkinDisease;
import com.vinay.savers.diseases.SnakeBite;
import com.vinay.savers.diseases.SpinalCord;
import com.vinay.savers.diseases.Tuberculosis;

public class Navigation extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,ConnectivityReceiver.ConnectivityReceiverListener,SearchView.OnQueryTextListener, SearchView.OnCloseListener{

    String message;
    int color;
    boolean isConnected ;
    private SearchView search;
    private MyListAdapter listAdapter;
    private ExpandableListView myList;
    private ArrayList<Group> groupList = new ArrayList<Group>();
    private ArrayList<Item> itemList = new ArrayList<Item>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       setSupportActionBar(toolbar);

       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
*/
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        search = (SearchView) findViewById(R.id.search);
        search.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        search.setIconifiedByDefault(false);
        search.setOnQueryTextListener(this);
        search.setOnCloseListener(this);

        displayList();//display the list
        myList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView listview, View view,
                                        int groupPos, int childPos, long id) {
                /*Toast.makeText(getApplicationContext(),
                        "You clicked : " + listAdapter.getChild(groupPos,childPos),
                        Toast.LENGTH_SHORT).show();*/
                Toast.makeText(Navigation.this, ""+ ((TextView) view.findViewById(R.id.name)).getText(), Toast.LENGTH_SHORT).show();
                String select= (String) ((TextView) view.findViewById(R.id.name)).getText();
                // startActivity(new Intent(MainActivity.this,Anemia.class));
                if(select.equals("Anemia")){
                    startActivity(new Intent(Navigation.this,Anemia.class));
                }
                if(select.equals("Flood")){
                    startActivity(new Intent(Navigation.this,Flood.class));
                }
                if(select.replaceAll("\\s","").equalsIgnoreCase("ElectricShock")){
                    startActivity(new Intent(Navigation.this,Electric.class));
                }
                if(select.replaceAll("\\s","").equalsIgnoreCase("CardiacArrest")){
                    startActivity(new Intent(Navigation.this,Cardiac.class));
                }
                if(select.replaceAll("\\s","").equalsIgnoreCase("HeartBlock")){
                    startActivity(new Intent(Navigation.this,Heart.class));
                }
                if(select.equals("Rabies")){
                    startActivity(new Intent(Navigation.this,Rabies.class));
                }
                if(select.equals("Tuberculosis")){
                    startActivity(new Intent(Navigation.this,Tuberculosis.class));
                }
                if(select.equals("Malaria")){
                    startActivity(new Intent(Navigation.this,Malaria.class));
                }
                if(select.equals("Asthma")){
                    startActivity(new Intent(Navigation.this,Asthma.class));
                }
                if(select.equals("Cyclone")){
                    startActivity(new Intent(Navigation.this,Cyclone.class));
                }
                if(select.equals("Pneumonia")){
                    startActivity(new Intent(Navigation.this,Pneumonia.class));
                }
                if(select.equals("Burns")){
                    startActivity(new Intent(Navigation.this,Burns.class));
                }

                if(select.replaceAll("\\s","").equalsIgnoreCase("ChickenPox")){
                    startActivity(new Intent(Navigation.this,Pox.class));
                }
                if(select.replaceAll("\\s","").equalsIgnoreCase("EarInfection")){
                    startActivity(new Intent(Navigation.this,Ear.class));
                }
                if(select.replaceAll("\\s","").equalsIgnoreCase("LungCancer")){
                    startActivity(new Intent(Navigation.this,LungCancer.class));
                }
                if(select.replaceAll("\\s","").equalsIgnoreCase("BoneFracture")){
                    startActivity(new Intent(Navigation.this,BoneFracture.class));
                }
                if(select.replaceAll("\\s","").equalsIgnoreCase("SnakeBite")){
                    startActivity(new Intent(Navigation.this,SnakeBite.class));
                }
                if(select.replaceAll("\\s","").equalsIgnoreCase("BloodPressure")){
                    startActivity(new Intent(Navigation.this,BloodPressure.class));
                }
                if(select.replaceAll("\\s","").equalsIgnoreCase("JointDislocation")){
                    startActivity(new Intent(Navigation.this,JointDislocation.class));
                }
                if(select.replaceAll("\\s","").equalsIgnoreCase("NoseBleed")){
                    startActivity(new Intent(Navigation.this,NoseBleed.class));

                }
                if(select.replaceAll("\\s","").equalsIgnoreCase("SkinDisease")){
                    startActivity(new Intent(Navigation.this,SkinDisease.class));
                }
                if(select.replaceAll("\\s","").equalsIgnoreCase("SpinalCord")){
                    startActivity(new Intent(Navigation.this,SpinalCord.class));
                }
                if(select.replaceAll("\\s","").equalsIgnoreCase("FoodPoisoning")){
                    startActivity(new Intent(Navigation.this,FoodPoisoning.class));
                }
                return true;
            }
        });




    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        /* startActivity(new Intent(MainActivity.this, ButtonUIActivity.class));*/
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            startActivity(new Intent(Navigation.this, MainActivity.class));

            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            //checkConnection();
            isConnected = ConnectivityReceiver.isConnected();
            if (isConnected)
                startActivity(new Intent(Navigation.this, MainActivityvs.class));
            else {
                message = "Sorry! Not connected to internet";
                color = Color.RED;
            }

            Snackbar snackbar = Snackbar
                    .make(findViewById(R.id.fab), message, Snackbar.LENGTH_LONG);

            View sbView = snackbar.getView();
            TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(color);
            snackbar.show();

        }else if (id == R.id.nav_send) {

            Intent intent=new Intent(Navigation.this,web.class);
            intent.putExtra("weba","https://vsvs.page.link/feedback");
            startActivity(intent);


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void checkConnection() {
        boolean isConnected = ConnectivityReceiver.isConnected();
        showSnack(isConnected);
    }

    // Showing the status in Snackbar
    private void showSnack(boolean isConnected) {

        //int color;
        if (isConnected) {
            message = "Good! Connected to Internet";
            color = Color.WHITE;
        }

        Snackbar snackbar = Snackbar
                .make(findViewById(R.id.fab), message, Snackbar.LENGTH_LONG);

        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(color);
        snackbar.show();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // register connection status listener
        MyApplication.getInstance().setConnectivityListener(this);
    }

    /**
     * Callback will be triggered when there is change in
     * network connection
     */
    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        showSnack(isConnected);
    }

    private void expandAll() {
        int count = listAdapter.getGroupCount();
        for (int i = 0; i < count; i++){
            myList.expandGroup(i);
        }
    }

    //method to expand all groups
    private void displayList() {
        loadSomeData();
        myList = (ExpandableListView) findViewById(R.id.expandableList);
        listAdapter = new MyListAdapter(Navigation.this, groupList);
        myList.setAdapter(listAdapter);
    }

    private void loadSomeData() {


        ArrayList<Item> itemList = new ArrayList<Item>();
        Item item = new Item("Anemia");
        itemList.add(item);
        item=new Item("BloodPressure");
        itemList.add(item);
        Group group = new Group("Blood", itemList);
        groupList.add(group);

        itemList = new ArrayList<Item>();
        item = new Item("Flood");
        itemList.add(item);
        item = new Item("Cyclone");
        itemList.add(item);
        item = new Item("Electric Shock");
        itemList.add(item);
        group = new Group("Environmental", itemList);
        groupList.add(group);

        itemList = new ArrayList<Item>();
        item = new Item("Cardiac arrest");
        itemList.add(item);
        item = new Item("Heart block");
        itemList.add(item);
        group = new Group("Heart", itemList);
        groupList.add(group);

        itemList = new ArrayList<Item>();
        item = new Item("Rabies");
        itemList.add(item);
        item = new Item("Malaria");
        itemList.add(item);
        item = new Item("Chicken Pox");
        itemList.add(item);
        item = new Item("Ear Infection");
        itemList.add(item);
        group = new Group("Infectious Disease", itemList);
        groupList.add(group);

        itemList = new ArrayList<Item>();
        item = new Item("Nose bleed");
        itemList.add(item);

        item = new Item("Bone fracture");
        itemList.add(item);
        item = new Item("Burns");
        itemList.add(item);
        item = new Item("Joint Dislocation");
        itemList.add(item);
        item = new Item("SnakeBite");
        itemList.add(item);
        group = new Group("Injury", itemList);
        groupList.add(group);

        itemList = new ArrayList<Item>();
        item = new Item("LungCancer");
        itemList.add(item);
        item = new Item("Asthma");
        itemList.add(item);
        item = new Item("Pneumonia");
        itemList.add(item);
        item = new Item("Tuberculosis");
        itemList.add(item);
        group = new Group("Lungs", itemList);
        groupList.add(group);



        itemList = new ArrayList<Item>();
        item = new Item("Skin disease");
        itemList.add(item);
        item = new Item("SpinalCord");
        itemList.add(item);
        item=new Item("FoodPoisoning");
        itemList.add(item);
        group = new Group("Other", itemList);
        groupList.add(group);

    }

    @Override
    public boolean onClose() {
        listAdapter.filterData("");
        expandAll();
        return false;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        listAdapter.filterData(query);
        expandAll();
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        listAdapter.filterData(query);
        expandAll();
        return false;
    }
}
