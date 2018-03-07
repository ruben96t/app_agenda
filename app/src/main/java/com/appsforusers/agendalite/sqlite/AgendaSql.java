package com.appsforusers.agendalite.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.appsforusers.agendalite.R;
import com.appsforusers.agendalite.constants.Constants;

/**
 * Created by preto on 06/03/2018.
 */

public class AgendaSql extends SQLiteOpenHelper {

    public SQLiteDatabase db;



    private final String CREATE_DEVELPERS_TABLE = "CREATE TABLE " + Constants.DEVELOPERS_TABLE +
                                                " (" + Constants.ID_FIELD + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                                    Constants.NAME_FIELD + " TEXT NOT NULL, " +
                                                    Constants.POSITION_FIELD + " TEXT NOT NULL, " +
                                                    Constants.AGE_FIELD + " INTEGER NOT NULL)";


    private final String DELETE_DEVELOPERS_TABLE = "DROP TABLE IF EXISTS " + Constants.DEVELOPERS_TABLE;



    public AgendaSql(Context context){
        super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DEVELPERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(DELETE_DEVELOPERS_TABLE);
        db.execSQL(CREATE_DEVELPERS_TABLE);
    }


    /**
     *
     * Insert a new record
     *
     * @param context
     * @param name
     * @param position
     * @param age
     */
    public void insertDeveloper(Context context, EditText name, Spinner position, EditText age){

        if((TextUtils.isEmpty(name.getText()) ||
                TextUtils.isEmpty(position.getSelectedItem().toString()) ||
                TextUtils.isEmpty(age.getText()))){

            Toast.makeText(context, R.string.field_empty, Toast.LENGTH_LONG).show();

        }else {

            String sql = "INSERT INTO " + Constants.DEVELOPERS_TABLE +
                    " (" + Constants.NAME_FIELD + ", " +
                    Constants.POSITION_FIELD + ", " +
                    Constants.AGE_FIELD + ") " +
                    "VALUES('" + name.getText() + "', '" +
                    position.getSelectedItem().toString() + "', '" +
                    age.getText() + "')";

            db.execSQL(sql);

            Toast.makeText(context, R.string.saved_successfully, Toast.LENGTH_LONG).show();

        }

    }

    public boolean findDeveloper(Context context, EditText name){

        boolean found = false;

        if(TextUtils.isEmpty(name.getText())){
            Toast.makeText(context, R.string.field_empty, Toast.LENGTH_LONG).show();
        }else{

            try {

                // Seleccionamos todos los registros de la tabla developers
                String sql = "SELECT * FROM " + Constants.DEVELOPERS_TABLE + " WHERE " + Constants.NAME_FIELD + " = ?" + name;

                Cursor c = db.rawQuery(sql, null);

                if (c != null && c.getCount() > 0) {
                    if (c.moveToFirst()) {
                        do{
                            Log.d("TAG", "Developer: " + c.getString(1));

                            if(name.getText().equals(c.getString(1))){
                                found = true;
                                break;
                            }else{
                                found = false;
                            }
                        } while (c.moveToNext());
                    }
                }

            }catch (SQLiteException e){
                // Si ocurre error lo mostramos en la consola
                Log.d("TAG", e.getMessage());
            }

        }

        return found;

    }


}
