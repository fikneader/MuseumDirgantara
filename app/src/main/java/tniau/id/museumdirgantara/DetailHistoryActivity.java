package tniau.id.museumdirgantara;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailHistoryActivity extends AppCompatActivity {

    Bundle extras;
    String judul,deskripsi,gambar;

    TextView txt_judul,txt_deskripsi;
    ImageView img_gambar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_history);

        extras = getIntent().getExtras();
        Intent intentku = getIntent();

        judul = extras.getString("judul");
        deskripsi = extras.getString("deskripsi");
        gambar = extras.getString("gambar");


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(judul);

        txt_judul = findViewById(R.id.judul_sejarah);
        txt_deskripsi = findViewById(R.id.deskripsi_sejarah);
        img_gambar = findViewById(R.id.gambar_sejarah);

        if (deskripsi!=null){
            String deskripsinew = deskripsi.replace("/n", System.getProperty("line.separator"));
            txt_deskripsi.setText(deskripsinew);
        }

        txt_judul.setText(judul);

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
