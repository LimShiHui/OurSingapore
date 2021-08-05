package sg.edu.rp.c346.id20032316.oursingapore;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "island.db";
    public static final int DATABASE_VERSION = 1;
    private static final String TABLE_ISLAND = "island";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_ISLAND_NAME = "island_name";
    private static final String COLUMN_ISLAND_DESCRIPTION = "island_description";
    private static final String COLUMN_ISLAND_SIZE = "island_size";
    private static final String COLUMN_ISLAND_STAR = "island_star";


    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createIslandSql = "CREATE TABLE " + TABLE_ISLAND + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_ISLAND_NAME + " TEXT,"
                + COLUMN_ISLAND_DESCRIPTION + " TEXT,"
                + COLUMN_ISLAND_SIZE + " INTEGER,"
                + COLUMN_ISLAND_STAR + " REAL)";
        db.execSQL(createIslandSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ISLAND);
        onCreate(db);

    }

    public  long insertIsland(String name, String description, int size, float star) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ISLAND_NAME, name);
        values.put(COLUMN_ISLAND_DESCRIPTION, description);
        values.put(COLUMN_ISLAND_SIZE, size);
        values.put(COLUMN_ISLAND_STAR, star);

        long result = db.insert(TABLE_ISLAND, null, values);
        db.close();
        return result;
    }

    public ArrayList<Island> getAllIsland() {
        ArrayList<Island> islands = new ArrayList<Island>();

        String selectQuery = "SELECT " + COLUMN_ID + ","
                + COLUMN_ISLAND_NAME + ","
                + COLUMN_ISLAND_DESCRIPTION + ","
                + COLUMN_ISLAND_SIZE + ","
                + COLUMN_ISLAND_STAR
                + " FROM " + TABLE_ISLAND;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String islandName = cursor.getString(1);
                String islandDes = cursor.getString(2);
                int islandSize = cursor.getInt(3);
                float islandStar = cursor.getFloat(4);
                Island note = new Island(id, islandName, islandDes, islandSize, islandStar);
                islands.add(note);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return islands;
    }

    public int updateIsland(Island data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ISLAND_NAME, data.getName());
        values.put(COLUMN_ISLAND_DESCRIPTION, data.getDescription());
        values.put(COLUMN_ISLAND_SIZE, data.getSquareKm());
        values.put(COLUMN_ISLAND_STAR, data.getStars());
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(data.get_id())};
        int result = db.update(TABLE_ISLAND, values, condition, args);
        db.close();
        return result;
    }

    public int deleteIsland(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_ISLAND, condition, args);
        db.close();
        return result;
    }

    public ArrayList<Island> getAllIslandsByStar(int starFilter) {
        ArrayList<Island> islands = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_ISLAND_NAME, COLUMN_ISLAND_DESCRIPTION, COLUMN_ISLAND_SIZE, COLUMN_ISLAND_STAR};
        String condition = COLUMN_ISLAND_STAR + " >= ?";
        String[] args = {String.valueOf(starFilter)};

        Cursor cursor = db.query(TABLE_ISLAND, columns, condition, args, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String islandName = cursor.getString(1);
                String islandDes = cursor.getString(2);
                int islandSize = cursor.getInt(3);
                float islandStar = cursor.getFloat(4);
                Island note = new Island(id, islandName, islandDes, islandSize, islandStar);
                islands.add(note);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return islands;
    }


}
