package com.vinay.savers;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.Manifest;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.telephony.SmsManager;
import android.view.View;

import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivityvs extends Activity {


    AlertDialog.Builder builder;
    public String phnum;
    FirebaseAuth auth;
    FirebaseUser user;
    DatabaseReference dbref;

    /////////////
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 1;
    private static final int MY_PERMISSIONS_REQUEST_call = 1;
    ///////////////////
    //location
    private LocationManager locationManager;
    Location location;

    private LocationListener locationListener;
    String mloc = new String("nill_bro");

    //
    NavigationView navigation;
    Button button;


    //////////////

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    Button emergencybutton;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainvs);
        builder = new AlertDialog.Builder(this);
        final MainActivity m=new MainActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivityvs.this);
        //FirebaseMessaging.getInstance().subscribeToTopic("test");
        FirebaseInstanceId.getInstance().getToken();
        // Log.d("TOKEN", FirebaseInstanceId.getInstance().getToken());
        emergencybutton=findViewById(R.id.embtn);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();




        dbref= FirebaseDatabase.getInstance().getReference("Number");
        if(user!=null) {

            dbref.child(user.getUid().toString().trim());
            dbref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot msg : dataSnapshot.getChildren()) {
                        if (msg.getKey().equalsIgnoreCase(user.getUid().toString())) {
                            phnum = (String) msg.child("number").getValue(String.class);
                            Toast.makeText(getApplicationContext(), "no is:"+phnum, Toast.LENGTH_SHORT).show();


                        }

                    }


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

        emergencybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                embtnpressed();

            }
        });

        //

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        // Listview Group click listener
        expListView.setOnGroupClickListener(new OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                // Toast.makeText(getApplicationContext(),
                // "Group Clicked " + listDataHeader.get(groupPosition),
                // Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Expanded",
                        Toast.LENGTH_SHORT).show();
            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Collapsed",
                        Toast.LENGTH_SHORT).show();

            }
        });

        // Listview on child click listener
        expListView.setOnChildClickListener(new OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
                /*Toast.makeText(
                        getApplicationContext(),
                        listDataHeader.get(groupPosition)
                                + " : "
                                + listDataChild.get(
                                listDataHeader.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT)
                        .show();*/




                /*switch (groupPosition){
                    case 0:
                        switch(childPosition){
                            case 0:displayalert("http://www.google.com","http://www.fb.com");



                    }



                }*/

                switch (((int)groupPosition)){
                    case 0:

                        switch(((int)childPosition)){
                            case 0:displayalert("https://vsvs.page.link/anemia","https://vsvs.page.link/animiayoutube");
                                break;
                        }break;
                    case 1:
                        switch(childPosition){
                            case 0:displayalert(" https://vsvs.page.link/heartblock","https://vsvs.page.link/heartblock-youtube");
                                break;

                            case 1:displayalert("https://vsvs.page.link/heartattack","https://vsvs.page.link/heartattackyoutube");
                                break;}break;

                    case 2:
                        switch(childPosition){
                            case 0:displayalert("https://vsvs.page.link/asthamainfo","https://vsvs.page.link/asthamayoutube");
                                break;}break;

                    case 3:
                        switch(childPosition){
                            case 0:displayalert("https://www.ready.gov/floods","https://vsvs.page.link/floodyou");
                                break;

                            case 1:displayalert(" https://vsvs.page.link/eleshock","https://vsvs.page.link/elecshockyou");
                                break;}break;

                    case 4: switch(childPosition){
                        case 0:displayalert("https://vsvs.page.link/rabiesinf","https://vsvs.page.link/rabiesyou ");
                            break;

                        case 1:displayalert("https://vsvs.page.link/malariainf","https://vsvs.page.link/malaria-vid");
                            break;
                        case 2:displayalert("https://vsvs.page.link/pox","https://vsvs.page.link/popxyou");
                            break;

                        case 3:displayalert("https://vsvs.page.link/earinf","https://vsvs.page.link/earyou");
                            break;}break;


                    case 5: switch(childPosition){
                        case 0:displayalert("https://vsvs.page.link/noseinf","https://vsvs.page.link/noseyou");
                            break;

                        case 1:displayalert("https://vsvs.page.link/animalbites","https://vsvs.page.link/animalvid");
                            break;
                        case 2:displayalert("https://vsvs.page.link/bones","https://vsvs.page.link/boneyou");
                            break;

                        case 3:displayalert("https://vsvs.page.link/burns","https://vsvs.page.link/burnsvid");
                            break;

                        case 4:displayalert("https://vsvs.page.link/head-inj","https://vsvs.page.link/headvid");
                            break;}


                    case 6: switch(childPosition){
                        case 0:displayalert("https://vsvs.page.link/pois","https://vsvs.page.link/poisvid");
                            break;

                        case 1:displayalert("https://vsvs.page.link/foodpois","https://vsvs.page.link/ffod");
                            break;}

                    case 7: switch(childPosition){
                        case 0:displayalert("https://vsvs.page.link/skin","https://vsvs.page.link/skinvid");
                            break;

                        case 1:displayalert("https://vsvs.page.link/spinal-inf","https://vsvs.page.link/spinalvid");
                            break;}break;




                }
                //Toast.makeText(MainActivityvs.this, "grouppos:"+groupPosition+"childpos:"+childPosition, Toast.LENGTH_SHORT).show();







                /*if((listDataChild.get(
                        listDataHeader.get(groupPosition)).get(
                        childPosition)).toString().equals("anemia")){
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://vinay.page.link/blood"));
                    startActivity(browserIntent);

                }*/

                return false;
            }
        });
    }

    void displayalert(final String  web,final String video){
        //Uncomment the below code to Set the message and title from the strings.xml file
        //builder.setMessage(R.string.dialog_message) .setTitle(R.string.dialog_title);

        //Setting message manually and performing action on button click
       // Toast.makeText(this, "link="+web+"video="+video, Toast.LENGTH_SHORT).show();
        builder.setMessage("which guide do you need?")
                .setCancelable(false)
                .setPositiveButton("web", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        // Toast.makeText(getApplicationContext(),"you choose yes action for alertbox",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(MainActivityvs.this,web.class);
                        intent.putExtra("weba",web);
                        startActivity(intent);
                        dialog.cancel();
                        dialog.dismiss();
                        return;
                    }
                })
                .setNegativeButton("video", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //  Action for 'NO' Button
                        dialog.cancel();
                        dialog.dismiss();
                        //Toast.makeText(getApplicationContext(),"you choose no action for alertbox",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(MainActivityvs.this,web.class);
                        intent.putExtra("weba",video);
                        startActivity(intent);

                        return;
                    }
                });
        //Creating dialog box
        final AlertDialog alert = builder.create();
        //Setting the title manually
        alert.setTitle("Attention");
        alert.show();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                alert.cancel();
            }
        }, 5000);
        //
    }
    private void embtnpressed() {

       // Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();
        sendSms();

        /////////////////////////////////////////
        Intent phoneIntent = new Intent(Intent.ACTION_CALL);


        phoneIntent.setData(Uri.parse("tel:"+phnum));
        Toast.makeText(this, "kulli calling"+phnum, Toast.LENGTH_SHORT).show();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(phoneIntent);


    }
    private void sendSms() {
        ////////////////////location sending
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {


                mloc="vs "+location.getLatitude()+"   "+location.getLongitude();
                //Toast.makeText(MainActivityvs.this, "loc updated", Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
               // Toast.makeText(MainActivityvs.this, "e1", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onProviderEnabled(String provider) {
               // Toast.makeText(MainActivityvs.this, "e2", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onProviderDisabled(String provider) {
                //Toast.makeText(MainActivityvs.this, "e3", Toast.LENGTH_SHORT).show();

            }
        };
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
           /* requestPermissions(new String[]{
                    android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.INTERNET
            },10);*/
            Toast.makeText(this, "no  permission", Toast.LENGTH_SHORT).show();
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        else{


            //locationManager.requestLocationUpdates("gps", 2000, 0, locationListener);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 0, locationListener);
            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            mloc="vs "+location.getLatitude()+"   "+location.getLongitude();
            //Toast.makeText(this, "loc updated", Toast.LENGTH_SHORT).show();

        }

        ///////////////////
        SmsManager smsManager = SmsManager.getDefault();

        Toast.makeText(this, "Your Cordintaes\n"+mloc+"\nreach me", Toast.LENGTH_SHORT).show();

       // smsManager.sendTextMessage(phnum, null, "i'm in emergency\n"+mloc+"\nreach me", null, null);
        Toast.makeText(this, "msg sent", Toast.LENGTH_SHORT).show();
        //FirebaseCrash.log("emergency report mloc");
        Toast.makeText(this, "log reported", Toast.LENGTH_SHORT).show();

    }

    public void onRequestPermissionsResult(int requestcode,String[] permissions,int[]grantresults) {
        switch (requestcode){
            case 10:
                if(grantresults.length>0 && grantresults[0]==PackageManager.PERMISSION_GRANTED)
                    return;
        }
    }
    public void checkForSmsPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) !=
                PackageManager.PERMISSION_GRANTED) {
            // Log.d(TAG, getString(R.string.permission_not_granted));
            // Permission not yet granted. Use requestPermissions().
            // MY_PERMISSIONS_REQUEST_SEND_SMS is an
            // app-defined int constant. The callback method gets the
            // result of the request.
          /*  ActivityCompat.requestPermissions(MainActivity,
                    new String[]{Manifest.permission.SEND_SMS},
                    MY_PERMISSIONS_REQUEST_SEND_SMS);*/
            Toast.makeText(this, "no permission1", Toast.LENGTH_SHORT).show();
        } else {
            // Permission already granted. Enable the SMS button.
            return;
        }
    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {

        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();



        // Adding child data
        listDataHeader.add("blood");
        listDataHeader.add("heart");
        listDataHeader.add("lungs");
        listDataHeader.add("Environmental");
        listDataHeader.add("Infectious Disease");
        listDataHeader.add("Injury");
        listDataHeader.add("Toxicological");
        listDataHeader.add("Other");



        // Adding child data
        List<String> blood = new ArrayList<String>();
        blood.add("anemia");


        List<String> heart = new ArrayList<String>();
        heart.add("heart block");
        heart.add("cardiac arrest");


        List<String>lungs = new ArrayList<String>();
        lungs.add("asthama");


        List<String>Environmental=new ArrayList<String>();
        Environmental.add("Flood");
        Environmental.add("Electric Shock");


        List<String>InfectiousDiseases=new ArrayList<String>();
        InfectiousDiseases.add("Rabies");
        InfectiousDiseases.add("Malaria");
        InfectiousDiseases.add("Chicken Pox");
        InfectiousDiseases.add("Ear Infection");


        List<String>Injury=new ArrayList<String>();
        Injury.add("Nose Bleed");
        Injury.add("Bite");
        Injury.add("Bone Fracture");
        Injury.add("Burns");
        Injury.add("Head Injury");

        List<String>Toxicological=new ArrayList<String>();
        Toxicological.add("Poisoning");
        Toxicological.add("Food Poisoning");


        List<String>Other=new ArrayList<String>();
        Other.add("Skin Disease");
        Other.add("Spinal Cord Injury");



        listDataChild.put(listDataHeader.get(0), blood); // Header, Child data
        listDataChild.put(listDataHeader.get(1), heart);
        listDataChild.put(listDataHeader.get(2),lungs);
        listDataChild.put(listDataHeader.get(3),Environmental);
        listDataChild.put(listDataHeader.get(4), InfectiousDiseases);
        listDataChild.put(listDataHeader.get(5), Injury);
        listDataChild.put(listDataHeader.get(6),Toxicological);
        listDataChild.put(listDataHeader.get(7),Other);

    }
}