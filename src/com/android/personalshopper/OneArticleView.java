package com.android.personalshopper;

import com.android.data.retrievers.ImageLoader;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class OneArticleView extends Activity {
	private TextView Brand;
	private ImageLoader imageLoader;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.one_article_view);
		//imageLoader = new ImageLoader(activity.getApplicationContext());

		
		
		Brand = (TextView) findViewById(R.id.brandOneArticle);
		Bundle bundle = getIntent().getExtras();
		Brand.setText(bundle.getString("brand"));
		//ImageView thumb_image = (ImageView) findViewById(R.id.imageOneArticle);
		//imageLoader.displayImage(article.getImage(), thumb_image);
	}

}
