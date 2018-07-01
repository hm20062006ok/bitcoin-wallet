package me.miao.gitcoin.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import me.miao.gitcoin.R;

public final class WalletAddressFragment extends Fragment {

    private CardView currentAddressQrCardView;
    private ImageView currentAddressQrView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.wallet_address_fragment, container, false);
        currentAddressQrView = view.findViewById(R.id.bitcoin_address_qr);

        currentAddressQrCardView = view.findViewById(R.id.bitcoin_address_qr_card);
        currentAddressQrCardView.setCardBackgroundColor(Color.WHITE);
        currentAddressQrCardView.setPreventCornerOverlap(false);
        currentAddressQrCardView.setUseCompatPadding(false);
        currentAddressQrCardView.setMaxCardElevation(0);

        return view;
    }

}
