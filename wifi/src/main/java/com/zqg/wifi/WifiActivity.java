package com.zqg.wifi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiEnterpriseConfig;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiNetworkSpecifier;
import android.net.wifi.WifiNetworkSuggestion;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import static android.net.wifi.WifiManager.STATUS_NETWORK_SUGGESTIONS_SUCCESS;

/**
 * <pre>
 *     author: zhuqiguang
 *     date  : 2020/12/10 16:58
 *     email : qiguang.zhu@foxmail.com
 *     desc  : https://developer.android.com/guide/topics/connectivity/wifi-suggest?hl=zh-cn
 *     https://stackoverflow.com/questions/58091968/connect-to-wifi-android-q
 * </pre>
 */
public class WifiActivity extends AppCompatActivity {

    private WifiManager wifiManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Android Q以上，WifiManager的部分API失效，改用WifiNetworkSpecifier
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    openWifiForQ("WM-Mobile", "testtest", "qiguang.zhu", "Guang123");
                } else {
                    openWifi();
                    connectWifi("WM-Mobile", "testtest", "qiguang.zhu", "test123");
                }

            }
        });
    }

    private void openWifi() {
        if (!wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(true);
        }

    }

    /**
     * android q以下连接wifi,
     *
     * @param ssid       wifi名称
     * @param passWord   wifi密码
     * @param id         企业wifi id
     * @param idPassWord 企业wifi id的密码
     */
    private void connectWifi(final String ssid, String passWord, String id, String idPassWord) {
        WifiEnterpriseConfig wifiEnterpriseConfig = new WifiEnterpriseConfig();
        wifiEnterpriseConfig.setIdentity(id);
        wifiEnterpriseConfig.setAnonymousIdentity(id);
        wifiEnterpriseConfig.setPassword(idPassWord);
        WifiConfiguration wifiConfiguration = new WifiConfiguration();
        wifiConfiguration.SSID = "\"" + ssid + "\"";
        wifiConfiguration.preSharedKey = "\"" + passWord + "\"";
        wifiConfiguration.enterpriseConfig = wifiEnterpriseConfig;
        int networkId = wifiManager.addNetwork(wifiConfiguration);
        wifiManager.disconnect();
        wifiManager.enableNetwork(networkId, true);
        wifiManager.reconnect();
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    private void addWifiSuggestion() {
        WifiNetworkSuggestion.Builder builder = new WifiNetworkSuggestion.Builder()
                .setSsid("YOUR NET NAME")
                .setWpa2Passphrase("YOUR_PASSWORD");
        WifiNetworkSuggestion suggestion = builder.build();

        ArrayList<WifiNetworkSuggestion> list = new ArrayList<>();
        list.add(suggestion);

        WifiManager manager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        int status = manager.addNetworkSuggestions(list);

        if (status == STATUS_NETWORK_SUGGESTIONS_SUCCESS) {
            //We have successfully added our wifi for the system to consider
        }
    }

    /**
     * android q及以上连接wifi,
     *
     * @param ssid       wifi名称
     * @param passWord   wifi密码
     * @param id         企业wifi id
     * @param idPassWord 企业wifi id的密码
     */
    @RequiresApi(api = Build.VERSION_CODES.Q)
    private void openWifiForQ(final String ssid, String passWord, String id, String idPassWord) {
        //企业wifi设置
        WifiEnterpriseConfig wifiEnterpriseConfig = new WifiEnterpriseConfig();
        wifiEnterpriseConfig.setEapMethod(WifiEnterpriseConfig.Eap.PEAP);
        wifiEnterpriseConfig.setIdentity(id);
        wifiEnterpriseConfig.setAnonymousIdentity(id);
        wifiEnterpriseConfig.setPassword(idPassWord);
        wifiEnterpriseConfig.setPhase2Method(WifiEnterpriseConfig.Phase2.NONE);
        wifiEnterpriseConfig.setCaCertificate(null);

        WifiNetworkSpecifier wifiNetworkSpecifier = new WifiNetworkSpecifier.Builder().setSsid(ssid)
                //todo wifi 密码
//                .setWpa2Passphrase(passWord)
                .setWpa2EnterpriseConfig(wifiEnterpriseConfig)
                .build();

        NetworkRequest networkRequest = new NetworkRequest.Builder()
                .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
                .setNetworkSpecifier(wifiNetworkSpecifier)
                .build();

        final ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        ConnectivityManager.NetworkCallback networkCallback = new ConnectivityManager.NetworkCallback() {
            @Override
            public void onUnavailable() {
                super.onUnavailable();
            }

            @Override
            public void onAvailable(@NonNull Network network) {
                super.onAvailable(network);
                connectivityManager.bindProcessToNetwork(network);
            }

            @Override
            public void onLosing(@NonNull Network network, int maxMsToLive) {
                super.onLosing(network, maxMsToLive);
            }

            @Override
            public void onLost(@NonNull Network network) {
                super.onLost(network);
            }
        };
        connectivityManager.requestNetwork(networkRequest, networkCallback);
    }
}
