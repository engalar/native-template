// native-template\android\app\src\main\java\com\mendix\nativetemplate\MainApplication.java
package com.mendix.nativetemplate;

import android.app.Activity;
import android.content.Context;

import androidx.annotation.NonNull;

import com.facebook.react.PackageList;
import com.facebook.react.ReactPackage;
import com.mendix.mendixnative.MendixReactApplication;
import com.mendix.mendixnative.react.splash.MendixSplashScreenPresenter;
import com.microsoft.codepush.react.CodePush;

import androidx.multidex.MultiDex;
// import com.pdftron.reactnative.RNPdftronPackage;

import org.devio.rn.splashscreen.SplashScreen;

import java.util.List;

public class MainApplication extends MendixReactApplication {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
    @Override
    public boolean getUseDeveloperSupport() {
        return false;
    }

    @Override
    public List<ReactPackage> getPackages() {
        List<ReactPackage> packages = new PackageList(this).getPackages();
        packages.addAll(new MendixPackageList(this).getPackages());

        // Packages that cannot be autolinked yet can be added manually here, for example:
        // packages.add(new MyReactNativePackage());
        packages.add(new CodePush(getCodePushKey(), getApplicationContext(), BuildConfig.DEBUG));
        // packages.add(new RNPdftronPackage());

        return packages;
    }

    @Override
    public MendixSplashScreenPresenter createSplashScreenPresenter() {
        return new MendixSplashScreenPresenter() {
            @Override
            public void show(@NonNull Activity activity) {
                hide(activity);
                SplashScreen.show(activity, true);
            }

            @Override
            public void hide(@NonNull Activity activity) {
                SplashScreen.hide(activity);
            }
        };
    }
}
