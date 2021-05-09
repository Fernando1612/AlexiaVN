package maceda.alejandro.alexiavnplayer;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileReader;


public class Preferences extends FragmentActivity{

    private int mmthemeId;
    private int themeId;
    private Button btnRegresar;
    private TextView textoArriba;
    FragmentTransaction transaction;
    GuardarFragment fragmentGuardar;
    CargarFragment fragmentCargar;
    GaleriaFragment fragmentGaleria;
    InfoFragment fragmentInformacion;
    AcercaDeFragment fragmentAcercaDe;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Intent intent = getIntent();
        mmthemeId = intent.getIntExtra("theme", android.R.style.Theme_Holo);
        getPreferencesTheme();
        setTheme(themeId);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        btnRegresar = (Button) findViewById(R.id.regresar);
        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regresar();
            }
        });

       traerVariables();


        //Instaciar todos los fragments
        fragmentGuardar = new GuardarFragment();
        fragmentCargar = new CargarFragment();
        fragmentGaleria = new GaleriaFragment();
        fragmentInformacion = new InfoFragment();
        fragmentAcercaDe = new AcercaDeFragment();

        //Fragment de inicio
        getSupportFragmentManager().beginTransaction().add(R.id.contenedor_fragment,fragmentGuardar).commit();
        textoArriba = (TextView) findViewById(R.id.guardar);
        textoArriba.setText("Guardar");
    }

    private void regresar() {
        finish();
    }


    public void guardar(View view) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.contenedor_fragment,fragmentGuardar);
        textoArriba.setText("Guardar");
        transaction.commit();
    }

    public void cargar(View view) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.contenedor_fragment,fragmentCargar);
        textoArriba.setText("Cargar");
        transaction.commit();
    }

    public void galeria(View view) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.contenedor_fragment,fragmentGaleria);
        textoArriba.setText("Galeria");
        transaction.commit();
    }

    public void preferencias(View view) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.contenedor_fragment,fragmentInformacion);
        textoArriba.setText("Preferencias");
        transaction.commit();
    }

    public void menuPrincipal(View view) {
        Intent menu = new Intent(this, MainActivity.class);
        menu.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(menu);
        finish();
    }

    public void acercaDe(View view) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.contenedor_fragment,fragmentAcercaDe);
        textoArriba.setText("Acerca de");
        transaction.commit();

    }

    private void getPreferencesTheme () {

        if (mmthemeId == android.R.style.Theme_Holo ) {
            themeId = android.R.style.Theme_Holo_NoActionBar_Fullscreen;
        }
        if (mmthemeId == android.R.style.Theme_Holo_Light_DarkActionBar) {
            themeId = android.R.style.Theme_Holo_Light_NoActionBar_Fullscreen;
        }
        if (mmthemeId == android.R.style.Theme_Holo_Light) {
            themeId = android.R.style.Theme_Holo_Light_NoActionBar_Fullscreen;
        }
        if (mmthemeId == android.R.style.Theme_Black) {
            themeId = android.R.style.Theme_Black_NoTitleBar_Fullscreen;
        }
        if (mmthemeId == android.R.style.Theme_Light) {
            themeId = android.R.style.Theme_Light_NoTitleBar_Fullscreen;
        }
    }

    private void traerVariables(){
        Intent intent = getIntent();
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