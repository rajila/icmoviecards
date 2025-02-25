package com.lauracercas.moviecards.controller;

import com.lauracercas.moviecards.model.Actor;
import com.lauracercas.moviecards.model.Movie;
import com.lauracercas.moviecards.service.actor.ActorService;
import com.lauracercas.moviecards.util.Messages;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Autor: Laura Cercas Ramos
 * Proyecto: TFM Integración Continua con GitHub Actions
 * Fecha: 04/06/2024
 */
@Controller
public class ActorController {

    private final ActorService actorService;
    private static final String lblActor = "actor";
    private static final String lblTitle = "title";
    private static final String lblPageForm = "actors/form";

    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping("actors")
    public String getActorsList(Model model) {
        model.addAttribute("actors", actorService.getAllActors());
        return "actors/list";
    }

    @GetMapping("actors/new")
    public String newActor(Model model) {
        model.addAttribute(lblActor, new Actor());
        model.addAttribute(lblTitle, Messages.NEW_ACTOR_TITLE);
        return lblPageForm;
    }

    @PostMapping("saveActor")
    public String saveActor(Actor actor, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            Actor actorSaved = actorService.save(actor);
            if (actor.getId() != null) {
                model.addAttribute("message", Messages.UPDATED_ACTOR_SUCCESS);
            } else {
                model.addAttribute("message", Messages.SAVED_ACTOR_SUCCESS);
            }

            model.addAttribute(lblActor, actorSaved);
            model.addAttribute(lblTitle, Messages.EDIT_ACTOR_TITLE);
        }
        return lblPageForm;
    }

    @GetMapping("editActor/{actorId}")
    public String editActor(@PathVariable Integer actorId, Model model) {
        Actor actor = actorService.getActorById(actorId);
        List<Movie> movies = actor.getMovies();
        model.addAttribute(lblActor, actor);
        model.addAttribute("movies", movies);

        model.addAttribute(lblTitle, Messages.EDIT_ACTOR_TITLE);

        return lblPageForm;
    }


}
