package artyom.lyan.sbt.ru.fr.rest;

/**
 * this seems to be async
 * TODO login is also requiered
 * Created by SBT-Lyan-AI on 20.12.2016.
 */
public class SenderThread  {


//
//    private final String login;
//    private final RequestBuilder.Method method;
//    private volatile boolean hasImage;
//
//    private volatile Bitmap image;
//    private ValidatorListener listener;
//    private String url;
//    private BitmapTransformer<String> transformer;
//
//    private final Object mSenderLock = new Object();
//
//    private SenderThread(String url, String login, RequestBuilder.Method method) {
//        this.url = url;
//        this.login = login;
//        this.method = method;
//
//        this.transformer = new Base64BitmapTransformer();
//    }
//
//    public void terminate() {
//        this.running = false;
//    }
//
//    /**
//     * this method is inteded to send photo and login to perform autentificaion
//     * on server
//     *
//     * @return
//     */
//    @Override
//    public void run() {
//        running = true;
//
//        do {
//            synchronized (mSenderLock) {
//                while (!hasImage) {
//                    try {
//                        mSenderLock.wait();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//
//            RequestResult result;
//            try {
//                result = sendRequest();
//            } catch (IOException e) {
//                e.printStackTrace();
//                continue;
//            } catch (JSONException e) {
//                e.printStackTrace();
//                continue;
//            }
//
//            checkStatus(result.status);
//
//        } while (running);
//    }
//
//
//
//
//    private boolean checkStatus(String status) {
//        return (StatusConst.checkStatus(status) == StatusConst.SUCCESS);
//    }
//
//    private volatile boolean running = false;
//
//    public synchronized void setImage(Mat image) {
//        Utils.matToBitmap(image, this.image);
//        hasImage = true;
//        mSenderLock.notify();
//    }
//
//    public static SenderThread urlLoginMethod(String url, String login,
//                                              RequestBuilder.Method method) {
//        SenderThread result = new SenderThread(url, login, method);
//        return result;
//    }
//
//    @Override
//    public void setValidatorListener(ValidatorListener listener) {
//        this.listener = listener;
//    }
}
