package moses.zal.Individual.Assignment.Entities;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "blog_post")
public class Post {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "post_id")
  private int postID;

  @Column(name = "date_time")
  private String dateTime;

  @NotBlank(message = "\"Title\" can't be blank!")
  @Column (name = "title")
  private String title;

  @NotBlank(message = "\"Content\" can't be blank!")
  @Column (name = "content")
  private String content;

  // We don't want to allow "creating a new post" to create a new user. So "PERSIST" is removed from the cascade types.
  @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
  @JoinColumn(name = "user_id")
  @Valid
  private User user;

  public Post() {
  }

  public Post(String dateTime, String title, String content, User user) {
    this.dateTime = dateTime;
    this.title = title;
    this.content = content;
    this.user = user;
  }

  public int getPostID() {
    return postID;
  }

  public void setPostID(int postID) {
    this.postID = postID;
  }

  public String getDateTime() {
    return dateTime;
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

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
