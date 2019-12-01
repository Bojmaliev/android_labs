package mk.ukim.finki.students.moviesomdb.holders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import mk.ukim.finki.students.moviesomdb.R;

public class CustomListViewHolder extends RecyclerView.ViewHolder {

    private ImageView imageView;
    private TextView textView;

    public CustomListViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imageItemView1);
        textView = itemView.findViewById(R.id.textItemView1);

        itemView.setTag(this);
    }

    public void setText(String text, View.OnClickListener listener){
        textView.setText(text);
        imageView.setImageResource(R.drawable.dpng);
        itemView.setOnClickListener(listener);
    }
}
