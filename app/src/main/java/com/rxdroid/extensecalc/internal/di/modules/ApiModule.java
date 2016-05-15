package com.rxdroid.extensecalc.internal.di.modules;


import com.rxdroid.data.RealmService;
import com.rxdroid.data.repository.impl.TransactionRepository;
import com.rxdroid.data.repository.impl.UserRepository;
import com.rxdroid.extensecalc.internal.di.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by robert on 21.03.16.
 */
@Module
public class ApiModule {

    @Provides
    @PerActivity
    UserRepository provideUserRepository(RealmService realmService){
        return new UserRepository(realmService);
    }

    @Provides
    @PerActivity
    TransactionRepository provideTransactionRepository(RealmService realmService){
        return new TransactionRepository(realmService);
    }

/*    @Provides
    @PerActivity
    EventLoader provideEventApiLoader(Retrofit retrofit) {
        return new EventLoader(retrofit);
    }

    @Provides
    @PerActivity
    AccountLoader provideAccountApiLoader(Retrofit retrofit) {
        return new AccountLoader(retrofit);
    }

    @Provides
    @PerActivity
    BetLoader provideBetApiLoader(Retrofit retrofit) {
        return new BetLoader(retrofit);
    }*/

/*    @Provides
    @PerActivity
    ConnectionChangeReceiver provideConnectionChangeReceiver(NetworkUtil networkUtil) {
        return new ConnectionChangeReceiver(networkUtil);
    }*/

}
