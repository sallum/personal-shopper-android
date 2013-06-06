package com.android.personalshopper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.android.adapter.LazyAdapter;
import com.android.datatypes.Article;
import com.android.datatypes.Entity;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * This class is the main view for the articles. ¨
 * 
 * @author Carlos Andres - 23-06-2013 - Initial prototype view with XML
 * @author Ignacio Mulas - 17-05-2013 - linked view with JSON
 * 
 */
public class ArticleListView extends Activity {

	// Server's url to make request
	private static final String URL = "http://mycorner.bugs3.com/Jsonmysql.php";

	private LazyAdapter adapter;
	private ListView list;

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
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// TODO: at the moment we only have one article in the list, make it
		// general!

		final List<Article> articleList = new ArrayList<Article>();

		// TODO: Move this to a separate class
		Log.d("JSON", "Starting Thread...");
		final List<Thread> threads = new ArrayList<Thread>();
		for (int i = 0; i < 5; i++) {
			final Thread thread = new Thread() {
				@Override
				public void run() {
					// TODO: Methods prepared for generalizing and using the
					// parser
					// for more types of entities
					Log.d("Instantiation", "Starting instantiation");
					Article article = instantiateType(Article.class);
					articleList.add(article);
					Log.d("Instantiation", article.toString());

				}
			};
			thread.run();
			threads.add(thread);
		}

		for (Thread thread : threads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		list = (ListView) findViewById(R.id.list);
		adapter = new LazyAdapter(this, articleList);
		list.setAdapter(adapter);

		// Click event for single list row
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
			}
		});
	}
}