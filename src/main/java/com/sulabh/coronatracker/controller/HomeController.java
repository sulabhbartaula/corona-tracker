package com.sulabh.coronatracker.controller;

import com.sulabh.coronatracker.services.CoronaDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    private final CoronaDateService coronaDateService;

    public HomeController(CoronaDateService coronaDateService) {
        this.coronaDateService = coronaDateService;
    }

    @GetMapping
    public String home(Model model){
        var allStats = coronaDateService.getAllData();
        int totalCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        model.addAttribute("allLocationData",allStats);
        model.addAttribute("totalReportedCases",totalCases);
        return "home";
    }
}
