package moses.zal.Individual.Assignment.Controllers;

import moses.zal.Individual.Assignment.DTOs.PostDTO;
import moses.zal.Individual.Assignment.Entities.Post;
import moses.zal.Individual.Assignment.Services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserRestController {

  private PostService postService;

  @Autowired
  public UserRestController(PostService postService) {
    this.postService = postService;
  }

  @GetMapping("/posts")
  public List<PostDTO> getAllPosts() {
    return postService.getAllPosts();
  }

  @PostMapping("/newpost")
  public ResponseEntity<String> postCreation (@RequestBody Post newPost) {
    return postService.postCreationLogic(newPost);
  }

  @PutMapping("/updatepost/{ID}")
  public ResponseEntity<String> postUpdate (@RequestBody Post postBeingEdited, @PathVariable int ID) {
    return postService.postUpdateLogic(postBeingEdited, ID);
  }

  @DeleteMapping ("/deletepost/{ID}")
  public ResponseEntity <String> postDeletion (@PathVariable int ID) {
    return postService.postDeletion(ID);
  }

  @GetMapping ("/posts/{ID}")
  public PostDTO postRetrieval (@PathVariable int ID) {
    return postService.postRetrieval(ID);
  }

}
