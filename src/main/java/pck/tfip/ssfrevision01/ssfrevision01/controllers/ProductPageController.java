package pck.tfip.ssfrevision01.ssfrevision01.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import pck.tfip.ssfrevision01.ssfrevision01.models.UserInfoModel;
import pck.tfip.ssfrevision01.ssfrevision01.repos.ProductInfoRepo;

@Controller
@RequestMapping
public class ProductPageController {

    @Autowired
    ProductInfoRepo repo;

    @GetMapping("productlist")
    public String productListPageHandler(Model model, HttpSession session) {
        //if session not created
        if (session == null) {
            
            return "redirect:/";
        }

        //need to cast as session.getAttribute returns generic object
        UserInfoModel userInfo = (UserInfoModel) session.getAttribute("userinfo");

        model.addAttribute("userinfo", userInfo);
        model.addAttribute("productlist", repo.readProductListFromRedis());

        return "productlist";
    }
}
