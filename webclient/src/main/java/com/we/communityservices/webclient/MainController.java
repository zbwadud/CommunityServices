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
import org.springframework.web.bind.annotation.ResponseBody;


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
    /*
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
    */
    //JSP form submission END
    
    
    //React form submission Start
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String reactForm(Model model) throws Exception {//Map<String, Object> model
        model.addAttribute("comment", new Comment());        
        List<Comment> comments = service.getComments();
        String commentBox = react.renderCommentBox(comments);
        String data = mapper.writeValueAsString(comments);
        model.addAttribute("commentContent", commentBox);
        model.addAttribute("data", data);
        System.out.println("GET:-"+data.toString());
        return "index";
    }
    
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public String reactForm(Comment comment, Model model) throws Exception {
        System.out.println("POST:-"+model.toString()+"Comment"+comment.getAuthor());
        
        /*List<Comment> comments = service.getComments();
        String commentBox = react.renderCommentBox(comments);
        String data = mapper.writeValueAsString(comments);
        model.addAttribute("commentContent", commentBox);
        model.addAttribute("data", data);*/
        
        return "index";
    }
    //React form submission END
    
    

}
