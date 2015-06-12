package com.we.communityservices.webclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * @author Benjamin Winterberg
 */
@Controller
public class MainController {

    private CommentService service;

    private React react;

    private ObjectMapper mapper;

    @Autowired
    public MainController(CommentService service) {
        this.service = service;
        this.react = new React();
        this.mapper = new ObjectMapper();
    }

    @RequestMapping("/")
    public String index(Map<String, Object> model) throws Exception {
        List<Comment> comments = service.getComments();
        String commentBox = react.renderCommentBox(comments);
        String data = mapper.writeValueAsString(comments);
        model.put("content", commentBox);
        model.put("data", data);
        return "index";
    }
    @RequestMapping("/todo")
    public String toDo(Map<String, Object> model) throws Exception {
        //List<Comment> comments = service.getComments();
        String toDo = react.renderToDo();
        String titleView = react.renderTitle("Zaid Wadud");
        //String data = mapper.writeValueAsString(comments);
        System.out.println("MainController:- "+titleView);
        model.put("content", toDo);
        model.put("title", titleView);
        //model.put("data", data);
        return "todo";
    }
    
    @RequestMapping("/react")
    public String toDoStatic(Map<String, Object> model) throws Exception {
        //List<Comment> comments = service.getComments();
        //String toDo = react.renderToDo();
        //String data = mapper.writeValueAsString(comments);
        model.put("content", "Content here...");
        //model.put("data", data);
        return "todo_static";
    }
    @RequestMapping("/html")
    public String html(Map<String, Object> model) throws Exception {
        //List<Comment> comments = service.getComments();
        //String toDo = react.renderToDo();
        //String data = mapper.writeValueAsString(comments);
        //model.put("content", toDo);
        //model.put("data", data);
        return "test";
    }

}
