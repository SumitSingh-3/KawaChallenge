package com.app.utlity;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

public class SnapHelper extends PagerSnapHelper {

    private OnSnapPositionCallBack onSnapPositionCallBack;

    public SnapHelper(OnSnapPositionCallBack onSnapPositionCallBack) {
        this.onSnapPositionCallBack = onSnapPositionCallBack;
    }

    @Override
    public int findTargetSnapPosition(RecyclerView.LayoutManager layoutManager, int velocityX, int velocityY) {

        if (!(layoutManager instanceof RecyclerView.SmoothScroller.ScrollVectorProvider)) {
            return RecyclerView.NO_POSITION;
        }

        final View currentView = findSnapView(layoutManager);

        if (currentView == null) {
            return RecyclerView.NO_POSITION;
        }

        LinearLayoutManager myLayoutManager = (LinearLayoutManager) layoutManager;

        int position1 = myLayoutManager.findFirstVisibleItemPosition();
        int position2 = myLayoutManager.findLastVisibleItemPosition();

        int currentPosition = layoutManager.getPosition(currentView);

        if (velocityX > 400) {
            currentPosition = position2;
        } else if (velocityX < 400) {
            currentPosition = position1;
        }

        onSnapPositionCallBack.onSnapPositionChange(currentPosition);

        return currentPosition;
    }

    public interface OnSnapPositionCallBack {
        void onSnapPositionChange(int position);
    }
}