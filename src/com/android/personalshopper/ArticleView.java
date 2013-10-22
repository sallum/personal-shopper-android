package com.android.personalshopper;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.memory.MemoryCache;

public class ArticleView extends Activity {
	private TextView address;
	private TextView brand;
	private TextView colour;
	private TextView prize;
	private TextView shop;
	private TextView size;
	private TextView type;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.article_view);

		brand = (TextView) findViewById(R.id.brandOneArticle);
		type = (TextView) findViewById(R.id.typeOneArticle);
		size = (TextView) findViewById(R.id.sizeOneArticle);
		colour = (TextView) findViewById(R.id.colourOneArticle);
		shop = (TextView) findViewById(R.id.shopOneArticle);
		address = (TextView) findViewById(R.id.addressOneArticle);
		prize = (TextView) findViewById(R.id.prizeOneArticle);

		Bundle bundle = getIntent().getExtras();
		brand.setText(bundle.getString("brand"));
		type.setText(bundle.getString("type"));
		size.setText(bundle.getString("size"));
		colour.setText(bundle.getString("colour"));
		shop.setText(bundle.getString("shop"));
		address.setText(bundle.getString("address"));
		prize.setText(bundle.getString("prize"));

		ImageView thumb_image = (ImageView) findViewById(R.id.imageOneArticle);
		thumb_image.setImageBitmap(MemoryCache.get(getIntent().getExtras()
				.getLong("id")));
	}
}
