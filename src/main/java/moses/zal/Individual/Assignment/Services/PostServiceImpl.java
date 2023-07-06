package moses.zal.Individual.Assignment.Services;

import moses.zal.Individual.Assignment.DTOs.PostDTO;
import moses.zal.Individual.Assignment.Entities.Post;
import moses.zal.Individual.Assignment.Exceptions.ResourceNotFoundException;
import moses.zal.Individual.Assignment.Repositories.PostRepository;
import moses.zal.Individual.Assignment.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

  private PostRepository postRepository;
  private UserRepository userRepository;

  @Autowired
  public PostServiceImpl(PostRepository postRepository, UserRepository userRepository) {
    this.postRepository = postRepository;
    this.userRepository = userRepository;
  }

  @Override
  public List<PostDTO> getAllPosts() {
    List <Post> allPosts = postRepository.findAll();
    List <PostDTO> postDTOs = new ArrayList<>();
    for (Post post : allPosts) {
      PostDTO postDTO = new PostDTO (post);
      postDTOs.add(postDTO);
    }
    return postDTOs;
  }

  @Override
  public ResponseEntity<String> postCreationLogic(Post newPost) {
    newPost.setPostID(0);
    newPost.setDateTime(currentDateTimeFormatterAndConverter());
    if (newPost.getUser() == null || newPost.getUser().getUsername() == null)
      return new ResponseEntity<>("Process Failed! The username of the user associated with this post is needed!",
              HttpStatus.BAD_REQUEST);
    else if (userRepository.existsUserByUsername(newPost.getUser().getUsername())) {
      newPost.setUser(userRepository.findUserByUsername(newPost.getUser().getUsername()));
      postRepository.save(newPost);
      return new ResponseEntity<>("Post is created successfully!", HttpStatus.CREATED);
    }
    else
      throw new ResourceNotFoundException("user", "username", newPost.getUser().getUsername());
  }

  @Override
  public String currentDateTimeFormatterAndConverter() {
    LocalDateTime currentDateTime = LocalDateTime.now();
    DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    return DTF.format(currentDateTime);
  }

  @Override
  public ResponseEntity<String> postUpdateLogic(Post jsonPost, int postID) {
    Post databasePost = postRepository.findById(postID).orElseThrow(() -> new ResourceNotFoundException("post", "ID", postID));
    if (jsonPost.getTitle() != null)
      databasePost.setTitle(jsonPost.getTitle());
    if (jsonPost.getContent() != null)
      databasePost.setContent(jsonPost.getContent());
    postRepository.save(databasePost);
    return new ResponseEntity<>("Post is updated successfully!", HttpStatus.OK);
  }

  @Override
  public ResponseEntity<String> postDeletion(int postID) {
    postRepository.findById(postID).orElseThrow(() -> new ResourceNotFoundException("post", "ID", postID));
    postRepository.deleteById(postID);
    return new ResponseEntity<>("Post is deleted successfully!", HttpStatus.OK);
  }

  @Override
  public PostDTO postRetrieval(int postID) {
    return new PostDTO(postRepository.findById(postID).orElseThrow(() ->
            new ResourceNotFoundException("post", "ID", postID)));
  }
}