package artyom.lyan.sbt.ru.fr.transformer;

import android.graphics.Bitmap;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

/**
 * this class converts android bitmap to base64 string
 * Created by SBT-Lyan-AI on 20.12.2016.
 */
public class Base64BitmapTransformer implements BitmapTransformer<String> {
    @Override
    public String tranform(Bitmap image) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        return Base64.encodeToString(
                stream.toByteArray(), Base64.DEFAULT
        );
    }
}
