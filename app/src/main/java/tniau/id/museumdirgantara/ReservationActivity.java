package tniau.id.museumdirgantara;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ReservationActivity extends AppCompatActivity {

    EditText txt_nama,txt_notelp,txt_alamat,txt_jumlahrombongan,txt_tanggal,txt_kegiatan;
    Button btn_kirimreservasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Reservasi Museum");

        txt_nama = findViewById(R.id.txt_nama);
        txt_notelp = findViewById(R.id.txt_notelp);
        txt_alamat = findViewById(R.id.txt_alamat);
        txt_jumlahrombongan = findViewById(R.id.txt_jumlahrombongan);
        txt_tanggal = findViewById(R.id.txt_tanggal);
        txt_kegiatan = findViewById(R.id.txt_kegiatan);
        btn_kirimreservasi = findViewById(R.id.btn_kirimreservasi);

        btn_kirimreservasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txt_nama.getText().toString().isEmpty()) {
                    txt_nama.setError("Silahkan isi nama anda terlebih dahulu");
                    txt_nama.requestFocus();
                    return;
                } else if (txt_notelp.getText().toString().isEmpty()){
                    txt_notelp.setError("Silahkan isi no telepon terlebih dahulu");
                    txt_notelp.requestFocus();
                    return;
                } else if (txt_alamat.getText().toString().isEmpty()){
                    txt_alamat.setError("Silahkan isi alamat terlebih dahulu");
                    txt_alamat.requestFocus();
                    return;
                } else if (txt_jumlahrombongan.getText().toString().isEmpty()){
                    txt_jumlahrombongan.setError("Silahkan isi jumlah rombongan terlebih dahulu");
                    txt_jumlahrombongan.requestFocus();
                    return;
                } else if (txt_tanggal.getText().toString().isEmpty()){
                    txt_tanggal.setError("Silahkan isi tanggal terlebih dahulu");
                    txt_tanggal.requestFocus();
                    return;
                } else if (txt_kegiatan.getText().toString().isEmpty()){
                    txt_kegiatan.setError("Silahkan isi kegiatan terlebih dahulu");
                    txt_kegiatan.requestFocus();
                    return;
                }
                else {
                    Toast.makeText(ReservationActivity.this, "Kirim", Toast.LENGTH_SHORT).show();
                }
            }
        });
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
