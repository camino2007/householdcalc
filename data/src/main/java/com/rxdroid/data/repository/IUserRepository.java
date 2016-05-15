package com.rxdroid.data.repository;

import com.rxdroid.data.realmmodels.RealmUser;
import com.rxdroid.data.repository.impl.OnUserRepoCallback;

/**
 * Created by rxdroid on 5/15/16.
 */
public interface IUserRepository {

    void setCallback(OnUserRepoCallback userRepoCallback);

    void addMainUser(RealmUser user);

    void addChildUser(RealmUser childUser);

    void deleteChildUserById(long childUserId);

    void getChildUserById(long childUserId);

    void getMainUserById(long userId);

}
