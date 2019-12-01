package mk.ukim.finki.students.moviesomdb.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import mk.ukim.finki.students.moviesomdb.R;
import mk.ukim.finki.students.moviesomdb.holders.CustomListViewHolder;

public class CustomListAdapter extends RecyclerView.Adapter {

    List<String> dataset;
    View.OnClickListener listener;



    public CustomListAdapter(List<String> dataset, View.OnClickListener listener){
        this.dataset=dataset;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_layout, viewGroup, false);
        return new CustomListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((CustomListViewHolder)viewHolder).setText(dataset.get(i), listener);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
    public void updateDataset(List<String> newDataset){
        this.dataset=newDataset;
        notifyDataSetChanged();
    }
}
