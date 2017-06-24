package com.yourcompany.facebooksignin;

import android.content.Intent;
import android.os.Bundle;

import io.flutter.app.FlutterActivity;
import android.util.Log;

import java.util.Arrays;

import io.flutter.app.FlutterActivity;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.PluginRegistry.Registrar;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

/**
 * Created by bramvanbilsen on 24/06/17.
 */

public class FacebookSignInActivity extends FlutterActivity {

    static CallbackManager callbackManager;

    FacebookSignInActivity(CallbackManager callback) {
        this.callbackManager = callback;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onActivityResult(int requestCode, int responseCode,
                                    Intent data) {
        super.onActivityResult(requestCode, responseCode, data);
        callbackManager.onActivityResult(requestCode, responseCode, data);
    }

}
