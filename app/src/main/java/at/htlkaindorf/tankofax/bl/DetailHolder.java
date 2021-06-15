package at.htlkaindorf.tankofax.bl;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import at.htlkaindorf.tankofax.beans.Tankstelle;

public class DetailHolder extends RecyclerView.ViewHolder {

    private final TextView tv_Address;
    private final TextView tv_Name;
    private final TextView tv_Price1;
    private final TextView tv_Price2;
    private final TextView tv_contact;
    private final TextView tv_v_contact;
    private final TextView tv_opening_hour;
    private final TextView tv_v_opening_hour;
    private final TextView tv_distance;
    private final TextView tv_v_distance;

    private RelativeLayout expandable_layout;
    private RelativeLayout unexpanded_layout;

    public DetailHolder(@NonNull View itemView, TextView tv_Address, TextView tv_Name, TextView tv_Price1, TextView tv_Price2
                        , TextView tv_contact, TextView tv_v_contact, TextView tv_opening_hour, TextView tv_v_opening_hour,
                        TextView tv_distance, TextView tv_v_distance, RelativeLayout expandable_layout, RelativeLayout unexpanded_layout, List<Tankstelle> tankstellen, DetailAdapter adapter) {
        super(itemView);
        this.tv_Address = tv_Address;
        this.tv_Name = tv_Name;
        this.tv_Price1 = tv_Price1;
        this.tv_Price2 = tv_Price2;
        this.tv_contact = tv_contact;
        this.tv_v_contact = tv_v_contact;
        this.tv_opening_hour = tv_opening_hour;
        this.tv_v_opening_hour = tv_v_opening_hour;
        this.tv_distance = tv_distance;
        this.tv_v_distance = tv_v_distance;

        this.expandable_layout = expandable_layout;
        this.unexpanded_layout = unexpanded_layout;

        unexpanded_layout.setOnClickListener(v -> {
            Tankstelle tankstelle = tankstellen.get(getAdapterPosition());
            tankstelle.setExpandable(!tankstelle.isExpandable());
            adapter.notifyItemChanged(getAdapterPosition());
        });
    }

    public TextView getTv_v_contact() {
        return tv_v_contact;
    }

    public TextView getTv_v_opening_hour() {
        return tv_v_opening_hour;
    }

    public TextView getTv_v_distance() {
        return tv_v_distance;
    }

    public RelativeLayout getExpandable_layout() {
        return expandable_layout;
    }

    public void setExpandable_layout(RelativeLayout expandable_layout) {
        this.expandable_layout = expandable_layout;
    }

    public void setUnexpanded_layout(RelativeLayout unexpanded_layout) {
        this.unexpanded_layout = unexpanded_layout;
    }

    public TextView getTv_Address() {
        return tv_Address;
    }

    public TextView getTv_Name() {
        return tv_Name;
    }

    public TextView getTv_Price2() {
        return tv_Price2;
    }

    public TextView getTv_Price1() {
        return tv_Price1;
    }

    public TextView getTv_contact() {
        return tv_contact;
    }

    public TextView getTv_opening_hour() {
        return tv_opening_hour;
    }

    public TextView getTv_distance() {
        return tv_distance;
    }
}
