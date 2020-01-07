package tniau.id.museumdirgantara;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailRoomActivity extends AppCompatActivity {
    TextView namaRuangan,deskripsiRuangan;
    ImageView gambarRuangan;
    String id,nama,deskripsi,gambar;
    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_room);

        extras = getIntent().getExtras();
        Intent intentku = getIntent();

        id = extras.getString("id");
        nama = extras.getString("nama");
        deskripsi = extras.getString("deskripsi");
        gambar = extras.getString("gambar");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(nama);

        namaRuangan = findViewById(R.id.nama_ruangan);
        deskripsiRuangan = findViewById(R.id.deskripsi_ruangan);
        gambarRuangan = findViewById(R.id.gambar_ruangan);

        namaRuangan.setText(nama);

        if (deskripsi!=null){
            String deskripsinew = deskripsi.replace("/n", System.getProperty("line.separator"));
            deskripsiRuangan.setText(deskripsinew);
        }

        Resources res = getApplicationContext().getResources();
        String mDrawableName = gambar;
        int resourceId = res.getIdentifier(mDrawableName , "drawable", getApplicationContext().getPackageName());
        Drawable drawable = res.getDrawable(resourceId);
        gambarRuangan.setImageDrawable(drawable );

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
