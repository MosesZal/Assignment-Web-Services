package moses.zal.Individual.Assignment.Repositories;

import moses.zal.Individual.Assignment.Entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository <Post, Integer> {
}
