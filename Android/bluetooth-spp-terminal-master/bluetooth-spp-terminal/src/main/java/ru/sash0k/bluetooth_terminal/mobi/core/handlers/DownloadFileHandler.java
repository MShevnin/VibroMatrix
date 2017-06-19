package ru.sash0k.bluetooth_terminal.mobi.core.handlers;


import org.drinkless.td.libcore.telegram.TdApi;

import ru.sash0k.bluetooth_terminal.mobi.model.Const;

public class DownloadFileHandler extends BaseHandler<TdApi.Ok> {
    public static final int HANDLER_ID = Const.DOWNLOAD_FILE_HANDLER_ID;

    @Override
    public TdApi.Ok resultHandler(TdApi.TLObject object) {
        if (object.getConstructor() == TdApi.Ok.CONSTRUCTOR) {
            return (TdApi.Ok) object;
        }
        return null;
    }

    @Override
    public int getHandlerId() {
        return HANDLER_ID;
    }
}
