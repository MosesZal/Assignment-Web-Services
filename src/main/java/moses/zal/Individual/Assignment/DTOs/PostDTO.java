package moses.zal.Individual.Assignment.DTOs;

import moses.zal.Individual.Assignment.Entities.Post;

public class PostDTO {
  private String username;
  private int postID;
  private String dateTime;
  private String title;
  private String content;

  public PostDTO() {
  }

  public PostDTO(String username, int postID, String dateTime, String title, String content) {
    this.username = username;
    this.postID = postID;
    this.dateTime = dateTime;
    this.title = title;
    this.content = content;
  }

  public PostDTO(Post post) {
    this.username = post.getUser().getUsername();
    this.postID = post.getPostID();
    this.dateTime = post.getDateTime();
    this.title = post.getTitle();
    this.content = post.getContent();
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public int getPostID() {
    return postID;
  }

  public void setPostID(int postID) {
    this.postID = postID;
  }

  public String getDateTime() {
    return dateTime.substring(0, dateTime.length() - 3);
  }

  public void setDateTime(String dateTime) {
    this.dateTime = dateTime;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }
}