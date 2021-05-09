package maceda.alejandro.alexiavnplayer;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import java.util.HashSet;
import java.util.Set;
import android.widget.Toast;
import android.content.Context;


public class DatabaseConnector {
	
	private static final String DB_NAME = "store";
	private SQLiteDatabase database;
	private DatabaseOpenHelper dbOpenHelper;
	private Cursor cursor;


	public DatabaseConnector(Context context) {
		dbOpenHelper = new DatabaseOpenHelper(context, DB_NAME, null, 1);
	}
	
	   public void open() throws SQLException 
	   {
	      //open database in reading/writing mode
	      database = dbOpenHelper.getWritableDatabase();
	   } 
	   public void close() 
	   {
	      if (database != null)
	         database.close();
	   }	   


			   //	 name, path, file, image, savefile, line, username
	public void insertRecent(String name, String path, String file, String image,  String savefile, int line,  String username )
	{
		ContentValues newCon = new ContentValues();
		newCon.put("name", name);
		newCon.put("path", path);
		newCon.put("file", file);
		newCon.put("image", image);
		newCon.put("savefile", savefile);
		newCon.put("line", line);
		newCon.put("username", username);



		open();
		database.insert("recents", null, newCon);
		close();
	}
			
			   public void updateRecent(long id, String savefile)
			   {
			      ContentValues editCon = new ContentValues();
			      editCon.put("file", savefile);

			      open();
			      database.update("recents", editCon, "_id=" + id, null);
			      close();
			   }


	public Cursor getAllRecents()
	{

	    //_id integer primary key autoincrement,  name, path, file, image, savefile, line, username
					cursor = database.query("recents", new String[] {"_id", "name", "path",
					"file",  "image", "savefile", "line", "username" },
											null, null, null, null, "_id DESC");

		// }
		//	}
		return cursor;

	}


    public void deleteRecent(long id)
    {
        open();
        database.delete("recents", "_id=" + id, null);
        close();
    }
	
}
