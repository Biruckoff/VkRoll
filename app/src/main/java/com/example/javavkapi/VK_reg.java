package com.example.javavkapi;


import android.content.Context;
import android.content.res.AssetManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;

import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.methods.VKApiGroups;
import com.vk.sdk.api.methods.VKApiWall;

import com.vk.sdk.api.model.VKList;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class VK_reg extends AppCompatActivity {

SoundPool soundPool;

    String[] obj;
    String[] id;
    int number =0;
static int i;
String link;
boolean flag;
    String st_1;
    String st_2;
    String st_3;
    String st_4;
    String st_1_x;
    String st_2_x;
    String st_3_x;
    String st_4_x;
    int z =0;
private int click;


    static public String link_2;


    private String[] scope = new String[]{ VKScope.FRIENDS, VKScope.WALL, VKScope.GROUPS};
    
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                AudioAttributes audioAttributes = new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION).setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION).build();
                soundPool = new SoundPool.Builder().setMaxStreams(2).setAudioAttributes(audioAttributes).build();

            } else {
                soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);

            }
            click = soundPool.load(this, R.raw.button_21, 100);

            VKSdk.login(this, scope);



        setContentView(R.layout.start);
            final Button start = findViewById(R.id.start);


            start.setOnClickListener(new View.OnClickListener() {
                @Override

                public void onClick(View v) {
                    play();

                    EditText link = findViewById(R.id.link);
try{
    link_2 = makeID(link.getText().toString());


                    if (linkType(link.getText().toString())) {
                        System.out.println(link_2);
                        begin(link_2);
                    } else {

                        VKRequest request = new VKApiGroups().getById(VKParameters.from("group_ids", link_2));
                        request.executeWithListener(new VKRequest.VKRequestListener() {
                            @Override
                            public void onComplete(VKResponse response) {
                                super.onComplete(response);
                                VKList vkList = (VKList) response.parsedModel;
                                try {

                                    int i = vkList.get(0).fields.getInt("id");
                                    final String s = "" + i;
                                    begin(s);
                                } catch (JSONException e) {
                                    e.printStackTrace();


                                }
                            }
                        });
                    }

                }catch (ArrayIndexOutOfBoundsException e){
                    System.out.println("Увидел");
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Вы перенаправлены на стандартный сервер", Toast.LENGTH_SHORT);
                    toast.show();
    begin("192062368");
                }finally {


                }
                }
            });
}



public void begin(String link){
        this.link = link;
    progruz();
    Ext();
    scrollButton();
con();
    }

    void makePost(String msg, final String ownerId) {


        VKParameters parameters = new VKParameters();
        parameters.put(VKApiConst.OWNER_ID, String.valueOf(ownerId));
        parameters.put(VKApiConst.MESSAGE, msg);
        VKRequest post = VKApi.wall().post(parameters);

        post.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                // post was added
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Пост сделан", Toast.LENGTH_SHORT);
                toast.show();

            }
            @Override
            public void onError(VKError error) {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Ошибка", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    void Repost_1(String id) {
        if (VKSdk.isLoggedIn()) {

            VKParameters parameters = new VKParameters();
            parameters.put("object", id);
            VKRequest post = VKApi.wall().repost(parameters);
            post.executeWithListener(new VKRequest.VKRequestListener() {
                @Override
                public void onError(VKError error) {


                }
            });
        }else{

            Toast toast = Toast.makeText(getApplicationContext(),
                    "Вы не подключены, перезапустите приложения", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
    void Join(String id){
if(VKSdk.isLoggedIn()){
        VKParameters parameters = new VKParameters();
        parameters.put("group_id", id);
        VKRequest post = VKApi.groups().join(parameters);
 post.executeWithListener(new VKRequest.VKRequestListener() {

     public void onError(VKError error) {
         super.onError(error);

         Toast toast = Toast.makeText(getApplicationContext(),
                 "Ошибка данных_2", Toast.LENGTH_SHORT);
         toast.show();
     }
 });}else{
    Toast toast = Toast.makeText(getApplicationContext(),
            "Вы не подключены, перезапустите приложение", Toast.LENGTH_SHORT);
    toast.show();
}
    }


String makeID(String link) throws  ArrayIndexOutOfBoundsException{

    char[] link_1 = link.toCharArray();
    String id = "";
if(link_1.length<15){
    ArrayIndexOutOfBoundsException exception = new ArrayIndexOutOfBoundsException();
    throw exception;
}else
    if ((link_1[15] == 'c') && (link_1[16] == 'l') && (link_1[17] == 'u') && (link_1[18] == 'b')) {
        for (int i = 19; i < link_1.length; i++) {
            id = id + link_1[i];

        }
        flag = false;
        return id;

    } else {
        for (int i = 15; i < link_1.length; i++) {
            id = id + link_1[i];

        }
        flag = true;

        return id;
    }


}


void nextPost(){
if(number<40){
    ++number;}
    }

    void progruz(){
        VKRequest request1 = new VKApiWall().get(VKParameters.from(VKApiConst.OWNER_ID, "-" + link, VKApiConst.COUNT, 39));

        request1.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);

                obj = new String[39];
                id = new String[39];
                try {

                    JSONObject jsonObject = (JSONObject) response.json.get("response");
                    JSONArray jsonArray = (JSONArray) jsonObject.get("items");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject post = (JSONObject) jsonArray.get(i);
                        System.out.println(post);
                            System.out.println(jsonArray.length());
                            obj[i] = post.getString("text");
                            id[i] = post.getString("id");

                    }



                }catch (JSONException e){
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Ошибка данных_3", Toast.LENGTH_SHORT);
                    toast.show();
                    e.printStackTrace();}
            }
});

    }




