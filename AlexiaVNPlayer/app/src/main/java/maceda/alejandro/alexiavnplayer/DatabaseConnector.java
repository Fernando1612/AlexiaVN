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
	   
	   public void insertStore(String name, String dir, String code, String phone, String mail, String image) 
			   {
			      ContentValues newCon = new ContentValues();
			      newCon.put("name", name);
			      newCon.put("dir", dir);
				  newCon.put("code", code);
			      newCon.put("phone", phone);
				   newCon.put("mail", mail);
				   newCon.put("image", image);
				  

			      open();
			      database.insert("store", null, newCon);
			      close();
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
/*
			  
			   public Cursor getAllContacts() 
			   {
			      return database.query("store", new String[] {"_id", "name"}, 
			         null, null, null, null, "name");
			   }
*/
			   public Cursor getOneStore() 
			   {
			      return database.query("store", null, "_id="+1, null, null, null, null);
			   }
			   
			   public void deleteStore(long id) 
			   {
			      open(); 
			      database.delete("store", "_id=" + id, null);
			      close();
			   }
	
	//" (_id integer primary key autoincrement, ean, product, price, units, category, location, min, max, notes, details, photo, date);";
//	public void insertProduct(String ean, String product, String cost, String price, String units, String category,
	//String location, String stock, String min, String expiration, String notes,  String photo, String date, String cost, String inventory) 
	public void insertProduct(String ean, String product, String price, String price2,
	String units, String category, String location,  String min, String expiration, 
	String notes,  String photo, String date, int typelist, String cost, int inventory) 
	{
		ContentValues newCon = new ContentValues();

		newCon.put("ean", ean);
		newCon.put("product", product);
		//newCon.put("cost", cost);
		newCon.put("price", price);
		newCon.put("price2", price2);
		newCon.put("units", units);
		newCon.put("category", category);
		newCon.put("location", location);
	//	newCon.put("stock", stock);
		newCon.put("min", min);
		newCon.put("expiration", expiration);	
		newCon.put("notes", notes);
		//newCon.put("details", details);
		newCon.put("photo", photo);
		newCon.put("date", date);
		//0 = compra-venta, 1= Compra, 2= Venta
		newCon.put("typelist", typelist);
		
		newCon.put("cost", cost);
		newCon.put("inventory", inventory);
		

		open();
		database.insert("products", null, newCon);
		close();
	}


//	public void updateProduct(long id, String ean, String product, String cost, String price, String units, 
	//String category, String location, String stock, String min, String expiration, String notes,  String photo, String date) 
	public void updateProduct(long id, String ean, String product, String price, 
	String price2, String units, String category, String location, String min, 
	String expiration, String notes,  String photo, String date, int typelist, String cost, int inventory)
	{

		ContentValues editCon = new ContentValues();

		editCon.put("ean", ean);
		editCon.put("product", product);
	//	editCon.put("cost", cost);
		editCon.put("price", price);
		editCon.put("price2", price2);
		editCon.put("units", units);
		editCon.put("category", category);
		editCon.put("location", location);
	//	editCon.put("stock", stock);
		editCon.put("min", min);
		editCon.put("expiration", expiration);	
		editCon.put("notes", notes);
		//newCon.put("details", details);
		editCon.put("photo", photo);
		editCon.put("date", date);
		editCon.put("typelist", typelist);
		editCon.put("cost", cost);
		editCon.put("inventory", inventory);
		
		
		
		open();
		database.update("products", editCon, "_id=" + id, null);
		close();
	}
	
	public void updatePricetransaction(String id,  String price)
	{

		ContentValues editCon = new ContentValues();

		
		//	editCon.put("cost", cost);
		editCon.put("price", price);
		
		open();
		database.update("transactions", editCon, "_id=" + id, null);
		close();
	}

	public Cursor getAllMovs(String filter) {
		
		//"CREATE TABLE movs (_id integer primary key autoincrement, type, product, quantity, price, status, details, transaction_id)
		//return database.query("transactions", null, "product="+filter, null, null, null, null);
		return database.query("movs", null, null, null, null, null, null);
	}
	public Cursor getIdMovs(String filter) {
		String args[] = new String[] {filter};
		//String createTransactions = "CREATE TABLE transactions (_id integer primary key autoincrement, 
		//number, product, quantity, price, type, suppliers, orders, datex);";
		//return database.query("transactions", null, "product="+filter, null, null, null, null);
		return database.query("movs", new String[] {"product", "quantity", "_id", "price", "transaction_id"} ,
								"transaction_id=?", args, null, null, null);
	}
	public Cursor getMovsByproduct(String filter) {
		String args[] = new String[] {filter, filter};
		//String createTransactions = "CREATE TABLE transactions (_id integer primary key autoincrement, 
		//number, product, quantity, price, type, suppliers, orders, datex);";
		//return database.query("transactions", null, "product="+filter, null, null, null, null);
		return database.query("movs", new String[] {"product", "quantity", "_id", "price", "details", "type"} ,
							  "(product=? AND type='entrada') OR (product=? AND type='salida')", args, null, null, null);
	}
	public Cursor getAllTransactions(String type, String initial, String ffinal, String filter) {
		//String createTransactions = "CREATE TABLE transactions (_id integer primary key autoincrement, number, product, quantity, price,
		//type, suppliers, orders, datex);";
		//return database.query("transactions", null, "product="+filter, null, null, null, null);
		if (filter == null || filter.equals("")) {
		
		cursor = database.query("transactions", null, "type = ? AND datex BETWEEN ? AND ?", new String[] {type, initial, ffinal}  , null, null, "_id DESC");
	//	cursor = database.query("transactions", null, "suppliers=?", new String [] {filter}  , null, null, null);
		}
		else
			cursor = database.query("transactions", null, "type = ? AND suppliers LIKE ? AND datex BETWEEN ? AND ?",
			new String[] { "venta", filter+"%", initial, ffinal}  , null, null, "_id DESC");
		//AND datex BETWEEN ? AND ?
	//	show_toast("like suppliers work");	
	return cursor;	
	}
	
	public Cursor getAllTransactions_count() {
		//String createTransactions = "CREATE TABLE transactions (_id integer primary key autoincrement, number, product, quantity, price,
		//type, suppliers, orders, datex);";
		//return database.query("transactions", null, "product="+filter, null, null, null, null);
		
			//cursor = database.query("transactions", null, "type = ? AND datex BETWEEN ? AND ?", new String[] {type, initial, ffinal}  , null, null, "_id DESC");
		
			//	cursor = database.query("transactions", null, "suppliers=?", new String [] {filter}  , null, null, null);
		
		//AND datex BETWEEN ? AND ?
		//	show_toast("like suppliers work");	
		return database.query("transactions", null, null, null, null, null, null);
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
	
	
	public Cursor getAllProductsSell(int option, String filter, String filters) 
	{
		switch (option) {
			case 0:
				String args[] = new String[] {filter};
				if (filter.length() == 0) {
					cursor = database.query("products", new String[] {"_id", "ean", 
												"product",  "price", "price2", "category", "location", "typelist", "inventory", "cost" }, 
											"typelist=0 OR typelist=2",  null, null, null, "product");
											//"typelist=? OR typelist=?",  new String[] {"0", "2"}, null, null, "_id");
				}
				else {
					cursor = database.query("products", new String[] {"_id", "ean", 
												"product", "price", "price2", "category", "location", "typelist", "inventory", "cost"}, 
											"(category=? AND typelist=?) OR (category=? AND typelist=?)",
											new String[] {filter, "0", filter, "2"}, null, null, "product");
				}

				break;
			case 1: 
				//String args1[] = new String[] {filter + "%"};
				// if (filter.length() == 0) {
				cursor = database.query("products", new String[] {"_id", "ean",
											"product", "price", "price2", "category", "location", "typelist", "inventory", "cost"},
										"category=? AND ean LIKE ?", new String[] {filters,filter+"%"}, null, null, "product");

				break;

			case 2: 
				//String args1[] = new String[] {filter + "%"};
				// if (filter.length() == 0) {
				cursor = database.query("products", new String[] {"_id", "ean",
											"product", "price","price2", "category", "location" , "typelist", "inventory", "cost"},
										"category=? AND product LIKE ?", new String[] {filters,filter+"%"}, null, null, "product");


				break;

			case 3:
				cursor = database.query("products", new String[] {"_id", "ean",
											"product", "price","price2", "category", "location" , "typelist", "inventory", "cost"},
										"(ean LIKE ? AND typelist=0) OR (ean LIKE ? AND typelist=2)", new String[] {filter+"%",  filter+"%" }, null, null, "product");
				break;		
			case 4:
				cursor = database.query("products", new String[] {"_id", "ean", 
											"product",  "price","price2", "category", "location", "typelist", "inventory", "cost"},
										"(product LIKE ? AND typelist=0) OR (product LIKE ? AND typelist=2)", new String[] {filter+"%", 
											filter+"%"}, null, null, "product");
				break;						
		}	
		// }
		//	}
		return cursor;

	}
	

	public Cursor getAllProductsBuy(int option, String filter, String filters) 
	{
		switch (option) {
			case 0:
				//String args[] = new String[] {filter};
				if (filter.length() == 0) {
					cursor = database.query("products", new String[] {"_id", "ean", 
												"product",  "cost", "price2", "category", "location", "typelist", "inventory" }, 
											"typelist=0 OR typelist=1",  new String[] {"0", "1"}, null, null, "product");
				}
				else {
					cursor = database.query("products", new String[] {"_id", "ean", 
												"product", "cost", "price2", "category", "location", "typelist", "inventory"}, 
											"(category=? AND typelist=0) OR (category=? AND typelist=1)",
											new String[] {filter,  filter}, null, null, "product");
				}

				break;
			case 1: 
				//String args1[] = new String[] {filter + "%"};
				// if (filter.length() == 0) {
				cursor = database.query("products", new String[] {"_id", "ean",
											"product", "cost", "price2", "category", "location", "typelist", "inventory"},
										"category=? AND ean LIKE ?", new String[] {filters,filter+"%"}, null, null, "product");

				break;

			case 2: 
				//String args1[] = new String[] {filter + "%"};
				// if (filter.length() == 0) {
				cursor = database.query("products", new String[] {"_id", "ean",
											"product", "cost","price2", "category", "location" , "typelist", "inventory"},
										"category=? AND product LIKE ?", new String[] {filters,filter+"%"}, null, null, "product");


				break;

			case 3:
				cursor = database.query("products", new String[] {"_id", "ean",
											"product", "cost","price2", "category", "location" , "typelist", "inventory"},
										"(ean LIKE ? AND typelist=0) OR (ean LIKE ? AND typelist=1)", new String[] 
										{filter+"%",  filter+"%" }, null, null, "product");
				break;		
			case 4:
				cursor = database.query("products", new String[] {"_id", "ean", 
											"product",  "cost","price2", "category", "location", "typelist", "inventory"},
										"(product LIKE ? AND typelist=0) OR (product LIKE ? AND typelist=1)", 
										new String[] {filter+"%", filter+"%"}, null, null, "product");
				break;						
		}	
		// }
		//	}
		return cursor;

	}
	
	
	
	
	

	public void insertClient(String rfc, String name, String dir, String code, String phone, String mail, String notes) 
	{
		ContentValues newCon = new ContentValues();

		newCon.put("rfc", rfc);
		newCon.put("name", name);
		newCon.put("dir", dir);
		newCon.put("code", code);
		newCon.put("phone", phone);
		newCon.put("mail", mail);
		newCon.put("notes", notes);

		open();
		database.insert("clients", null, newCon);
		close();
	}


	public void updateClient(long id, String rfc, String name, String dir, String code, String phone, String mail, String notes) 
	{

		ContentValues editCon = new ContentValues();

		editCon.put("rfc", rfc);
		editCon.put("name", name);
		editCon.put("dir", dir);
		editCon.put("code", code);
		editCon.put("phone", phone);
		editCon.put("mail", mail);
		editCon.put("notes", notes);
		open();
		database.update("clients", editCon, "_id=" + id, null);
		close();
	}
	
	public Cursor getAllClients(int option, String filter) 
	{
		switch (option) {
			case 0:
			//	String args[] = new String[] {filter};
				if (filter.length() == 0) {
					cursor = database.query("clients", new String[] {"_id", "rfc", "name"}, 
											null, null, null, null, "_id");
				}
				
				break;

			case 1:
				cursor = database.query("clients", new String[] {"_id", "rfc", "name"}, 
										"rfc LIKE ?", new String[] {filter+"%"}, null, null, "_id");
				break;		
			case 2:
				cursor = database.query("clients", new String[] {"_id", "rfc", "name"}, 
										"name LIKE ?", new String[] {filter+"%"}, null, null, "_id");
		}	
		// }
		//	}
		return cursor;

	}

	public void insertProviders(String rfc, String name, String dir, String code, String phone, String mail, String notes) 
	{
		ContentValues newCon = new ContentValues();

		newCon.put("rfc", rfc);
		newCon.put("name", name);
		newCon.put("dir", dir);
		newCon.put("code", code);
		newCon.put("phone", phone);
		newCon.put("mail", mail);
		newCon.put("notes", notes);

		open();
		database.insert("providers", null, newCon);
		close();
	}


	public void updateProviders(long id, String rfc, String name, String dir, String code, String phone, String mail, String notes) 
	{

		ContentValues editCon = new ContentValues();

		editCon.put("rfc", rfc);
		editCon.put("name", name);
		editCon.put("dir", dir);
		editCon.put("code", code);
		editCon.put("phone", phone);
		editCon.put("mail", mail);
		editCon.put("notes", notes);
		open();
		database.update("providers", editCon, "_id=" + id, null);
		close();
	}

	public Cursor getAllProviders(int option, String filter) 
	{
		switch (option) {
			case 0:
				//	String args[] = new String[] {filter};
				if (filter.length() == 0) {
					cursor = database.query("providers", new String[] {"_id", "rfc", "name"}, 
											null, null, null, null, "_id");
				}

				break;

			case 1:
				cursor = database.query("providers", new String[] {"_id", "rfc", "name"}, 
										"rfc LIKE ?", new String[] {filter+"%"}, null, null, "_id");
				break;		
			case 2:
				cursor = database.query("providers", new String[] {"_id", "rfc", "name"}, 
										"name LIKE ?", new String[] {filter+"%"}, null, null, "_id");
		}	
		// }
		//	}
		return cursor;

	}
	public void insertUnits(String units) 
	{
		ContentValues newUnit = new ContentValues();

		newUnit.put("units", units);

		open();
		database.insert("units", null, newUnit);
		close();
	}		

	public void updateUnits(long id, String units) 
	{

		ContentValues editUnits = new ContentValues();

		editUnits.put("units", units);

		open();
		database.update("units", editUnits, "_id=" + id, null);
		close();
	}
	

	public void insertLocations(String location) 
	{
		ContentValues newLocation = new ContentValues();

		newLocation.put("location", location);

		open();
		database.insert("locations", null, newLocation);
		close();
	}		

	public void updateLocations(long id, String location) 
	{

		ContentValues editLocations = new ContentValues();

		editLocations.put("location", location);

		open();
		database.update("locations", editLocations, "_id=" + id, null);
		close();
	}	
	public void insertCateg(String categ) 
	{
		ContentValues newCateg = new ContentValues();

		newCateg.put("categ", categ);

		open();
		database.insert("category", null, newCateg);
		close();
	}		

	public void updateCateg(long id, String categ) 
	{

		ContentValues editCateg = new ContentValues();

		editCateg.put("categ", categ);

		open();
		database.update("category", editCateg, "_id=" + id, null);
		close();
	}

	public void updateQtyMovs(String id, float quantity) 
	{

		ContentValues editMovs = new ContentValues();

		editMovs.put("quantity", quantity);

		open();
		database.update("movs", editMovs, "_id=" + id, null);
		close();
	}
	
	public Cursor getAllUnits() 
	{
		return database.query("units", new String[] {"_id", "units"}, 
							  null, null, null, null, "_id");
	}

	public Cursor getAllCategory() 
	{
		return database.query("category", new String[] {"_id", "categ"}, 
							  null, null, null, null, "_id");
	}
	public Cursor getAllLocations() 
	{
		return database.query("locations", new String[] {"_id", "location"}, 
							  null, null, null, null, "_id");
	}	
	
	public Cursor getOneProduct(long id) 
	{
		return database.query("products", null, "_id=" + id, null, null, null, null);
	}
	public Cursor getOneClient(long id) 
	{
		return database.query("Clients", null, "_id=" + id, null, null, null, null);
	}
	public Cursor getOneProvider(long id) 
	{
		return database.query("providers", null, "_id=" + id, null, null, null, null);
		}
	public Cursor getOneUnits (long id)
	{
		return database.query("units", null, "_id=" + id, null, null, null, null);
	}
	public Cursor getOneCategory(long id)
	{ 
		return database.query("category", null, "_id=" + id, null, null, null, null);
	}	
	public Cursor getOneLocation(long id)
	{ 
		return database.query("locations", null, "_id=" + id, null, null, null, null);
	}	
	
	
	// DELETE ITEMS ON TABLES

    public void deleteRecent(long id)
    {
        open();
        database.delete("recents", "_id=" + id, null);
        close();
    }
	public void deleteProduct(long id) 
	{
		open(); 
		database.delete("products", "_id=" + id, null);
		close();
	}
	
	public void deleteClient(long id) 
	{
		open(); 
		database.delete("clients", "_id=" + id, null);
		close();
	}
	
	public void deleteProviders(long id) 
	{
		open(); 
		database.delete("providers", "_id=" + id, null);
		close();
	}
	
	public void deleteCategory(long id) 
	{
		open(); 
		database.delete("category", "_id=" + id, null);
		close();
	}
	
	public void deleteUnits(long id) 
	{
		open(); 
		database.delete("units", "_id=" + id, null);
		close();
	}
	
	public void deleteLocations(long id) 
	{
		open(); 
		database.delete("locations", "_id=" + id, null);
		close();
	}
	
	public void deleteTransactions(String id) 
	{
		open(); 
		database.delete("transactions", "_id=" + id, null);
		close();
	}	
	
	public void deleteMovs(String id) 
	{
		open(); 
		database.delete("movs", "transaction_id=" + "'"+id+"'", null);
		close();
	}	
	public void deleteMovsID(String id) 
	{
		open(); 
		database.delete("movs", "_id=" + id, null);
		close();
	}	
	
	// GET ARRAYS OF TABLES
	public Set<String> getAllCategoryArray() {
		Set<String> set = new HashSet<String>();

		//String selectQuery = "select * from " + TABLE_NAME;

		//	SQLiteDatabase db = this.getReadableDatabase();
		open();
		Cursor cursor = database.query("category", new String[] {"_id", "categ"}, 
									   null, null, null, null, "_id");

		if (cursor.moveToFirst()) {
			do {
				set.add(cursor.getString(1));
			} while (cursor.moveToNext());
		}

		cursor.close();
		close();

		return set;
	}
	public Set<String> getAllUnitsArray() {
		Set<String> set = new HashSet<String>();

		//String selectQuery = "select * from " + TABLE_NAME;

		//	SQLiteDatabase db = this.getReadableDatabase();
		open();
		Cursor cursor = database.query("units", new String[] {"_id", "units"}, 
									   null, null, null, null, "_id");

		if (cursor.moveToFirst()) {
			do {
				set.add(cursor.getString(1));
			} while (cursor.moveToNext());
		}

		cursor.close();
		close();

		return set;
	}
	public Set<String> getAllLocationsArray() {
		Set<String> set = new HashSet<String>();

		//String selectQuery = "select * from " + TABLE_NAME;

		//	SQLiteDatabase db = this.getReadableDatabase();
		open();
		Cursor cursor = database.query("locations", new String[] {"_id", "location"}, 
									   null, null, null, null, "_id");

		if (cursor.moveToFirst()) {
			do {
				set.add(cursor.getString(1));
			} while (cursor.moveToNext());
		}

		cursor.close();
		close();

		return set;
	} 
	
	
	public void deleteAllProducts () {
		open();
		database.delete("inventary", null, null);
		database.
			close();
	}
//	String createMovs = "CREATE TABLE movs (_id integer primary key autoincrement, type, product, quantity,
	// price, datex, status);";	
	public void insertMovs(String type, String product, String qty,
	String price, String transaction_id, String details) 
	{
		ContentValues newCon = new ContentValues();

		newCon.put("type", type);
		newCon.put("product", product);
		newCon.put("quantity", qty);
		newCon.put("price", price);
		newCon.put("transaction_id", transaction_id);
		newCon.put("details", details);

		open();
		database.insert("movs", null, newCon);
		close();
	}
//String createTransactions="CREATE TABLE transactions (_id integer primary key autoincrement,type,quantity,price,suppliers,status,datex);";	
	public void insertTransactions(String type, String price, String supplier, String status, String date, String time, String profit) 
	{
		ContentValues newCon = new ContentValues();

		newCon.put("type", type);
	//	newCon.put("quantity", qty);
		newCon.put("price", price);
		newCon.put("suppliers", supplier);
		newCon.put("status", status);
		newCon.put("datex", date);
		newCon.put("time", time);
		newCon.put("profit", profit);

		open();
		database.insert("transactions", null, newCon);
		close();
	}
	//Regresa nombre para movimientos con llave producto
	
	public String nameProduct (String ean) {
		String args[] = new String[] {ean};
		cursor = database.query("products", new String[] {"product"}, 
								"ean=?", args, null, null, null);
	
		String n = ean;
		
		if (cursor.moveToFirst())
		n = cursor.getString(0); 
		
		return n;
	}
	
	public String stockProduct(String ean) {
		String total;
		String args[] = new String[] {ean};
		cursor = database.query("movs", new String[] {"quantity", "type"}, 
								"product=?", args, null, null, null);
						
		float i = 0;

		if (cursor.moveToFirst()) {
			do {
				if (cursor.getString(1).equals("compra") || cursor.getString(1).equals("entrada") ){
					i += Float.parseFloat(cursor.getString(0));
				}
				
				else {
					i -= Float.parseFloat(cursor.getString(0));
				}
				//	totalStock = result.getString(0);
			} while (cursor.moveToNext());
		}

		total = ""+i;			
		return total;
	}
	
	
	public String priceProduct(String ean) {
		String total="";
		String args[] = new String[] {ean};
		cursor = database.query("movs", new String[] {"price"}, 
								"product=?", args, null, null, null);

	
		if (cursor.moveToLast()) {
			total = cursor.getString(0);
		}
		
		return total;
	}
	
	public boolean ean_exist (String ean) {
		boolean exist = false;
		String args[] = new String[] {ean};
		cursor = database.query("products", new String[] { "_id", "ean", "product", "price", "min", "category", "location"}, 
								"ean=?", args, null, null, "_id");
		if (cursor.getCount() == 0 ) {
			exist = false;
		}						
		else {
			exist = true;
		}
		return exist;
	}
	
	
}
