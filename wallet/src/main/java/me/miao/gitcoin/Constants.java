package me.miao.gitcoin;


import org.bitcoinj.core.Context;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.params.MainNetParams;
import org.bitcoinj.params.TestNet3Params;

// TODO: 7/5/2018  了解 bitcoinj 包
public final class Constants {

    public static final boolean TEST = true;
    public static final NetworkParameters NETWORK_PARAMETERS = TEST ? TestNet3Params.get() : MainNetParams.get();
    public static final Context CONTEXT = new Context(NETWORK_PARAMETERS);

    public final static class Files {
        private static final String FILENAME_NETWORK_SUFFIX = NETWORK_PARAMETERS.getId().equals(NETWORK_PARAMETERS.ID_MAINNET) ? "" : "-testnet";

        //老的备份文件名（只能读）
        public static final String WALLET_KEY_BACKUP_BASE58 = "key-backup-base58" + FILENAME_NETWORK_SUFFIX;
        /** 自动备份的钱包文件名称*/
        public static final String WALLET_KEY_BACKUP_PROTOBUF = "key-backup-protobuf" + FILENAME_NETWORK_SUFFIX;

        /**
         * Filename of the wallet.
         */
        public static final String WALLET_FILENAME_PROTOBUF = "wallet-protobuf" + FILENAME_NETWORK_SUFFIX;
    }
}
