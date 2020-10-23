package android.example.firebaselearn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {


    DatabaseReference retrieveDatabase;


    ArrayList<String> Users;
    ArrayList<String> UserNames,UserArts,UserPhoneNumbers;

    //recycler view iimplementation
    MyAdapter adapter;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        retrieveDatabase= FirebaseDatabase.getInstance().getReference().child("Job Seekers");
        retrieveDatabase.addValueEventListener(new ValueEventListener() {                //ValueEventListener  is used to retrieve data from fireBase realtime dataBase
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

               Users= new ArrayList();
               for(DataSnapshot dataSnapshot:snapshot.getChildren())     //here we use to get the keys of all registered users,by this key,we can get the appropriate inner key and values
               {

                   String key=dataSnapshot.getKey();
                    Users.add(key);                                          //the key user stored in arraylist
               }

                UserNames=new ArrayList<>();
               UserArts=new ArrayList<>();
               UserPhoneNumbers=new ArrayList<>();


                for(String User:Users)                            //by the key user, it gets iinner keys and its value
                {
                    String  name=snapshot.child(User).child("regName").getValue().toString();
                    String phoneNumber=snapshot.child(User).child("regPhoneNumber").getValue().toString();
                    String arts=snapshot.child(User).child("regArts").getValue().toString();

                    UserNames.add(name);                        // the attributes too saved  in appropriate arrayList
                    UserArts.add(arts);                          //
                    UserPhoneNumbers.add(phoneNumber);             //



                }








                //Recycler View thing (implementing these results in RecyclerView)

                recyclerView=findViewById(R.id.recycler_view);
                adapter=new MyAdapter(SecondActivity.this,UserNames,UserArts,UserPhoneNumbers);


                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(SecondActivity.this));







            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {                     //if the data didnt retrieved, this code get executed
                  Toast.makeText(SecondActivity.this,"Not going correct!!",Toast.LENGTH_SHORT).show();
            }
        });








    }
}