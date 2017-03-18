package com.whut.dao;

import java.util.List;

import com.whut.model.Comment;

public interface ICommentDao {
	
	public int addComment(Comment comment);

	public int deleteCommentById(Integer id);

	public List<Comment> findCommentsByBookId(Integer bookId, int pageBegin, int pageSize);

	public int countCommentsByBookId(Integer bookId);
}
