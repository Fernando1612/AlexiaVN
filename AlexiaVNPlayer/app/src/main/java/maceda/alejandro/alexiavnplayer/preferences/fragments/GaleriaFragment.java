package maceda.alejandro.alexiavnplayer.preferences.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.io.File;
import java.util.ArrayList;

import maceda.alejandro.alexiavnplayer.R;
import maceda.alejandro.alexiavnplayer.adapters.ViewPagerAdapter;
import me.relex.circleindicator.CircleIndicator;


public class GaleriaFragment extends Fragment {

    private String file_path;
    private ArrayList<Bitmap> bitmaps = new ArrayList<>();
    private ViewPager viewPager;

    public GaleriaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_galeria, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cargarDatos();
        file_path = file_path + "Scenes/";
        crearArreglo();

        viewPager = view.findViewById(R.id.pager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getContext(), bitmaps);
        viewPager.setAdapter(viewPagerAdapter);

        CircleIndicator indicator = (CircleIndicator) view.findViewById(R.id.indicator);
        indicator.setViewPager(viewPager);


    }

    private void cargarDatos() {
        Intent intent = getActivity().getIntent();
        file_path = intent.getStringExtra("path");
    }

    private void crearArreglo() {
        File dir = new File(file_path);
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                Bitmap myBitmap = BitmapFactory.decodeFile(child.getAbsolutePath());
                bitmaps.add(myBitmap);
            }
        }
    }
}