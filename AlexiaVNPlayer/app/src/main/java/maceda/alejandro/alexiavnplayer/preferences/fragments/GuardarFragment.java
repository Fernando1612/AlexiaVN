package maceda.alejandro.alexiavnplayer.preferences.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import java.io.File;
import java.text.DateFormat;
import java.util.Calendar;

import maceda.alejandro.alexiavnplayer.R;


public class GuardarFragment extends Fragment implements View.OnClickListener, View.OnLongClickListener {

    Context context;
    private TextView txtSlots;
    private ImageButton primerSlot;
    private ImageButton segundoSlot;
    private ImageButton tercerSlot;
    private ImageButton cuartoSlot;
    private ImageButton quintoSlot;
    private ImageButton sextoSlot;
    private TextView date1;
    private TextView date2;
    private TextView date3;
    private TextView date4;
    private TextView date5;
    private TextView date6;
    private Bitmap myBitmap;
    private int imagen;
    private static final String IMAGE_RESOURCE = "image-resource";
    Bitmap image;
    private String fechaActual;
    private String vn_name = "";
    private String file_path = "";
    public static String file_name = "";
    private String savefile = "";
    private String file_image = "";
    private int line = 0;
    public static long recent_id = 0;
    private String username = "";
    private int startpage = 0;
    private int textSize;
    private BitmapFactory.Options options;

    private String saveImageFondo;
    private String saveImageBox;
    private String saveText;
    private String saveImageChar;
    private String saveLineaExacta;

