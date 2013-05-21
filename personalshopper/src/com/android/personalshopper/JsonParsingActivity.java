package com.android.personalshopper;

import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;

import com.android.datatypes.Article;
import com.android.datatypes.Entity;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Json parsing activity. This class is in charge of retrieving the Json string
 * from a server's url and instanciate a new entity object from it.
 * 
 * @author Ignacio Mulas - 17-05-2013 - Initial version
 * 
 */
public class JsonParsingActivity extends Activity {

	// Server's url to make request
	private static final String URL = "http://mycorner.bugs3.com/Jsonmysql.php";

	private Runnable getDataThread;

	/**
	 * Helper method to instantiate an entity class
	 * 
	 * @param clazz
	 *            - Class to instantiate
	 * @return - instance
	 */
	private <T extends Entity> T instantiateType(Class<T> clazz) {
		ObjectMapper mapper = new ObjectMapper();
		// read JSON from a file
		T entity = null;
		try {
			// TODO: read from server!
			// entity = mapper.readValue(URL, clazz);
			InputStream is = getAssets().open("article.json");
			entity = mapper.readValue(is, clazz);
		} catch (JsonParseException e1) {
			Log.e("Parsing error", e1.getCause().toString());
		} catch (JsonMappingException e1) {
			Log.e("Parsing error", e1.getCause().toString());
		} catch (IOException e1) {
			Log.e("Parsing error", e1.getCause().toString());
		}
		return entity;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO : Remove Strictmode
		StrictMode.enableDefaults();

		super.onCreate(savedInstanceState);
		setContentView(R.layout.product_view);

		Log.d("JSON", "Starting Thread...");

		getDataThread = new Runnable() {
			@Override
			public void run() {
				// TODO: Methods prepared for generalizing and using the parser
				// for more types of entities
				Log.d("Instantiation", "Starting instantiation");
				Article article = instantiateType(Article.class);
				// InformationManager.put(article);
				Log.d("Instantiation", article.toString());
			}
		};

		getDataThread.run();
	}
}