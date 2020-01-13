package tniau.id.museumdirgantara;

import androidx.appcompat.app.AppCompatActivity;
import tniau.id.museumdirgantara.sendmail.GMailSender;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
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
                    sendMessage(txt_nama.getText().toString(),txt_notelp.getText().toString(),txt_alamat.getText().toString(),
                            txt_jumlahrombongan.getText().toString(),txt_tanggal.getText().toString(),txt_kegiatan.getText().toString());
                }
            }
        });
    }

    private void sendMessage(final String nama, final String notelp, final String alamat, final String jumlahrombongan, final String tanggal, final String kegiatan) {
        final ProgressDialog dialog = new ProgressDialog(ReservationActivity.this);
        dialog.setTitle("Mengirim");
        dialog.setMessage("Mohon tunggu sebentar");
        dialog.show();
        final String pesan = "Nama saya " + nama + " ingin reservasi pada tanggal " + tanggal + " untuk kegiatan " + kegiatan;
        Thread sender = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    GMailSender sender = new GMailSender(BuildConfig.email, BuildConfig.password);
                    sender.sendMail(ReservationActivity.this,"Reservasi Museum Dirgantara Mandala - " + nama,
                            pesan,
                            BuildConfig.email,
                            BuildConfig.recipients);
                    dialog.dismiss();
                } catch (Exception e) {
                    Log.e("mylog", "Error: " + e.getMessage());
                }
            }
        });
        sender.start();
        txt_nama.getText().clear();
        txt_notelp.getText().clear();
        txt_alamat.getText().clear();
        txt_jumlahrombongan.getText().clear();
        txt_tanggal.getText().clear();
        txt_kegiatan.getText().clear();
        txt_nama.requestFocus();
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
