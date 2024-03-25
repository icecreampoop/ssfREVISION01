package pck.tfip.ssfrevision01.ssfrevision01.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import pck.tfip.ssfrevision01.ssfrevision01.models.UserInfoModel;

@Controller
@RequestMapping
public class LoginController {

    @GetMapping(path={"/login", "/", "/index"})
    public String login(Model model){

        UserInfoModel userInfo = new UserInfoModel();
        model.addAttribute("userInfo", userInfo);

        return "login";
    }
    
    @PostMapping("/login")
    public String postLoginHandler(@Valid @ModelAttribute("userInfo") UserInfoModel userinfomodel, BindingResult result,
    Model model, HttpSession session) {

        if (result.hasErrors()) {
            return "login";
        }

        session.setAttribute("userinfo", userinfomodel);

        return "productlist";
    }
}