    public GuardarFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_guardar, container, false);
        txtSlots = (TextView) view.findViewById(R.id.editTextTextPersonName5);
        txtSlots.setText("Slots");

        primerSlot = (ImageButton) view.findViewById(R.id.imgGuardar1);
        primerSlot.setOnClickListener(this);
        primerSlot.setOnLongClickListener(this);
        segundoSlot = (ImageButton) view.findViewById(R.id.imgGuardar2);
        segundoSlot.setOnClickListener(this);
        segundoSlot.setOnLongClickListener(this);
        tercerSlot = (ImageButton) view.findViewById(R.id.imgGuardar3);
        tercerSlot.setOnClickListener(this);
        tercerSlot.setOnLongClickListener(this);
        cuartoSlot = (ImageButton) view.findViewById(R.id.imgGuardar4);
        cuartoSlot.setOnClickListener(this);
        cuartoSlot.setOnLongClickListener(this);
        quintoSlot = (ImageButton) view.findViewById(R.id.imgGuardar5);
        quintoSlot.setOnClickListener(this);
        quintoSlot.setOnLongClickListener(this);
        sextoSlot = (ImageButton) view.findViewById(R.id.imgGuardar6);
        sextoSlot.setOnClickListener(this);
        sextoSlot.setOnLongClickListener(this);

        date1 = (TextView) view.findViewById(R.id.dateGuardar1);
        date2 = (TextView) view.findViewById(R.id.dateGuardar2);
        date3 = (TextView) view.findViewById(R.id.dateGuardar3);
        date4 = (TextView) view.findViewById(R.id.dateGuardar4);
        date5 = (TextView) view.findViewById(R.id.dateGuardar5);
        date6 = (TextView) view.findViewById(R.id.dateGuardar6);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cargarTodasPrefrencias();
        cargarDatos();
    }

    private void cargarPreferencias(String fileSlot, ImageButton slot, TextView textView) {
        SharedPreferences preferences = this.getActivity().getSharedPreferences(fileSlot, Context.MODE_PRIVATE);
        fechaActual = preferences.getString("date", "No hay datos...");
        if (fechaActual.equals("No hay datos...")) {
            textView.setText("");
        } else {
            textView.setText(fechaActual);
        }

        String slot1 = preferences.getString("image", "No hay datos...");
        String imgPath = slot1;
        File imgFile = new File(imgPath);
        myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
        slot.setImageBitmap(myBitmap);
    }

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    private void eliminarPreferencias(String fileSlot, ImageButton slot, TextView textView) {
        SharedPreferences preferences = this.getActivity().getSharedPreferences(fileSlot, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear().apply();
        editor.commit();
        cargarPreferencias(fileSlot, slot, textView);
    }

    private void cargarDatos() {
        Intent intent = getActivity().getIntent();
        vn_name = intent.getStringExtra("vnname");
        file_path = intent.getStringExtra("path");
        file_name = intent.getStringExtra("file");
        savefile = intent.getStringExtra("savefile");
        file_image = intent.getStringExtra("image");
        line = intent.getIntExtra("line", 0);
        username = intent.getStringExtra("username");
        recent_id = intent.getLongExtra("recent_id", 0);
        startpage = intent.getIntExtra("start", 1);
        textSize = intent.getIntExtra("textSize", 16);
        saveImageFondo = intent.getStringExtra("saveImageFondo");
        saveImageBox = intent.getStringExtra("saveImageBox");
        saveText = intent.getStringExtra("saveText");
        saveImageChar = intent.getStringExtra("saveImageChar");
        saveLineaExacta = intent.getStringExtra("saveLineaExacta");
    }

    private void ponerImagen(String file_image, ImageButton slot) {
        String imgPath = file_image;
        File imgFile = new File(imgPath);
        if (imgFile.exists()) {
            myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            slot.setImageBitmap(myBitmap);
            Toast.makeText(getContext(), "Guardando...", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Archivo no encontrado...", Toast.LENGTH_SHORT).show();
        }

    }


    private void guardarPreferencias(String fileSlot) {
        SharedPreferences preferences = this.getActivity().getSharedPreferences(fileSlot, Context.MODE_PRIVATE);
        String saveImage = file_image;
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("date", fechaActual);
        editor.putString("vnname", vn_name);
        editor.putString("path", file_path);
        editor.putString("file", file_name);
        editor.putString("savefile", savefile);
        editor.putString("image", file_image);
        editor.putInt("line", line);
        editor.putString("username", username);
        editor.putFloat("recent_id", recent_id);
        editor.putInt("start", startpage);
        editor.putInt("textSize", textSize);
        editor.putString("image1", saveImage);
        editor.putString("saveImageFondo", saveImageFondo);
        editor.putString("saveImageBox", saveImageBox);
        editor.putString("saveText", saveText);
        editor.putString("saveImageChar", saveImageChar);
        editor.putString("saveLineaExacta", saveLineaExacta);
        editor.commit();
    }

    private void cargarTodasPrefrencias() {
        cargarPreferencias("saveVNslot1", primerSlot, date1);
        cargarPreferencias("saveVNslot2", segundoSlot, date2);
        cargarPreferencias("saveVNslot3", tercerSlot, date3);
        cargarPreferencias("saveVNslot4", cuartoSlot, date4);
        cargarPreferencias("saveVNslot5", quintoSlot, date5);
        cargarPreferencias("saveVNslot6", sextoSlot, date6);
    }

    private void crearAlerta(final String fileSlot, ImageButton slot, TextView textView) {
        AlertDialog.Builder alerta = new AlertDialog.Builder(getContext());
        alerta.setMessage("¿Deseas borrar los datos?")
                .setCancelable(false)
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        eliminarPreferencias(fileSlot, slot, textView);
                        Toast.makeText(getContext(), "Datos borrados...", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog titulo = alerta.create();
        titulo.setTitle("Borrar datos");
        titulo.show();
    }

    private void ponerFecha(TextView textView) {
        Calendar calendar = Calendar.getInstance();
        fechaActual = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        textView.setText(fechaActual);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgGuardar1:
                ponerImagen(file_image, primerSlot);
                ponerFecha(date1);
                guardarPreferencias("saveVNslot1");
                break;
            case R.id.imgGuardar2:
                ponerImagen(file_image, segundoSlot);
                ponerFecha(date2);
                guardarPreferencias("saveVNslot2");
                break;
            case R.id.imgGuardar3:
                ponerImagen(file_image, tercerSlot);
                ponerFecha(date3);
                guardarPreferencias("saveVNslot3");
                break;
            case R.id.imgGuardar4:
                ponerImagen(file_image, cuartoSlot);
                ponerFecha(date4);
                guardarPreferencias("saveVNslot4");
                break;
            case R.id.imgGuardar5:
                ponerImagen(file_image, quintoSlot);
                ponerFecha(date5);
                guardarPreferencias("saveVNslot5");
                break;
            case R.id.imgGuardar6:
                ponerImagen(file_image, sextoSlot);
                ponerFecha(date6);
                guardarPreferencias("saveVNslot6");
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onLongClick(View v) {
        switch (v.getId()) {
            case R.id.imgGuardar1:
                crearAlerta("saveVNslot1", primerSlot, date1);
                break;
            case R.id.imgGuardar2:
                crearAlerta("saveVNslot2", segundoSlot, date2);
                break;
            case R.id.imgGuardar3:
                crearAlerta("saveVNslot3", tercerSlot, date3);
                break;
            case R.id.imgGuardar4:
                crearAlerta("saveVNslot4", cuartoSlot, date4);
                break;
            case R.id.imgGuardar5:
                crearAlerta("saveVNslot5", quintoSlot, date5);
                break;
            case R.id.imgGuardar6:
                crearAlerta("saveVNslot6", sextoSlot, date6);
                break;
            default:
                break;
        }
        return false;
    }
}
