package maceda.alejandro.alexiavnplayer.database;


import android.app.*;
import android.content.Context;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseOpenHelper extends SQLiteOpenHelper {

    private Context context;

    public DatabaseOpenHelper(Context context, String name,
                              CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createRecents = "CREATE TABLE recents (_id integer primary key autoincrement, name, path, file, image, savefile, line, username);";

        db.execSQL(createRecents);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
