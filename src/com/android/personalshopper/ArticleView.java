package com.android.personalshopper;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.memory.MemoryCache;

public class ArticleView extends Activity {
	private TextView Brand;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.article_view);

		Brand = (TextView) findViewById(R.id.brandOneArticle);
		Bundle bundle = getIntent().getExtras();
		Brand.setText(bundle.getString("brand"));

		ImageView thumb_image = (ImageView) findViewById(R.id.imageOneArticle);
		thumb_image.setImageBitmap(MemoryCache.get(getIntent().getExtras()
				.getLong("id")));
	}
}
