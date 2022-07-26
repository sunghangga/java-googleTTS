
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class SimpleTextToSpeech {
    private static final String TEXT_TO_SPEECH_SERVICE = 
            "http://translate.google.com/translate_tts";
    private static final String USER_AGENT =  
            "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:11.0) " +
            "Gecko/20100101 Firefox/11.0";

    public static void main(String[] args) throws Exception {
        String language = args[0];
        String text = args[1];
        text = URLEncoder.encode(text, "utf-8");
        new SimpleTextToSpeech().go(language, text);
    }

    public void go(String language, String text) throws Exception {
        // Create url based on input params
        String strUrl = TEXT_TO_SPEECH_SERVICE + "?ie=UTF-8&total=1&idx=0&textlen=32&client=tw-ob&q=" + text + "&tl="+ language;
        URL url = new URL(strUrl);
//http://translate.google.com/translate_tts?ie=UTF-8&total=1&idx=0&textlen=32&client=tw-ob&q=hallo&tl=En-gb
        // Etablish connection
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        // Get method
        connection.setRequestMethod("GET");
        // Set User-Agent to "mimic" the behavior of a web browser. In this 
        // example, I used my browser's info
        connection.addRequestProperty("User-Agent", USER_AGENT);
        connection.connect();

        // Get content
        BufferedInputStream bufIn = 
                new BufferedInputStream(connection.getInputStream());
        byte[] buffer = new byte[1024];
        int n;
        ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
        while ((n = bufIn.read(buffer)) > 0) {
            bufOut.write(buffer, 0, n);
        }

        // Done, save data
        //URL location = main.class.getProtectionDomain().getCodeSource().getLocation();
        File output = new File("output.mp3");
        BufferedOutputStream out = 
                new BufferedOutputStream(new FileOutputStream(output));
        out.write(bufOut.toByteArray());
        out.flush();
        out.close();
        //System.out.println("Done");
    }
}