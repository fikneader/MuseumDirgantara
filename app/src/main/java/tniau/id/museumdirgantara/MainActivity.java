package tniau.id.museumdirgantara;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
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
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://maps.google.co.in/maps?q=" +"Museum Dirgantara"));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
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
}