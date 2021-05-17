package maceda.alejandro.alexiavnplayer;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import maceda.alejandro.alexiavnplayer.adapters.TvShowAdapter;
import maceda.alejandro.alexiavnplayer.database.DatabaseConnector;
import maceda.alejandro.alexiavnplayer.settings.Ayuda;
import maceda.alejandro.alexiavnplayer.settings.Settings;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class MainActivity extends AppCompatActivity implements Settings.FinalizaCuadroDialogo {
    RecyclerView recyclerView;
    TvShowAdapter tvShowAdapter;
    private ArrayList<TvShow> tvShows = new ArrayList<TvShow>();
    private String curFileName;
    private String curPath;
    private TextView tv_no_recent;
    private String nombre;
    private String sexo;
    Context contexto;

    public static final String[] TvShows = {"Breaking Bad", "Rick and Morty", "FRIENDS", "Sherlock", "Stranger Things"};
    public static final int[] TvShowImgs = {R.drawable.b5cm, R.drawable.ecchi, R.drawable.folder, R.drawable.folder, R.drawable.folder};

    DatabaseConnector dbConnector;
    public HashMap<String, String> map;
    public static final List<HashMap<String, String>> fillMaps = new ArrayList<HashMap<String, String>>();
    private ProgressDialog pd;

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestPermission();
        //encontrarArchivo();
        cargarPreferencias();
    }

    @Override
    protected void onStart() {
        super.onStart();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkPermission()) {
                    open_alexavn();
                } else {
                    show_toast(getString(R.string.sin_permisos));
                }
            }
        });
        tv_no_recent = (TextView) findViewById(R.id.tv_no_recent);
        tvShowAdapter = new TvShowAdapter(tvShows);
        recyclerView = (RecyclerView) findViewById(R.id.TvShows);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(tvShowAdapter);
    }

    private void cargarPreferencias() {
        SharedPreferences preferences = getSharedPreferences("Informacion", contexto.MODE_PRIVATE);
        String nombre = preferences.getString("nombre", "No existe la informacion");
        String sexo = preferences.getString("sexo", "No existe la informacion");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        contexto = this;
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_settings:
                new Settings(contexto, MainActivity.this);
                return true;
            case R.id.action_ayuda:
                new Ayuda(contexto);
                return true;
            case R.id.action_project:
                crearAlerta();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    private void open_alexavn() {
        Intent chooseFile = new Intent(getApplicationContext(), FileChooser.class);
        chooseFile.putExtra("option", 1);
        startActivityForResult(chooseFile, 1);
    }

    private boolean checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            return Environment.isExternalStorageManager();
        } else {
            int result = ContextCompat.checkSelfPermission(this, READ_EXTERNAL_STORAGE);
            int result1 = ContextCompat.checkSelfPermission(this, WRITE_EXTERNAL_STORAGE);
            return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED;
        }
    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            try {
                Intent intent = new Intent(android.provider.Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                intent.addCategory("android.intent.category.DEFAULT");
                intent.setData(Uri.parse(String.format("package:%s",getApplicationContext().getPackageName())));
                startActivityForResult(intent, 2296);
            } catch (Exception e) {
                Intent intent = new Intent();
                intent.setAction(android.provider.Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                startActivityForResult(intent, 2296);
            }
        } else {
            //below android 11
            ActivityCompat.requestPermissions(this, new String[]{WRITE_EXTERNAL_STORAGE}, 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0) {
                    boolean READ_EXTERNAL_STORAGE = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean WRITE_EXTERNAL_STORAGE = grantResults[0] == PackageManager.PERMISSION_GRANTED;

                    if (READ_EXTERNAL_STORAGE && WRITE_EXTERNAL_STORAGE) {
                        createcarpet();
                        //descomprime();
                        String srcName = "UltimoDinosaurio";
                        String dstName = "/sdcard/alexavn/UltimoDinosaurio";
                        copyAssetFolder(this,srcName,dstName);
                    } else {
                        Toast.makeText(this, "Allow permission for storage access!", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                //	show_toast("inside result");
                if (resultCode == RESULT_OK) {

                    curFileName = data.getStringExtra("GetFileName");
                    curPath = data.getStringExtra("GetPath");
                    String[] sarray = new String[3];

                    //	file.setText(curPath+curFileName);
                    boolean succes = false;
                    try {
                        FileReader file = new FileReader(curPath + curFileName);
                        BufferedReader buffer = new BufferedReader(file);
                        String line = "";
                        if ((line = buffer.readLine()) != null) {
                            sarray = line.split(",", -1);
                            saveRecent(sarray[0], curPath, sarray[1], sarray[2]);
                            //			loadProducts(sarray[0], sarray[1], sarray[2],  sarray[3], sarray[4], sarray[5]);
                        }else {
                            show_toast(getString(R.string.archivo_vacioa));
                        }
                        buffer.close();
                    } catch (Exception ex) {
                        show_toast(getString(R.string.sin_permisos));
                        succes = false;

                    } finally {
                        if (succes) {
                            show_toast(getString(R.string.cargando));
                        }

                    }

                    //start_alexavn(curPath, curFileName);
                    // tvShows.clear();
                    //new GetRecents().execute();
                }
                break;
        }
    }

    private void cargarDatos(String path, String nFile){
        String[] sarray = new String[3];
        boolean succes = false;
        try {
            FileReader file = new FileReader(path + nFile);
            BufferedReader buffer = new BufferedReader(file);
            String line = "";
            if ((line = buffer.readLine()) != null) {
                sarray = line.split(",", -1);
                saveRecent(sarray[0], path, sarray[1], sarray[2]);
            }
            buffer.close();
        } catch (Exception ex) {
            succes = false;
        }
    }

    @Override
    public void onResume() {
        super.onResume();


        tvShows.clear();
        new GetRecents().execute();

    }


    public void saveRecent(final String m_name, final String m_curPath, final String m_curFileName, final String mImage) {
        dbConnector = new DatabaseConnector(this);

        dbConnector.open();


        AsyncTask<Object, Object, Cursor> insertTask =
                new AsyncTask<Object, Object, Cursor>() {
                    @Override
                    protected Cursor doInBackground(Object... params) {


                        //" (_id integer primary key autoincrement, name, path, file, line, image)
                        dbConnector.insertRecent(m_name, m_curPath + "Scripts/", m_curFileName,
                                m_curPath + mImage, m_curFileName, 0, "");

                        //dbConnector.open();
                        return null;

                    }

                    @Override
                    protected void onPostExecute(Cursor result) {

                        dbConnector.close();

                    }
                };
        insertTask.execute();

    }

    @Override
    public void ResultadoCuadroDialogo(String nombre, String sexo) {
        guardarPreferencias(nombre, sexo);
    }

    private void guardarPreferencias(String nombre, String sexo) {
        SharedPreferences preferences = getSharedPreferences("Informacion", contexto.MODE_PRIVATE);
        String Nombre = nombre;
        String Sexo = sexo;

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("nombre", Nombre);
        editor.putString("sexo", sexo);

        editor.commit();
    }

    public class GetRecents extends AsyncTask<String, String, Cursor> {
        DatabaseConnector dbConnector_products = new DatabaseConnector(MainActivity.this);


        @Override
        protected Cursor doInBackground(String... params) {
            dbConnector_products.open();
            return dbConnector_products.getAllRecents();
        }

        @Override
        protected void onPostExecute(Cursor result) {

            if (result.moveToFirst()) {
                tv_no_recent.setVisibility(View.GONE);
                do {

                    /*
                    map = new HashMap<String, String>();
                    map.put("_id", result.getString(0));
                    map.put("Nombre", result.getString(2));
                    map.put("Ean", result.getString(1));
                    fillMaps.add(map);
                    */
                    TvShow tvShow = new TvShow();

                    tvShow.setTvshow_id(Long.parseLong(result.getString(0)));
                    tvShow.setTvshow(result.getString(1));
                    tvShow.setTvshow_path(result.getString(2));
                    tvShow.setTvshow_file(result.getString(3));
                    tvShow.setImgTvshow(result.getString(4));
                    tvShow.setTvshow_savefile(result.getString(5));
                    tvShow.setTvshow_line(result.getInt(6));
                    tvShow.setTvshow_username(result.getString(7));


                    tvShows.add(tvShow);

                } while (result.moveToNext());
            }


            dbConnector_products.close();
            result.close();
            tvShowAdapter.notifyDataSetChanged();

        }
    }

    private void show_toast(String s) {
        Toast.makeText(this, s,
                Toast.LENGTH_SHORT).show();
    }

    private void createcarpet() {

        File f = new File("/sdcard/alexavn/");
        f.getAbsolutePath();

        if (f.mkdirs()) {
            //se ha creado bien
            //string.replace(" ", "\\ ");
            show_toast(getString(R.string.carpeta_creada));
            //unzip();

        } else {
            if (f.exists()) {
                /*File fzip = new File("/sdcard/alexavn/UltimoDinosaurio");
                if (!fzip.exists()) {
                    unzip();
                }*/
            } else
                show_toast(getString(R.string.carpeta_no_encontrada));
        }
    }

    private void crearAlerta(){

        final View customAlertDialog = getLayoutInflater().inflate(R.layout.custom_alertdialog, null);
        final AlertDialog dialog = new AlertDialog.Builder(this)
                .setView(customAlertDialog)
                .setTitle("Crear proyecto en blanco")
                .setPositiveButton("Aceptar", null)
                .setNegativeButton("Cancelar", null)
                .show();
        Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = customAlertDialog.findViewById(R.id.edit_text);
                String nombreProyecto = editText.getText().toString().trim();
                if (nombreProyecto.isEmpty()){
                    show_toast(getString(R.string.ingresar_nombre));
                }else{
                    if (checkPermission()) {
                        show_toast(getString(R.string.creando_proyecto));
                        crearProyecto(nombreProyecto);
                        dialog.dismiss();
                    } else {
                        show_toast(getString(R.string.sin_permisos));
                    }
                }
            }
        });
        Button negativeButton = dialog.getButton(AlertDialog.BUTTON_NEGATIVE);
        negativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    private void crearProyecto(String nombreProyecto){
        File nombre = new File("/sdcard/alexavn/"+nombreProyecto);
        File BGM = new File("/sdcard/alexavn/"+nombreProyecto+"/BGM");
        File Chars = new File("/sdcard/alexavn/"+nombreProyecto+"/Chars");
        File FX = new File("/sdcard/alexavn/"+nombreProyecto+"/FX");
        File Menu = new File("/sdcard/alexavn/"+nombreProyecto+"/Menu");
        File Scenes = new File("/sdcard/alexavn/"+nombreProyecto+"/Scenes");
        File Scripts = new File("/sdcard/alexavn/"+nombreProyecto+"/Scripts");
        File Textbox = new File("/sdcard/alexavn/"+nombreProyecto+"/Textbox");
        File Video = new File("/sdcard/alexavn/"+nombreProyecto+"/Video");
        File Voices = new File("/sdcard/alexavn/"+nombreProyecto+"/Voices");
        File config = new File("/sdcard/alexavn/"+nombreProyecto+"/config.avn");
        if (nombre.exists()){
            show_toast(getString(R.string.proyecto_exitente));
        }else{
            nombre.mkdirs();
            if(nombre.isDirectory()){
                BGM.mkdirs();
                Chars.mkdirs();
                FX.mkdirs();
                Menu.mkdirs();
                Scenes.mkdirs();
                Scripts.mkdirs();
                Textbox.mkdirs();
                Video.mkdirs();
                Voices.mkdirs();
                if(!config.exists()){
                    try {
                        config.createNewFile();
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
                show_toast(getString(R.string.proyecto_creado));
            }else {
                show_toast(getString(R.string.error_crear_proyecto));
            }
        }
    }

    private static boolean copyAssetFolder(Context context, String srcName, String dstName) {
        try {
            boolean result = true;
            String fileList[] = context.getAssets().list(srcName);
            if (fileList == null) return false;

            if (fileList.length == 0) {
                result = copyAssetFile(context, srcName, dstName);
            } else {
                File file = new File(dstName);
                result = file.mkdirs();
                for (String filename : fileList) {
                    result &= copyAssetFolder(context, srcName + File.separator + filename, dstName + File.separator + filename);
                }
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean copyAssetFile(Context context, String srcName, String dstName) {
        try {
            InputStream in = context.getAssets().open(srcName);
            File outFile = new File(dstName);
            OutputStream out = new FileOutputStream(outFile);
            byte[] buffer = new byte[1024];
            int read;
            while ((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
            in.close();
            out.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void encontrarArchivo(){
        String miDictorio = "/sdcard/alexavn/";
        File f = new File(miDictorio);
        if(f.exists() && f.isDirectory()){
            final Pattern p = Pattern.compile("^[\\w\\d]+");
            File[] flist = f.listFiles(new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    return p.matcher(pathname.getName()).matches();
                }
            });
            for(File files: flist){
                String directorio = files.toString() + "/";
                String nFile = "config.avn";
                cargarDatos(directorio,nFile);
            }
        }
    }

}
