package maceda.alejandro.alexiavnplayer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;


public class CargarFragment extends Fragment {

    private TextView txtSlots;
    private Bitmap myBitmap;
    private ImageButton primerSlot;
    private String vn_name = "";
    private String file_path="";
    public static String file_name="";
    private String savefile="";
    private String file_image = "";
    private int line = 0;
    public static long recent_id = 0;
    private String username="";
    private int startpage=0;
    private int textSize;
    private String datosVacios = "No hay datos...";

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


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        primerSlot = (ImageButton) view.findViewById(R.id.cargarImgBtn1);
        cargarDatos();

        primerSlot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (file_path.equals(datosVacios)){
                    Toast.makeText(v.getContext(),"No tienes datos guardados...", Toast.LENGTH_SHORT).show();
                }else {
                    start_alexavn(recent_id, vn_name, file_path, file_name, savefile, line, username,file_image);
                }
            }
        });
    }

    private void cargarDatos(){
        SharedPreferences preferences = this.getActivity().getSharedPreferences("saveVN", Context.MODE_PRIVATE);
        String slot1 = preferences.getString("image1","No hay datos...");

        vn_name = preferences.getString("vnname", "No hay datos...");
        file_path = preferences.getString("path", "No hay datos...");
        savefile = preferences.getString("savefile", "No hay datos...");
        file_name = preferences.getString("file","No hay datos...");
        file_image = preferences.getString("image","No hay datos...");
        line = preferences.getInt("line", 0);
        username = preferences.getString("username","No hay datos...");
        //recent_id = preferences.getLong("recent_id",0);
        startpage = preferences.getInt("start",1);
        textSize = preferences.getInt("textSize",16);

        Intent intent = getActivity().getIntent();
        recent_id = intent.getLongExtra("recent_id", 0);

        String imgPath = slot1;
        File imgFile = new File(imgPath);
        myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
        primerSlot.setImageBitmap(myBitmap);
    }

    private void start_alexavn(long recent_id, String vn_name, String file_path, String file_name, String savefile, int line, String username, String file_image) {
        Intent alexavn = new Intent (getContext(), alexavn.class);
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
        alexavn.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(alexavn);
        getActivity().finish();

    }

}