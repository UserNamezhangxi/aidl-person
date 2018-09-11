package com.zx.aidldemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/9/11.
 */

public class IRemoteService extends Service {
    private ArrayList<Person> persons;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        persons = new ArrayList<Person>();
        return mBinder;
    }

    private IBinder mBinder = new IMyAidlInterface.Stub(){

        @Override
        public List<Person> add(Person person) throws RemoteException {
            persons.add(person);
            return persons;
        }
    };
}
