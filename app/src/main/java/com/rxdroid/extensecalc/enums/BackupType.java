package com.rxdroid.extensecalc.enums;

import android.content.Context;

import com.rxdroid.extensecalc.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by rxdroid on 4/16/16.
 */
public enum BackupType {

    NOT_NOW(R.string.backup_not_now),
    GOOGLE_DRIVE(R.string.backup_google_drive),
    DROP_BOX(R.string.backup_drop_box),
    EMAIL(R.string.backup_email);

    private int mStringResId;

    BackupType(int stringResId) {
        mStringResId = stringResId;
    }

    public int getStringResId() {
        return mStringResId;
    }

    public static List<BackupType> getBackupTypes() {
        return Arrays.asList(BackupType.class.getEnumConstants());
    }

    public static List<String> getBackupTypeStrings(Context context) {
        List<String> strings = new ArrayList<>();
        for (BackupType backupType : getBackupTypes()) {
            strings.add(context.getString(backupType.getStringResId()));
        }
        return strings;
    }
}
