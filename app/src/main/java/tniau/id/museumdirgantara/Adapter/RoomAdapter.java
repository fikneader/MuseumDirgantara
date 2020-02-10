package tniau.id.museumdirgantara.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import tniau.id.museumdirgantara.Function.DatabaseHelper;
import tniau.id.museumdirgantara.Activity.ListRoomActivity;
import tniau.id.museumdirgantara.Model.Room;
import tniau.id.museumdirgantara.R;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.ViewHolder>{
    public List<Room> data;
    public RoomAdapter(){}
    public DatabaseHelper mDatabase;
    Context context;
    public void setData(List<Room> data){
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        mDatabase =  new DatabaseHelper(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View wordView = inflater.inflate(R.layout.item_room,parent,false);
        ViewHolder viewHolder = new ViewHolder(wordView,context);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Room roomModel = data.get(position);
        holder.namaRuangan.setText(roomModel.getNama_ruangan());
        holder.deskripsiRuangan.setText(roomModel.getDeskripsi_ruangan());

        Resources res = context.getResources();
        String mDrawableName = roomModel.getGambar_ruangan();
        int resourceId = res.getIdentifier(mDrawableName , "drawable", context.getPackageName());
        Drawable drawable = res.getDrawable(resourceId);
        holder.gambarRuangan.setImageDrawable(drawable );

        holder.itemRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ListRoomActivity.class);
                intent.putExtra("id",roomModel.getId_ruangan());
                intent.putExtra("nama",roomModel.getNama_ruangan());
                intent.putExtra("deskripsi",roomModel.getDeskripsi_ruangan());
                intent.putExtra("gambar",roomModel.getGambar_ruangan());
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
        public RelativeLayout itemRoom;
        public Context context;
        public TextView namaRuangan,deskripsiRuangan;
        public ImageView gambarRuangan;
        public ViewHolder(View itemView, final Context context) {
            super(itemView);
            this.context = context;
            namaRuangan = itemView.findViewById(R.id.nama_ruangan);
            deskripsiRuangan = itemView.findViewById(R.id.deskripsi_ruangan);
            gambarRuangan = itemView.findViewById(R.id.gambar_ruangan);
            itemRoom = itemView.findViewById(R.id.item_room);

            final DatabaseHelper db = new DatabaseHelper(context);
            db.openDatabase();
        }

    }
}
