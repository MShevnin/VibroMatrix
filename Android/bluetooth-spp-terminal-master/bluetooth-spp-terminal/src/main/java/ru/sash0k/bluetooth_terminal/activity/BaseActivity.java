package ru.sash0k.bluetooth_terminal.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;

import org.drinkless.td.libcore.telegram.Client;
import org.drinkless.td.libcore.telegram.TG;
import org.drinkless.td.libcore.telegram.TdApi;

import java.io.File;

import ru.sash0k.bluetooth_terminal.R;
import ru.sash0k.bluetooth_terminal.Utils;
import ru.sash0k.bluetooth_terminal.mobi.core.ApiClient;
import ru.sash0k.bluetooth_terminal.mobi.core.handlers.BaseHandler;
import ru.sash0k.bluetooth_terminal.mobi.core.handlers.GetStateHandler;
import ru.sash0k.bluetooth_terminal.mobi.core.handlers.UpdatesHandler;
import ru.sash0k.bluetooth_terminal.mobi.model.holder.DataHolder;

/**
 * Общий базовый класс. Инициализация BT-адаптера
 * Created by sash0k on 09.12.13.
 */
public abstract class BaseActivity extends Activity  {

    // Intent request codes
    static final int REQUEST_CONNECT_DEVICE = 1;
    static final int REQUEST_ENABLE_BT = 2;

    // Message types sent from the DeviceConnector Handler
    public static final int MESSAGE_STATE_CHANGE = 1;
    public static final int MESSAGE_READ = 2;
    public static final int MESSAGE_WRITE = 3;
    public static final int MESSAGE_DEVICE_NAME = 4;
    public static final int MESSAGE_TOAST = 5;

    BluetoothAdapter btAdapter;
    Client client;

    private static final String SAVED_PENDING_REQUEST_ENABLE_BT = "PENDING_REQUEST_ENABLE_BT";
    // do not resend request to enable Bluetooth
    // if there is a request already in progress
    // See: https://code.google.com/p/android/issues/detail?id=24931#c1
    boolean pendingRequestEnableBt = false;
    // ==========================================================================

    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);
        getActionBar().setHomeButtonEnabled(false);

        if (state != null) {
            pendingRequestEnableBt = state.getBoolean(SAVED_PENDING_REQUEST_ENABLE_BT);
        }
        btAdapter = BluetoothAdapter.getDefaultAdapter();
        if (btAdapter == null) {
            final String no_bluetooth = getString(R.string.no_bt_support);
            showAlertDialog(no_bluetooth);
            Utils.log(no_bluetooth);
        }

      /*  TG.setDir(getApplicationContext().getCacheDir().getAbsolutePath());
        client = TG.getClientInstance();

        TdApi.AuthSetPhoneNumber phoneMessage = new TdApi.AuthSetPhoneNumber("+79127617110");
        client.send(phoneMessage, new Client.ResultHandler() {
            @Override
            protected void finalize() throws Throwable {
                super.finalize();
            }

            @Override
            public void onResult(TdApi.TLObject object) {
                String code = "94176"; // received code from Telegram

                TdApi.AuthSetCode codeMessage = new TdApi.AuthSetCode(code);
                client.send(codeMessage, new Client.ResultHandler() {
                    @Override
                    public void onResult(TdApi.TLObject object) {

                    }
                });


            }
        });*/

        //                TdApi.InputMessageContent msg = new TdApi.InputMessageText("Hello!");
//                //"https://t.me/joinchat/AAAAAEKcBWnrI6zWx5wx_w"
//
//                TdApi.SendMessage request = new TdApi.SendMessage(, 0, false, false, null, msg);
//                client.send(request, new Client.ResultHandler() {
//                    @Override
//                    public void onResult(TdApi.TLObject object) {
//
//                    }
//                });

    }
    // ==========================================================================


    @Override
    public void onStart() {
        super.onStart();
        if (btAdapter == null) return;

        if (!btAdapter.isEnabled() && !pendingRequestEnableBt) {
            pendingRequestEnableBt = true;
            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
        }


    }
    // ==========================================================================


    @Override
    public synchronized void onResume() {
        super.onResume();
    }
    // ==========================================================================


    @Override
    public synchronized void onPause() {
        super.onPause();
    }
    // ==========================================================================


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(SAVED_PENDING_REQUEST_ENABLE_BT, pendingRequestEnableBt);
    }
    // ==========================================================================


    /**
     * Проверка адаптера
     *
     * @return - true, если готов к работе
     */
    boolean isAdapterReady() {
        return (btAdapter != null) && (btAdapter.isEnabled());
    }
    // ==========================================================================


    /**
     * Показывает диалоговое окно с предупреждением.
     * TODO: При переконфигурациях будет теряться
     *
     * @param message - сообщение
     */
    void showAlertDialog(String message) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle(getString(R.string.app_name));
        alertDialogBuilder.setMessage(message);
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

}
