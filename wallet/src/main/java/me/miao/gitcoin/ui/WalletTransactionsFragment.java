package me.miao.gitcoin.ui;


import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;
import android.widget.ViewAnimator;

import org.bitcoinj.core.Sha256Hash;

import me.miao.gitcoin.R;
import me.miao.gitcoin.WalletApplication;

public  final class WalletTransactionsFragment extends Fragment implements TransactionsAdapter.OnClickListener {

    private ViewAnimator viewGroup;
    private RecyclerView recyclerView;
    private TextView emptyView;
    private AbstractWalletActivity activity;
    private TransactionsAdapter adapter;
    private WalletApplication application;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity = (AbstractWalletActivity) context;
        this.application = activity.getWalletApplication();
        adapter = new TransactionsAdapter(activity, application.maxConnectedPeers(), this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.wallet_transactions_fragment, container, false);

        viewGroup = view.findViewById(R.id.wallet_transactions_group);
        viewGroup.setDisplayedChild(2);

        emptyView = (TextView) view.findViewById(R.id.wallet_transactions_empty);

        recyclerView = (RecyclerView) view.findViewById(R.id.wallet_transactions_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new StickToTopLinearLayoutManager(activity));
        recyclerView.setItemAnimator(new TransactionsAdapter.ItemAnimator());
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {

            private final int PADDING = 2 * activity.getResources().getDimensionPixelOffset(R.dimen.card_padding_vertical);
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                final int position = parent.getChildAdapterPosition(view);
                if (position == 0) {
                    outRect.top += PADDING;
                }else if(position == parent.getAdapter().getItemCount() - 1){
                    outRect.bottom += PADDING;
                }

            }
        });
        return view;
    }

    @Override
    public void onTransactionClick(View view, Sha256Hash transactionHash) {

    }

    @Override
    public void onTransactionMenuClick(View view, Sha256Hash transactionHash) {

    }

    @Override
    public void onWarningClick(View view) {

    }
}
