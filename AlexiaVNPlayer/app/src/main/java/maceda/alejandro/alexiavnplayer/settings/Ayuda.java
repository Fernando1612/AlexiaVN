package maceda.alejandro.alexiavnplayer.settings;

import android.app.Dialog;
import android.content.Context;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import maceda.alejandro.alexiavnplayer.R;

public class Ayuda {

    public Ayuda(Context context) {
        final Dialog dialogo = new Dialog(context);
        dialogo.setCancelable(false);
        dialogo.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogo.setContentView(R.layout.ayuda_view);

        Button ok = (Button) dialogo.findViewById(R.id.btnOk);
        TextView tuorial1 = (TextView) dialogo.findViewById(R.id.tutorialP1);
        TextView tutorial2 = (TextView) dialogo.findViewById(R.id.tutorialP2);
        TextView documentacion = (TextView) dialogo.findViewById(R.id.documentacion);

        tuorial1.setClickable(true);
        tutorial2.setClickable(true);
        documentacion.setClickable(true);

        tuorial1.setMovementMethod(LinkMovementMethod.getInstance());
        tutorial2.setMovementMethod(LinkMovementMethod.getInstance());
        documentacion.setMovementMethod(LinkMovementMethod.getInstance());

        String link1 = "<a href='https://www.youtube.com/watch?v=5eOza4v4VcE&t=3s'>Tutorial alexia novel player: primera parte</a>";
        String link2 = "<a href='https://www.youtube.com/watch?v=MmtBu0sTIdw&t=0s'>Tutorial alexia novel player: segunda parte</a>";
        String linkDocumentacion = "<a href='https://github.com/Fernando1612/AlexiaVN'>Documentación de Alexia visual novel</a>";

        tuorial1.setText(Html.fromHtml(link1));
        tutorial2.setText(Html.fromHtml(link2));
        documentacion.setText(Html.fromHtml(linkDocumentacion));

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogo.dismiss();
            }
        });
        dialogo.show();
    }
}
