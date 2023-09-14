package com.example.demo.ServiceImplementation;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import com.example.demo.dto.CommentDTO;
import com.example.demo.dto.PostDTO;
import com.example.demo.entities.Comment;
import com.example.demo.entities.Post;
import com.example.demo.entities.User;
import com.example.demo.repositories.CommentRepository;
import com.example.demo.repositories.PostRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService{
	

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	
	public PostDTO create(CommentDTO commentDTO, Integer postId, String name) {

		User currentUser = userRepository.findByEmail(name);

		Optional<Post> post = postRepository.findById(postId);

		if (post.isPresent()) {

			Comment comment = new Comment();
			
			comment.setUser(currentUser);
			comment.setComment(commentDTO.getComment());
			comment.setPost(commentDTO.getPost());
			
			post.get().getComments().add(comment);

			commentRepository.save(comment);
			postRepository.save(post.get());

		}
		return new PostDTO(post.get());

	}

}
