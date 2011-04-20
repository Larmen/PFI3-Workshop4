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

public class LoginScreen extends Activity implements EssemmessListener{

	
	public final Essemmess mServer = EssemmessHelper.getServer(this);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.login);
		
		//EssemmessHelper.getServer(this);
		
		final Button button2 = (Button) findViewById(R.id.loginButton);
		final EditText username = (EditText) findViewById(R.id.username_field);
		final EditText password = (EditText) findViewById(R.id.password_field); 
		
		mServer.addEssemmessEventListener(this);
		
        
        button2.setOnClickListener(new OnClickListener() {
        	public void onClick(View v){
        		//Perform action on click
        		
        		mServer.login(username.getText().toString(), password.getText().toString());
        		Log.d("loginscreen", "onClick()");
        		
        		//essemmessLoginEvent.getLoggedin();
        		Intent intent = new Intent(v.getContext(), Main.class);
        		startActivity(intent);
        		
        		//Toast.makeText(LoginScreen.this, "Click", Toast.LENGTH_LONG).show();  
        		
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
