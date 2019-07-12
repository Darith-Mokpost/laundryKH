package com.example.laundrykh.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laundrykh.R;


import java.util.LinkedList;

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ItemViewHolder>{
    LayoutInflater mInflater;
    LinkedList<Item> itemList;
    Context context;

    public ItemListAdapter(Context context, LinkedList<Item> itemList) {
        mInflater = LayoutInflater.from(context);
        this.itemList = itemList;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mItemView = mInflater.inflate(R.layout.recycle_clothes, viewGroup, false);
        return new ItemViewHolder(mItemView, this);


    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int position) {
//        Movie m2 = mWordList.get(position);
//        m2.getTitle();
//        m2.getDec();
//        m2.getImg();
//        m2.getScore();

        String name_clothes = itemList.get(position).getName_clothes();
        String num_clothes = itemList.get(position).getNum_clothes();
        int img_clothes = itemList.get(position).getImg_clothes();
        //Button btn = movieList.get(position).getBtn();

        //wordViewHolder.Dec.setText(mWordList.get(position).getTitle());

        itemViewHolder.name_clothes.setText(name_clothes);
        itemViewHolder.num_clothes.setText(num_clothes);
        itemViewHolder.imageClothes.setImageResource(img_clothes);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder  {
        TextView name_clothes;
        TextView num_clothes;
        ImageView imageClothes;
        Button Btn_minu, Btn_push;
        RecyclerView.Adapter mAdapter;

        public ItemViewHolder(View itemView, ItemListAdapter adapter) {
            super(itemView);
            imageClothes = itemView.findViewById(R.id.imgClothes);
            name_clothes = itemView.findViewById(R.id.name_clothes);
            num_clothes = itemView.findViewById(R.id.num_clothes);
            Btn_push = itemView.findViewById(R.id.btn_push);
            Btn_push.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Hello" + getAdapterPosition(), Toast.LENGTH_SHORT).show();
                }
            });
            Btn_minu = itemView.findViewById(R.id.btn_minus);
            Btn_minu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Hello" + getAdapterPosition(), Toast.LENGTH_SHORT).show();
                }
            });

//            itemView.setOnClickListener(this);
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(context, Detail.class);
//                    intent.putExtra("KEY_TITLE", name_clothes.getText().toString());
//                    context.startActivity(intent);
//                }
//            });

            this.mAdapter = adapter;

            //itemView.setOnClickListener(this);
        }

//        @Override
//        public void onClick(View v) {
//            Toast.makeText(context, Title.getText().toString(), Toast.LENGTH_SHORT).show();
//        }
    }

}
