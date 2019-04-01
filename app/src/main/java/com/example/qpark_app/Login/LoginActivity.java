package com.example.qpark_app.Login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qpark_app.Main.MainActivity;
import com.example.qpark_app.R;
import com.example.qpark_app.Util.ConstSTATUS;
import com.example.qpark_app.Util.ConstURL;

import org.json.JSONArray;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    EditText inputId;
    EditText inputPw;
    TextView mainText;

    Button Login;
    Button Register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputId = (EditText) findViewById(R.id.IdEdit);
        inputPw = (EditText) findViewById(R.id.PwEdit);
        mainText = (TextView) findViewById(R.id.MainText);

        Login = (Button) findViewById(R.id.Login);
        Register = (Button) findViewById(R.id.Register);

        String Q = "<font color=\"#FFFF00\">Q</font>";
        String Park = "-Park";
        mainText.setText(Html.fromHtml(Q + Park));

        Login.setOnClickListener(BtClickListener);
    }

    Button.OnClickListener BtClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id = v.getId();

            switch (id) {
                case R.id.Login:

                    try {

                        JSONObject RESULT = new LoginTask(LoginActivity.this).execute(ConstURL.LoginUrl, inputId.getText().toString(), inputPw.getText().toString()).get();
                        JSONObject HEAD = RESULT.getJSONObject("HEAD");
                        JSONObject BODY = RESULT.getJSONObject("BODY");
                        JSONArray LIST = BODY.getJSONArray("LIST");
                        int STATUS_CODE = HEAD.getInt("STATUS_CODE");
                        if(STATUS_CODE == ConstSTATUS.SUCCESS){

                            JSONObject tmp = LIST.getJSONObject(0);
                            String NAME = tmp.getString("NAME");
                            String EMAIL = tmp.getString("EMAIL");

                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            Toast.makeText(getApplicationContext(),NAME+"님 환영합니다.",Toast.LENGTH_SHORT).show();
                            startActivity(intent);


                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    break;


            }
        }
    };
}
