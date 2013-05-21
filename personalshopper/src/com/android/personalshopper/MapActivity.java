package com.android.personalshopper;

import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * This class contains the map activity
 * 
 * @author Ignacio Mulas - 17-05-2013 - Initial version
 * 
 */
public class MapActivity extends android.support.v4.app.FragmentActivity
		implements LocationListener {

	/**
	 * Map object
	 */
	private static GoogleMap mMap;

	/**
	 * Initial location
	 */
	// TODO: Add location dynamically when starts
	private static final LatLng STOCKHOLM = new LatLng(59.332434, 18.06295);

	/**
	 * Only prompt the settings the first time
	 */
	private boolean hasAsked = false;

	private Button locationButton;

	/**
	 * Object that will contain the location information
	 */
	private LocationManager service;

	/**
	 * Check if GPS is active or not and prompt the screen to enable if needed
	 */
	private void checkIfGPSIsActive() {

		// Check if enabled and if not send user to the GSP settings
		// Better solution would be to display a dialog and suggesting to
		// go to the settings
		if (!service.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
			Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
			startActivity(intent);
		}
	}

	/**
	 * Called each time the Activity is used in order to initialize the button.
	 */
	private void initLocationButton() {

		locationButton = (Button) findViewById(R.id.button_Location);
		locationButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				service = (LocationManager) getSystemService(LOCATION_SERVICE);

				if (!hasAsked) {
					hasAsked = true;
					checkIfGPSIsActive();
				}

				locateMe();
			}
		});

	}

	/**
	 * This is where we can add markers or lines, add listeners or move the
	 * camera. In this case, we just add a marker near Africa.
	 * <p>
	 * This should only be called once and when we are sure that {@link #mMap}
	 * is not null.
	 */
	private void setUpMap() {
		mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(STOCKHOLM, 13));
	}

	/**
	 * Sets up the map if it is possible to do so (i.e., the Google Play
	 * services APK is correctly installed) and the map has not already been
	 * instantiated.. This will ensure that we only ever call
	 * {@link #setUpMap()} once when {@link #mMap} is not null.
	 * <p>
	 * If it isn't installed {@link SupportMapFragment} (and
	 * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt
	 * for the user to install/update the Google Play services APK on their
	 * device.
	 * <p>
	 * A user can return to this Activity after following the prompt and
	 * correctly installing/updating/enabling the Google Play services. Since
	 * the Activity may not have been completely destroyed during this process
	 * (it is likely that it would only be stopped or paused),
	 * {@link #onCreate(Bundle)} may not be called again so we should call this
	 * method in {@link #onResume()} to guarantee that it will be called.
	 */
	private void setUpMapIfNeeded() {
		// Do a null check to confirm that we have not already instantiated the
		// map.
		if (mMap == null) {
			// Try to obtain the map from the SupportMapFragment.
			mMap = ((SupportMapFragment) getSupportFragmentManager()
					.findFragmentById(R.id.map)).getMap();
			// Check if we were successful in obtaining the map.
			if (mMap != null) {
				setUpMap();
			}
		}
	}

	/**
	 * Method for locating myself
	 */
	protected void locateMe() {
		Criteria criteria = new Criteria();
		Location location = service.getLastKnownLocation(service
				.getBestProvider(criteria, false));

		// Initialize the location fields
		if (location != null) {
			onLocationChanged(location);
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map_view);
		setUpMapIfNeeded();
		initLocationButton();

	}

	@Override
	protected void onResume() {
		super.onResume();
		setUpMapIfNeeded();
		initLocationButton();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.map_menu, menu);
		return true;
	}

	@Override
	public void onLocationChanged(Location location) {
		LatLng myPosition = new LatLng(location.getLatitude(),
				location.getLongitude());
		mMap.addMarker(new MarkerOptions().position(myPosition));
		mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myPosition, 13));
	}

	@Override
	public void onProviderDisabled(String provider) {
		Toast.makeText(this, "Disabled provider " + provider,
				Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onProviderEnabled(String provider) {
		Toast.makeText(this, "Enabled new provider " + provider,
				Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO: La aplication falla cuando la quitas y la pones dos veces...
		// mirar a ver como lo podemos arreglar! :=)
	}

}
