package com.app.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.app.R;
import com.app.databinding.ActivityMainBinding;
import com.app.modal.user.User;
import com.app.ui.adapter.AdapterCarousel;
import com.app.ui.adapter.AdapterList;
import com.app.ui.adapter.AdapterListener;
import com.app.ui.adapter.AdapterMenu;
import com.app.utlity.SnapHelper;
import com.app.viewmodal.MainViewModal;

import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterListener, SnapHelper.OnSnapPositionCallBack {

    ActivityMainBinding binding;

    private MainViewModal viewModel;
    private List<User> userList;
    private AdapterList adapterList;
    private AdapterCarousel adapterCarousel;
    private AdapterMenu adapterMenu;
    private int selectedPosition = 0;
    private int selectedMenuPosition = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        viewModel = new ViewModelProvider(this).get(MainViewModal.class);
        initUI();
        setListener();
        setObserver();
        viewModel.getUserAPI();
    }

    private void initUI() {
        binding.menu.menuMain.setVisibility(View.GONE);
        adapterList = new AdapterList(this);
        adapterCarousel = new AdapterCarousel();
        adapterMenu = new AdapterMenu(this);
        binding.rvList.setAdapter(adapterList);
        SnapHelper snapHelper = new SnapHelper(this);
        snapHelper.attachToRecyclerView(binding.rvCard);
        binding.rvCard.setAdapter(adapterCarousel);
        binding.menu.rvMenu.setAdapter(adapterMenu);
    }

    private void setListener() {
        binding.ivLeftArrow.setOnClickListener(v -> {
            if (selectedPosition > 0) {
                scrollUpdatePosition(selectedPosition - 1);
            }
        });

        binding.ivRightArrow.setOnClickListener(view -> {
            if (selectedPosition < userList.size() - 1) {
                scrollUpdatePosition(selectedPosition + 1);
            }
        });

        binding.toolBar.menuIcon.setOnClickListener(v -> {
            if (binding.menu.menuMain.getVisibility() == View.VISIBLE)
                binding.menu.menuMain.setVisibility(View.GONE);
            else
                binding.menu.menuMain.setVisibility(View.VISIBLE);
        });

        binding.menu.ivCross.setOnClickListener(view -> {
            binding.menu.menuMain.setVisibility(View.GONE);
        });
    }

    private void setObserver() {

        viewModel.userResponse.observe(this, userResponse -> {
            if (!userResponse.results.isEmpty()) {
                userList = userResponse.results;
                setData();
            }
        });

        viewModel.error.observe(this, errors -> {
            Toast.makeText(this, errors.responseMessage, Toast.LENGTH_SHORT).show();
        });

    }


    private void setData() {
        binding.scrollView.setVisibility(View.VISIBLE);
        binding.progress.setVisibility(View.GONE);
        adapterList.updateList(userList);
        adapterCarousel.updateList(userList);
    }

    @Override
    public void onItemClick(User user, int position) {
        binding.scrollView.smoothScrollTo(0, 0);
        scrollUpdatePosition(position);
    }

    @Override
    public void onItemClick(int position) {
        binding.menu.menuMain.setVisibility(View.GONE);
        adapterMenu.updateSelectedPos(position);
        adapterMenu.notifyItemChanged(selectedMenuPosition);
        selectedMenuPosition = position;
        adapterMenu.notifyItemChanged(position);
    }


    @Override
    public void onSnapPositionChange(int position) {
        selectedPosition = position;
        adapterList.updatePosition(position);
    }

    public void scrollUpdatePosition(int position) {
        selectedPosition = position;
        adapterList.updatePosition(position);
        binding.rvCard.smoothScrollToPosition(position);
    }

}
