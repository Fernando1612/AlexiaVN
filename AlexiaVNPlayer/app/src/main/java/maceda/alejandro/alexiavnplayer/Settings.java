package maceda.alejandro.alexiavnplayer;

import android.app.Dialog;
import android.content.Context;
import android.text.InputType;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class Settings {

    public interface FinalizaCuadroDialogo{
        void ResultadoCuadroDialogo(String nombre,String sexo);
    }

    private FinalizaCuadroDialogo interfaz;
    private String sexo;

    public Settings(Context contexto, FinalizaCuadroDialogo actividad){
        interfaz = actividad;
        final Dialog dialogo = new Dialog(contexto);
        dialogo.setCancelable(false);
        dialogo.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogo.setContentView(R.layout.settings_view);

        final EditText nombre  = (EditText) dialogo.findViewById(R.id.editTextTextPersonName);
        final RadioButton hombre = (RadioButton) dialogo.findViewById(R.id.radioButton);
        final RadioButton mujer = (RadioButton) dialogo.findViewById(R.id.radioButton3);
        Button guardar = (Button) dialogo.findViewById(R.id.guardar);


        nombre.setInputType(InputType.TYPE_CLASS_TEXT);

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (hombre.isChecked() == true && !nombre.getText().toString().isEmpty())  {
                    sexo = "Hombre";
                    interfaz.ResultadoCuadroDialogo(nombre.getText().toString(),sexo);
                    dialogo.dismiss();
                }else if (mujer.isChecked() == true && !nombre.getText().toString().isEmpty()) {
                    sexo = "Mujer";
                    interfaz.ResultadoCuadroDialogo(nombre.getText().toString(),sexo);
                    dialogo.dismiss();
                }else {

                }

            }
        });

        dialogo.show();
    }


}
