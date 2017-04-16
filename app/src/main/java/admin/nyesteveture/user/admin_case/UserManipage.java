package admin.nyesteveture.user.admin_case;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.kosalgeek.android.photoutil.ImageBase64;
import com.kosalgeek.android.photoutil.ImageLoader;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import admin.nyesteveture.user.admin_case.FG.CameraPhoto;
import admin.nyesteveture.user.admin_case.FG.GalleryPhoto;

/**
 * Created by lalu on 4/15/2017.
 */

//id sdfgggg
public class UserManipage extends AppCompatActivity {
    private static final int GALLERY_DATAD =11 ;
    private static final int CROPE =44 ;
    String  _userId,_userName,_nofifiID;
    LinearLayout first_L,second_L,Third_L;
    EditText msg1,location1,msg2,location2,msg3,location3;
   Button choose_pic,send_notifi,send_request,send_pic;
    ImageView cameraview;
    CheckBox poli,fire,hospi,ambula;
    CameraPhoto cameraPhoto;
    GalleryPhoto galleryPhoto;
    private int CAMERACLICk=13;
    private int CROPEx=15;
    String photostring;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.usermainpage);
        Intent io=getIntent();
        _userId=io.getStringExtra("iduser");
        _userName=io.getStringExtra("nameuser");
        getSupportActionBar().setTitle(_userName);
        first_L=(LinearLayout)findViewById(R.id.first_notification);
        second_L=(LinearLayout)findViewById(R.id.first_second);
        Third_L=(LinearLayout)findViewById(R.id.third_notific);
        msg1=(EditText)findViewById(R.id.user_main_msg);
        msg2=(EditText)findViewById(R.id.user_main2_msg);
        msg3=(EditText)findViewById(R.id.user_main3_msg);
        location1=(EditText)findViewById(R.id.user_main_location);
        location2=(EditText)findViewById(R.id.user_main2_location);
      //  location3=(EditText)findViewById(R.id.user_main3_location);

        choose_pic=(Button)findViewById(R.id.take_pic);
        send_notifi=(Button)findViewById(R.id.send_notification);
        send_request=(Button)findViewById(R.id.send2_notification);
        send_pic=(Button)findViewById(R.id.send_pic_server);
        cameraview=(ImageView)findViewById(R.id.take_pic_previewww);
        poli=(CheckBox)findViewById(R.id.check_police);
        fire=(CheckBox)findViewById(R.id.check_fireforse);
        hospi=(CheckBox)findViewById(R.id.check_hospital);
        ambula=(CheckBox)findViewById(R.id.check_ambulance);

        cameraPhoto=new CameraPhoto(getApplicationContext());
        galleryPhoto=new GalleryPhoto(getApplicationContext());


        second_L.setVisibility(View.GONE);
        Third_L.setVisibility(View.GONE);



        send_notifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SendSERVER1();
            }
        });


    }

    private void SendSERVER1() {
        String msg=msg1.getText().toString();
        String place=location1.getText().toString();
        if(msg.isEmpty())
        {
            msg1.setError("invalid");
            return;
        }else
            if(place.isEmpty())
            {
                location1.setError("invalid");
                return;

            }
        else
            {
                HashMap<String,String> hashmap=new HashMap<>();
                hashmap.put("tag","notification");
                hashmap.put("subject",msg);
                hashmap.put("Location",place);
                hashmap.put("user_id",_userId);
                new PostASynClass(UserManipage.this, hashmap, new PostASynClass.Response() {
                    @Override
                    public void Response(String s) {
                        Log.e("response",s);

                        try{
                            JSONObject ss = new JSONObject(s);
                            if (ss.getInt("success")==1) {
                                String g = ss.getString("Notification");
                                JSONObject cf = new JSONObject(g);
                                _nofifiID= cf.getString("uid");
                                second_L.setVisibility(View.VISIBLE);
                                 SendREQUEST();

                            } else {
                                showsnackbar("Something went wrong");
                            }

                        }catch(JSONException e)
                        {
                            showsnackbar("Something went wrong");
                        }
                    }
                });
            }

    }

    private void SendREQUEST() {
        first_L.setVisibility(View.GONE);

        send_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SendREQUEST2();
            }
        });



    }

    private void SendREQUEST2() {
        String msg=msg2.getText().toString();
        String location=location2.getText().toString();
        if(msg.isEmpty())
        {
            msg2.setError("invalid");
            return;
        }
        else
        if(location.isEmpty())
        {
            location2.setError("invalid");
            return;
        }
        else{
            String polis="no",fires="no",hosp="no",ambu="no";
            polis=poli.isChecked()?"yes":"no";
            fires=fire.isChecked()?"yes":"no";
            hosp=hospi.isChecked()?"yes":"no";
            ambu=ambula.isChecked()?"yes":"no";

            HashMap<String,String> hashmap=new HashMap<>();
            hashmap.put("tag","send_request");
            hashmap.put("userid",_userId);
            hashmap.put("notification_id",_nofifiID);
            hashmap.put("insident",msg);
            hashmap.put("location",location);
            hashmap.put("police",polis);
            hashmap.put("ambulance",ambu);
            hashmap.put("fireforse",fires);
            hashmap.put("hospital",hosp);
            new PostASynClass(UserManipage.this, hashmap, new PostASynClass.Response() {
                @Override
                public void Response(String s) {
                    try{
                        JSONObject ss = new JSONObject(s);
                        if (ss.getInt("success")==1) {


                            Third_L.setVisibility(View.VISIBLE);
                            SendREQUEST2d();

                        } else {
                            showsnackbar("Something went wrong");
                        }

                    }catch(JSONException e)
                    {
                        showsnackbar("Something went wrong");
                    }
                }
            });



        }




    }

    private void SendREQUEST2d() {
        
       choose_pic.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               choosePic();
           }
       });
        send_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Send_Pic();
            }
        });


            }

