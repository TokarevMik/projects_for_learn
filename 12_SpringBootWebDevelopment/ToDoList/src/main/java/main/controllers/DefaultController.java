package main.controllers;

import main.model.Deal;
import main.model.DealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DefaultController {
    @Autowired
    private DealRepository dealRepository;
    @RequestMapping("/")
        public String listOfAllCases(Model model){
        Iterable<Deal> dealIterable = dealRepository.findAll();
        List<String> dealList = new ArrayList<>();
        for (Deal deal:dealIterable) {
            StringBuilder sb = new StringBuilder();
            sb.append(deal.getId()).append(" ").append(deal.getName());
            dealList.add(sb.toString());
        }
        if(dealList.size()==0){
            model.addAttribute("deals","Нет дел в списке");
        }else{
        model.addAttribute("deals", dealList);}
        return "index";
    }
}
