package tniau.id.museumdirgantara;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

import tniau.id.museumdirgantara.sendmail.GMailSender;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ReservationActivity extends AppCompatActivity {

    EditText txt_nama,txt_notelp,txt_alamat,txt_jumlahrombongan,txt_tanggal,txt_kegiatan, txt_email;
    Button btn_kirimreservasi;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Reservasi Museum");

        txt_nama = findViewById(R.id.txt_nama);
        txt_notelp = findViewById(R.id.txt_notelp);
        txt_email = findViewById(R.id.txt_email);
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        txt_alamat = findViewById(R.id.txt_alamat);
        txt_jumlahrombongan = findViewById(R.id.txt_jumlahrombongan);
        txt_tanggal = findViewById(R.id.txt_tanggal);
        txt_kegiatan = findViewById(R.id.txt_kegiatan);
        btn_kirimreservasi = findViewById(R.id.btn_kirimreservasi);

        txt_tanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });

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
                }else if (txt_email.getText().toString().isEmpty()){
                    txt_email.setError("Silahkan isi email terlebih dahulu");
                    txt_email.requestFocus();
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
                    sendMessage(txt_nama.getText().toString(),txt_notelp.getText().toString(),txt_email.getText().toString(),txt_alamat.getText().toString(),
                            txt_jumlahrombongan.getText().toString(),txt_tanggal.getText().toString(),txt_kegiatan.getText().toString());
                }
            }
        });
    }

    private void sendMessage(final String nama, final String notelp,final String email ,final String alamat, final String jumlahrombongan, final String tanggal, final String kegiatan) {
        final ProgressDialog dialog = new ProgressDialog(ReservationActivity.this);
        dialog.setTitle("Mengirim");
        dialog.setMessage("Mohon tunggu sebentar");
        dialog.show();
        final String pesan =  " Nama Lengkap : " + nama + System.getProperty("line.separator") + " Nomor Telepon : " + notelp + System.getProperty("line.separator")  + " Email : " + email + System.getProperty("line.separator")  + " Instansi/Alamat : " + alamat + System.getProperty("line.separator")  + " Jumlah Orang : " + jumlahrombongan + " Orang"  +System.getProperty("line.separator")  +" Tanggal Reservasi : " + tanggal + System.getProperty("line.separator") + " Keperluan : " + kegiatan + System.getProperty("line.separator") + " Atas perhatian Bapak/Ibu kami ucapkan Terimakasih";
        Thread sender = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    GMailSender sender = new GMailSender(BuildConfig.email, BuildConfig.password);
                    sender.sendMail(getApplicationContext(),"#Reservasi Museum Dirgantara Mandala - " + nama,
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
        txt_email.getText().clear();
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
    private void showDateDialog(){
        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                txt_tanggal.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
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
                callIntent.setData(Uri.parse("tel:0274484453"));    //this is the phone number calling
                if (ActivityCompat.checkSelfPermission(ReservationActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    //request permission from user if the app hasn't got the required permission
                    ActivityCompat.requestPermissions(ReservationActivity.this,
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
