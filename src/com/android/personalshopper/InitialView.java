package com.android.personalshopper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Welcome screen!
 * 
 * @author Carlos Andres
 * @author Ignacio Mulas
 * 
 */
public class InitialView extends Activity {

	public void executeListView(View view) {
		Intent i = new Intent(this, ArticleListView.class);
		startActivity(i);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.initial_view);
	}

}
