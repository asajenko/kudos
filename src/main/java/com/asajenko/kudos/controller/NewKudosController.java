package com.asajenko.kudos.controller;

import com.asajenko.kudos.model.Kudos;
import com.asajenko.kudos.service.KudosService;
import com.asajenko.kudos.service.SaveKudosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class NewKudosController {

    private final SaveKudosService saveKudosService;
    private final KudosService kudosService;

    @Autowired
    public NewKudosController(SaveKudosService saveKudosService, KudosService kudosService) {
        this.saveKudosService = saveKudosService;
        this.kudosService = kudosService;
    }

    @GetMapping({"/", "/add"})
    public String mainPage(Model model) {
        model.addAttribute("kudos", new Kudos());
        model.addAttribute("kudosList", kudosService.getAllKudos());
        return "new";
    }

    @PostMapping("/saveKudos")
    public String saveKudos(@ModelAttribute Kudos kudos, Model model) {
        saveKudosService.saveKudos(kudos);
        model.addAttribute("kudos", new Kudos());
        model.addAttribute("kudosList", kudosService.getAllKudos());
        return "new";
    }
}
