package com.android.kharada.designsample.util;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by haradakazumi on 2017/01/30.
 */

public class AssetUtil {

    public static String assetLoadString(Context context,String assetName) {

        AssetManager assetManager = context.getResources().getAssets();
        String str = null;
        try {
            InputStream inputStream = assetManager.open(assetName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            reader.close();
            str = stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }

}
