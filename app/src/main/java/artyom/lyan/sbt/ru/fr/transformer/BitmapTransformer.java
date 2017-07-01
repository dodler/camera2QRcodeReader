package artyom.lyan.sbt.ru.fr.transformer;

import android.graphics.Bitmap;

/**
 * Created by SBT-Lyan-AI on 20.12.2016.
 */
public interface BitmapTransformer <T>{
    T tranform(Bitmap image);
}
