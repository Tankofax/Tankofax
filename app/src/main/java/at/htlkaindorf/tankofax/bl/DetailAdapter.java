package at.htlkaindorf.tankofax.bl;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import at.htlkaindorf.tankofax.R;
import at.htlkaindorf.tankofax.beans.Tankstelle;

public class DetailAdapter extends RecyclerView.Adapter<DetailHolder> {
    private List<Tankstelle> tankstellen;
    @NonNull
    @Override
    public DetailHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tankstelle_item,parent,false);
        TextView tv_Adresse = view.findViewById(R.id.tv_Adresse);
        TextView tv_Name = view.findViewById(R.id.tv_Name);
        TextView tv_Price1 = view.findViewById(R.id.tv_Price1);
        TextView tv_Price2 = view.findViewById(R.id.tv_Price2);
        return new DetailHolder(view,tv_Adresse,tv_Name,tv_Price1,tv_Price2);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull DetailHolder holder, int position) {
        Tankstelle tankstelle = tankstellen.get(position);
        holder.getTv_Adresse().setText(tankstelle.getLocation().getAddress());
        holder.getTv_Name().setText(tankstelle.getName());
        holder.getTv_Price1().setText("Preis");
        try {
            holder.getTv_Price2().setText(tankstelle.getPrices()[0].getAmount() + " €");
        } catch (ArrayIndexOutOfBoundsException ex) {
            holder.getTv_Price1().setText("");
            holder.getTv_Price2().setText("");
        }
    }

    @Override
    public int getItemCount() {
        return tankstellen.size();
    }

    public void setFuel(List<Tankstelle> tankstellen) {
        this.tankstellen = tankstellen;
    }
}