package com.android.personalshopper;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.android.adapter.LazyAdapter;
import com.android.data.manager.DataManager;
import com.android.data.retrievers.Locator;
import com.android.ui.animations.SwipeListViewTouchListener;

/**
 * This class is the main view for the articles. ¨
 * 
 * @author Carlos Andres
 * @author Ignacio Mulas
 * 
 */
public class ArticleListView extends Activity {

	// Server's url to make request
	private static final String URL = "http://mycorner.bugs3.com/Jsonmysql.php";

	private LazyAdapter adapter;
	private ListView list;
	private Locator locator;
	private DataManager manager;

	private void executeMap() {
		Intent i = new Intent(this, MapView.class);
		startActivity(i);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.article_list_view);

		locator = new Locator(getApplicationContext());
		Location location = locator.getBestLocation();
		// Log.d("ArticleView", location.toString());

		manager = new DataManager(getApplicationContext());
		manager.getArticles(3);

		list = (ListView) findViewById(R.id.list);
		adapter = new LazyAdapter(this, manager.getArticlesList());
		list.setAdapter(adapter);

		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent i = new Intent(ArticleListView.this, ArticleView.class);
				TextView brand = (TextView) view.findViewById(R.id.brand);
				TextView type = (TextView) view.findViewById(R.id.type);
				TextView size = (TextView) view.findViewById(R.id.size);
				TextView colour = (TextView) view.findViewById(R.id.colour);
				TextView shop = (TextView) view.findViewById(R.id.shop);
				TextView address = (TextView) view.findViewById(R.id.address);
				TextView prize = (TextView) view.findViewById(R.id.prize);

				String brandString = brand.getText().toString();
				String typeString = type.getText().toString();
				String sizeString = size.getText().toString();
				String colourString = colour.getText().toString();
				String shopString = shop.getText().toString();
				String addressString = address.getText().toString();
				String prizeString = prize.getText().toString();

				long articleId = manager.getArticlesList().get(position)
						.getId();

				i.putExtra("id", articleId);
				i.putExtra("brand", brandString);
				i.putExtra("type", typeString);
				i.putExtra("size", sizeString);
				i.putExtra("colour", colourString);
				i.putExtra("shop", shopString);
				i.putExtra("address", addressString);
				i.putExtra("prize", prizeString);

				startActivity(i);

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
						// manager.getShop(shopId);
						// Log.d("JSON", shop.toString());
						// TODO: Download shop information and display a
						// mrker with shop's information together with your
						// current position
						// TODO: while swiping change color and set something
						// like "Go!"
					}
				}, true, // example : left action = dismiss
				false); // example : right action without dismiss animation

		list.setOnTouchListener(touchListener);
		// finish();
		// Setting this scroll listener is required to ensure that during
		// ListView scrolling, we don't look for swipes.
		list.setOnScrollListener(touchListener.makeScrollListener());
	}
}