package se.mah.k3.larmen.com.essemmess;

import java.util.ArrayList;

import se.k3.goransson.andreas.essemmesslib.Essemmess;
import se.k3.goransson.andreas.essemmesslib.EssemmessHelper;
import se.k3.goransson.andreas.essemmesslib.EssemmessListener;
import se.k3.goransson.andreas.essemmesslib.EssemmessLoginEvent;
import se.k3.goransson.andreas.essemmesslib.EssemmessPublishEvent;
import se.k3.goransson.andreas.essemmesslib.EssemmessReadEvent;
import se.k3.goransson.andreas.essemmesslib.Post;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class WallScreen extends ListActivity implements EssemmessListener {

	public final Essemmess mServer = EssemmessHelper.getServer(this);
	public String filter ="";
	public String[] posts;	
	public ArrayList<Post> postHat;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		ListView lv = getListView();
		lv.setTextFilterEnabled(true);
		  
		lv.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Toast.makeText(WallScreen.this, postHat.get(arg2).toString(), Toast.LENGTH_SHORT).show();
				
			}

					
			
		});
		  
		  
		  
		  mServer.addEssemmessEventListener(this);
		  mServer.read(filter);	
	 
	}

	
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		mServer.read(filter);		  
			
	}

	

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		
		AlertDialog.Builder alert = new AlertDialog.Builder(this);

		alert.setTitle("Filter");
		alert.setMessage("Set tag filter");

		// Set an EditText view to get user input 
		final EditText input = new EditText(this);
		alert.setView(input);

		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int whichButton) {
		  String value = input.getText().toString();
		   // Do something with value!
		  
		  filter = value;
		  mServer.read(filter);
		  
		  }
		});

		alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
		  public void onClick(DialogInterface dialog, int whichButton) {
		    // Canceled.
		  }
		});

		alert.show();
		
		return super.onPrepareOptionsMenu(menu);
		
	}


	@Override
	public void NewEssemmessPosts(EssemmessReadEvent evt) {
		// TODO Auto-generated method stub
		
		//Toast.makeText(this, "NewEssemmessPosts", Toast.LENGTH_LONG).show();
		
		postHat = evt.getPosts();
			
		posts = new String[evt.getPosts().size()];
		for(int i = 0; i < evt.getPosts().size(); i++){
			posts[i] = evt.getPosts().get(i).getMessage();
		}
		
		
		setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, posts));
		
	}

	@Override
	public void NewEssemmessLogin(EssemmessLoginEvent evt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void NewEssemmessPublish(EssemmessPublishEvent evt) {
		// TODO Auto-generated method stub
		
	}

}
