package at.htlkaindorf.tankofax.bl;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import at.htlkaindorf.tankofax.R;

public class DetailAdapter extends RecyclerView.Adapter<DetailHolder> {
    @NonNull
    @Override
    public DetailHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.detail_tankstelle,parent,false);
        TextView tv_Adresse = view.findViewById(R.id.tv_Adresse);
        TextView tv_Name = view.findViewById(R.id.tv_Name);
        TextView tv_Price1 = view.findViewById(R.id.tv_Price1);
        TextView tv_Price2 = view.findViewById(R.id.tv_Price2);
        DetailHolder dh = new DetailHolder(view,tv_Adresse,tv_Name,tv_Price1,tv_Price2);
        return dh;
    }

    @Override
    public void onBindViewHolder(@NonNull DetailHolder holder, int position) {
        /*
        Movie movie = movies.get(position);
        holder.getTvMovieName().setText(movie.getName());
        holder.getTvReleaseYear().setText(movie.getFormattedRelease());
        holder.getTvDuration().setText(movie.getFormattedDuration());
         */
    }

    @Override
    public int getItemCount() {
        return 0;
        //return movies.size();
    }
}
