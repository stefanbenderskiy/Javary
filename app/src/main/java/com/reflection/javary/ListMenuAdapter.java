package com.reflection.javary;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ListMenuAdapter extends ArrayAdapter<ListMenuItem>{

    private LayoutInflater inflater;
    private int resource;
    public ListMenuAdapter(@NonNull Context context, int resource, @NonNull List<ListMenuItem> items) {
        super(context,resource,items);
        inflater = LayoutInflater.from(context);


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ListMenuItem item = getItem(position);

        convertView = inflater.inflate(R.layout.listmenu_item,null);
        View icon = convertView.findViewById(R.id.listmenu_item_icon);
        icon.setBackground(item.getIcon());
        TextView title = convertView.findViewById(R.id.listmenu_item_title);
        title.setText(item.getTitle());
        convertView.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(),item.getActivity());
            getContext().startActivity(intent);
        });
        return  convertView;
    }

}
