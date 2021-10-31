package com.app.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.app.R;
import com.app.databinding.AdapterMenuItemBinding;
import com.app.utlity.AppUtil;

public class AdapterMenu extends RecyclerView.Adapter<AdapterMenu.ViewHolder> {

    private int selectedPos = -1;
    AdapterListener itemClickListener;

    public AdapterMenu(AdapterListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdapterMenuItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.adapter_menu_item, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(AppUtil.MENU_ITEM.get(position), position == selectedPos);
    }

    @Override
    public int getItemCount() {
        return AppUtil.MENU_ITEM.size();
    }

    public void updateSelectedPos(int pos) {
        selectedPos = pos;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        AdapterMenuItemBinding binding;

        public ViewHolder(AdapterMenuItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(String item, boolean selected) {
            binding.tvMenuItem.setText(item);
            binding.setSelected(selected);

            switch (item) {
                case "Product":
                    binding.icIcon.setImageResource(R.drawable.ic_menu_product);
                    break;
                case "Pricing":
                    binding.icIcon.setImageResource(R.drawable.ic_menu_price);
                    break;
                case "Download":
                    binding.icIcon.setImageResource(R.drawable.ic_menu_download);
                    break;
            }

            binding.item.setOnClickListener(v -> itemClickListener.onItemClick(getAdapterPosition()));
        }
    }
}