void Ext(){
try {


        Scanner sc = new Scanner(obj[number]);
        if (sc.hasNextLine()) {
            st_1 = sc.nextLine();
        } else {
            st_1 = " \n";
        }
        if (sc.hasNextLine()) {
            st_2 = sc.nextLine();
        } else {
            st_2 = " \n";
        }
        if (sc.hasNextLine()) {
            st_3 = sc.nextLine();
        } else {
            st_3 = " \n";
        }
        if (sc.hasNextLine()) {
            st_4 = sc.nextLine();
            i = Integer.parseInt(st_4);
        } else {
            st_4 = " \n";
        }

System.out.println(obj[0]);


    }catch (Exception e){

}
    try {
        if(obj[number+1]!=null) {

            Scanner sc = new Scanner(obj[number+1]);
            if (sc.hasNextLine()) {
                st_1_x = sc.nextLine();
            } else {
                st_1_x = " \n";
            }
            if (sc.hasNextLine()) {
                st_2_x = sc.nextLine();
            } else {
                st_2_x = " \n";
            }
            if (sc.hasNextLine()) {
                st_3_x = sc.nextLine();
            } else {
                st_3_x = " \n";
            }
            if (sc.hasNextLine()) {
                st_4_x = sc.nextLine();
                i = Integer.parseInt(st_4);
            } else {
                st_4_x = " \n";
            }
           System.out.println(st_1_x+" "+st_2_x+" "+st_3_x+" "+st_4_x + " ");
           setText(st_1_x,st_2_x,st_3_x);
        }else{
            final TextView text_1 = findViewById(R.id.qwerty_1);
            text_1.setText("Посты закончились,"+"\n"+"попробуйте позже");
        }}catch (Exception e){

    }
}






String makeWallId(String link){

        char[] link_1 = link.toCharArray();
        String s ="";
        try{
        for(int i=15; i< link_1.length; i++){
            s = s+link_1[i];

        }
        }
            catch (Exception e){
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Неправильный формат данных", Toast.LENGTH_LONG);
                toast.show();

            }
        return s;
}


