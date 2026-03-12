package com.mkyong.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Controller
public class WelcomeController {

    // inject via application.properties
    @Value("${welcome.message}")
    private String message;

    private List<String> tasks = new ArrayList<>(Arrays.asList("Buy groceries", "Read a book", "Write tests"));

    @GetMapping("/")
    public String main(
            @RequestParam(name = "filter", required = false, defaultValue = "") String filter,
            Model model) {

        List<String> filtered = tasks.stream()
                .filter(t -> t.toLowerCase().contains(filter.toLowerCase()))
                .collect(Collectors.toList());

        model.addAttribute("message", message);
        model.addAttribute("tasks", filtered);
        model.addAttribute("filter", filter);

        System.out.println("Example log from std out.");
        System.out.println("Another example log from std out!");

        return "welcome"; //view
    }

    @PostMapping("/")
    public String addTask(
            @RequestParam(name = "task", required = false, defaultValue = "") String task) {

        String trimmed = task.trim();
        if (!trimmed.isEmpty()) {
            tasks.add(trimmed);
        }
        return "redirect:/";
    }

    // /hello?name=kotlin
    @GetMapping("/hello")
    public String mainWithParam(
            @RequestParam(name = "name", required = false, defaultValue = "") String name, Model model) {

        model.addAttribute("message", name);

        System.out.println("Hello controller, name = " + name);

        return "welcome"; //view
    }

}
