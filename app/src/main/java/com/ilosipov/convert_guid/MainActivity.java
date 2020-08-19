package com.ilosipov.convert_guid;

import android.os.Bundle;

import java.util.Arrays;

import android.util.Base64;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.nio.ByteBuffer;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UUID uuid = UUID.fromString("9D7CEE8C-3B1E-4C08-ACBF-B9DE1847533B");
        TextView result = findViewById(R.id.tex_result);
        result.setText(convert(uuid));
    }

    private String convert(UUID uuid) {
        ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
        bb.putLong(uuid.getMostSignificantBits());
        bb.putLong(uuid.getLeastSignificantBits());

        byte[] uuid_bytes = bb.array();
        byte[] guid_bytes = Arrays.copyOf(uuid_bytes, uuid_bytes.length);

        guid_bytes[0] = uuid_bytes[3];
        guid_bytes[1] = uuid_bytes[2];
        guid_bytes[2] = uuid_bytes[1];
        guid_bytes[3] = uuid_bytes[0];
        guid_bytes[4] = uuid_bytes[5];
        guid_bytes[5] = uuid_bytes[4];
        guid_bytes[6] = uuid_bytes[7];
        guid_bytes[7] = uuid_bytes[6];
        return Base64.encodeToString(guid_bytes, Base64.NO_WRAP);
    }
}