package com.ucll.sqliteandroid;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DBHelper testdb;

    EditText editText,editText2,editText3 ;
    Button button , button2, button3, button4 ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testdb = new DBHelper(this);
        editText = (EditText)findViewById(R.id.editText);
        editText2 = (EditText)findViewById(R.id.editText2);
        editText3 = (EditText)findViewById(R.id.editText3);
        button = (Button)findViewById(R.id.button);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        button4 = (Button)findViewById(R.id.button4);
        Insert();
        //selectAll();
        update();
        delete();
        selectDescription();
    }


    public void Insert(){
        button.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        boolean inserted = testdb.insertData(
                                editText.getText().toString(),
                                editText2.getText().toString(),
                                "beschrijving van het gebouw"
                        );
                        if (inserted == true){
                            Toast.makeText(MainActivity.this,"data inserted",Toast.LENGTH_LONG).show();
                        }else  {
                            Toast.makeText(MainActivity.this,"data not inserted",Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }

    public void selectAll(){
        button2.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Cursor res = testdb.getAllData();
                        if (res.getCount() ==0){
                            showMessage("error","no data found");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()){
                            buffer.append("id :"+ res.getString(0)+"\n");
                            buffer.append("longitude :"+ res.getString(1)+"\n");
                            buffer.append("latitude :"+ res.getString(2)+"\n");
                            buffer.append("description : "+ res.getString(3)+"\n");
                        }
                        showMessage("data",buffer.toString());
                    }
                }
        );
    }

    public void selectDescription(){
        button2.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        String idGebouw = editText3.getText().toString();
                        Log.d("myTag", idGebouw);
                        Cursor res = testdb.getDataById(idGebouw);
                        if (res.getCount() ==0){
                            showMessage("error","no data found");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()){
                            buffer.append("id :"+ res.getString(0)+"\n");
                            buffer.append("description :"+ res.getString(3)+"\n");
                        }
                        showMessage("data",buffer.toString());
                    }
                }
        );
    }


    public void showMessage ( String title , String message){
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setCancelable(true);
        alertBuilder.setTitle(title);
        alertBuilder.setMessage(message);
        alertBuilder.show();
    }

    public void update(){
        button3.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean updated = testdb.update(
                                editText3.getText().toString(),
                                editText.getText().toString(),
                                editText2.getText().toString(),
                                editText2.getText().toString()

                        );
                        if (updated == true){
                            Toast.makeText(MainActivity.this,"data updated",Toast.LENGTH_LONG).show();
                        }else  {
                            Toast.makeText(MainActivity.this,"data not updated",Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }


    public void delete(){
        button4.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int deleted = testdb.delete(editText3.getText().toString());
                        if (deleted <0){
                            Toast.makeText(MainActivity.this,"data not deleted",Toast.LENGTH_LONG).show();
                        }else  {
                            Toast.makeText(MainActivity.this,"data deleted",Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }
}
