package com.whut.service.impl;

import java.util.List;

import com.whut.dao.ICommentDao;
import com.whut.dao.impl.CommentDao;
import com.whut.model.Comment;
import com.whut.service.ICommentService;
import com.whut.utils.Page;

public class CommentService implements ICommentService {

	private ICommentDao commentDao = new CommentDao();

	@Override
	public int addComment(Comment comment) {
		return commentDao.addComment(comment);
	}

	@Override
	public int deleteCommentById(Integer id) {
		return commentDao.deleteCommentById(id);
	}

	@Override
	public Page<Comment> findCommentsByBookId(Integer bookId, int pageNum, int pageSize) {
		int pageBegin = (pageNum - 1) * pageSize;
		List<Comment> list = commentDao.findCommentsByBookId(bookId, pageBegin, pageSize);
		int totalRecord = commentDao.countCommentsByBookId(bookId);
		int totalPage = totalRecord / pageSize;
		if(totalRecord % pageSize != 0){
			totalPage++;
		}
		return new Page<>(pageNum,totalPage,list);
	}

}
