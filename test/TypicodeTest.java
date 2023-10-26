/*
 * File: TypicodeTest.java
 * Author: Patrik Hajdara
 * Copyright: 2023, Patrik Hajdara
 * Group: SZOFT II/2/N
 * Date: 2023-10-26
 * Github: https://github.com/06776/
 * Licenc: GNU GPL
 * Az esetlegesen elofordulo hibakert nem all modomban felelosseget vallalni
 */

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
    Todo todo = new Todo(1, "Post teszteles folyamatban", false);
    Gson gson = new Gson();
    String body = gson.toJson(todo);

    String result = client.post(url, body);
    Todo todoResult = gson.fromJson(result, Todo.class);
    String resultTitle = todoResult.getTitle();
    Boolean actual = resultTitle.equals("Post teszteles folyamatban");
    Assert.assertTrue(actual);
  }

  @Test
  public void testPutMethod() {
    String url = host + "/1";
    Todo todo = new Todo(1, "Put teszteles folyamatban", false);
    Gson gson = new Gson();
    String body = gson.toJson(todo);

    String result = client.put(url, body);
    Todo todoResult = gson.fromJson(result, Todo.class);
    String resultTitle = todoResult.getTitle();
    Boolean actual = resultTitle.equals("Put teszteles folyamatban");
    Assert.assertTrue(actual);
  }

  @Test
  public void testDeleteMethod() {
    String url = host + "/1";
    String result = client.delete(url);
    Boolean actual = result.contains("{}");
    Assert.assertTrue(actual);
  }
}
