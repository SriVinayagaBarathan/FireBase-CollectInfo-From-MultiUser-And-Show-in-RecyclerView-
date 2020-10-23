package android.example.firebaselearn;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.OwnAdapter>
{

    ArrayList<String> UserNames,UserArts,UserPhoneNumbers;
    Context context;



    public   MyAdapter(Context ct,ArrayList uname,ArrayList uarts,ArrayList uphonenumber)
    {

        this.context=ct;
        this.UserNames=uname;
        this.UserArts=uarts;
        this.UserPhoneNumbers=uphonenumber;

    }


    @NonNull
    @Override
    public MyAdapter.OwnAdapter onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


        LayoutInflater inflater=LayoutInflater.from(context);
        View MyGroup = inflater.inflate(R.layout.row, viewGroup, false);
        return new OwnAdapter(MyGroup);


    }

    @Override
    public void onBindViewHolder(@NonNull final MyAdapter.OwnAdapter ownAdapter, int i)
    {
        ownAdapter.textDesc.setText("Name==> "+UserNames.get(i)+"\nTalented in ==> "+UserArts.get(i)+"\nPhone Number==> "+UserPhoneNumbers.get(i));



    }

    @Override
    public int getItemCount() {
        return UserNames.size();
    }

    public  class OwnAdapter extends RecyclerView.ViewHolder
    {

        TextView textDesc;
        public OwnAdapter(@NonNull View itemView) {
            super(itemView);

            textDesc=itemView.findViewById(R.id.show_result_text_view_recycler);


        }











    }
}