package com.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Troy on 10/21/16.
 */
@Controller
public class HurricaneTrackerController {
    @Autowired
    HurricaneRepository hurricanes;

    @Autowired
    UserRepository users;

    @RequestMapping(path = "/",method = RequestMethod.GET)
    public String home(Model model, Integer category,String search,HttpSession session) {
        String name = (String) session.getAttribute("username");
        User user = users.findFirstByName(name);
        List<Hurricane> hlist;
        if (category!= null) {
            hlist = hurricanes.findByCategory(category);
        }
        else if (search != null) {
            hlist = hurricanes.findByNameContainingOrLocationContaining(search,search);
        }
        else {
            hlist = (List<Hurricane>) hurricanes.findAll();
        }
        model.addAttribute("hurricanes",hlist);
        model.addAttribute("user",user);
        return "home";
    }

    @RequestMapping(path = "/hurricane",method = RequestMethod.POST)
    public String addHurricane(String hname, String hlocation, Integer hcategory, String himage) {
        Hurricane h = new Hurricane(hname,hlocation,hcategory,himage);
        hurricanes.save(h);
        return "redirect:/";
    }

    @RequestMapping(path = "/login",method = RequestMethod.POST)
    public String login(String username, String password, HttpSession session) throws Exception {
        User user = users.findFirstByName(username);
        if (user == null) {
            user = new User(username,password);
            users.save(user);
        }
        else if (!password.equals(user.password)) {
            throw new Exception("Wrong password");
        }
        session.setAttribute("username",username);
        return "redirect:/";
    }

    @RequestMapping(path = "/logout",method = RequestMethod.POST )
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
