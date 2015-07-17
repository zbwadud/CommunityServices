package com.we.communityservices.webclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMethod;

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
    //JSP form submission Start
    @RequestMapping(value = "/contactform", method = RequestMethod.GET)
    public String index(Model model) throws Exception {//Map<String, Object> model
        model.addAttribute("comment", new Comment());
        System.out.println("GET:-"+model.toString());
        
        return "contactform";
    }
    @RequestMapping(value = "/contactform", method = RequestMethod.POST)
    public String indexForm(@ModelAttribute Comment comment, Model model) throws Exception {//Map<String, Object> model
        model.addAttribute("comment", comment);
        System.out.println("POST:-Comment Auther "+comment.getAuthor()+"Comment Text"+comment.getText());
        
        return "result";
    }
    //JSP form submission END
    
    
    //React form submission Start
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String reactForm(Model model) throws Exception {//Map<String, Object> model
        model.addAttribute("comment", new Comment());
        System.out.println("GET:-"+model.toString());
        List<Comment> comments = service.getComments();
        String commentBox = react.renderCommentBox(comments);
        String data = mapper.writeValueAsString(comments);
        model.addAttribute("commentContent", commentBox);
        model.addAttribute("data", data);
        return "index";
    }
    
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String reactForm(@ModelAttribute Comment comment, Model model) throws Exception {//Map<String, Object> model
        System.out.println("POST:-"+model.toString()+"Comment"+comment.getAuthor());
        //Comment comment = new Comment();
        //comment.setAuthor("Wadud");
        //comment.setText("Probhu is here...");
        //service.addComment(comment);
        List<Comment> comments = service.getComments();
        String commentBox = react.renderCommentBox(comments);
        String data = mapper.writeValueAsString(comments);
        model.addAttribute("commentContent", commentBox);
        model.addAttribute("data", data);
        //model.put("commentContent", commentBox);
        //model.put("data", data);
        return "index";
    }
    //React form submission END
    
    @RequestMapping("/todo")
    public String toDo(Map<String, Object> model) throws Exception {
        //List<Comment> doList = service.getComments();
        int [] doList = {3};
        String toDo = react.renderToDo(doList);
        String data = mapper.writeValueAsString(toDo);
        String titleView = react.renderTitle("Zaid Wadud");
        //String data = mapper.writeValueAsString(comments);
        //System.out.println("MainController:- titleView--> "+titleView);
        //System.out.println("MainController:- todDdata--> "+data);
        //model.put("content", toDo);
        //model.put("data", data);
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
    @RequestMapping("/contactform")
    public String contactForm(Map<String, Object> model) throws Exception {
        //List<Comment> comments = service.getComments();
        //String toDo = react.renderToDo();
        //String data = mapper.writeValueAsString(comments);
        //model.put("content", toDo);
        //model.put("data", data);
        return "contactform";
    }

}
