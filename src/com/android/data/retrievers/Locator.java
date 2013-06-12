package com.android.data.retrievers;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;

/**
 * This class is in charge of handling the user's location
 * 
 * @author Ignacio Mulas
 */
public class Locator {

	private static final String TAG = "Locator";
	private Context context;

	public Locator(Context context) {
		this.context = context;
	}

	/**
	 * Get the last known location from a specific provider (network/gps)
	 */
	private Location getLocationByProvider(String provider) {
		Location location = null;
		if (!isProviderSupported(provider)) {
			return null;
		}
		LocationManager locationManager = (LocationManager) context
				.getSystemService(Context.LOCATION_SERVICE);

		try {
			if (locationManager.isProviderEnabled(provider)) {

				location = locationManager.getLastKnownLocation(provider);

			}
		} catch (IllegalArgumentException e) {
			Log.d(TAG, "Cannot acces Provider " + provider);
		}
		return location;
	}

	/**
	 * Checker to be sure that only GPS or Network are used.
	 * 
	 * @param provider
	 * @return
	 */
	private boolean isProviderSupported(String provider) {
		return LocationManager.GPS_PROVIDER.equals(provider)
				|| LocationManager.NETWORK_PROVIDER.equals(provider);
	}

	/**
	 * Try to get the 'best' location selected from all providers
	 */
	public Location getBestLocation() {
		Location gpslocation = getLocationByProvider(LocationManager.GPS_PROVIDER);
		Location networkLocation = getLocationByProvider(LocationManager.NETWORK_PROVIDER);

		// if we have only one location available, the choice is easy
		if (gpslocation == null) {
			Log.d(TAG, "No GPS Location available.");
			return networkLocation;
		}
		if (networkLocation == null) {
			Log.d(TAG, "No Network Location available");
			return gpslocation;
		}

		// a locationupdate is considered 'old' if its older than the configured
		// update interval. this means, we didn't get a
		// update from this provider since the last check
		// TODO: Update the constanat value to get the preferred by the user
		// long old = System.currentTimeMillis() -
		// getGPSCheckMilliSecsFromPrefs();
		long old = System.currentTimeMillis() - 60000l;
		boolean gpsIsOld = (gpslocation.getTime() < old);
		boolean networkIsOld = (networkLocation.getTime() < old);

		// gps is current and available, gps is better than network
		if (!gpsIsOld) {
			Log.d(TAG, "Returning current GPS Location");
			return gpslocation;
		}

		// gps is old, we can't trust it. use network location
		if (!networkIsOld) {
			Log.d(TAG, "GPS is old, Network is current, returning network");
			return networkLocation;
		}

		// both are old return the newer of those two
		if (gpslocation.getTime() > networkLocation.getTime()) {
			Log.d(TAG, "Both are old, returning gps(newer)");
			return gpslocation;
		} else {
			Log.d(TAG, "Both are old, returning network(newer)");
			return networkLocation;
		}
	}

}
