package ru.sash0k.bluetooth_terminal.mobi.core.handlers;


import android.app.FragmentManager;
import android.util.Log;

import org.drinkless.td.libcore.telegram.TdApi;

import ru.sash0k.bluetooth_terminal.mobi.ui.fragments.fragmentDialogs.DialogAuthKeyInvalid;
import ru.sash0k.bluetooth_terminal.mobi.ui.fragments.fragmentDialogs.DialogAuthKeyUnregistered;
import ru.sash0k.bluetooth_terminal.mobi.ui.fragments.fragmentDialogs.DialogCodeUnexpected;
import ru.sash0k.bluetooth_terminal.mobi.ui.fragments.fragmentDialogs.DialogFirstNameInvalid;
import ru.sash0k.bluetooth_terminal.mobi.ui.fragments.fragmentDialogs.DialogLastNameInvalid;
import ru.sash0k.bluetooth_terminal.mobi.ui.fragments.fragmentDialogs.DialogPhoneCodeEmpty;
import ru.sash0k.bluetooth_terminal.mobi.ui.fragments.fragmentDialogs.DialogPhoneCodeExpired;
import ru.sash0k.bluetooth_terminal.mobi.ui.fragments.fragmentDialogs.DialogPhoneCodeInvalid;
import ru.sash0k.bluetooth_terminal.mobi.ui.fragments.fragmentDialogs.DialogPhoneNumberInvalid;
import ru.sash0k.bluetooth_terminal.mobi.ui.fragments.fragmentDialogs.DialogPhoneNumberOccupied;
import ru.sash0k.bluetooth_terminal.mobi.ui.fragments.fragmentDialogs.DialogPhoneNumberUnoccupied;
import ru.sash0k.bluetooth_terminal.mobi.ui.fragments.fragmentDialogs.DialogSessionExpired;
import ru.sash0k.bluetooth_terminal.mobi.ui.fragments.fragmentDialogs.DialogSessionRevoked;
import ru.sash0k.bluetooth_terminal.mobi.ui.fragments.fragmentDialogs.DialogUserDeactivated;

public class ErrorHandler {

    private FragmentManager fm;
    private TdApi.Error error;

    public ErrorHandler(FragmentManager fm, TdApi.Error error) {
        this.fm = fm;
        this.error = error;
        handle();
    }

    private void handle() {
        Log.e("Log", "ErrorHandler: " + error.toString());

        if ((error.code == 8 && error.text.contains("UNEXPECTED"))) {
            DialogCodeUnexpected dialogCodeUnexpected = new DialogCodeUnexpected();
            dialogCodeUnexpected.show(fm, "UNEXPECTED");
        }

        if ((error.code == 400 && error.text.contains("PHONE_NUMBER_INVALID"))) {
            DialogPhoneNumberInvalid dialogPhoneNumberInvalid = new DialogPhoneNumberInvalid();
            dialogPhoneNumberInvalid.show(fm, "PHONE_NUMBER_INVALID");
        }

        if ((error.code == 400 && error.text.contains("PHONE_CODE_HASH_EMPTY"))) {
            Log.e("Log", "PHONE_CODE_HASH_EMPTY " + error);
        }

        if ((error.code == 400 && error.text.contains("PHONE_CODE_EMPTY"))) {
            DialogPhoneCodeEmpty phoneCodeEmpty = new DialogPhoneCodeEmpty();
            phoneCodeEmpty.show(fm, "PHONE_CODE_EMPTY");
        }

        if ((error.code == 400 && error.text.contains("PHONE_CODE_EXPIRED"))) {
            DialogPhoneCodeExpired phoneCodeExpired = new DialogPhoneCodeExpired();
            phoneCodeExpired.show(fm, "PHONE_CODE_EXPIRED");
        }

        if ((error.code == 400 && error.text.contains("PHONE_NUMBER_OCCUPIED"))) {
            DialogPhoneNumberOccupied phoneNumberOccupied = new DialogPhoneNumberOccupied();
            phoneNumberOccupied.show(fm, "PHONE_NUMBER_OCCUPIED");
        }

        if ((error.code == 400 && error.text.contains("PHONE_NUMBER_UNOCCUPIED"))) {
            DialogPhoneNumberUnoccupied phoneNumberUnoccupied = new DialogPhoneNumberUnoccupied();
            phoneNumberUnoccupied.show(fm, "PHONE_NUMBER_UNOCCUPIED");
        }

        if ((error.code == 400 && error.text.contains("FIRSTNAME_INVALID"))) {
            DialogFirstNameInvalid firstNameInvalid = new DialogFirstNameInvalid();
            firstNameInvalid.show(fm, "FIRSTNAME_INVALID");
        }

        if ((error.code == 400 && error.text.contains("LASTNAME_INVALID"))) {
            DialogLastNameInvalid lastNameInvalid = new DialogLastNameInvalid();
            lastNameInvalid.show(fm, "LASTNAME_INVALID");
        }

        if ((error.code == 400 && error.text.contains("PHONE_CODE_INVALID"))) {
            DialogPhoneCodeInvalid phoneCodeInvalid = new DialogPhoneCodeInvalid();
            phoneCodeInvalid.show(fm, "PHONE_CODE_INVALID");

        }

        if ((error.code == 400 && error.text.contains("AUTH_KEY_UNREGISTERED"))) {
            DialogAuthKeyUnregistered authKeyUnregistered = new DialogAuthKeyUnregistered();
            authKeyUnregistered.show(fm, "AUTH_KEY_UNREGISTERED");
        }
        if ((error.code == 401 && error.text.contains("AUTH_KEY_INVALID"))) {
            DialogAuthKeyInvalid dialogAuthKeyInvalid = new DialogAuthKeyInvalid();
            dialogAuthKeyInvalid.show(fm, "AUTH_KEY_INVALID");
        }


        if ((error.code == 401 && error.text.contains("USER_DEACTIVATED"))) {
            DialogUserDeactivated userDeactivated = new DialogUserDeactivated();
            userDeactivated.show(fm, "USER_DEACTIVATED");
        }

        if ((error.code == 401 && error.text.contains("SESSION_REVOKED"))) {
            DialogSessionRevoked sessionRevoked = new DialogSessionRevoked();
            sessionRevoked.show(fm, "SESSION_REVOKED");
        }

        if ((error.code == 401 && error.text.contains("SESSION_EXPIRED"))) {
            DialogSessionExpired sessionExpired = new DialogSessionExpired();
            sessionExpired.show(fm, "SESSION_EXPIRED");
        }

//        if ((error.code == 420 && error.text.contains("FLOOD_WAIT"))) {
//            FragmentTransaction fragmentTransaction = fm.beginTransaction();
//            ReceiverCodeFragment receiverCodeFragment = new ReceiverCodeFragment();
//            fragmentTransaction.replace(R.id.fragmentContainer, receiverCodeFragment);
//            fragmentTransaction.addToBackStack(null);
//            fragmentTransaction.commit();
//            DialogFloodWait dialogFloodWait = new DialogFloodWait();
//            dialogFloodWait.show(fm, "FLOOD_WAIT");
//        }

        if ((error.code == 401 && error.text.contains("ACTIVE_USER_REQUIRED"))) {
            Log.e("Log", "ACTIVE_USER_REQUIRED " + error);
        }

        if ((error.code == 401 && error.text.contains("ACTIVE_USER_REQUIRED"))) {
            Log.e("Log", "AUTH_KEY_PERM_EMPTY " + error);
        }
    }

}
