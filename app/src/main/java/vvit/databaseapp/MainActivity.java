package vvit.databaseapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnItemSelectedListener {
     Spinner sp;
    ArrayList<String> list;
    DatabaseHandler db;
    EditText t1,t2,t3;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp=(Spinner)findViewById(R.id.contacts);

        db = new DatabaseHandler(this);
        list = new ArrayList<String>();
        /**
         * CRUD Operations
         * */
        // Inserting Contacts
        Log.d("Insert: ", "Inserting ..");
        db.addContact(new Contact("Ravi", "9100000000"));
        db.addContact(new Contact("Srinivas", "9199999999"));
        db.addContact(new Contact("Tommy", "9522222222"));
        db.addContact(new Contact("Karthik", "9533333333"));

        // Reading all contacts
        Log.d("Reading: ", "Reading all contacts..");
        List<Contact> contacts = db.getAllContacts();

        for (Contact cn : contacts) {
           /* String log = "Id: "+cn.getID()+" ,Name: " + cn.getName() + " ,Phone: " + cn.getPhoneNumber();
            // Writing Contacts to log
            Log.d("Name: ", log);*/
            list.add(String.valueOf(cn.getID()));

        }
        ArrayAdapter spAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,list);
        sp.setAdapter(spAdapter);
        sp.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Contact ct;
        int cid;
        cid=Integer.parseInt((String)sp.getSelectedItem());
        ct=db.getContact(cid);
        t1=(EditText)findViewById(R.id.ed1);
        t2=(EditText)findViewById(R.id.ed2);
        t3=(EditText)findViewById(R.id.ed3);
        t1.setText(String.valueOf(ct.getID()));
        t2.setText(ct.getName());
        t3.setText(ct.getPhoneNumber());


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
