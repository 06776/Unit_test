/*
 * File: Todo.java
 * Author: Patrik Hajdara
 * Copyright: 2023, Patrik Hajdara
 * Group: SZOFT II/2/N
 * Date: 2023-10-26
 * Github: https://github.com/06776/
 * Licenc: GNU GPL
 * Az esetlegesen elofordulo hibakert nem all modomban felelosseget vallalni
 */

public class Todo {

  Integer userId;
  Integer id;
  String title;
  Boolean completed;

  public Todo(Integer userId, String title, Boolean completed) {
    this.userId = userId;
    this.title = title;
    this.completed = completed;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Boolean getCompleted() {
    return completed;
  }

  public void setCompleted(Boolean completed) {
    this.completed = completed;
  }
}
