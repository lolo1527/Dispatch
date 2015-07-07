package com.lbi.mytestapplication.process;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.transaction.UserTransaction;

import com.lbi.mytestapplication.domain.PostDAO;
import com.lbi.mytestapplication.domain.entity.Post;

public class PostManager {

	Logger logger = Logger.getLogger(PostManager.class.getName());
	
    @Inject
    PostDAO postDAO;

	@Inject
    private UserTransaction utx;

	public List<Post> getAllPosts() {
		return postDAO.getAllPosts();
	}

	public void createPost(Post p){
		try {
			utx.begin();
			postDAO.createPost(p);
			utx.commit();
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(),e);
			try {
				utx.rollback();
			} catch (Exception e1) {
				logger.log(Level.SEVERE, e.getMessage(),e);
			}
		}

	}
}
