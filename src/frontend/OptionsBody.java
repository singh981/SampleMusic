package frontend;

import com.example.samplemusic.R;

import backend.GridAdapter;
import backend.SongAdapter;

import android.app.Service;
import android.content.Context;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

public class OptionsBody extends RelativeLayout{
	LayoutInflater mInflater;
	Context ctx;
	
	//Constructors begin here
	public OptionsBody(Context context,int x) {
		super(context);
		ctx=context;
		mInflater = LayoutInflater.from(context);
        init(x);
	
	}
	
	public void init(int x)
	   {
		if(x==0)
	       mInflater.inflate(R.layout.options, this, true);
		else mInflater.inflate(R.layout.options, this, true);
	   }
	
	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
	    if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
	    	if (event.getAction() == KeyEvent.ACTION_DOWN
	                && event.getRepeatCount() == 0) {

	            // Tell the framework to start tracking this event.
	            getKeyDispatcherState().startTracking(event, this);
	            return true;

	        }
	    	
	    	else if (event.getAction() == KeyEvent.ACTION_UP) {
	            getKeyDispatcherState().handleUpEvent(event);
	            if (event.isTracking() && !event.isCanceled()) {

	                // DO BACK ACTION HERE
	         		if(SongAdapter.isLongClick() ){
	         				Serve.removeOptions(SongAdapter.getSelected());
	         				SongAdapter.setLongClick(false);
	         				SongAdapter.getSelected().clear();
	         				return true;
	         		}
	         		else if(GridAdapter.isLongClick()){
         				Serve.removeOptions(GridAdapter.getSelected());
         				GridAdapter.setLongClick(false);
         				GridAdapter.getSelected().clear();
	    	    		return true;
	         		}
	            }
	        }
	    	Log.d("VI","Back button pressed");
	    	return super.dispatchKeyEvent(event);
	    } 
	    else if(event.getKeyCode() == KeyEvent.KEYCODE_HOME){
	    	((Service) ctx).stopSelf();
	    	Log.d("VI","Home button pressed");
	    	return true;
	    
	    }
	    else {
	        return super.dispatchKeyEvent(event);
	    }
	}
	
}
