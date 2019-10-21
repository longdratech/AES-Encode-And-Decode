package com.thl.tranhuulong.formatstring;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class MainActivity extends AppCompatActivity {
    EditText editText, edtKey;
    TextView edtEncode, textView;
    Button button, btnDecode;
    String original;
    Cipher cipher;
    SecretKeySpec skeySpec;
    byte[] byteEncrypted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        edtEncode = findViewById(R.id.editEncode);
        edtKey = findViewById(R.id.editKey);


        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String SECRET_KEY = edtKey.getText().toString();
                    skeySpec = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
                    String original = editText.getText().toString();
                    cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
                    cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
                    byteEncrypted = cipher.doFinal(original.getBytes());
                    String encrypted = Base64.getEncoder().encodeToString(byteEncrypted);
                    cipher.init(Cipher.DECRYPT_MODE, skeySpec);
                    byte[] byteDecrypted = cipher.doFinal(byteEncrypted);
                    String decrypted = new String(byteDecrypted);
                    edtEncode.setText(encrypted);
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (NoSuchPaddingException e) {
                    e.printStackTrace();
                } catch (InvalidKeyException e) {
                    e.printStackTrace();
                } catch (BadPaddingException e) {
                    e.printStackTrace();
                } catch (IllegalBlockSizeException e) {
                    e.printStackTrace();
                }
            }
        });
        btnDecode = findViewById(R.id.btnDecode);
        btnDecode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    cipher.init(Cipher.DECRYPT_MODE, skeySpec);
                    byte[] byteDecrypted = new byte[0];
                    byteDecrypted = cipher.doFinal(byteEncrypted);
                    String decrypted = new String(byteDecrypted);
                    textView = findViewById(R.id.textView);
                    textView.setText(decrypted);



                } catch (InvalidKeyException e) {
                    e.printStackTrace();
                } catch (BadPaddingException e) {
                    e.printStackTrace();
                } catch (IllegalBlockSizeException e) {
                    e.printStackTrace();
                }


            }
        });

    }

}
