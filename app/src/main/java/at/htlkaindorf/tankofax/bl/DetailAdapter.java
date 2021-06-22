package at.htlkaindorf.tankofax.bl;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
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
                .inflate(R.layout.tankstelle_item, parent, false);
        TextView tv_v_Adresse = view.findViewById(R.id.tv_v_Adresse);
        TextView tv_v_Name = view.findViewById(R.id.tv_v_Name);
        TextView tv_v_price = view.findViewById(R.id.tv_v_price);
        TextView tv_Distance = view.findViewById(R.id.tv_distance);
        TextView tv_Contact = view.findViewById(R.id.tv_contact);

        TextView tv_v_Distance = view.findViewById(R.id.tv_v_distance);
        TextView tv_v_Contact = view.findViewById(R.id.tv_v_contact1);

        TextView tv_v_contact1 = view.findViewById(R.id.tv_v_contact1);
        TextView tv_v_contact2 = view.findViewById(R.id.tv_v_contact2);
        TextView tv_v_contact3 = view.findViewById(R.id.tv_v_contact3);
        TextView tv_v_contact4 = view.findViewById(R.id.tv_v_contact4);

        TextView tv_v_oh1 = view.findViewById(R.id.tv_v_oh1);
        TextView tv_v_oh2 = view.findViewById(R.id.tv_v_oh2);
        TextView tv_v_oh3 = view.findViewById(R.id.tv_v_oh3);
        TextView tv_v_oh4 = view.findViewById(R.id.tv_v_oh4);

        RelativeLayout exapandable = view.findViewById(R.id.expandable_layout);
        RelativeLayout unexpanded = view.findViewById(R.id.unexpanded_layout);

        return new DetailHolder(view, tv_v_Adresse, tv_v_Name, tv_v_price,tv_Distance, tv_v_Distance, tv_Contact, tv_v_Contact, exapandable, unexpanded, tankstellen, this);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull DetailHolder holder, int position) {
        Tankstelle tankstelle = tankstellen.get(position);

        holder.getTv_v_Name().setText(tankstelle.getName());
        holder.getTv_v_price().setText(tankstelle.getPrices()[0].getAmount() + "");
        holder.getTv_v_Address().setText(tankstelle.getLocation().getAddress());


        holder.getTv_v_distance().setText(tankstelle.getDistance() + "");
        holder.getTv_v_contact().setText(tankstelle.getContact() + "");
        boolean isExpandable;
        try {
            isExpandable = tankstellen.get(position).isExpandable();
            holder.getExpandable_layout().setVisibility(isExpandable ? View.VISIBLE : View.GONE);
        } catch (IndexOutOfBoundsException ex) {
            System.out.println(ex.toString());
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