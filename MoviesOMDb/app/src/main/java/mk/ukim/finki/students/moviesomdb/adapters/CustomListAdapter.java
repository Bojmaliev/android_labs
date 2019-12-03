package mk.ukim.finki.students.moviesomdb.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import mk.ukim.finki.students.moviesomdb.R;
import mk.ukim.finki.students.moviesomdb.holders.CustomListViewHolder;
import mk.ukim.finki.students.moviesomdb.models.OMDbMovie;

public class CustomListAdapter extends RecyclerView.Adapter {

    List<OMDbMovie> dataset;
    View.OnClickListener listener;
    Context c;



    public CustomListAdapter(Context c, View.OnClickListener listener){
        this.dataset = new ArrayList<>();
        this.listener = listener;
        this.c = c;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_layout, viewGroup, false);
        return new CustomListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((CustomListViewHolder)viewHolder).setInformations(c, dataset.get(i), listener);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void updateDataset(List<OMDbMovie> newDataset){
        this.dataset=newDataset;
        notifyDataSetChanged();
    }

    public String getClickedItemId(CustomListViewHolder holder) {
        int adapterPosition = holder.getAdapterPosition();
        return dataset.get(adapterPosition).imdbID;
    }
}
