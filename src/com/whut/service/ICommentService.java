package com.whut.service;

import com.whut.model.Comment;
import com.whut.utils.Page;

public interface ICommentService {
	
	public int addComment(Comment comment);

	public int deleteCommentById(Integer id);

	public Page<Comment> findCommentsByBookId(Integer bookId, int pageNum, int pageSize);
}
