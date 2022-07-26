		import java.net.URL;

public class main {

	@SuppressWarnings({ "static-access" })
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SimpleTextToSpeech s = new SimpleTextToSpeech();
		String[] data = {args[0], args[1]};
		URL location = main.class.getProtectionDomain().getCodeSource().getLocation();			
		try {
			s.main(data);
	        String filename = "output.mp3";
	        //System.out.println(filename);
	        MP3 mp3 = new MP3(filename);
	        mp3.play();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static String first_char(String phrase) {

	    String rephrase = null;
	    if (phrase != null && phrase.length() > 1) {
	        rephrase = phrase.substring(1, phrase.length());
	    }

	    return rephrase;
	}
	
}
