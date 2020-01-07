package tniau.id.museumdirgantara.Adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import tniau.id.museumdirgantara.Function.DatabaseHelper;
import tniau.id.museumdirgantara.Model.ListRoom;
import tniau.id.museumdirgantara.R;

public class ListRoomAdapter extends RecyclerView.Adapter<ListRoomAdapter.ViewHolder>{

    public List<ListRoom> data;
    public ListRoomAdapter(){}
    public DatabaseHelper mDatabase;
    Context context;
    public void setData(List<ListRoom> data){
        this.data = data;
    }

    @Override
    public ListRoomAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        mDatabase =  new DatabaseHelper(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View wordView = inflater.inflate(R.layout.item_room,parent,false);
        ViewHolder viewHolder = new ViewHolder(wordView,context);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ListRoomAdapter.ViewHolder holder, int position) {
        ListRoom roomDetailModel = data.get(position);
        holder.namaRuangan.setText(roomDetailModel.getNama_ruangan());
        holder.deskripsiRuangan.setText(roomDetailModel.getDeskripsi_ruangan());
//
        Resources res = context.getResources();
        String mDrawableName = roomDetailModel.getGambar_ruangan();
        int resourceId = res.getIdentifier(mDrawableName , "drawable", context.getPackageName());
        Drawable drawable = res.getDrawable(resourceId);
        holder.gambarRuangan.setImageDrawable(drawable );

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public Context context;
        public TextView namaRuangan,deskripsiRuangan;
        public ImageView gambarRuangan;
        public ViewHolder(View itemView, final Context context) {
            super(itemView);
            this.context = context;
            namaRuangan = itemView.findViewById(R.id.nama_ruangan);
            deskripsiRuangan = itemView.findViewById(R.id.deskripsi_ruangan);
            gambarRuangan = itemView.findViewById(R.id.gambar_ruangan);

            final DatabaseHelper db = new DatabaseHelper(context);
            db.openDatabase();
        }

    }

}
