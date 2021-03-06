package com.benyamephrem.web.controller;

import com.benyamephrem.service.dto.stock.Stock;
import com.benyamephrem.service.resttemplate.stock.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MainController {

    @Autowired
    private StockService stockService;

    @RequestMapping("/")
    public String getHomepage(ModelMap modelMap){
        return "index";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String getTickerData(@RequestParam String ticker, RedirectAttributes redirectAttributes){

//        //Pass data to modelmap
//        try {
//            Stock stock = stockService.getStockHistoryByTicker(ticker);
//
//            redirectAttributes.addFlashAttribute("name", stock.getData().getName());
//            redirectAttributes.addFlashAttribute("description", stock.getData().getDescription());
//            redirectAttributes.addFlashAttribute("startDate", stock.getData().getStartDate());
//            redirectAttributes.addFlashAttribute("endDate", stock.getData().getEndDate());
//
//            redirectAttributes.addFlashAttribute("days",
//                    stock.getData().getStockFinancialData());
//
//        } catch(Exception ex){
//            return "error";
//        }

        return "redirect:/";
    }

}