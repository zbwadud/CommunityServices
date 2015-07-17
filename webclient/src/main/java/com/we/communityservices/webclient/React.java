package com.we.communityservices.webclient;

import jdk.nashorn.api.scripting.NashornScriptEngine;

import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import javax.script.Invocable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class React {
    private static Logger LOG = LoggerFactory.getLogger(React.class);
    private NashornScriptEngine nashorn;

    public React() {
        try {
            LOG.info("Loading nashorn...");
            nashorn = (NashornScriptEngine) new ScriptEngineManager().getEngineByName("nashorn");
            nashorn.eval(read("static/nashorn-polyfill.js"));
            nashorn.eval(read("static/vendor/react.js"));
            //nashorn.eval(read("static/vendor/JSXTransformer.js"));
            nashorn.eval(read("static/vendor/showdown.min.js"));
            nashorn.eval(read("static/commentBox.js"));
            //nashorn.eval(read("static/toDo.js"));
            nashorn.eval(read("static/app.js"));
            nashorn.eval(read("static/contactForm.js"));
            
        }
        catch (ScriptException e) {
            throw new IllegalStateException("could not init nashorn", e);
        }
    }

    public String renderCommentBox(List<Comment> comments) {
        try {
            System.out.println("renderCommentBox::Server::Default Comment List Size:-"+comments.size());
            Invocable invocable = (Invocable) nashorn;
            Object html = invocable.invokeFunction("renderServer", comments);
            System.out.println("renderCommentBox::Server Calling "+html.toString());
            return String.valueOf(html);
        }
        catch (Exception e) {
            throw new IllegalStateException("failed to render react component", e);
        }
    }
    public String renderTitle(String arg1) {
        try {
            //System.out.println("React :--> Passing renderTitle"+arg1);
            Object html = nashorn.invokeFunction("renderTitle",arg1);
            //System.out.println("React :--> HTML OBJECT "+html.toString());
            return String.valueOf(html);
        }
        catch (Exception e) {
            throw new IllegalStateException("failed to render react component", e);
        }
    }
    
    public String renderToDo(int [] doList) {
        System.out.println("In React--data from client--->"+doList[0]);
        try {
            Object html = nashorn.invokeFunction("renderServer",doList);
            return String.valueOf(html);
        } catch (Exception e) {
            throw new IllegalStateException("***failed to render react component***", e);
        }
    }
    
    private Reader read(String path) {
        InputStream in = getClass().getClassLoader().getResourceAsStream(path);
        return new InputStreamReader(in);
    }
}
