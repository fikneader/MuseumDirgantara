
package tniau.id.museumdirgantara.Function;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import tniau.id.museumdirgantara.BuildConfig;
import tniau.id.museumdirgantara.Model.Heroes;
import tniau.id.museumdirgantara.Model.ListRoom;
import tniau.id.museumdirgantara.Model.Room;
import tniau.id.museumdirgantara.Model.History;

/**
 * Created by fikneader on 1/29/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static Context mContext;
    public static final String DATABASE_NAME = "museumdirgantara.sqlite";
    public static String idruangan;
    public static final int DBVERSION = 1;
    public static final String DBLOCATION = "/data/data/"+ BuildConfig.APPLICATION_ID+"/databases/";
    public static final String QUERY_LISTROOM = "Select * From ruangan Where nama_ruangan Like ? ORDER BY id_ruangan ASC";
    public static final String QUERY_LISTROOMDETAIL = "Select * From daftar_ruangan Where nama_ruangan Like ? AND id_ruangan=";
    public static final String QUERY_LISTHISTORY = "Select * From sejarah Where judul_sejarah Like ? ORDER BY id ASC";
    public static final String QUERY_LISTHEROES = "Select * From pahlawan Where nama_pahlawan Like ? ORDER BY id ASC";
    private SQLiteDatabase mDatabase;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DBVERSION);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }

    public void openDatabase() {
        String dbPath = mContext.getDatabasePath(DATABASE_NAME).getPath();
        if (mDatabase != null && mDatabase.isOpen()) {
        return;
        }
        mDatabase = SQLiteDatabase.openDatabase(dbPath,null,SQLiteDatabase.OPEN_READWRITE);
    }

    public void closeDatabase(){
        if (mDatabase != null){
            mDatabase.close();
        }
    }

    public List<Room> getListRoom(String wordSearch){
        Room roomModel = null;
        List<Room> roomModelList = new ArrayList<>();
        openDatabase();
        String[] args = {"%" + wordSearch +"%"};

        Cursor cursor = mDatabase.rawQuery(QUERY_LISTROOM,args);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            roomModel = new Room(cursor.getString(0),cursor.getString(1),cursor.getString(2), cursor.getString(3));
            roomModelList.add(roomModel);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return roomModelList;
    }

    public List<ListRoom> getListRoomDetail(String wordSearch, String idruanganku){
        ListRoom listroomModel = null;
        List<ListRoom> roomModelList = new ArrayList<>();
        openDatabase();
        String[] args = {"%" + wordSearch +"%"};

        Cursor cursor = mDatabase.rawQuery(QUERY_LISTROOMDETAIL + idruanganku,args);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            listroomModel = new ListRoom(cursor.getString(0),cursor.getString(1),cursor.getString(2), cursor.getString(3),cursor.getString(4));
            roomModelList.add(listroomModel);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return roomModelList;
    }

    public List<History> getListHistory(String wordSearch){
        History historyModel = null;
        List<History> historyModelList = new ArrayList<>();
        openDatabase();
        String[] args = {"%" + wordSearch +"%"};

        Cursor cursor = mDatabase.rawQuery(QUERY_LISTHISTORY,args);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            historyModel = new History(cursor.getString(0),cursor.getString(1),cursor.getString(2), cursor.getString(3));
            historyModelList.add(historyModel);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return historyModelList;
    }

    public List<Heroes> getListHeroes(String wordSearch){
        Heroes heroesModel = null;
        List<Heroes> heroesModelList = new ArrayList<>();
        openDatabase();
        String[] args = {"%" + wordSearch +"%"};

        Cursor cursor = mDatabase.rawQuery(QUERY_LISTHEROES,args);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            heroesModel = new Heroes(cursor.getString(0),cursor.getString(1),cursor.getString(2), cursor.getString(3));
            heroesModelList.add(heroesModel);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return heroesModelList;
    }

}