void makeComent(String ownerId, String postId, String msg){
if(VKSdk.isLoggedIn()){
    VKParameters vkParameters = new VKParameters();
    vkParameters.put("owner_id", ownerId);
    vkParameters.put("post_id", postId);
    vkParameters.put("message", msg);
    VKRequest request = VKApi.wall().addComment(vkParameters);
  request.executeWithListener(new VKRequest.VKRequestListener() {
      @Override
      public void onComplete(VKResponse response) {
          Toast toast = Toast.makeText(getApplicationContext(),
                  "Жалоба отправлена", Toast.LENGTH_SHORT);
          toast.show();
      }

      @Override
      public void onError(VKError error) {
          Toast toast = Toast.makeText(getApplicationContext(),
                  "Ошибка данных_4", Toast.LENGTH_SHORT);
          toast.show();
      }
  });}else{
    Toast toast = Toast.makeText(getApplicationContext(),
            "Вы не подключены, перезапустите приложение", Toast.LENGTH_SHORT);
    toast.show();
}

}



public boolean linkType(String link){

    char[] s = link.toCharArray();

    if ((s[15] == 'c') && (s[16] == 'l') && (s[17] == 'u') && (s[18] == 'b')) {
        flag = true;
        return flag;
    } else {
        flag = false;
        return flag;
    }
}




public void baw(){
    FrameLayout fl = findViewById(R.id.l);
    Animation animation = AnimationUtils.loadAnimation(this, R.anim.main);
    fl.startAnimation(animation);
}
public void play(){

    soundPool.play(click,1,1,100,0,1);
    }
    public boolean isrepost(String link){
      char[] l = link.toCharArray();
      if((l[15]=='w')&&(l[16]=='a')&&(l[17]=='l')){
          return true;
      }else{
          return false;
      }
    }




    public void con(){
        //CREATE////CREATE////CREATE////CREATE////CREATE////CREATE////CREATE////CREATE//
        Button create = findViewById(R.id.Create);
        //     VKSdk.logout();
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play();

                setContentView(R.layout.choose_act);
                try{TimeUnit.MILLISECONDS.sleep(100);} catch (InterruptedException ex){

                }



                Button subscribe = findViewById(R.id.subscribe);
                Button repost = findViewById(R.id.repost);

                subscribe.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        play();
                        setContentView(R.layout.create);
                        con();
                        Button make_1 = findViewById(R.id.make_1);
                        make_1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                EditText link_1 = findViewById(R.id.link_1);
                                EditText write_1 = findViewById(R.id.write_1);
                                System.out.println(link_1.getText().toString());
                                System.out.println(write_1.getText().toString());
                                if(link_1.getText().toString().toCharArray().length <15){
                                    Toast toast = Toast.makeText(getApplicationContext(),
                                            "Ссылка обязательна", Toast.LENGTH_SHORT);
                                    toast.show();
                                }else{ if(write_1.getText().toString().toCharArray().length ==0) {
                                    String y = "Описания нет ";
                                    makePost(link_1.getText().toString() + "\n" + y + "\n" + "Подписаться" + "\n" + "0", "-" + link);
                                }else{
                                    makePost(link_1.getText().toString() + "\n" + write_1.getText().toString() + "\n" + "Подписаться" + "\n" + "0", "-" + link);
                                }}
                            }
                        });

                    }
                });



                repost.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        play();
                        setContentView(R.layout.create_repost);
                        con();
                        Button make_1 = findViewById(R.id.make_1);
                        make_1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                EditText link_1 = findViewById(R.id.link_1);
                                EditText write_1 = findViewById(R.id.write_1);



                                System.out.println(link_1.getText().toString());
                                System.out.println(write_1.getText().toString());


                                if((link_1.getText().toString().toCharArray().length>15)&&(isrepost(link_1.getText().toString()))){


                                    if(write_1.getText().toString().toCharArray().length==0){
                                        String y = "Нет описания";
                                        makePost(link_1.getText().toString()+ "\n"+y+ "\n" + "Репост" + "\n" + "1", "-" + link);
                                    }else{
                                        makePost(link_1.getText().toString()+ "\n"+write_1.getText().toString()+ "\n" + "Репост" + "\n" + "1", "-" + link);
                                    }
                                }else{
                                    Toast toast = Toast.makeText(getApplicationContext(),
                                            "Проверьте ссылку", Toast.LENGTH_SHORT);
                                    toast.show();
                                }
                            }
                        });

                    }
                });
                con();

            }
        });

