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

        TextView tv_v_Distance = view.findViewById(R.id.tv_v_distance);

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

        return new DetailHolder(view, tv_v_Adresse, tv_v_Name, tv_v_price, tv_v_contact1, tv_v_Distance, exapandable, unexpanded, tankstellen, this, tv_v_contact2, tv_v_contact3, tv_v_contact4, tv_v_oh1, tv_v_oh2, tv_v_oh3, tv_v_oh4);
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @Override
    public void onBindViewHolder(@NonNull DetailHolder holder, int position) {
        Tankstelle tankstelle = tankstellen.get(position);

        holder.getTv_v_Name().setText(tankstelle.getName());
        holder.getTv_v_price().setText(tankstelle.getPrices()[0].getAmount() + "");
        holder.getTv_v_Address().setText(tankstelle.getLocation().getAddress());

        holder.getTv_v_distance().setText(String.format("%.2f km", tankstelle.getDistance()));

        holder.getTv_v_contact1().setText(tankstelle.getContact().getTelephone());
        holder.getTv_v_contact2().setText(tankstelle.getContact().getFax());
        holder.getTv_v_contact3().setText(tankstelle.getContact().getWebsite());
        holder.getTv_v_contact4().setText(tankstelle.getContact().getMail());

        holder.getTv_v_openingHours1().setText(tankstelle.getOpeningHours()[0].getFrom() + " - " + tankstelle.getOpeningHours()[0].getTo());
        holder.getTv_v_openingHours2().setText(tankstelle.getOpeningHours()[4].getFrom() + " - " + tankstelle.getOpeningHours()[4].getTo());
        holder.getTv_v_openingHours3().setText(tankstelle.getOpeningHours()[5].getFrom() + " - " + tankstelle.getOpeningHours()[5].getTo());
        holder.getTv_v_openingHours4().setText(tankstelle.getOpeningHours()[6].getFrom() + " - " + tankstelle.getOpeningHours()[6].getTo());

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