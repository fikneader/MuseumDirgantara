package tniau.id.museumdirgantara;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import tniau.id.museumdirgantara.Adapter.ListRoomAdapter;
import tniau.id.museumdirgantara.Adapter.RoomAdapter;
import tniau.id.museumdirgantara.Function.DatabaseHelper;
import tniau.id.museumdirgantara.Model.ListRoom;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class ListRoomActivity extends AppCompatActivity {

    String TAG = "Dirgantara";
    private RecyclerView rvListRoom;
    private ListRoomAdapter roomAdapter;
    private List<ListRoom> roomList;
    private DatabaseHelper mDBHelper;
    TextView no_data;
    String id,nama,deskripsi,gambar;
    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_room);

        extras = getIntent().getExtras();
        Intent intentku = getIntent();

        id = extras.getString("id");
        nama = extras.getString("nama");
        deskripsi = extras.getString("deskripsi");
        gambar = extras.getString("gambar");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(nama);

        rvListRoom = findViewById(R.id.hasilcari);
        no_data = findViewById(R.id.txt_nodata);
        rvListRoom.setLayoutManager(new LinearLayoutManager(this));

        mDBHelper = new DatabaseHelper(this);

        File database = getApplicationContext().getDatabasePath(DatabaseHelper.DATABASE_NAME);
        if(!database.exists()){
            mDBHelper.getReadableDatabase();
            mDBHelper.close();
            if (copyDatabase(this)){
                Log.i(TAG,"Copy Success");
            } else {
                Log.i(TAG,"Copy Failed");
            }
        } else {
            Log.i(TAG,"Database exits");
        }

        rvListRoom.setHasFixedSize(true);
        rvListRoom.setItemAnimator(new DefaultItemAnimator());
        roomList = mDBHelper.getListRoomDetail("",id);
        roomAdapter = new ListRoomAdapter();
        roomAdapter.setData(roomList);
        rvListRoom.setAdapter(roomAdapter);

        SearchView searchView = findViewById(R.id.cari_ruangan);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchRoom(query,id);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchRoom(newText,id);
                return false;
            }
        });
    }

    private void searchRoom(String wordSearch,String idruanganku){
        roomList.clear();
        if (mDBHelper.getListRoomDetail(wordSearch,idruanganku).isEmpty()){
//            Toast.makeText(this,"Data "+ wordSearch +" tidak ditemukan !",Toast.LENGTH_SHORT).show();
            no_data.setText("Data "+ wordSearch +" tidak ditemukan !");
            no_data.setVisibility(View.VISIBLE);
        } else {
            roomList.clear();
            no_data.setVisibility(View.GONE);
            roomList = mDBHelper.getListRoomDetail(wordSearch,idruanganku);
            roomAdapter.setData(roomList);
            rvListRoom.setAdapter(roomAdapter);
        }

    }

    private boolean copyDatabase(Context context){
        try {
            InputStream inputStream = context.getAssets().open(DatabaseHelper.DATABASE_NAME);
            String outFileName = DatabaseHelper.DBLOCATION + DatabaseHelper.DATABASE_NAME;
            OutputStream outputStream = new FileOutputStream(outFileName);
            byte [] buff = new byte[1024];
            int length = 0;
            while ((length = inputStream.read(buff)) > 0){
                outputStream.write(buff,0,length);
            }
            outputStream.flush();
            outputStream.close();
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }

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
