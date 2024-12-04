package com.example.oldencryption;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.util.Base64;

public class MainActivity extends AppCompatActivity {

    // Hardcoded encryption key
    private static final String ENCRYPTION_KEY = "secret_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set the title for the page
        setTitle("CRYPTIFY");

        // Get references to UI elements
        EditText inputText = findViewById(R.id.inputText);
        EditText encryptedText = findViewById(R.id.encryptedText);
        EditText decryptedText = findViewById(R.id.decryptedText);
        Button encryptButton = findViewById(R.id.encryptButton);
        Button decryptButton = findViewById(R.id.decryptButton);

        // Encrypt button click listener
        encryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = inputText.getText().toString();
                String encrypted = encrypt(input); // Encrypt the input text
                encryptedText.setText(encrypted); // Show encrypted text in the encryptedText EditText
            }
        });

        // Decrypt button click listener
        decryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String encrypted = encryptedText.getText().toString();
                String decrypted = decrypt(encrypted); // Decrypt the encrypted text
                decryptedText.setText(decrypted); // Show decrypted text in the decryptedText EditText
            }
        });
    }

    // XOR Encryption function with Base64 encoding
    private String encrypt(String input) {
        byte[] encryptedBytes = xorOperation(input.getBytes(), ENCRYPTION_KEY.getBytes());
        return Base64.encodeToString(encryptedBytes, Base64.DEFAULT); // Encode the encrypted bytes to Base64
    }

    // XOR Decryption function with Base64 decoding
    private String decrypt(String input) {
        byte[] decodedBytes = Base64.decode(input, Base64.DEFAULT); // Decode the Base64 input
        byte[] decryptedBytes = xorOperation(decodedBytes, ENCRYPTION_KEY.getBytes());
        return new String(decryptedBytes); // Convert bytes back to string
    }

    // XOR Operation function
    private byte[] xorOperation(byte[] input, byte[] key) {
        byte[] result = new byte[input.length];
        for (int i = 0; i < input.length; i++) {
            result[i] = (byte) (input[i] ^ key[i % key.length]);
        }
        return result;
    }
}
