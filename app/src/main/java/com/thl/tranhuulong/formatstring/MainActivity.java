package com.thl.tranhuulong.formatstring;

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
    EditText editText;
    TextView edtEncode;
    Button button;
    String original;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        edtEncode = findViewById(R.id.editEncode);


        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String SECRET_KEY = "stackjava.com.if";
                    SecretKeySpec skeySpec = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");

                    String original = editText.getText().toString();

                    Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
                    cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
                    byte[] byteEncrypted = cipher.doFinal(original.getBytes());
                    String encrypted = Base64.getEncoder().encodeToString(byteEncrypted);


                    cipher.init(Cipher.DECRYPT_MODE, skeySpec);
                    byte[] byteDecrypted = cipher.doFinal(byteEncrypted);
                    String decrypted = new String(byteDecrypted);

                    System.out.println("original  text: " + original);

                    System.out.println("encrypted text: " + encrypted);
                    edtEncode.setText(encrypted);
                    System.out.println("decrypted text: " + decrypted);


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


    }

}
