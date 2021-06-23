package at.htlkaindorf.tankofax.bl;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import at.htlkaindorf.tankofax.beans.Tankstelle;

public class DetailHolder extends RecyclerView.ViewHolder {

    private final TextView tv_v_Address;
    private final TextView tv_v_Name;
    private final TextView tv_v_price;
    private final TextView tv_v_contact1; //Tel
    private final TextView tv_v_contact2; //Fax
    private final TextView tv_v_contact3; //Website
    private final TextView tv_v_contact4; //Email

    private final TextView tv_v_distance; //Distance

    private final TextView tv_v_openingHours1; //Mo-Do
    private final TextView tv_v_openingHours2; //Fr
    private final TextView tv_v_openingHours3; //Sa
    private final TextView tv_v_openingHours4; //So-Feiertag

    private RelativeLayout expandable_layout;
    private RelativeLayout unexpanded_layout;

    public DetailHolder(@NonNull View itemView, TextView tv_v_Address, TextView tv_v_Name, TextView tv_v_price, TextView tv_v_contact1, TextView tv_v_distance,
                        RelativeLayout expandable_layout, RelativeLayout unexpanded_layout, List<Tankstelle> tankstellen, DetailAdapter adapter, TextView tv_v_contact2, TextView tv_v_contact3, TextView tv_v_contact4, TextView tv_v_openingHours1, TextView tv_v_openingHours2, TextView tv_v_openingHours3, TextView tv_v_openingHours4) {
        super(itemView);
        this.tv_v_Address = tv_v_Address;
        this.tv_v_Name = tv_v_Name;
        this.tv_v_price = tv_v_price;

        this.expandable_layout = expandable_layout;
        this.unexpanded_layout = unexpanded_layout;
        this.tv_v_distance = tv_v_distance;
        this.tv_v_contact1 = tv_v_contact1;
        this.tv_v_contact2 = tv_v_contact2;
        this.tv_v_contact3 = tv_v_contact3;
        this.tv_v_contact4 = tv_v_contact4;
        this.tv_v_openingHours1 = tv_v_openingHours1;
        this.tv_v_openingHours2 = tv_v_openingHours2;
        this.tv_v_openingHours3 = tv_v_openingHours3;
        this.tv_v_openingHours4 = tv_v_openingHours4;

        unexpanded_layout.setOnClickListener(v -> {
            Tankstelle tankstelle = tankstellen.get(getAdapterPosition());
            tankstelle.setExpandable(!tankstelle.isExpandable());
            adapter.notifyItemChanged(getAdapterPosition());
        });
    }

    public TextView getTv_v_contact() {
        return tv_v_contact;
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
    public TextView getTv_v_Address() {
        return tv_v_Address;
    }

    public TextView getTv_v_Name() {
        return tv_v_Name;
    }

    public TextView getTv_v_price() {
        return tv_v_price;
    }

    public TextView getTv_v_contact1() {
        return tv_v_contact1;
    }

    public TextView getTv_v_contact2() {
        return tv_v_contact2;
    }

    public TextView getTv_v_contact3() {
        return tv_v_contact3;
    }

    public TextView getTv_v_contact4() {
        return tv_v_contact4;
    }

    public TextView getTv_v_distance() {
        return tv_v_distance;
    }

    public TextView getTv_v_openingHours1() {
        return tv_v_openingHours1;
    }

    public TextView getTv_v_openingHours2() {
        return tv_v_openingHours2;
    }

    public TextView getTv_v_openingHours3() {
        return tv_v_openingHours3;
    }

    public TextView getTv_v_openingHours4() {
        return tv_v_openingHours4;
    }

    public RelativeLayout getUnexpanded_layout() {
        return unexpanded_layout;
    }
}
