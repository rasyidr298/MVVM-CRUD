package id.rrdev.mvvmroomdatabase.view.webService;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import id.rrdev.mvvmroomdatabase.R;
import id.rrdev.mvvmroomdatabase.data.network.response.Room;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.ViewHolder> {

    private List<Room> roomsList = new ArrayList<>();
    private Callback.ItemClick itemClick;
    private Callback.ItemLongClick itemLongClick;

    public void submitList(List<Room> list){
        roomsList.clear();
        roomsList.addAll(list);
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(Callback.ItemClick callback){
        itemClick = callback;
    }

    public void setOnItemLongClickListener(Callback.ItemLongClick callback){
        itemLongClick = callback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_room, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.bind(roomsList.get(position));

        if (itemClick != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClick.onItemClick(v, roomsList.get(position), position);
                }
            });
        }

        if (itemLongClick != null){
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return itemLongClick.onItemLongClick(v, roomsList.get(position), position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return roomsList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvNamaRoom, tvKapasitasRoom;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            tvNamaRoom = itemView.findViewById(R.id.tvNamaRoomHome);
            tvKapasitasRoom = itemView.findViewById(R.id.tvKapasitasRoomHome);
        }

        public void bind(Room room){
            tvNamaRoom.setText(room.getNamaRoom());
            tvKapasitasRoom.setText(room.getKapasitas());
        }
    }

    interface Callback {
        interface ItemClick {
            void onItemClick(View view, Room room, int position);
        }
        interface ItemLongClick {
            Boolean onItemLongClick(View view, Room room, int position);
        }
    }
}
