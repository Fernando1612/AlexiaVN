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
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;





import java.io.File;
import java.util.ConcurrentModificationException;


public class GuardarFragment extends Fragment {

    Context context;
    private TextView txtSlots;
    private ImageButton primerSlot;
    private Bitmap myBitmap;
    private int imagen;
    private static final String IMAGE_RESOURCE = "image-resource";
    Bitmap image;
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
    private BitmapFactory.Options options;


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

        cargarPreferencias();

        primerSlot.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                String imgPath = file_image;
                File imgFile = new File(imgPath);
                if (imgFile.exists()){
                    myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                    primerSlot.setImageBitmap(myBitmap);
                    guardarPreferencias();
                    //eliminarPreferencias();
                    Toast.makeText(v.getContext(),"Guardando...",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(v.getContext(),"Archivo no encontrado...",Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cargarDatos();

    }

    private void cargarPreferencias(){
        SharedPreferences preferences = this.getActivity().getSharedPreferences("saveVN",Context.MODE_PRIVATE);
        String slot1 = preferences.getString("image","No hay datos...");

        String imgPath = slot1;
        File imgFile = new File(imgPath);
        myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
        primerSlot.setImageBitmap(myBitmap);

    }

    private void guardarPreferencias(){
        SharedPreferences preferences = this.getActivity().getSharedPreferences("saveVN",Context.MODE_PRIVATE);
        String saveImage = file_image;
        SharedPreferences.Editor editor = preferences.edit();
        file_path = file_path+"Scripts/";
        editor.putString("vnname",vn_name);
        editor.putString("path",file_path);
        editor.putString("file",file_name);
        editor.putString("savefile",savefile);
        editor.putString("image",file_image);
        editor.putInt("line",line);
        editor.putString("username", username);
        editor.putFloat("recent_id",recent_id);
        editor.putInt("start",startpage);
        editor.putInt("textSize",textSize);
        editor.putString("image1",saveImage);
        editor.commit();
    }

    private void eliminarPreferencias(){
        SharedPreferences preferences = this.getActivity().getSharedPreferences("saveVN",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear().apply();
    }

    private void cargarDatos(){
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
    }

}
