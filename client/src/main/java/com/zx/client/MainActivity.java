package com.zx.client;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.List;

import com.zx.aidldemo.IMyAidlInterface;
import com.zx.aidldemo.Person;

public class MainActivity extends AppCompatActivity {
    private IMyAidlInterface aidl;
    private TextView mTv;
    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d("TAG","onServiceConnected");
            // 得到相应的我们定义的AIDL接口的代理对象, 然后可以执行相应的方法
            aidl = IMyAidlInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d("TAG","onServiceConnected");
            aidl = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        autuBindService();
        mTv = (TextView) findViewById(R.id.text);
        Button btn= (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    List<Person> persons = aidl.add(new Person("zx",27));
                    Log.d("TAG",persons.toString());
                    mTv.setText(persons.toString());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void autuBindService() {
        Intent intent = new Intent();
        //5.0 以后要求显式调用 Service，所以我们无法通过 action 或者 filter 的形式调用 Service
        intent.setComponent(new ComponentName("com.zx.aidldemo","com.zx.aidldemo.IRemoteService"));
        bindService(intent,conn, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(conn);
    }
}
