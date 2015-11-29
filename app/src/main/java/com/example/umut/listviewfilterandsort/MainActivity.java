package com.example.umut.listviewfilterandsort;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.Comparator;

public class MainActivity extends AppCompatActivity {
    ListView liste;
    ArrayAdapter<String> adapter;
    EditText arama;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String sozcukler[] = {"caa", "aab", "dbb", "abc", "abcd",
                "abcdd", "ee", "eef"};

        liste = (ListView) findViewById(R.id.listView);
        arama = (EditText) findViewById(R.id.editText);

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_expandable_list_item_1, android.R.id.text1, sozcukler);
        liste.setAdapter(adapter);

        //listemizi alfabetik şekilde sıralıyoruz...
        adapter.sort(new Comparator<String>() {
            @Override
            public int compare(String lhs, String rhs) {
                return lhs.compareTo(rhs);
            }
        });

        //TextChangedListener dinleyicisi ekleyerek EditText içindeki değişiklikler de neler olması gerektiğini belirliyoruz.
        arama.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            //EditText içindeki yazı değiştiğinde neler olacağını bu kısımda belirliyoruz.
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //filtreleme özelliğini belirliyoruz.
                MainActivity.this.adapter.getFilter().filter(s);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
