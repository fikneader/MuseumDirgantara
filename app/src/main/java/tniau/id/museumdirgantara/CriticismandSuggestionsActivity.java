package tniau.id.museumdirgantara;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class CriticismandSuggestionsActivity extends AppCompatActivity {

    ImageView btn_facebook,btn_twitter,btn_instagram;
    EditText txt_nama,txt_kritikdansaran;
    Button btn_kirimkritikdansaran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criticismand_suggestions);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Kritik dan Saran Museum");

        btn_facebook = findViewById(R.id.btn_facebook);
        btn_twitter = findViewById(R.id.btn_twitter);
        btn_instagram = findViewById(R.id.btn_instagram);
        txt_nama = findViewById(R.id.txt_nama);
        txt_kritikdansaran = findViewById(R.id.txt_kritikdansaran);
        btn_kirimkritikdansaran = findViewById(R.id.btn_kirimkritikdansaran);

        btn_kirimkritikdansaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txt_nama.getText().toString().isEmpty()) {
                    txt_nama.setError("Silahkan isi nama anda terlebih dahulu");
                    txt_nama.requestFocus();
                    return;
                } else if (txt_kritikdansaran.getText().toString().isEmpty()){
                    txt_kritikdansaran.setError("Silahkan isi kritik/saran terlebih dahulu");
                    txt_kritikdansaran.requestFocus();
                    return;
                } else {
                    Toast.makeText(CriticismandSuggestionsActivity.this, "Kirim", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getOpenFacebookIntent(getApplicationContext());
            }
        });

        btn_twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getOpenTwitterIntent(getApplicationContext());
            }
        });

        btn_instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getOpenInstagramIntent(getApplicationContext());
            }
        });


    }

    public static void getOpenFacebookIntent(Context context) {
        String YourPageURL = "https://www.facebook.com/n/?MuspusdirlaYogyakarta";
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(YourPageURL));
        browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(browserIntent);
    }

    public static void getOpenTwitterIntent(Context context) {
        String YourPageURL = "https://twitter.com/musdirgantara";
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(YourPageURL));
        browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(browserIntent);
    }

    public static void getOpenInstagramIntent(Context context) {
        String YourPageURL = "https://www.instagram.com/museumdirgantara/";
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(YourPageURL));
        browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(browserIntent);
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
