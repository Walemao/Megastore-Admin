package com.walemao.megastore.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.walemao.megastore.service.CommentService;

@Controller
public class CommentController extends BaseController{
	private Logger logger = LoggerFactory.getLogger(CommentController.class);
	@Autowired
	private CommentService commentService;
}
