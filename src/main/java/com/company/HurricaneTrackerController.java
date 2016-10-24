package com.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Troy on 10/21/16.
 */
@Controller
public class HurricaneTrackerController {
    @Autowired
    HurricaneRepository hurricanes;

    @RequestMapping(path = "/",method = RequestMethod.GET)
    public String home(Model model, Integer category,String search) {
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
        return "home";
    }

    @RequestMapping(path = "/hurricane",method = RequestMethod.POST)
    public String addHurricane(String hname, String hlocation, Integer hcategory, String himage) {
        Hurricane h = new Hurricane(hname,hlocation,hcategory,himage);
        hurricanes.save(h);
        return "redirect:/";
    }
}
