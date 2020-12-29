package maceda.alejandro.alexiavnplayer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class GuardarFragment extends Fragment {


    public GuardarFragment() {
        // Required empty public constructor
    }

    public static GuardarFragment newInstance(String param1, String param2) {
        GuardarFragment fragment = new GuardarFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_guardar, container, false);
    }
}