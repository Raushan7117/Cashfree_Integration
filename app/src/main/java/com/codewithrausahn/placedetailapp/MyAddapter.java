package com.codewithrausahn.placedetailapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class MyAddapter extends ArrayAdapter {
     Context context;
     List<String> listtitale;
     List<Integer> listimage;
     List<String> listquantity;
    List<String> listprice;
    public MyAddapter(@NonNull Context context, List<String> titale,List<Integer> image,List<String> quant, List<String> price ) {
        super(context, R.layout.samplelayout,titale);
        this.context=context;
        this.listtitale=titale;
        this.listimage=image;
        this.listquantity=quant;
        this.listprice=price;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view= LayoutInflater.from(context).inflate(R.layout.samplelayout,parent,false);
        ImageView imageView=view.findViewById(R.id.image);
        TextView textview =view.findViewById(R.id.text1);
        TextView textview1 =view.findViewById(R.id.text2);
        TextView textview2 =view.findViewById(R.id.text3);
        imageView.setImageResource(listimage.get(position));
        textview.setText(listtitale.get(position));
        textview1.setText(listquantity.get(position));
        textview2.setText(listprice.get(position));
        return view;
    }
}
