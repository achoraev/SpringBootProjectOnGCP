package com.mofino.springapp.web.controllers;

import com.mofino.springapp.domain.entities.models.binding.UserLoginBindingModel;
import com.mofino.springapp.domain.entities.models.binding.UserRegisterBindingModel;
import com.mofino.springapp.domain.entities.models.service.UserServiceModel;
import com.mofino.springapp.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/register")
    public ModelAndView register(ModelAndView modelAndView){
        modelAndView.setViewName("register.html");

        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView registerConfirm(@ModelAttribute UserRegisterBindingModel model, ModelAndView modelAndView){

        if (!model.getPassword().equals(model.getConfirmPassword())){
            throw new IllegalArgumentException("Passwords did not match!");
        }

        if (!this.userService.registerUser(this.modelMapper.map(model, UserServiceModel.class))){
            throw new IllegalArgumentException("Registration failed!");
        }

        modelAndView.setViewName("redirect:/login.html");
        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView login(ModelAndView modelAndView){

        modelAndView.setViewName("login.html");
        return modelAndView;
    }

    @PostMapping("/login")
    public ModelAndView loginConfirm(@ModelAttribute UserLoginBindingModel model,
                                     ModelAndView modelAndView, HttpSession session){

        modelAndView.setViewName("login.html");
        return modelAndView;
    }
}