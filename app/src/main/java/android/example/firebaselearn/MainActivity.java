package android.example.firebaselearn;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    EditText name,arts,phoneNumber;
    Button RegisterButton,showOtherCandidates;

    DatabaseReference database;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name=findViewById(R.id.name);
        arts=findViewById(R.id.arts);
        phoneNumber=findViewById(R.id.phone_number);
        RegisterButton=findViewById(R.id.register_button);
        showOtherCandidates=findViewById(R.id.otherCandidates_button);







       // data insertion into firebase realtime database
        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                database=FirebaseDatabase.getInstance().getReference("Job Seekers");            // job seeker is the name, it became parent

                String reg_name=name.getText().toString();
                String reg_arts=arts.getText().toString();
                String reg_phone_number=phoneNumber.getText().toString();

                if((!TextUtils.isEmpty(reg_name)) && (!TextUtils.isEmpty(reg_arts)) && (!TextUtils.isEmpty(reg_phone_number)))
                {
                    String unique_id=database.push().getKey();                           //getting unique id for refferencing each element
                    HashMap reg_candidates = new HashMap();

                    reg_candidates.put("regName",reg_name);
                    reg_candidates.put("regArts",reg_arts);
                    reg_candidates.put("regPhoneNumber",reg_phone_number);
                    reg_candidates.put("regid",unique_id);


                    database.child(reg_name).setValue(reg_candidates).addOnCompleteListener(new OnCompleteListener<Void>() {  //if the data get stored in fireBase ,this code get executed
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(MainActivity.this, "Registered!", Toast.LENGTH_SHORT).show();
                        }
                    })  ;






                }

                else
                {
                    Toast.makeText(MainActivity.this, "Fill all the details,Given above!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        //





     showOtherCandidates.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent intent=new Intent(MainActivity.this,SecondActivity.class);
             startActivity(intent);
         }
     });



    }
}