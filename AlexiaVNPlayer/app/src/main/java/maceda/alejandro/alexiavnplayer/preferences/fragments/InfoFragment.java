package maceda.alejandro.alexiavnplayer.preferences.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import maceda.alejandro.alexiavnplayer.R;


public class InfoFragment extends Fragment {


    public InfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_info, container, false);
        TextView Nombre = (TextView) view.findViewById(R.id.textView3);
        TextView Sexo = (TextView) view.findViewById(R.id.textView4);

        SharedPreferences preferences = this.getActivity().getSharedPreferences("Informacion", Context.MODE_PRIVATE);

        String nombre = preferences.getString("nombre", "No existe la informacion");
        String sexo = preferences.getString("sexo", "No existe la informacion");

        Nombre.setText(nombre);
        Sexo.setText(sexo);

        return view;
    }
}