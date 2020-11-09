package io.github.batetolast1.springcms.controller;

import io.github.batetolast1.springcms.dto.ArticleDto;
import io.github.batetolast1.springcms.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class HomePageController {

    private final ArticleService articleService;

    @GetMapping
    public String home(Model model) {
        List<ArticleDto> articleDtos = articleService.getLastArticles();
        model.addAttribute("articleDtos", articleDtos);
        return "index";
    }
}
