package com.example.prueba;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.android.personalshopper.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
 
public class CustomizedListView extends Activity {
    // All static variables
    static final String URL = "http://api.androidhive.info/music/music.xml";
    // XML node keys
    static final String KEY_SONG = "song"; // parent node
    static final String KEY_ID = "id";
    static final String KEY_TITLE = "title";
    static final String KEY_ARTIST = "artist";
    static final String KEY_DURATION = "duration";
    static final String KEY_THUMB_URL = "thumb_url";
 
    ListView list;
    LazyAdapter adapter;
 
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
 
        ArrayList<HashMap<String, String>> songsList = new ArrayList<HashMap<String, String>>();
 
        DocumentBuilder docBuilderFactory;
		try {
			docBuilderFactory = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = docBuilderFactory.parse(getAssets().open("music.xml"));
			doc.getDocumentElement ().normalize ();
			
			NodeList nl = doc.getElementsByTagName(KEY_SONG);
			
			// looping through all song nodes &lt;song&gt;
	        for (int i = 0; i < nl.getLength(); i++) {
	            // creating new HashMap
	            HashMap<String, String> map = new HashMap<String, String>();
	            Element e = (Element) nl.item(i);
	            // adding each child node to HashMap key =&gt; value
	            //KEY_ID
	            NodeList idList = e.getElementsByTagName(KEY_ID);
	            Element firstIdElement = (Element)idList.item(0);
	            NodeList textIdList = firstIdElement.getChildNodes();
	            //--id
	            map.put(KEY_ID,((Node)textIdList.item(0)).getNodeValue().trim());
	            
	            //KEY_TITLE
	            NodeList titleList = e.getElementsByTagName(KEY_TITLE);
	            Element firstTitleElement = (Element)titleList.item(0);
	            NodeList textTitleList = firstTitleElement.getChildNodes();
	            //--title
	            map.put(KEY_TITLE,((Node)textTitleList.item(0)).getNodeValue().trim());
	            
	            //KEY_ARTIST
	            NodeList artistList = e.getElementsByTagName(KEY_ARTIST);
	            Element firstArtistElement = (Element)artistList.item(0);
	            NodeList textArtistList = firstArtistElement.getChildNodes();
	            //--artist
	            map.put(KEY_ARTIST,((Node)textArtistList.item(0)).getNodeValue().trim());

	            //KEY_DURATION
	            NodeList durationList = e.getElementsByTagName(KEY_DURATION);
	            Element firstDurationElement = (Element)durationList.item(0);
	            NodeList textDurationList = firstDurationElement.getChildNodes();
	            //--duration
	            map.put(KEY_DURATION,((Node)textDurationList.item(0)).getNodeValue().trim());

	            //KEY_THUMB_URL
	            NodeList thumbList = e.getElementsByTagName(KEY_THUMB_URL);
	            Element firstThumbElement = (Element)thumbList.item(0);
	            NodeList textThumbList = firstThumbElement.getChildNodes();
	            //--image
	            map.put(KEY_THUMB_URL,((Node)textThumbList.item(0)).getNodeValue().trim());

	            // adding HashList to ArrayList
	            songsList.add(map);
	        }
			
			
		} catch (ParserConfigurationException e1) {
	
			e1.printStackTrace();
		} catch (IOException e1) {

			e1.printStackTrace();
		} catch (SAXException e1) {

			e1.printStackTrace();
		}

        list=(ListView)findViewById(R.id.list);
 
        // Getting adapter by passing xml data ArrayList
        adapter=new LazyAdapter(this, songsList);
        list.setAdapter(adapter);
 
        // Click event for single list row
        list.setOnItemClickListener(new OnItemClickListener() {
 
        @Override
        public void onItemClick(AdapterView<?>parent, View view, int position, long id) {}});
    }
}