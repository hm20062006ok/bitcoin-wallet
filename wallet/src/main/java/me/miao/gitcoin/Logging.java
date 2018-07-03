package me.miao.gitcoin;

import java.io.File;

public class Logging {

    private static final String LOG_DIRECTORY_NAME = "log";
    private static final String LOG_FILE_NAME = "wallet.log";
    private static final String LOG_ROLLING_FILE_NAME_PATTERN = "wallet.%d{yyyy-MM-dd, UTC}.log.gz";

    private static File logFile;
    public  static synchronized void init(final File filesDir){
        if (logFile != null) {
            return;
        }
//        new File()
    }
}
