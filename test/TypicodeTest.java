import com.example.client.Client;
import com.google.gson.Gson;
import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TypicodeTest {

  Client client;
  String host;

  @BeforeTest
  public void init() throws IOException {
    client = new Client();
    host = "https://jsonplaceholder.typicode.com/todos";
  }

  @Test
  public void testGetMethod() {
    String url = host;
    String result = client.get(url);
    boolean actual = result.contains("userId");
    Assert.assertTrue(actual);
  }

  @Test
  public void testPostMethod() {
    String url = host;
    Todo todo = new Todo(1, "Teszteles folyamatban", false);
    Gson gson = new Gson();
    String body = gson.toJson(todo);

    String result = client.post(url, body);
    Todo todoResult = gson.fromJson(result, Todo.class);
    String resultTitle = todoResult.getTitle();
    Boolean actual = resultTitle.equals("Teszteles folyamatban");
    Assert.assertTrue(actual);
  }
}
