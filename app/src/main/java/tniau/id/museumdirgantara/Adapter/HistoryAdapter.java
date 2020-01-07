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

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import tniau.id.museumdirgantara.DetailHistoryActivity;
import tniau.id.museumdirgantara.Function.DatabaseHelper;
import tniau.id.museumdirgantara.Model.History;
import tniau.id.museumdirgantara.NewsActivity;
import tniau.id.museumdirgantara.R;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder>{

    public List<History> data;
    public HistoryAdapter(){}
    public DatabaseHelper mDatabase;
    Context context;
    public void setData(List<History> data){
        this.data = data;
    }

    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        mDatabase =  new DatabaseHelper(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View wordView = inflater.inflate(R.layout.item_sejarah,parent,false);
        ViewHolder viewHolder = new ViewHolder(wordView,context);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(HistoryAdapter.ViewHolder holder, int position) {
        final History historyModel = data.get(position);
        holder.judulSejarah.setText(historyModel.getJudul_sejarah());
        String deskripsinew = historyModel.getDeskripsi_sejarah().replace("/n", " ");
        holder.deskripsiSejarah.setText(deskripsinew);

        Resources res = context.getResources();
        String mDrawableName = historyModel.getGambar_sejarah();
        int resourceId = res.getIdentifier(mDrawableName , "drawable", context.getPackageName());
        Drawable drawable = res.getDrawable(resourceId);
        holder.gambarSejarah.setImageDrawable(drawable );

        holder.itemSejarah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailHistoryActivity.class);
                intent.putExtra("judul",historyModel.getJudul_sejarah());
                intent.putExtra("deskripsi",historyModel.getDeskripsi_sejarah());
                intent.putExtra("gambar",historyModel.getGambar_sejarah());
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
        public LinearLayout itemSejarah;
        public TextView judulSejarah,deskripsiSejarah;
        public ImageView gambarSejarah;
        public ViewHolder(View itemView, final Context context) {
            super(itemView);
            this.context = context;
            itemSejarah = itemView.findViewById(R.id.item_sejarah);
            judulSejarah = itemView.findViewById(R.id.judul_sejarah);
            deskripsiSejarah = itemView.findViewById(R.id.deskripsi_sejarah);
            gambarSejarah = itemView.findViewById(R.id.gambar_sejarah);

            final DatabaseHelper db = new DatabaseHelper(context);
            db.openDatabase();
        }

    }
}
