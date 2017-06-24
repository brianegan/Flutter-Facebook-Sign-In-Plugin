package com.yourcompany.facebooksignin;

import android.content.Intent;
import android.os.Bundle;
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
 * FacebookSignInPlugin
 */
public class FacebookSignInPlugin implements MethodCallHandler {

  private FlutterActivity activity;
  CallbackManager callbackManager;
  AccessToken token;

  /**
   * Plugin registration.
   */
  public static void registerWith(Registrar registrar) {
    final MethodChannel channel = new MethodChannel(registrar.messenger(), "facebook_sign_in");
    FacebookSignInPlugin instance = new FacebookSignInPlugin((FlutterActivity) registrar.activity());
    channel.setMethodCallHandler(instance);
  }

  private FacebookSignInPlugin(FlutterActivity activity) {
    this.activity = activity;
  }


  @Override
  public void onMethodCall(MethodCall call, Result result) {
    if (call.method.equals("signInUser")) {
      FacebookSdk.setApplicationId("XXX");
      FacebookSdk.sdkInitialize(this.activity);
      callbackManager = CallbackManager.Factory.create();
      LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {

          token = loginResult.getAccessToken();
          Log.d("Facebook", token.toString());
        }

        @Override
        public void onCancel() {
          System.out.println("cancel");
          Log.d("Facebook", "Cancel");
        }

        @Override
        public void onError(FacebookException error) {

        }
      });
      login();
      result.success(token);
    } else {
      result.notImplemented();
    }
  }

  public void login() {
    LoginManager.getInstance().logInWithReadPermissions(this.activity, Arrays.asList("public_profile", "user_friends"));
  }

  public void logout() {
    LoginManager.getInstance().logOut();
  }
}
