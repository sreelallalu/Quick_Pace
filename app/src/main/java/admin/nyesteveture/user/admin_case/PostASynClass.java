package admin.nyesteveture.user.admin_case;

import android.content.Context;

import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.EachExceptionsHandler;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.HashMap;

/**
 * Created by lalu on 4/15/2017.
 */

public class PostASynClass {
    Context c;
    HashMap<String,String> hashMap=new HashMap<>();
    Response ss;
public interface Response{

    void Response(String s);
}
    public PostASynClass(Context c, HashMap<String, String> hashMap, final Response ss) {
        this.c = c;
        this.hashMap = hashMap;
        this.ss=ss;
        PostResponseAsyncTask task=new PostResponseAsyncTask(c, hashMap, new AsyncResponse() {
            @Override
            public void processFinish(String s) {
              ss.Response(s);
            }
        });
        task.execute("http://quick.nyesteventuretech.com/index.php");
        task.setEachExceptionsHandler(new EachExceptionsHandler() {
            @Override
            public void handleIOException(IOException e) {
                e.printStackTrace();
            }

            @Override
            public void handleMalformedURLException(MalformedURLException e) {
                e.printStackTrace();
            }

            @Override
            public void handleProtocolException(ProtocolException e) {
                e.printStackTrace();
            }

            @Override
            public void handleUnsupportedEncodingException(UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        });




    }

}
