package at.htlkaindorf.tankofax.bl;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;





public class DetailHolder extends RecyclerView.ViewHolder {
    private TextView tv_Adresse;
    private TextView tv_Name;
    private TextView tv_Price1;
    private TextView tv_Price2;

    public DetailHolder(@NonNull View itemView, TextView tv_Adresse, TextView tv_Name, TextView tv_Price1, TextView tv_Price2) {
        super(itemView);
        itemView.setOnClickListener((e) -> Log.d("DetailHolder.java","clicked"));
    }

    public TextView getTv_Adresse() {
        return tv_Adresse;
    }

    public void setTv_Adresse(TextView tv_Adresse) {
        this.tv_Adresse = tv_Adresse;
    }

    public TextView getTv_Name() {
        return tv_Name;
    }

    public void setTv_Name(TextView tv_Name) {
        this.tv_Name = tv_Name;
    }

    public TextView getTv_Price1() {
        return tv_Price1;
    }

    public void setTv_Price1(TextView tv_Price1) {
        this.tv_Price1 = tv_Price1;
    }

    public TextView getTv_Price2() {
        return tv_Price2;
    }

    public void setTv_Price2(TextView tv_Price2) {
        this.tv_Price2 = tv_Price2;
    }
}