boolean exit=false;
    @Override
    public void onBackPressed() {
        if (exit)
            System.exit(0);
        else {
            Toast.makeText(this, "Press Back again to Exit.",
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);

        }

    }

    private void Send_Pic() {
        String msg=msg3.getText().toString();
        //String place=location3.getText().toString();
        if(msg.isEmpty())
        {
            msg1.setError("invalid");
            return;
        }

        else {
            String encodeImage = "";

            Bitmap bitmap = null;

            if (photostring != null) {
                try {
                    bitmap = ImageLoader.init().from(photostring).requestSize(200, 200).getBitmap();
                    encodeImage = ImageBase64.encode(bitmap);
                    // Log.e("respo",encodeImage.toString());

                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }


                HashMap<String, String> hashmap = new HashMap<>();
                hashmap.put("tag", "send_photo");
                hashmap.put("messege", msg);
                hashmap.put("location", "location");
                hashmap.put("photo", encodeImage);
                hashmap.put("userid", _userId);
                new PostASynClass(UserManipage.this, hashmap, new PostASynClass.Response() {
                    @Override
                    public void Response(String s) {

                        try{
                            JSONObject ss = new JSONObject(s);
                            if (ss.getInt("success")==1) {

                                showsnackbar("Success");
                                first_L.setVisibility(View.VISIBLE);
                                msg1.setText("");
                                msg2.setText("");
                                msg3.setText("");
                                location2.setText("");
                                location1.setText("");
                                second_L.setVisibility(View.GONE);
                                Third_L.setVisibility(View.GONE);

                            } else {
                                showsnackbar("something went wrong");
                            }

                        }catch(JSONException e)
                        {
                            showsnackbar("something went wrong");
                        }

Log.e("response",s);
                    }
                });
            }
        }}

    private void choosePic() {
        takeapic();
    }
    private void takeapic() {
        ActivityCompat.requestPermissions(UserManipage.this,
                new String[]{
                        Manifest.permission.CAMERA,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.WAKE_LOCK,
                        Manifest.permission.INTERNET,
                        Manifest.permission.ACCESS_NETWORK_STATE},
                1);


        String choice[]={"Take a Picture","Select From Gallery"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(UserManipage.this,android.R.layout.select_dialog_item, choice);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Option");
        builder.setAdapter(adapter, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                Log.e("Selected Item", String.valueOf(which));
                if (which == 0) {
//cameratestdemo();
                    callcameraxc();
                }
                if (which == 1) {
                    try {
                        gallareydf();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }

        });
        final AlertDialog dialog = builder.create();
        dialog.show();


    }
    private void gallareydf()throws Exception {
        startActivityForResult(galleryPhoto.openGalleryIntent(),GALLERY_DATAD);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                    showsnackbar("Permission denied to read your External storage");
                    //Toast.makeText(UserManipage.this, "", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }
    private void callcameraxc() {
        try {
            startActivityForResult(cameraPhoto.takePhotoIntent(),CAMERACLICk);
        }
        catch (Exception e)
        {e.printStackTrace();}
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode==UserManipage.RESULT_OK) {

            if (requestCode == CAMERACLICk)
            {
                try{
                String photoPaths = cameraPhoto.getPhotoPath();
                File op = new File(photoPaths);
                Uri uricv = Uri.fromFile(op);
               // Log.e("crop",photoPaths);
                try {
                    performCrop(uricv);
                } catch (Exception e) {
                    e.printStackTrace();
                    finish();
                }}catch (Exception e)
                {finish();}
            }
            else if (requestCode == GALLERY_DATAD)
            {
                try {
                    Uri uri = data.getData();
                    galleryPhoto.setPhotoUri(uri);
                    String   photoPaths = galleryPhoto.getPath();
                    File op = new File(photoPaths);
                    Uri uricv = Uri.fromFile(op);
                   // Log.e("crop",photoPaths);
                    try {
                        performCropnm(uricv);
                    } catch (Exception e) {
                        e.printStackTrace(); finish();

                    }

                } catch (Exception e1) {
                    e1.printStackTrace();
                finish();
                }
            }

            else
            if (requestCode==CROPE)

            { try {
                String photoPaths = cameraPhoto.getPhotoPath();
                photostring = photoPaths;
                Log.e("path", photoPaths);
                try {
                    File op = new File(photoPaths);

                    if (op.exists()) {

                        Picasso.with(UserManipage.this).load(op).into(cameraview);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }catch (Exception e)
            {}
            }
            else
            if (requestCode==CROPEx)

            { try{
                String photoPaths = cameraPhoto.getPhotoPath();
                photostring=photoPaths;
                Log.e("path",photoPaths);
                try{
                    File op = new File(photoPaths);

                    if(op.exists()){

                        Picasso.with(UserManipage.this).load(op).into(cameraview);


                    }}catch (Exception e){
                    e.printStackTrace();
                }

            }catch (Exception e){}}
        }
    }

    private void performCrop(Uri picUri) {

        try {
            Intent cropIntent = new Intent("com.android.camera.action.CROP");
            cropIntent.setDataAndType(picUri, "image/*");
            cropIntent.putExtra("crop", "true");
            cropIntent.putExtra("aspectX", 0);
            cropIntent.putExtra("aspectY", 0);
            cropIntent.putExtra("outputX", 256);
            cropIntent.putExtra("outputY", 256);
            File photoFile = cameraPhoto.createImageFile();
            cropIntent.putExtra("output",Uri.fromFile(photoFile));
            startActivityForResult(cropIntent,CROPE);
        }

        catch (ActivityNotFoundException anfe) {
            Toast toast = Toast
                    .makeText(this, "This device doesn't support the crop action!", Toast.LENGTH_SHORT);
            toast.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void performCropnm(Uri picUri) {

        try {

            Intent cropIntent = new Intent("com.android.camera.action.CROP");
            cropIntent.setDataAndType(picUri, "image/*");
            cropIntent.putExtra("crop", "true");
            cropIntent.putExtra("aspectX", 0);
            cropIntent.putExtra("aspectY", 0);
            cropIntent.putExtra("outputX", 256);
            cropIntent.putExtra("outputY", 256);
            File photoFile = cameraPhoto.createImageFile();
            cropIntent.putExtra("output",Uri.fromFile(photoFile));

            startActivityForResult(cropIntent,CROPEx);

        }
        // respond to users whose devices do not support the crop action
        catch (ActivityNotFoundException anfe) {
            Toast toast = Toast
                    .makeText(this, "This device doesn't support the crop action!", Toast.LENGTH_SHORT);
            toast.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showsnackbar(String message){


        Snackbar snackbar = Snackbar
                .make(findViewById(R.id.sdfgggg), message, Snackbar.LENGTH_SHORT);


        View sbView = snackbar.getView();
        sbView.setBackgroundColor(Color.parseColor("#A981CA"));
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.BLACK);
        snackbar.show();




    }
}
