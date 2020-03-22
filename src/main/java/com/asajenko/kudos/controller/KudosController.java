package com.asajenko.kudos.controller;

import com.asajenko.kudos.model.Kudos;
import com.asajenko.kudos.service.KudosService;
import com.asajenko.kudos.service.SaveKudosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class KudosController {

    private final SaveKudosService saveKudosService;
    private final KudosService kudosService;

    @Autowired
    public KudosController(SaveKudosService saveKudosService, KudosService kudosService) {
        this.saveKudosService = saveKudosService;
        this.kudosService = kudosService;
    }

    @GetMapping({"/", "/add"})
    public String mainPage(Model model) {
        model.addAttribute("kudos", new Kudos());
        model.addAttribute("kudosList", kudosService.getAllKudosForActualInterval());
        return "new";
    }

    @PostMapping("/saveKudos")
    public String saveKudos(@ModelAttribute Kudos kudos, Model model) {
        saveKudosService.saveKudos(kudos);
        model.addAttribute("kudos", new Kudos());
        model.addAttribute("kudosList", kudosService.getAllKudosForActualInterval());
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
}
