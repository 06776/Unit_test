/*
 * File: Client.java
 * Author: Patrik Hajdara
 * Copyright: 223, Patrik Hajdara
 * Group: SZOFT II/2/N
 * Date: 2023-10-24
 * Github: https://github.com/06776/
 * Licenc: GNU GPL
 * Az esetlegesen elofordulo hibakert nem all modomban felelosseget vallalni
 */

package com.example.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.Builder;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class Client {

  HttpClient client;

  public Client() throws IOException {
    client = HttpClient.newHttpClient();
  }

  public String get(String url) {
    HttpRequest request = genGetRequest(url);
    return sendRequest(request);
  }

  public HttpRequest genGetRequest(String url) {
    HttpRequest request = HttpRequest
      .newBuilder()
      .uri(URI.create(url))
      .GET()
      .build();
    return request;
  }

  public String post(String url, String body, String... args) {
    HttpRequest request = genPostRequest(url, body, args);
    return sendRequest(request);
  }

  public HttpRequest genPostRequest(String url, String body, String... args) {
    Builder builder = HttpRequest.newBuilder();
    builder.uri(URI.create(url));
    builder.POST(HttpRequest.BodyPublishers.ofString(body));
    builder.header("Content-Type", "application/json");

    if (args.length > 0) {
      builder.header("Authorization", "Bearer " + args[0]);
    }
    return builder.build();
  }

  public String put(String url, String body, String... args) {
    HttpRequest request = genPutRequest(url, body, args);
    return sendRequest(request);
  }

  public HttpRequest genPutRequest(String url, String body, String... args) {
    Builder builder = HttpRequest.newBuilder();
    builder.uri(URI.create(url));
    builder.PUT(HttpRequest.BodyPublishers.ofString(body));
    builder.header("Content-Type", "application/json");

    if (args.length > 0) {
      builder.header("Authorization", "Bearer " + args[0]);
    }
    return builder.build();
  }

  public String delete(String url, String... args) {
    HttpRequest request = genDeleteRequest(url, args);
    return sendRequest(request);
  }

  public HttpRequest genDeleteRequest(String url, String... args) {
    Builder builder = HttpRequest.newBuilder();
    builder.uri(URI.create(url));
    builder.DELETE();

    if (args.length > 0) {
      builder.header("Authorization", "Bearer " + args[0]);
    }
    return builder.build();
  }

  public String sendRequest(HttpRequest request) {
    String result = "";
    try {
      result = trySendRequest(request);
    } catch (InterruptedException e) {
      System.err.println("'Hiba, megszakitas tortent!'");
      System.err.println(e);
    } catch (IOException e) {
      System.err.println("'Hiba tortent az atvitel soran!'");
      System.err.println(e);
    }
    return result;
  }

  public String trySendRequest(HttpRequest request)
    throws IOException, InterruptedException {
    HttpResponse<String> response = client.send(
      request,
      BodyHandlers.ofString()
    );
    return response.body();
  }
}
