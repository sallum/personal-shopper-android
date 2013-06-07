package com.android.personalshopper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.android.adapter.LazyAdapter;
import com.android.data.types.Article;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
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

	/**
	 * Private class that implements gets the articles information from the
	 * server
	 */
	private class GetArticle extends Thread {

		/**
		 * Helper method to instantiate an entity class
		 * 
		 * @param clazz
		 *            - Class to instantiate
		 * @return - instance
		 */
		// private <T extends Entity> List<T> instantiateType(Class<T> clazz) {
		private List<Article> instantiateArticle() {
			ObjectMapper mapper = new ObjectMapper();
			List<Article> articleList = null;
			try {
				// TODO: read from server!
				// entity = mapper.readValue(URL, clazz);
				InputStream is = getAssets().open("article.json");
				// entity = mapper.readValue(is, clazz);
				articleList = mapper.readValue(is,
						new TypeReference<ArrayList<Article>>() {
						});
			} catch (JsonParseException e1) {
				Log.e("Parsing error", e1.getCause().toString());
			} catch (JsonMappingException e1) {
				Log.e("Parsing error", e1.getCause().toString());
			} catch (IOException e1) {
				Log.e("Parsing error", e1.getCause().toString());
			}
			return articleList;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void run() {
			List<Article> articles = instantiateArticle();
			articleList.addAll(articles);
		}
	}

	// Server's url to make request
	private static final String URL = "http://mycorner.bugs3.com/Jsonmysql.php";
	private LazyAdapter adapter;
	private List<Article> articleList;
	private ListView list;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.articles_view);

		// TODO: at the moment we only have one article in the list, make it
		// general!
		articleList = Collections.synchronizedList(new ArrayList<Article>());

		// TODO: Launch more threads to get more results...
		// List<GetArticle> threads = new ArrayList<GetArticle>();
		// for (int i = 0; i < 5; i++) {
		// GetArticle articleThread = new GetArticle();
		// articleThread.run();
		// threads.add(articleThread);
		// }
		//
		// for (GetArticle thread : threads) {
		// try {
		// thread.join();
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		// }

		GetArticle articleThread = new GetArticle();
		articleThread.run();
		try {
			articleThread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		list = (ListView) findViewById(R.id.list);
		adapter = new LazyAdapter(this, articleList);
		list.setAdapter(adapter);

		// Click event for single list row
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO: GO to item display information
			}
		});
	}
}