package com.android.personalshopper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.android.adapter.LazyAdapter;
import com.android.data.retrievers.Locator;
import com.android.data.types.Article;
import com.android.ui.animations.SwipeListViewTouchListener;
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
		private List<Article> instantiateArticle() {
			ObjectMapper mapper = new ObjectMapper();
			List<Article> articleList = null;
			try {
				// TODO: read from server!
				// entity = mapper.readValue(URL, clazz);
				InputStream is = getAssets().open("article.json");
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
	private Locator locator;
	private List<GetArticle> threads;

	private void executeMap() {
		Intent i = new Intent(this, MapView.class);
		startActivity(i);
	}

	/**
	 * Thread starter. Just provide the number of threads to launch.
	 * 
	 * @param numberOfThreads
	 */
	private void startThreads(int numberOfThreads) {
		for (int i = 0; i < numberOfThreads; i++) {
			GetArticle articleThread = new GetArticle();
			articleThread.run();
			threads.add(articleThread);
		}

		for (GetArticle thread : threads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.articles_view);

		locator = new Locator(getApplicationContext());
		Location location = locator.getBestLocation();
		Log.d("ArticleView", location.toString());

		articleList = Collections.synchronizedList(new ArrayList<Article>());
		threads = new ArrayList<GetArticle>();

		startThreads(3);

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

		// Create a ListView-specific touch listener. ListViews are given
		// special treatment because
		// by default they handle touches for their list items... i.e. they're
		// in charge of drawing
		// the pressed state (the list selector), handling list item clicks,
		// etc.
		SwipeListViewTouchListener touchListener = new SwipeListViewTouchListener(
				list, new SwipeListViewTouchListener.OnSwipeCallback() {
					@Override
					public void onSwipeLeft(ListView listView,
							int[] reverseSortedPositions) {
						// Log.i(this.getClass().getName(),
						// "swipe left : pos="+reverseSortedPositions[0]);
						// TODO : Remove the row from the view!
						// TODO : While swiping change color and set a "delete"
						// message
					}

					@Override
					public void onSwipeRight(ListView listView,
							int[] reverseSortedPositions) {
						// Log.i(ProfileMenuActivity.class.getClass().getName(),
						// "swipe right : pos="+reverseSortedPositions[0]);
						// TODO : Go to see the ARticle location
						// TODO: Download shop information and display a
						// mrker with shop's information together with your
						// current position
						// TODO: while swiping change color and set something
						// like "Go!"
					}
				}, true, // example : left action = dismiss
				false); // example : right action without dismiss animation

		list.setOnTouchListener(touchListener);
		// Setting this scroll listener is required to ensure that during
		// ListView scrolling, we don't look for swipes.
		list.setOnScrollListener(touchListener.makeScrollListener());
	}
}