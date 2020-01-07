package tniau.id.museumdirgantara;

import androidx.appcompat.app.AppCompatActivity;
import tniau.id.museumdirgantara.Function.DatabaseHelper;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class SplashScreenActivity extends AppCompatActivity {

    String TAG = "Dirgantara";
    private DatabaseHelper mDBHelper;
    private File database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        mulaiAnimasiFadein();

        mDBHelper = new DatabaseHelper(this);
        database = getApplicationContext().getDatabasePath(DatabaseHelper.DATABASE_NAME);

        Thread timerThread = new Thread(){
            public void run(){
                try{

                    if(!database.exists()){
                        mDBHelper.getReadableDatabase();
                        mDBHelper.close();
                        if (copyDatabase(getApplicationContext())){
                            Log.i(TAG,"Copy Success");
                        } else {
                            Log.i(TAG,"Copy Failed");
                        }
                    } else {
                        Log.i(TAG,"Database exits");
                    }

                    sleep(2500);
                    Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{

                }
            }
        };
        timerThread.start();
    }

    //memanggil method untuk menjalankan animasi pada button onClick mulaiAnimasiFadein
    public void mulaiAnimasiFadein() {
        ImageView imageView = findViewById(R.id.imageView);
        TextView textView = findViewById(R.id.textView);
        //menjalankanya pada imageview
        Animation startAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein);
        //memanggil resource animasi di folder res anim fade in
        imageView.startAnimation(startAnimation);
        //mulai animasi
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
}
