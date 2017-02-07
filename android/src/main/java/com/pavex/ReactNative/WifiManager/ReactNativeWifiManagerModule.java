/**
 * @author Pavex <pavex@ines.cz>
 * Many thanks for Ray Gläske for base code of getWifiList and startScan
 *   from (https://github.com/Haargeeel/react-native-wifi-checker)
 */
package com.pavex.ReactNative.WifiManager;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;





/**
 */
public class ReactNativeWifiManagerModule extends ReactContextBaseJavaModule {


	public ReactNativeWifiManagerModule(ReactApplicationContext reactContext) {
		super(reactContext);
	}





	@Override
	public String getName() {
		return "ReactNativeWifiManager";
	}





	@ReactMethod
	public void getScanResults(Promise promise) {
		StringBuilder response = new StringBuilder();
		WifiManager wifiManager = (WifiManager) getReactApplicationContext().getSystemService(Context.WIFI_SERVICE);

		WritableArray scanResults = Arguments.createArray();
		for (int i = 0; i < wifiManager.getScanResults().size(); i++) {
			ScanResult result = wifiManager.getScanResults().get(i);
			WritableMap map = Arguments.createMap();
			map.putString("SSID", result.SSID);
			map.putString("BSSID", result.BSSID);
			map.putString("capabilities", result.capabilities);
			map.putInt("frequency", result.frequency);
			map.putInt("level", WifiManager.calculateSignalLevel(result.level, 100));
			map.putInt("rssi", result.level);
			map.putDouble("timestamp", result.timestamp);
//			map.putString("operatorFriendlyName", getString(result.operatorFriendlyName));
//			map.putString("venueName", getString(result.venueName));
//			map.putInt("centerFreq0", result.centerFreq0);
//			map.putInt("centerFreq1", result.centerFreq1);
//			map.putInt("channelWidth", result.channelWidth);
			scanResults.pushMap(map);
		}
		if (scanResults.size() > 0) {
			promise.resolve(scanResults);
		}
		else {
			promise.reject("No found wifi.");
		}
	}





	@ReactMethod
	public void startScan(Promise promise) {
		WifiManager wifiManager = (WifiManager) getReactApplicationContext().getSystemService(Context.WIFI_SERVICE);
		if (wifiManager.startScan()) {
			promise.resolve(true);
		}
		else {
			promise.reject("Can not scan for wifi.");
		}
	}





	@ReactMethod
	public void isEnabled(Promise promise) {
		WifiManager wifiManager = (WifiManager) getReactApplicationContext().getSystemService(Context.WIFI_SERVICE);
		if (wifiManager.isWifiEnabled()) {
			promise.resolve(true);
		}
		else {
			promise.reject("Wifi is disable.");
		}
	}





	@ReactMethod
	public void setEnabled(Boolean enabled, Promise promise) {
		WifiManager wifiManager = (WifiManager) getReactApplicationContext().getSystemService(Context.WIFI_SERVICE);
		if (wifiManager.setWifiEnabled(enabled)) {
			promise.resolve(true);
		}
		else {
			promise.reject("Can not change wifi state.");
		}
	}


}
