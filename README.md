# Wifi manager for React Native

Simple wifi manager primary for scanning existing networks. This supports list with scan and wifi enable checking and enabler.
This plugin now available for Android only.


## Installation

```
npm install --save pavex-react-native-wifi-manager
```

## Methods

#### `WifiManager.getScanResults();` 

Retrieves a list of the available networks as an array of objects.
Returned the following list structure:

```js
    scanResult = [
        {   
            "SSID": ssid,                 // SSID as string
            "BSSID": bssid,               // MAC address of WiFi router as string
            "capabilities": capabilities, // Describes the authentication, etc.
            "frequency": frequency,       // frequency of the access point channel in MHz
            "rssi": rssi_level,           // raw RSSI value
            "level": signal_level         // RSSI value in percent
        },
        {
            ...
        }
    ]
```

#### `WifiManager.startScan();` 

Call befoe getWifiList to retrieves current list of available networks.

#### `WifiManager.isEnabled();` 

Retrieves the current wifi status and passes `true` or `false` over Promise.

#### `WifiManager.setEnabled(enabled);` 

Set wifi status.


## Examples

Check if wifi service is enabled.
```js
WifiManager.isEnabled()
  .then((enabled) => {
    console.log('Wifi is enabled.');
  })
  .catch((error) => {
    console.warn(error);
  });
```

Scan available wifi networks

```js
WifiManager.startScan()
  .then((scanned) => {
     WifiManager.getScanResults()
       .then((scanResult) => {
         console.log(scanResult);
       })
       .catch((error) => {
         console.warn(error);
       });
    })
    .catch((error) => {
      console.warn(error);
    });
```
