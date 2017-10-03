package com.example.rynel.savingdata;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main_Activity";
    private TextView tvGetDataFromSharedPref;
    private EditText etSaveDataToSharedPref;
    private double btnClearAllSharedPref;
    private EditText etPersonName;
    private EditText etPersonAge;
    private EditText etPersonGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etSaveDataToSharedPref = (EditText) findViewById(R.id.etSaveDataToSharedPref);
        tvGetDataFromSharedPref = (TextView) findViewById(R.id.getDataToSharedPref);

        //for SQL
        etPersonName = (EditText) findViewById(R.id.etPersonName);
        etPersonAge = (EditText) findViewById(R.id.etPersonAge);
        etPersonGender = (EditText) findViewById(R.id.etPersonGender);




    }

    //learning to write and save data
    public void usingSharedPref(View view){

        SharedPreferences sharedPreferences = getSharedPreferences("mySharedPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        switch (view.getId()){

            case R.id.btnSaveDataToSharedPref:
                editor.putString("data", etSaveDataToSharedPref.getText().toString());
                boolean isSaved = editor.commit();

                if(isSaved)
                    Toast.makeText(this, "saved", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(this, "Not Saved", Toast.LENGTH_SHORT).show();

                break;
            case R.id.getDataToSharedPref:
                String data = sharedPreferences.getString("data", "defaultValue");
                tvGetDataFromSharedPref.setText(data);

                editor.clear().commit();

                break;

            case R.id.btnClearAllSharedPref:

                boolean isCleared = editor.clear().commit();
                        if (isCleared)
                            Toast.makeText(this, "Data cleared", Toast.LENGTH_SHORT).show();

                        else
                            Toast.makeText(this,"Data not cleared", Toast.LENGTH_SHORT).show();

                break;


        }
    }

    public void usingSQLite(View view){

        DatabaseHelper databaseHelper = new DatabaseHelper(this);

        switch (view.getId()){

            case R.id.btnSavePersonToDB:

                String personName = etPersonName.getText().toString();
                String personAge = etPersonAge.getText().toString();
                String personGender = etPersonGender.getText().toString();


                Person person = new Person(personName, personAge, personGender);

                long rowId = databaseHelper.savePerson(person);

                Toast.makeText(this, "Row id: " + rowId, Toast.LENGTH_SHORT).show();

                break;

            case R.id.btnGetPersonsFromDB:

                List<Person> personList = databaseHelper.getPersonList();
                for(int i = 0; i <personList.size(); i++)
                {
                    Log.d(TAG, "usingSQLite: " + personList.get(i).toString());
                }

                Intent intent = new Intent(this, PersonListActivity.class);
                startActivity(intent);

                break;
        }

        //TODO: 10/2/17 Create a database to save the data
    }


}
