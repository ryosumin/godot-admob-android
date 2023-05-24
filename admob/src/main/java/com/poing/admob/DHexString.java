package com.poing.admob;

import android.provider.Settings;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;
import android.app.Activity;


public class DHexString{
    public DHexString() {
    }
    /**
     * Generate MD5 for the deviceID
     * @param  s The string to generate de MD5
     * @return String The MD5 generated
     */
    private static String md5(final String s)
    {
        try
        {
            // Create MD5 Hash
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte[] messageDigest = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                StringBuilder h = new StringBuilder(Integer.toHexString(0xFF & b));
                while (h.length() < 2) h.insert(0, "0");
                hexString.append(h);
            }
            return hexString.toString();
        }
        catch(NoSuchAlgorithmException e)
        {
            //Logger.logStackTrace(TAG,e);
        }
        return "";
    }

    /**
     * Get the Device ID for AdMob
     * @return String Device ID
     */
    public static String getDeHexString(Activity aActivity)
    {
        String android_id = Settings.Secure.getString(aActivity.getContentResolver(), Settings.Secure.ANDROID_ID);
        return md5(android_id).toUpperCase(Locale.US);
    }
}