//CREATE////CREATE////CREATE////CREATE////CREATE////CREATE////CREATE////CREATE////CREATE////CREATE//

        //REPORT////REPORT////REPORT////REPORT////REPORT////REPORT////REPORT////REPORT////REPORT////REPORT//
        final Button report = findViewById(R.id.Report);

        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play();

                setContentView(R.layout.report);

                try{TimeUnit.MILLISECONDS.sleep(100);} catch (InterruptedException ex){

                }
                con();
                Button spam = findViewById(R.id.spam);
                Button spam_1 = findViewById(R.id.spam_1);
                Button spam_2 = findViewById(R.id.spam_2);
                Button spam_3 = findViewById(R.id.spam_3);
                Button spam_4 = findViewById(R.id.spam_4);
                Button spam_5 = findViewById(R.id.spam_5);
                TextView reportId = findViewById(R.id.report_1);
                reportId.setText(id[number]);


                try {
                    spam.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            play();
                            makeComent("-" + link, id[number], "Спам");


                        }
                    });
                    spam_1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            play();
                            makeComent("-" + link, id[number], "Насилие");

                        }
                    });
                    spam_2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            play();
                            makeComent("-" + link, id[number], "Порнография");

                        }
                    });
                    spam_3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            play();
                            makeComent("-" + link, id[number], "Нецензурная лексика");
                        }
                    });
                    spam_4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            play();
                            makeComent("-" + link, id[number], "Экстремизм");
                        }
                    });
                    spam_5.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            play();
                            makeComent("-" + link, id[number], "Призыв к суициду");
                        }
                    });


                }catch (Exception e){
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Ошибка данных", Toast.LENGTH_SHORT);
                    toast.show();
                }





            }
        });
//REPORT////REPORT////REPORT////REPORT////REPORT////REPORT////REPORT////REPORT////REPORT////REPORT//








        //SCROLL////SCROLL////SCROLL////SCROLL////SCROLL////SCROLL////SCROLL////SCROLL////SCROLL////SCROLL//
        Button scroll = findViewById(R.id.Scroll);

        scroll.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
     scrollButton();

            }
        });

        //SCROLL////SCROLL////SCROLL////SCROLL////SCROLL////SCROLL////SCROLL////SCROLL////SCROLL////SCROLL////SCROLL////SCROLL////SCROLL////SCROLL////SCROLL//
    }
    public void scrollButton(){

        play();
        setContentView(R.layout.activity_main);

        try{TimeUnit.MILLISECONDS.sleep(100);} catch (InterruptedException ex){

        }
        con();

        Button Yes = findViewById(R.id.da);
        Yes.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (z == 0) {
                    play();
                    baw();
                    Ext();
                    nextPost();
                    z++;
                } else {
                    play();
                    baw();
                    Ext();
                    if (i == 1) {

                        try {
                            Repost_1(makeWallId(st_1));
                        } catch (IndexOutOfBoundsException e) {

                        }


                    } else if (i == 0) {


                        try {


                            VKRequest request = new VKApiGroups().getById(VKParameters.from("group_ids", makeID(st_1)));
                            request.executeWithListener(new VKRequest.VKRequestListener() {
                                @Override
                                public void onComplete(VKResponse response) {
                                    super.onComplete(response);
                                    VKList vkList = (VKList) response.parsedModel;
                                    try {
                                        Ext();
                                        int i = vkList.get(0).fields.getInt("id");
                                        String s = "" + i;
                                        nextPost();
                                        Join(s);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });

                        } catch (IndexOutOfBoundsException e) {

                        }

                    } else {
                        Ext();
                        nextPost();

                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Действие не выполненно", Toast.LENGTH_SHORT);
                        toast.show();
                    }


                }
            }});
        Button No = findViewById(R.id.net);
        No.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                baw();
                play();
                Ext();
                nextPost();
if(z==0){
    z++;
}

            }
        });

    }
    public void setText(String one,String two,String three){
        final TextView text_1 = findViewById(R.id.qwerty_1);
        text_1.setText(" Ссылка:\n" + one + "\n Действие:\n" + three + "\n Описание:\n" +two);
    }
}




