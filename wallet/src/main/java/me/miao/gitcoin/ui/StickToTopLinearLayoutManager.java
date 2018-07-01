package me.miao.gitcoin.ui;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class StickToTopLinearLayoutManager extends LinearLayoutManager {

    public StickToTopLinearLayoutManager(final Context context) {
        super(context);
    }

    @Override
    public void onItemsAdded(RecyclerView recyclerView, int positionStart, int itemCount) {
        super.onItemsAdded(recyclerView, positionStart, itemCount);
        if (positionStart == 0 && findFirstCompletelyVisibleItemPosition() <= itemCount) {
            scrollToPosition(0);
        }
    }
}
