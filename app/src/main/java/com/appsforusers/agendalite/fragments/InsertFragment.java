package com.appsforusers.agendalite.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.appsforusers.agendalite.R;
import com.appsforusers.agendalite.sqlite.AgendaSql;

/**
 * Created by preto on 05/03/2018.
 */

public class InsertFragment extends Fragment implements View.OnClickListener{

    EditText editTextName;
    Spinner spinnerPosition;
    EditText editTextAge;

    Button btnInsert;

    public InsertFragment(){
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.insert_layout, container, false);

        editTextName = view.findViewById(R.id.editTextName);
        spinnerPosition = view.findViewById(R.id.spinnerPosition);
        editTextAge = view.findViewById(R.id.editTextAge);

        btnInsert = view.findViewById(R.id.buttonInsert);
        btnInsert.setOnClickListener(this);


        // Obtiene el string-array para llenar el combo
        ArrayAdapter spinner_adapter = ArrayAdapter.createFromResource(getActivity(), R.array.developer_positions , android.R.layout.simple_spinner_item);
        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPosition.setAdapter(spinner_adapter);

        // Devolvemos la vista al fragment
        return view;

    }


    /**
     *
     * Insert a new record
     *
     */
    private void insertInto(){

        // TODO: Creamos referencia de la clase AgendaSQL
        AgendaSql agendaSql = new AgendaSql(getActivity());

        // Llamamos método para insertar el registro
        agendaSql.insertDeveloper(getActivity(), editTextName, spinnerPosition, editTextAge);

        // Cerramos la base de datos
        agendaSql.close();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonInsert:
                insertInto();
                break;
        }
    }
}
