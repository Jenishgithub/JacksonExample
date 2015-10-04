import java.io.File;
import java.io.IOException;
import java.net.URL;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserTest {
	public static void main(String[] args) throws JsonParseException,
			JsonMappingException, IOException {
		File jsonFile = new File("user.json");
		URL jsonUrl = new URL(
				"https://gist.github.com/Jenishgithub/2d0cb747a6c59bceed53/raw/e6b2e8c318bb138ec832860a2037d24afe618131/user.json");
		String jsonStr = "{\"name\":{\"first\":\"Joe\",\"last\":\"Sixpack\"},\"gender\":\"MALE\",\"verified\":false,\"userImage\":\"Rm9vYmFyIQ==\"}";
		User user = null;

		ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally

		// IMPORTANT
		// without this option set adding new fields breaks old code
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
				false);

		user = mapper.readValue(jsonFile, User.class);
		System.out.println(user.getName().getFirst());

		user = mapper.readValue(jsonUrl, User.class);
		System.out.println(user.getName().getLast());

		user = mapper.readValue(jsonStr, User.class);
		System.out.println(user.getGender());

		// convert java object back to json
//		mapper.writeValue(new File("article.json"), user);

		String freak = mapper.writeValueAsString(user);
		System.out.println(freak);
		System.out.println(mapper.writeValueAsString(user));

	}
}
