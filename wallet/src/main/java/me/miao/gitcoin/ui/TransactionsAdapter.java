package me.miao.gitcoin.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import org.bitcoinj.core.Sha256Hash;

import java.util.EnumSet;
import java.util.List;

public class TransactionsAdapter extends ListAdapter<TransactionsAdapter.ListItem, RecyclerView.ViewHolder>{

    public TransactionsAdapter(final  Context context, final int maxConnectPeers, final  @Nullable OnClickListener onClickListener){
        super(new DiffUtil.ItemCallback<ListItem>() {
            @Override
            public boolean areItemsTheSame(ListItem oldItem, ListItem newItem) {
                /* TODO */
                return false;
            }

            @Override
            public boolean areContentsTheSame(ListItem oldItem, ListItem newItem) {
                return false;
            }
        });

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    public static class ListItem {
    }

    public interface OnClickListener {
        void onTransactionClick(View view, Sha256Hash transactionHash);

        void onTransactionMenuClick(View view, Sha256Hash transactionHash);

        void onWarningClick(View view);
    }

    private enum ChangeType{
        CONFIDENCE, TIME, ADDRESS, FEE, VALUE, FIAT, MESSAGE, IS_SELECTED
    }
    public static class ItemAnimator extends DefaultItemAnimator{
        @Override
        public boolean canReuseUpdatedViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, @NonNull List<Object> payloads) {
            for (final Object payload : payloads) {
                final EnumSet<TransactionsAdapter.ChangeType> changes = (EnumSet<TransactionsAdapter.ChangeType>) payload;
                if (changes.contains(ChangeType.IS_SELECTED)) {
                    return  false;
                }
            }
            return super.canReuseUpdatedViewHolder(viewHolder, payloads);
        }
    }
}
