package tniau.id.museumdirgantara;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import tniau.id.museumdirgantara.Function.Tools;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;

public class MainActivity extends AppCompatActivity {

    String TAG = "MUSEUMDIRGANTARA";

    boolean doubleBackToExitPressedOnce = false;

    //Fragment
    final Fragment fragmentHome = new HomeFragment();
    final Fragment fragmentInfo = new InfoFragment();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = fragmentHome ;
    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fm.beginTransaction().add(R.id.frame_container, fragmentInfo, "2").hide(fragmentInfo).commit();
        fm.beginTransaction().add(R.id.frame_container,fragmentHome, "1").commit();

        //Bottom Nav Bar
        SpaceNavigationView spaceNavigationView = (SpaceNavigationView) findViewById(R.id.space);
        spaceNavigationView.initWithSaveInstanceState(savedInstanceState);
        spaceNavigationView.addSpaceItem(new SpaceItem("Beranda", R.drawable.ic_home_blue_24dp));
        spaceNavigationView.addSpaceItem(new SpaceItem("Info", R.drawable.ic_info_blue_24dp));
        spaceNavigationView.shouldShowFullBadgeText(true);
        spaceNavigationView.setCentreButtonIconColorFilterEnabled(false);

        spaceNavigationView.setSpaceOnClickListener(new SpaceOnClickListener() {
            @Override
            public void onCentreButtonClick() {
                showDialogImageCenter();
            }

            @Override
            public void onItemClick(int itemIndex, String itemName) {
                if (itemName.equals("Beranda")){
                    fm.beginTransaction().hide(active).show(fragmentHome).commit();
                    active = fragmentHome;
                }
                if (itemName.equals("Info")){
                        fm.beginTransaction().hide(active).show(fragmentInfo).commit();
                        active = fragmentInfo;
                }
            }

            @Override
            public void onItemReselected(int itemIndex, String itemName) {
//                Toast.makeText(HomeActivity.this, itemIndex + " " + itemName, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Tekan Lagi Untuk Keluar", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.threedot, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle item selection
        switch (item.getItemId()) {

            case R.id.tentang:
                Intent intentTentang = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intentTentang);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showDialogImageCenter() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
        dialog.setContentView(R.layout.dialog_main_button);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(true);

        ImageView close = dialog.findViewById(R.id.imgclose);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
                dialog.dismiss();
            }
        });

        CardView openhpone = dialog.findViewById(R.id.open_phone);
        openhpone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL); //use ACTION_CALL class
                callIntent.setData(Uri.parse("tel:0895371393492"));    //this is the phone number calling
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    //request permission from user if the app hasn't got the required permission
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.CALL_PHONE},   //request specific permission from user
                            10);
                    return;
                }else {     //have got permission
                    try{
                        startActivity(callIntent);  //call activity and make phone call
                    }
                    catch (android.content.ActivityNotFoundException ex){
                        Toast.makeText(getApplicationContext(),"Terjadi kesalahan pada aplikasi",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        CardView openmap = dialog.findViewById(R.id.open_map);
        openmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://maps.google.co.in/maps?q=" +"Museum Dirgantara"));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        dialog.show();
    }

}