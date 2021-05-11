package maceda.alejandro.alexiavnplayer.preferences.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import maceda.alejandro.alexiavnplayer.R;


public class AcercaDeFragment extends Fragment {

    private TextView link;

    public AcercaDeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_acerca_de, container, false);
        link = (TextView) view.findViewById(R.id.linkPag);
        link.setClickable(true);
        link.setMovementMethod(LinkMovementMethod.getInstance());
        String text = "<a href='http://www.macedas.com'>Macedas</a>";
        link.setText(Html.fromHtml(text));
        return view;
    }
}