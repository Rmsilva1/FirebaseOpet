package com.example.labdesenvolvimento.firebaseopet;

import android.app.Activity;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends Activity {

    EditText editPalavra;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;
    TextView textResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
            myRef = database.getReference("palavra");

        // Read from the database
       myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String dado = dataSnapshot.getValue(String.class);
                textResultado.setText(dado);
                Log.d("1", "Value is: " + dado);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("2", "Failed to read value.", error.toException());
            }
        });


    }

    public void salvarNoBanco(View v){
        myRef.setValue(editPalavra.getText().toString());
    }

}
