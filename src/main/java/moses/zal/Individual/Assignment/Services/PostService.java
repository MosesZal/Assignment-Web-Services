package moses.zal.Individual.Assignment.Services;

import moses.zal.Individual.Assignment.Entities.Post;
import moses.zal.Individual.Assignment.DTOs.PostDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PostService {
  List<PostDTO> getAllPosts();
  ResponseEntity<String> postCreationLogic(Post newPost);
  String currentDateTimeFormatterAndConverter();
  ResponseEntity<String> postUpdateLogic(Post postBeingEdited, int postID);
  ResponseEntity<String> postDeletion (int postID);
  PostDTO postRetrieval (int postID);


}
