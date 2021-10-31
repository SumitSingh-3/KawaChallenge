package com.app.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.app.R;
import com.app.databinding.AdapterCardBinding;
import com.app.modal.user.User;

import java.util.List;

public class AdapterCarousel extends RecyclerView.Adapter<AdapterCarousel.ViewHolder> {

    private List<User> data;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdapterCardBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.adapter_card, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }


    public void updateList(List<User> data) {
        this.data = data;
        notifyDataSetChanged();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        AdapterCardBinding binding;

        public ViewHolder(AdapterCardBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(User user) {
            binding.setItem(user);
        }
    }
}
