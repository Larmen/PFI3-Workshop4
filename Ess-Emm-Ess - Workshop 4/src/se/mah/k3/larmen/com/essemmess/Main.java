package se.mah.k3.larmen.com.essemmess;

import se.k3.goransson.andreas.essemmesslib.Essemmess;
import se.k3.goransson.andreas.essemmesslib.EssemmessHelper;
import se.k3.goransson.andreas.essemmesslib.EssemmessListener;
import se.k3.goransson.andreas.essemmesslib.EssemmessLoginEvent;
import se.k3.goransson.andreas.essemmesslib.EssemmessPublishEvent;
import se.k3.goransson.andreas.essemmesslib.EssemmessReadEvent;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main extends Activity implements EssemmessListener{
	
	public final Essemmess mServer = EssemmessHelper.getServer(this);

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.main);     
        
        mServer.addEssemmessEventListener(this);
              
        final Button postButton = (Button) findViewById(R.id.post_button);
        final Button readButton = (Button) findViewById(R.id.read_button);
        final EditText messageField = (EditText) findViewById(R.id.messageField);
        final EditText tagField = (EditText) findViewById(R.id.tagField);
        
        postButton.setOnClickListener(new OnClickListener() {
        	public void onClick(View v){
        		//Perform action on click
        		
        		mServer.post(messageField.getText().toString(), tagField.getText().toString());
        		
        		/*Intent intent = new Intent(v.getContext(), LoginScreen.class);
        		startActivityForResult(intent, 0);*/
        		
        		Toast.makeText(Main.this, "Message posted", Toast.LENGTH_SHORT).show();  
        		messageField.setText("");
        		tagField.setText("");
        		
        	}   	
        	       });
    
    
    readButton.setOnClickListener(new OnClickListener() {
    	
   	
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			Intent intent = new Intent(v.getContext(), WallScreen.class);
    		startActivity(intent);
			
		}
	});
    
    
    }

	@Override
	public void NewEssemmessPosts(EssemmessReadEvent evt) {
		// TODO Auto-generated method stub
		
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