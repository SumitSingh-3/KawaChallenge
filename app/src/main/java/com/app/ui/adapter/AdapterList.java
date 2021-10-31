package com.app.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.app.R;
import com.app.databinding.AdapterListBinding;
import com.app.modal.user.User;

import java.util.List;

public class AdapterList extends RecyclerView.Adapter<AdapterList.ViewHolder> {

    private List<User> data;
    AdapterListener adapterListener;
    private int selectedPos = 0;

    public AdapterList(AdapterListener adapterListener){
        this.adapterListener = adapterListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdapterListBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.adapter_list, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(data.get(position), position == selectedPos);
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0 ;
    }


    public void updateList(List<User> data){
        this.data = data;
        notifyDataSetChanged();
    }

    public void updatePosition(int pos){
        notifyItemChanged(selectedPos);
        selectedPos = pos;
        notifyItemChanged(pos);
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        AdapterListBinding binding;

        public ViewHolder(AdapterListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(User user, boolean status){
            binding.setItem(user);
            binding.setActive(status);

            binding.cardView.setOnClickListener(view -> adapterListener.onItemClick(user, getAdapterPosition()));
        }
    }
}
