package at.htlkaindorf.tankofax.bl;

import android.annotation.SuppressLint;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import at.htlkaindorf.tankofax.R;
import at.htlkaindorf.tankofax.beans.Tankstelle;

public class DetailAdapter extends RecyclerView.Adapter<DetailHolder> {
    private boolean expand = false;
    private List<Tankstelle> tankstellen;
    private List<Tankstelle> tankstellenToRemove = new ArrayList<>();

    @NonNull
    @Override
    public DetailHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tankstelle_item,parent,false);
        TextView tv_Adresse = view.findViewById(R.id.tv_Adresse);
        TextView tv_Name = view.findViewById(R.id.tv_Name);
        TextView tv_Price1 = view.findViewById(R.id.tv_Price1);
        TextView tv_Price2 = view.findViewById(R.id.tv_Price2);

        TextView tv_Distance = view.findViewById(R.id.tv_distance);
        TextView tv_v_Distance = view.findViewById(R.id.tv_v_distance);
        TextView tv_Opening_Hour = view.findViewById(R.id.tv_opening_hour);
        TextView tv_v_Opening_Hour = view.findViewById(R.id.tv_v_opening_hour);
        TextView tv_Contact = view.findViewById(R.id.tv_contact);
        TextView tv_v_Contact = view.findViewById(R.id.tv_v_contact);

        RelativeLayout exapandable = view.findViewById(R.id.expandable_layout);
        RelativeLayout unexpanded = view.findViewById(R.id.unexpanded_layout);

        return new DetailHolder(view,tv_Adresse,tv_Name,tv_Price1,tv_Price2,tv_Distance,tv_v_Distance,tv_Opening_Hour,tv_v_Opening_Hour,tv_Contact,tv_v_Contact,exapandable,unexpanded, tankstellen,this);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull DetailHolder holder, int position) {
        Tankstelle tankstelle = tankstellen.get(position);
        holder.getTv_Adresse().setText(tankstelle.getLocation().getAddress());
        holder.getTv_Name().setText(tankstelle.getName());
        try {
            holder.getTv_Price2().setText(tankstelle.getPrices()[0].getAmount() + "");
        } catch (ArrayIndexOutOfBoundsException ex) {
            holder.getTv_Price2().setText("price");
        }
        holder.getTv_v_distance().setText(tankstelle.getDistance() + "");
        holder.getTv_v_opening_hour().setText(Arrays.toString(tankstelle.getOpeningHours()) + "");
        holder.getTv_v_contact().setText(tankstelle.getContact() + "");
        if (holder.getTv_Price2().getText().equals("price")) {
            tankstellenToRemove.add(tankstelle);
            tankstellen.removeAll(tankstellenToRemove);
            tankstellenToRemove.clear();
        }
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

    public List<Tankstelle> getTankstellenToRemove() {
        return tankstellenToRemove;
    }

    public void setExpand(boolean expand) {
        this.expand = expand;
    }

    public boolean isExpand() {
        return expand;
    }
}