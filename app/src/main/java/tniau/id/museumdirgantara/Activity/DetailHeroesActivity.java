package tniau.id.museumdirgantara.Activity;

import androidx.appcompat.app.AppCompatActivity;
import tniau.id.museumdirgantara.R;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailHeroesActivity extends AppCompatActivity {

    Bundle extras;
    String nama,deskripsi,gambar;

    TextView txt_nama,txt_deskripsi;
    ImageView img_gambar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_heroes);

        extras = getIntent().getExtras();
        Intent intentku = getIntent();

        nama = extras.getString("nama");
        deskripsi = extras.getString("deskripsi");
        gambar = extras.getString("gambar");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(nama);

        txt_nama = findViewById(R.id.nama_pahlawan);
        txt_deskripsi = findViewById(R.id.deskripsi_pahlawan);
        img_gambar = findViewById(R.id.gambar_pahlawan);

        if (deskripsi!=null){
            String deskripsinew = deskripsi.replace("/n", System.getProperty("line.separator"));
            txt_deskripsi.setText(deskripsinew);
        }

        txt_nama.setText(nama);

        Resources res = getApplicationContext().getResources();
        String mDrawableName = gambar;
        int resourceId = res.getIdentifier(mDrawableName , "drawable", getApplicationContext().getPackageName());
        Drawable drawable = res.getDrawable(resourceId);
        img_gambar.setImageDrawable(drawable );
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
