package com.asajenko.kudos.controller;

import com.asajenko.kudos.model.Kudos;
import com.asajenko.kudos.model.Period;
import com.asajenko.kudos.service.KudosService;
import com.asajenko.kudos.service.PeriodService;
import com.asajenko.kudos.service.SaveKudosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class KudosController {

    private final SaveKudosService saveKudosService;
    private final KudosService kudosService;
    private final PeriodService periodService;

    @Autowired
    public KudosController(SaveKudosService saveKudosService, KudosService kudosService, PeriodService periodService) {
        this.saveKudosService = saveKudosService;
        this.kudosService = kudosService;
        this.periodService = periodService;
    }

    @GetMapping({"/", "/add"})
    public String mainPage(Model model) {
        model.addAttribute("kudos", new Kudos());
        model.addAttribute("kudosList", kudosService.getAllKudosForActualInterval(periodService.getActualPeriod()));
        return "new";
    }

    @PostMapping("/saveKudos")
    public String saveKudos(@ModelAttribute Kudos kudos, Model model) {
        saveKudosService.saveKudos(kudos);
        model.addAttribute("kudos", new Kudos());
        model.addAttribute("kudosList", kudosService.getAllKudosForActualInterval(periodService.getActualPeriod()));
        return "new";
    }

    @GetMapping("/all")
    public String allPage(Model model) {
        model.addAttribute("kudosList", kudosService.getAllKudos());
        return "all";
    }

    @GetMapping("/my")
    public String myPage(Model model, @RequestParam(value = "search") String email) {
        model.addAttribute("kudosFromList", kudosService.getAllKudosFrom(email));
        model.addAttribute("kudosToList", kudosService.getAllKudosTo(email));
        return "my";
    }

    @GetMapping("/lottery")
    public String lottery(Model model) {
        Period period = periodService.finishPeriod();
        List<Kudos> allKudosForPeriodTime = kudosService.getAllKudosForPeriodTime(period);
        model.addAttribute("kudosList", List.copyOf(allKudosForPeriodTime));
        model.addAttribute("winKudos", kudosService.randomKudos(2, allKudosForPeriodTime));
        model.addAttribute("readKudos", kudosService.randomKudos(3, allKudosForPeriodTime));
        return "lottery";
    }
}
