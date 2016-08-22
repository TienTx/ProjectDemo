/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tientx.supercode.myproejectdemov3.controller;

import java.util.ArrayList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import tientx.supercode.myproejectdemov3.model.User;
import tientx.supercode.myproejectdemov3.service.SimilarityService;
import tientx.supercode.myproejectdemov3.service.SimilarityServiceImpl;
import tientx.supercode.myproejectdemov3.service.UserService;
import tientx.supercode.myproejectdemov3.service.UserServiceImpl;

/**
 *
 * @author zOzDarKzOz
 */
@Controller
public class HomeCtr
{

    private final UserService userService = new UserServiceImpl();
    private final SimilarityService similarityService = new SimilarityServiceImpl();

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public String redirectToHome(Model model)
    {
        return "redirect:/home.html";
    }

    @RequestMapping(value = "home.html", method = RequestMethod.GET)
    public ModelAndView home(ModelAndView mav)
    {
        mav = new ModelAndView("home");
        ArrayList<User> users = userService.getAll();
        mav.addObject("users", users);
        return mav;
    }
}
