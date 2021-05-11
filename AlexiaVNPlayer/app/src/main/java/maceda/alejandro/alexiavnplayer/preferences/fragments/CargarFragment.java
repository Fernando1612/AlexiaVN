package maceda.alejandro.alexiavnplayer.preferences.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

import maceda.alejandro.alexiavnplayer.R;


public class CargarFragment extends Fragment implements View.OnClickListener {

    private TextView txtSlots;
    private Bitmap myBitmap;
    private ImageButton primerSlot;
    private ImageButton segundoSlot;
    private ImageButton tercerSlot;
    private ImageButton cuartoSlot;
    private ImageButton quintoSlot;
    private ImageButton sextoSlot;
    private String datosVacios = "No hay datos...";
    private TextView date1;
    private TextView date2;
    private TextView date3;
    private TextView date4;
    private TextView date5;
    private TextView date6;


    public CargarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cargar, container, false);
        txtSlots = (TextView) view.findViewById(R.id.editTextSlots);
        txtSlots.setText("Slots");
        date1 = (TextView) view.findViewById(R.id.dateCargar1);
        date2 = (TextView) view.findViewById(R.id.dateCargar2);
        date3 = (TextView) view.findViewById(R.id.dateCargar3);
        date4 = (TextView) view.findViewById(R.id.dateCargar4);
        date5 = (TextView) view.findViewById(R.id.dateCargar5);
        date6 = (TextView) view.findViewById(R.id.dateCargar6);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        primerSlot = (ImageButton) view.findViewById(R.id.cargarImgBtn1);
        primerSlot.setOnClickListener(this);
        segundoSlot = (ImageButton) view.findViewById(R.id.cargarImgBtn2);
        segundoSlot.setOnClickListener(this);
        tercerSlot = (ImageButton) view.findViewById(R.id.cargarImgBtn3);
        tercerSlot.setOnClickListener(this);
        cuartoSlot = (ImageButton) view.findViewById(R.id.cargarImgBtn4);
        cuartoSlot.setOnClickListener(this);
        quintoSlot = (ImageButton) view.findViewById(R.id.cargarImgBt5);
        quintoSlot.setOnClickListener(this);
        sextoSlot = (ImageButton) view.findViewById(R.id.cargarImgBt6);
        sextoSlot.setOnClickListener(this);
        cargarTodasPrefrencias();
    }

    public void cargarPreferencias(String fileSlot, ImageButton imgSlot, TextView textView) {
        SharedPreferences preferences = this.getActivity().getSharedPreferences(fileSlot, Context.MODE_PRIVATE);

        String fechaGuardada = preferences.getString("date", "No hay datos...");
        if (fechaGuardada.equals(datosVacios)) {
            textView.setText("");
        } else {
            textView.setText(fechaGuardada);
        }

        String slot1 = preferences.getString("image1", "No hay datos...");
        String imgPath = slot1;
        File imgFile = new File(imgPath);
        myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
        imgSlot.setImageBitmap(myBitmap);
    }

    public void cargarTodasPrefrencias() {
        cargarPreferencias("saveVNslot1", primerSlot, date1);
        cargarPreferencias("saveVNslot2", segundoSlot, date2);
        cargarPreferencias("saveVNslot3", tercerSlot, date3);
        cargarPreferencias("saveVNslot4", cuartoSlot, date4);
        cargarPreferencias("saveVNslot5", quintoSlot, date5);
        cargarPreferencias("saveVNslot6", sextoSlot, date6);
    }


    private void start_alexavn(long recent_id, String vn_name, String file_path, String file_name,
                               String savefile, int line, String username, String file_image,
                               String saveImageFondo, String saveImageBox, String saveText, String saveImageChar,
                               String saveLineaExacta) {
        Intent alexavn = new Intent(getContext(), maceda.alejandro.alexiavnplayer.alexavn.class);
        alexavn.putExtra("vnname", vn_name);
        alexavn.putExtra("path", file_path);
        alexavn.putExtra("file", file_name);
        alexavn.putExtra("savefile", savefile);
        alexavn.putExtra("line", line);
        alexavn.putExtra("username", username);
        alexavn.putExtra("recent_id", recent_id);
        alexavn.putExtra("image", file_image);
        alexavn.putExtra("textSize", 16);
        alexavn.putExtra("start", 1);
        alexavn.putExtra("saveImageFondo", saveImageFondo);
        alexavn.putExtra("saveImageBox", saveImageBox);
        alexavn.putExtra("saveText", saveText);
        alexavn.putExtra("saveImageChar", saveImageChar);
        alexavn.putExtra("saveLineaExacta", saveLineaExacta);
        alexavn.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(alexavn);
        getActivity().finish();
    }

    private void iniciarAlexa(String fileSlot) {
        SharedPreferences preferences = this.getActivity().getSharedPreferences(fileSlot, Context.MODE_PRIVATE);
        String vn_name = preferences.getString("vnname", "No hay datos...");
        String file_path = preferences.getString("path", "No hay datos...");
        String savefile = preferences.getString("savefile", "No hay datos...");
        String file_name = preferences.getString("file", "No hay datos...");
        String file_image = preferences.getString("image", "No hay datos...");
        int line = preferences.getInt("line", 0);
        String username = preferences.getString("username", "No hay datos...");
        Intent intent = getActivity().getIntent();
        Long recent_id = intent.getLongExtra("recent_id", 0);
        String saveImageFondo = preferences.getString("saveImageFondo", "No hay datos...");
        String saveImageBox = preferences.getString("saveImageBox", "No hay datos...");
        String saveText = preferences.getString("saveText", "No hay datos...");
        String saveImageChar = preferences.getString("saveImageChar", "No hay datos...");
        String saveLineaExacta = preferences.getString("saveLineaExacta", "No hay datos...");
        file_path = file_path + "Scripts/";
        if (file_path.equals(datosVacios)) {
            Toast.makeText(getContext(), "No tienes datos guardados...", Toast.LENGTH_SHORT).show();
        } else {
            start_alexavn(recent_id, vn_name, file_path, file_name, savefile, line, username, file_image, saveImageFondo, saveImageBox, saveText, saveImageChar, saveLineaExacta);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cargarImgBtn1:
                iniciarAlexa("saveVNslot1");
                break;
            case R.id.cargarImgBtn2:
                iniciarAlexa("saveVNslot2");
                break;
            case R.id.cargarImgBtn3:
                iniciarAlexa("saveVNslot3");
                break;
            case R.id.cargarImgBtn4:
                iniciarAlexa("saveVNslot4");
                break;
            case R.id.cargarImgBt5:
                iniciarAlexa("saveVNslot5");
                break;
            case R.id.cargarImgBt6:
                iniciarAlexa("saveVNslot6");
                break;
            default:
                break;
        }

    }
}