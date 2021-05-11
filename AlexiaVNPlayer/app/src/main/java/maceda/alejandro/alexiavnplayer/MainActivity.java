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
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
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
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import maceda.alejandro.alexiavnplayer.adapters.TvShowAdapter;
import maceda.alejandro.alexiavnplayer.database.DatabaseConnector;
import maceda.alejandro.alexiavnplayer.settings.Ayuda;
import maceda.alejandro.alexiavnplayer.settings.Settings;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Snackbar.make(view, "Abriendo VN...", Snackbar.LENGTH_LONG)
                //       .setAction("Action", null).show();
                if (isStoragePermissionGranted()) {
                    createcarpet();
                    open_alexavn();
                } else {
                    show_toast("No hay permisos otorgados");
                }
            }
        });

        tv_no_recent = (TextView) findViewById(R.id.tv_no_recent);

        /*

        for(int i=0;i<TvShows.length;i++)
        {
            TvShow tvShow = new TvShow();

            tvShow.setTvshow(TvShows[i]);
            tvShow.setImgTvshow(TvShowImgs[i]);

            tvShows.add(tvShow);

        }
        if (tvShows.size() > 0) {
            tv_no_recent.setVisibility(View.GONE);
        }
        */
        tvShowAdapter = new TvShowAdapter(tvShows);

        recyclerView = (RecyclerView) findViewById(R.id.TvShows);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(tvShowAdapter);


        cargarPreferencias();

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

    public boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (this.checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                // Log.v(TAG, "Permission is granted");
                //    isGPSPermissionGranted();
                return true;
            } else {
                //  Log.v(TAG, "Permission is revoked");
                // context.startActivity(new Intent(context, ProfileActivity.class));
                //  isGPSPermissionGranted();
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            //  Log.v(TAG, "Permission is granted");
            return true;
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
                            //			loadProducts(sarray[0], sarray[1], sarray[2],  sarray[3], sarray[4], sarray[5]);
                        }
                        buffer.close();
                    } catch (Exception ex) {
                        show_toast("Open failed");
                        succes = false;

                    } finally {
                        if (succes) {
                            show_toast("Loading");

                        }

                    }
                    saveRecent(sarray[0], curPath, sarray[1], sarray[2]);
                    //start_alexavn(curPath, curFileName);
                    // tvShows.clear();
                    //new GetRecents().execute();

                }
                break;
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
            show_toast("Carpeta creada");
            unzip();

        } else {
            if (f.exists()) {
                File fzip = new File("/sdcard/alexavn/UltimoDinosaurio");
                if (!fzip.exists()) {
                    unzip();
                }
            } else
                show_toast("Carpeta o Sd no encontrada, dar permisos de almacenamiento");
        }
    }

    public void unzip() //throws IOException
    {
        pd = new ProgressDialog(MainActivity.this);
        pd.setTitle("Extrayendo archivos");
        pd.setMessage("Por favor espere...");
        //	pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setCancelable(false);
        pd.setIndeterminate(true);
        pd.show();
        new UnZipTask().execute("", "");
    }

    private class UnZipTask extends AsyncTask<String, Void, Boolean> {
        @SuppressWarnings("rawtypes")
        @Override
        protected Boolean doInBackground(String... params) {
            AssetManager assetManager = getAssets();

            //String filePath = params[0];
            //String destinationPath = params[1];

            InputStream is;
            ZipInputStream zis;
            try {
                String filename;
                //is = new FileInputStream(path + zipname);
                is = assetManager.open("UltimoDinosaurio.zip"); //new FileInputStream("assets/VN.zip");
                zis = new ZipInputStream(new BufferedInputStream(is));
                ZipEntry mZipEntry;
                byte[] buffer = new byte[1024];
                int count;

                while ((mZipEntry = zis.getNextEntry()) != null) {
                    // zapis do souboru
                    filename = mZipEntry.getName();

                    // Need to create directories if not exists, or
                    // it will generate an Exception...
                    if (mZipEntry.isDirectory()) {
                        File fmd = new File("/sdcard/alexavn/" + filename); //path
                        fmd.mkdirs();
                        continue;
                    }

                    FileOutputStream fout = new FileOutputStream("/sdcard/alexavn/" + filename); //path

                    // cteni zipu a zapis
                    while ((count = zis.read(buffer)) != -1) {
                        fout.write(buffer, 0, count);
                    }

                    fout.close();
                    zis.closeEntry();
                    //Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                }

                zis.close();
            } catch (Exception e) {
                return false;
            }

            return true;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            pd.dismiss();

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
                    show_toast("Ingresa un nombre por favor");
                }else{
                    if (isStoragePermissionGranted()) {
                        show_toast("Creando proyecto");
                        crearProyecto(nombreProyecto);
                        dialog.dismiss();
                    } else {
                        show_toast("No hay permisos otorgados");
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
            show_toast("El proyecto ya existe");
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
                show_toast("Proyecto creado");
            }else {
                show_toast("Error al crear el poyecto");
            }
        }
    }
}
