package com.rxdroid.extensecalc.provider;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.rxdroid.data.realmmodels.RealmTransaction;
import com.rxdroid.data.realmmodels.RealmUser;
import com.rxdroid.extensecalc.model.Transaction;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.subscriptions.Subscriptions;

/**
 * Created by rxdroid on 4/16/16.
 */
public final class RealmLoader {

    private File EXPORT_REALM_PATH = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
    private String EXPORT_REALM_FILE_NAME = "expensecalc.realm";
    private String IMPORT_REALM_FILE_NAME = "default.realm"; // Eventually replace this if you're using a custom db name
    private static final String TAG = "RealmLoader";

    private Subscription mSubscription = Subscriptions.empty();

    private Realm mRealm;
    private Observable<Realm> mRealmObservable;
    private RealmResults<RealmTransaction> mRealmTransactions;

    @Inject
    public RealmLoader(Context context) {
        // Create a RealmConfiguration which is to locate Realm file in package's "files" directory.
        RealmConfiguration realmConfig = new RealmConfiguration.Builder(context)
                .schemaVersion(1)
                .build();

        //TODO add Realm migration
    /*
     https://github.com/realm/realm-java/blob/master/examples/migrationExample/src/main/java/io/realm/examples/realmmigrationexample/model/Migration.java
     try {
            Realm.migrateRealm(config0, new Migration());
        } catch (FileNotFoundException ignored) {
            // If the Realm file doesn't exist, just ignore.
        }*/


        // Get a Realm instance for this thread
        mRealm = Realm.getInstance(realmConfig);
        mRealmObservable = mRealm.asObservable();
        mRealmTransactions = mRealm.where(RealmTransaction.class).findAll();

    }

    @SuppressWarnings("unchecked")
    public void execute(Observable observable, Subscriber subscriber) {
        Log.d(TAG, "execute");
        mSubscription = observable
                .subscribe(subscriber);
    }

    public void unsubscribe() {
        if (!mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }


    public long getNextUserKey() {
        Number lastPrimaryKey = mRealm.where(RealmUser.class).max("id");
        if (lastPrimaryKey != null) {
            return lastPrimaryKey.longValue();
        }
        return 0;
    }

    public long getNextTransactionKey() {
        Number lastPrimaryKey = mRealm.where(RealmTransaction.class).max("id");
        if (lastPrimaryKey != null) {
            return lastPrimaryKey.longValue();
        }
        return 0;
    }

    public void persistUser(RealmUser realmUser) {
        mRealm.beginTransaction();
        mRealm.copyToRealm(realmUser);
        mRealm.commitTransaction();
        Log.d(TAG, "initializePersistUser - done!");
    }

    public void updateUser(RealmUser realmUser) {
        mRealm.beginTransaction();
        mRealm.copyToRealmOrUpdate(realmUser);
        mRealm.commitTransaction();
        Log.d(TAG, "updateUser - done!");
    }


    public Observable<RealmUser> getLoadUserObservable(long userId) {
        return mRealm.where(RealmUser.class)
                .equalTo("id", userId)
                .findFirstAsync()
                .asObservable();
    }


    public void getAddTransactionObservable(Transaction transaction) {
        Observable.just(transaction)
                .map(new Func1<Transaction, RealmTransaction>() {
                    @Override
                    public RealmTransaction call(Transaction transaction) {
                        return Transaction.convertToRealm(transaction);
                    }
                })
                .map(new Func1<RealmTransaction, Object>() {
                    @Override
                    public Object call(RealmTransaction realmTransaction) {
                        mRealm.beginTransaction();
                        mRealm.copyToRealmOrUpdate(realmTransaction);
                        mRealm.commitTransaction();
                        return null;
                    }
                });

    }

/*    public boolean addExpense(final RealmTransaction expense) {


        boolean isSuccess = false;
        //RealmUser realmUser = loadUser(userId);
        mRealm.beginTransaction();
        //realmUser.getExpenseList().add(expense);
        mRealm.copyToRealmOrUpdate(expense);
        mRealm.commitTransaction();
        return true;
    }


    public void addIncome(RealmTransaction income, long userId) {
        RealmUser realmUser = loadUser(userId);
        mRealm.beginTransaction();
        realmUser.getIncomeList().add(income);
        mRealm.copyToRealmOrUpdate(realmUser);
        mRealm.commitTransaction();
    }*/


    /**
     * http://stackoverflow.com/questions/28478987/how-to-view-my-realm-file-in-the-realm-browser
     *
     * @param context
     * @return
     */
    public File export(Context context) {
        File exportRealmFile = null;
        Log.d(TAG, "Realm DB Path = " + mRealm.getPath());
        try {
            EXPORT_REALM_PATH.mkdirs();
            // create a backup file
            exportRealmFile = new File(EXPORT_REALM_PATH, EXPORT_REALM_FILE_NAME);
            // if backup file already exists, delete it
            exportRealmFile.delete();
            // copy current realm to backup file
            mRealm.writeCopyTo(exportRealmFile);
        } catch (IOException e) {
            Log.e(TAG, "IOException", e);
        }
        mRealm.close();
        return exportRealmFile;
    }

    public void restore(Context context) {
        //Restore
        String restoreFilePath = EXPORT_REALM_PATH + "/" + EXPORT_REALM_FILE_NAME;

        Log.d(TAG, "oldFilePath = " + restoreFilePath);

        copyBundledRealmFile(context, restoreFilePath, IMPORT_REALM_FILE_NAME);
        Log.d(TAG, "Data restore is done");
    }

    /**
     * https://medium.com/glucosio-project/example-class-to-export-import-a-realm-database-on-android-c429ade2b4ed#.sxwrotcsc
     *
     * @param context
     * @param oldFilePath
     * @param outFileName
     * @return
     */
    private String copyBundledRealmFile(Context context, String oldFilePath, String outFileName) {
        try {
            File file = new File(context.getApplicationContext().getFilesDir(), outFileName);
            FileOutputStream outputStream = new FileOutputStream(file);
            FileInputStream inputStream = new FileInputStream(new File(oldFilePath));
            byte[] buf = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buf)) > 0) {
                outputStream.write(buf, 0, bytesRead);
            }
            outputStream.close();
            return file.getAbsolutePath();
        } catch (IOException e) {
            Log.e(TAG, "IOException", e);
        }
        return null;
    }


}
