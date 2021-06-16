package id.rrdev.mvvmroomdatabase.view.roomDb;

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
import id.rrdev.mvvmroomdatabase.data.database.Note;

/**
 * Created on 6/3/21 by Pengkuh Dwi Septiandi (@pengdst)
 * <p>
 * - Github https://github.com/pengdst
 * - Gitlab https://gitlab.com/pengdst
 * - LinkedIn https://linkedin.com/in/pengdst
 */

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {

    private List<Note> noteList = new ArrayList<>();
    private Callback.ItemClick itemClick;
    private Callback.ItemLongClick itemLongClick;

    public void submitList(List<Note> list){
        noteList.clear();
        noteList.addAll(list);
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
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.bind(noteList.get(position));

        if (itemClick != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClick.onItemClick(v, noteList.get(position), position);
                }
            });
        }

        if (itemLongClick != null){
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return itemLongClick.onItemLongClick(v, noteList.get(position), position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvNoteTitle, tvNoteDesc;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            tvNoteTitle = itemView.findViewById(R.id.tv_note_title);
            tvNoteDesc = itemView.findViewById(R.id.tv_note_desc);
        }

        public void bind(Note note){
            tvNoteTitle.setText(note.getTitle());
            tvNoteDesc.setText(note.getDescription());
        }
    }

    interface Callback {
        interface ItemClick {
            void onItemClick(View view, Note note, int position);
        }
        interface ItemLongClick {
            Boolean onItemLongClick(View view, Note note, int position);
        }
    }
}
