package at.htlkaindorf.tankofax.bl;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;





public class DetailHolder extends RecyclerView.ViewHolder {
    private TextView tv_Adresse;
    private TextView tv_Name;
    private TextView tv_Price1;
    private TextView tv_Price2;

    public DetailHolder(@NonNull View itemView, ) {
        super(itemView);
    }
}
