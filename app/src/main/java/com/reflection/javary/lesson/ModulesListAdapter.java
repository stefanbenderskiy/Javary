package com.reflection.javary.lesson;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.reflection.javary.LessonsController;
import com.reflection.javary.R;

import java.util.ArrayList;
import java.util.List;

public class ModulesListAdapter extends RecyclerView.Adapter<ModulesListAdapter.ViewHolder> {
    private LessonsController lessonsController;
    private Context context;
    private List<Module> modules;
    public ModulesListAdapter(Context context) {
        this.context = context;
        lessonsController = new LessonsController(context);
        modules = lessonsController.getModules();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView title;
        public final  ProgressBar progressBar;
        ViewHolder(View view){
            super(view);
            title= view.findViewById(R.id.item_module_title);
            progressBar = view.findViewById(R.id.item_module_progress_bar);

        }
    }
    @NonNull
    @Override
    public ModulesListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View  view= LayoutInflater.from(context).inflate(R.layout.item_module,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Module module= modules.get(position);
        holder.title.setText(module.getTitle());
        holder.progressBar.setMax(module.getSize());
        holder.progressBar.setProgress(lessonsController.getModuleData(position+1).getInt("progress",0));

    }


    @Override
    public int getItemCount() {
        return modules.size();
    }
}
