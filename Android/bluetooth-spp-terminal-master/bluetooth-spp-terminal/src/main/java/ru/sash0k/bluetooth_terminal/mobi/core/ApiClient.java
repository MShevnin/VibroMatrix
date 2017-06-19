package ru.sash0k.bluetooth_terminal.mobi.core;

import android.os.AsyncTask;
import android.widget.Toast;


import org.drinkless.td.libcore.telegram.Client;
import org.drinkless.td.libcore.telegram.TG;
import org.drinkless.td.libcore.telegram.TdApi;

import ru.sash0k.bluetooth_terminal.R;
import ru.sash0k.bluetooth_terminal.mobi.core.handlers.BaseHandler;
import ru.sash0k.bluetooth_terminal.mobi.model.Const;
import ru.sash0k.bluetooth_terminal.mobi.model.Enums;
import ru.sash0k.bluetooth_terminal.mobi.model.holder.DataHolder;
import ru.sash0k.bluetooth_terminal.mobi.utils.Utils;


public class ApiClient<TFunction extends TdApi.TLFunction, THandler extends BaseHandler> extends AsyncTask<Void, Integer, THandler> {

    private static final long THREAD_SLEEP_TIME = 150;
    private static final int TIMEOUT = 50;
    private TFunction function;
    private THandler handler;
    private OnApiResultHandler resultHandler;

    public ApiClient(TFunction func, THandler handl, OnApiResultHandler listener) {
        function = func;
        handler = handl;
        resultHandler = listener;
    }

    @Override
    protected THandler doInBackground(Void... params) {
        Client client = TG.getClientInstance();
        if (Utils.isOnline()) {
            DataHolder.clearCountNoInternetToast();
            if (client != null) {
                client.send(function, handler);
                for (int i = 0; i < TIMEOUT; i++) {
                    if (handler.hasAnswer()) {
                        return handler;
                    }
                    try {
                        Thread.sleep(THREAD_SLEEP_TIME);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        return null;
                    }
                }
                return null;
            }
        } else {
            this.cancel(true);
        }
        return null;
    }

    @Override
    protected void onPostExecute(THandler tOutput) {
        super.onPostExecute(tOutput);
        if (resultHandler != null) {
            resultHandler.onApiResult(tOutput);
        }
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        if (DataHolder.getCountNoInternetToast() % 5 == 0) {
            Toast.makeText(DataHolder.getContext(), R.string.no_internet_toast, Toast.LENGTH_SHORT).show();
        }
        DataHolder.setCountNoInternetToast();
    }

    public interface OnApiResultHandler {
        void onApiResult(BaseHandler output);
    }
}

