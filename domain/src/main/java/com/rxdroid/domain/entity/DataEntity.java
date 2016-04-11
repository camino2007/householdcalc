package com.rxdroid.domain.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by rxdroid on 4/10/16.
 */
public class DataEntity {

    @SerializedName("months") List<MonthEntity> mMonthEntityList;

    public List<MonthEntity> getMonthEntityList() {
        return mMonthEntityList;
    }
}
