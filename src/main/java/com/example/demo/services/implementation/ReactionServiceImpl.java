package com.example.demo.services.implementation;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CommentDTO;
import com.example.demo.dto.PostDTO;
import com.example.demo.dto.ReactionDTO;
import com.example.demo.entities.Comment;
import com.example.demo.entities.Post;
import com.example.demo.entities.Reaction;
import com.example.demo.entities.User;
import com.example.demo.repositories.CommentRepository;
import com.example.demo.repositories.PostRepository;
import com.example.demo.repositories.ReactionRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.ReactionService;

@Service
public class ReactionServiceImpl implements ReactionService {

	private final ReactionRepository reactionRepository;

	private final PostRepository postRepository;
	
	private final UserRepository userRepository;
	
	private final CommentRepository commentRepository;
	
	public ReactionServiceImpl(ReactionRepository reactionRepository, PostRepository postRepository,
			UserRepository userRepository, CommentRepository commentRepository) {
		this.reactionRepository = reactionRepository;
		this.postRepository = postRepository;
		this.userRepository = userRepository;
		this.commentRepository = commentRepository;
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
	
	@Override
	public CommentDTO createCommentReaction(Integer commentId, String reactionType, String name) throws Exception{
		
		User currentUser = userRepository.findByEmail(name);
		Optional<Comment> comment = commentRepository.findById(commentId);
		
		if(comment.isEmpty()) {
			throw new Exception();
		}
		
		Reaction reactions = new Reaction();
		reactions.setUser(currentUser);
		reactions.setComment(comment.get());
		
		if ("like".equals(reactionType)) {
			reactions.setLike(true);
			reactions.setDislike(false);
		}
		if ("dislike".equals(reactionType)) {
			reactions.setLike(false);
			reactions.setDislike(true);
		} 
		
		reactionRepository.save(reactions);
		reactionRepository.save(comment.get());
		commentRepository.save(comment.get());
		
		
		
		return new CommentDTO(comment.get());
	}
}



