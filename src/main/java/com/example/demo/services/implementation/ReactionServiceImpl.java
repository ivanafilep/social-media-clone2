package com.example.demo.services.implementation;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.PostDTO;
import com.example.demo.dto.ReactionDTO;
import com.example.demo.entities.Post;
import com.example.demo.entities.Reaction;
import com.example.demo.entities.User;
import com.example.demo.repositories.PostRepository;
import com.example.demo.repositories.ReactionRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.ReactionService;

@Service
public class ReactionServiceImpl implements ReactionService {

	private final ReactionRepository reactionRepository;

	private final PostRepository postRepository;
	
	private final UserRepository userRepository;
	
	public ReactionServiceImpl(ReactionRepository reactionRepository, PostRepository postRepository,
			UserRepository userRepository) {
		this.reactionRepository = reactionRepository;
		this.postRepository = postRepository;
		this.userRepository = userRepository;
	}
	
	@Override
	public PostDTO createPostReaction(Integer postId, String reactionType, String name) throws Exception {
		
		User currentUser = userRepository.findByEmail(name);
		Optional<Post> post = postRepository.findById(postId);

		if (post.isEmpty()) {
			throw new Exception();
		}
		
		Reaction reaction = new Reaction();
		reaction.setUser(currentUser);
		reaction.setPost(post.get());

		if ("like".equals(reactionType)) {
			reaction.setLike(true);
			reaction.setDislike(false);
		}
		if ("dislike".equals(reactionType)) {
			reaction.setLike(false);
			reaction.setDislike(true);
		} 

		reactionRepository.save(reaction);
		reactionRepository.save(post.get());
		postRepository.save(post.get());
	
		return new PostDTO(post.get());
	}
}



