package com.app.ui.adapter;

import com.app.modal.user.User;

public interface AdapterListener {
    void onItemClick(User user, int position);
    void onItemClick(int position);
}
