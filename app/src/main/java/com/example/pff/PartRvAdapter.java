package com.example.pff;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
public class PartRvAdapter extends RecyclerView.Adapter<PartRvAdapter.PartViewHolder> {
 private ArrayList<Partement>partements;
    OnRecyclerViewItemClickListener listener;
 public PartRvAdapter(ArrayList<Partement>partements,OnRecyclerViewItemClickListener listener)
 {
     this.partements=partements;
     this.listener=listener;
 }

    public ArrayList<Partement> getPartements() {
        return partements;
    }

    public void setPartements(ArrayList<Partement> partements) {
        this.partements = partements;
    }

    @NonNull
    @Override
    public PartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

     View V= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_par_layout,null,false);
       PartViewHolder viewHolder=new PartViewHolder(V);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull PartViewHolder holder, int position) {
      Partement p=partements.get(position);
      if(p.getImage()!=null && !p.getImage().isEmpty())
         holder.iv_image.setImageURI(Uri.parse(p.getImage()));
        holder.nb_chambre.setText(p.getNombre_de_chambre()+"");
         holder.tv_prix.setText(p.getPrix_part()+"");
         holder.superficier.setText(p.getSuperficier());
         //holder.iv_image.setTag(p.getId());
        holder.id=p.getId();

    }
    @Override
    public int getItemCount() {
        return partements.size();
    }
    class PartViewHolder extends RecyclerView.ViewHolder
     {
         TextView nb_chambre,superficier;
         ImageView iv_image;
         TextView tv_prix;
         int id;
         public PartViewHolder(@NonNull View itemView) {
             super(itemView);
             tv_prix=itemView.findViewById(R.id.custom_par_iv_prix);
             iv_image=itemView.findViewById(R.id.custom_par_iv);
             nb_chambre=itemView.findViewById(R.id.custom_par_iv_Nb_chambre);
             superficier=itemView.findViewById(R.id.custom_par_iv_Superficier);
             itemView.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                    // int id=(int) iv_image.getTag();
                     listener.OnItemclick(id);
                 }
             });
         }
     }
}
