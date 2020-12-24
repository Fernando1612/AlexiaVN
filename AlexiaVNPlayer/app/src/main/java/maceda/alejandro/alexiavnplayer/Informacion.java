package maceda.alejandro.alexiavnplayer;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class Informacion {
    public Informacion(Context context){
        final Dialog dialogo = new Dialog(context);
        dialogo.setCancelable(false);
        dialogo.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogo.setContentView(R.layout.info_view);

        TextView Nombre = (TextView)dialogo.findViewById(R.id.textView3);
        TextView Sexo = (TextView)dialogo.findViewById(R.id.textView4);
        Button ok = (Button)dialogo.findViewById(R.id.btnOk);

        SharedPreferences preferences = context.getSharedPreferences("Informacion", context.MODE_PRIVATE);
        String nombre = preferences.getString("nombre","No existe la informacion");
        String sexo = preferences.getString("sexo", "No existe la informacion");

        Nombre.setText(nombre);
        Sexo.setText(sexo);


        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogo.dismiss();
            }
        });
        dialogo.show();
    }

}
