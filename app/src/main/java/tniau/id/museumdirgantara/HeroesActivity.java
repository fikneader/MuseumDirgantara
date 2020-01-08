package tniau.id.museumdirgantara;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import tniau.id.museumdirgantara.Adapter.HeroesAdapter;
import tniau.id.museumdirgantara.Function.DatabaseHelper;
import tniau.id.museumdirgantara.Model.Heroes;

import android.content.Context;
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

public class HeroesActivity extends AppCompatActivity {

    String TAG = "Dirgantara";
    private RecyclerView rvListRoom;
    private HeroesAdapter heroesAdapter;
    private List<Heroes> heroesList;
    private DatabaseHelper mDBHelper;
    TextView no_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heroes);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Pahlawan TNI AU");

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
        heroesList = mDBHelper.getListHeroes("");
        heroesAdapter = new HeroesAdapter();
        heroesAdapter.setData(heroesList);
        rvListRoom.setAdapter(heroesAdapter);

        SearchView searchView = findViewById(R.id.cari_pahlawan);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchPahlawan(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchPahlawan(newText);
                return false;
            }
        });

    }

    private void searchPahlawan(String wordSearch){
        heroesList.clear();
        if (mDBHelper.getListHistory(wordSearch).isEmpty()){
            no_data.setText("Data "+ wordSearch +" tidak ditemukan !");
            no_data.setVisibility(View.VISIBLE);
        } else {
            heroesList.clear();
            no_data.setVisibility(View.GONE);
            heroesList = mDBHelper.getListHeroes(wordSearch);
            heroesAdapter.setData(heroesList);
            rvListRoom.setAdapter(heroesAdapter);
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
