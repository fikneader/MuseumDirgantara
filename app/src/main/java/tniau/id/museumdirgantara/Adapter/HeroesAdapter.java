package tniau.id.museumdirgantara.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import tniau.id.museumdirgantara.DetailHeroesActivity;
import tniau.id.museumdirgantara.Function.DatabaseHelper;
import tniau.id.museumdirgantara.Function.Tools;
import tniau.id.museumdirgantara.Model.Heroes;
import tniau.id.museumdirgantara.R;

public class HeroesAdapter extends RecyclerView.Adapter<HeroesAdapter.ViewHolder>{

    public List<Heroes> data;
    public HeroesAdapter(){}
    public DatabaseHelper mDatabase;
    Context context;
    public void setData(List<Heroes> data){
        this.data = data;
    }

    @Override
    public HeroesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        mDatabase =  new DatabaseHelper(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View wordView = inflater.inflate(R.layout.item_heroes,parent,false);
        ViewHolder viewHolder = new ViewHolder(wordView,context);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(HeroesAdapter.ViewHolder holder, int position) {
        final Heroes heroesModel = data.get(position);
        holder.namaPahlawan.setText(heroesModel.getNama_pahlawan());
        String deskripsinew = heroesModel.getDeskripsi_pahlawan().replace("/n", " ");
        holder.deskripsiPahlawan.setText(deskripsinew);

        Resources res = context.getResources();
        String mDrawableName = heroesModel.getGambar_pahlawan();
        int resourceId = res.getIdentifier(mDrawableName , "drawable", context.getPackageName());
        Drawable drawable = res.getDrawable(resourceId);
//        holder.gambarPahlawan.setImageDrawable(drawable );

        Tools.displayImageRound(context, holder.gambarPahlawan,resourceId);

        holder.itemPahlawan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailHeroesActivity.class);
                intent.putExtra("nama",heroesModel.getNama_pahlawan());
                intent.putExtra("deskripsi",heroesModel.getDeskripsi_pahlawan());
                intent.putExtra("gambar",heroesModel.getGambar_pahlawan());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public Context context;
        public LinearLayout itemPahlawan;
        public TextView namaPahlawan,deskripsiPahlawan;
        public ImageView gambarPahlawan;
        public ViewHolder(View itemView, final Context context) {
            super(itemView);
            this.context = context;
            itemPahlawan = itemView.findViewById(R.id.item_heroes);
            namaPahlawan = itemView.findViewById(R.id.nama_pahlawan);
            deskripsiPahlawan = itemView.findViewById(R.id.deskripsi_pahlawan);
            gambarPahlawan = itemView.findViewById(R.id.gambar_pahlawan);

            final DatabaseHelper db = new DatabaseHelper(context);
            db.openDatabase();
        }

    }
}
