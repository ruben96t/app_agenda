package com.appsforusers.agendalite.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.appsforusers.agendalite.R;
import com.appsforusers.agendalite.sqlite.AgendaSql;

/**
 * Created by preto on 05/03/2018.
 */

public class SearchFragment extends Fragment implements View.OnClickListener{

    EditText editTextDeveloper;
    Button btnFind;

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.seach_layout, container, false);

        editTextDeveloper = view.findViewById(R.id.editTextFind);
        btnFind = view.findViewById(R.id.buttonFind);
        btnFind.setOnClickListener(this);

        return view;
    }

    private void findByEmail(){

        // TODO: Creamos referencia de la clase AgendaSQL
        AgendaSql agendaSql = new AgendaSql(getActivity());

        // Llamamos método para buscar el registro
        if (agendaSql.findDeveloper(getActivity(), editTextDeveloper)){
            Toast.makeText(getActivity(), "Developer " + editTextDeveloper.getText() + " found!!", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getActivity(), "Developer " + editTextDeveloper.getText() + " not found!!", Toast.LENGTH_LONG).show();
        }

        // Cerramos la base de datos
        agendaSql.close();
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.buttonFind:
                findByEmail();
                break;
        }
    }
}
