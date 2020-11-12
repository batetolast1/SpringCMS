package io.github.batetolast1.springcms.controller;

import io.github.batetolast1.springcms.dto.ArticleDto;
import io.github.batetolast1.springcms.dto.AuthorDto;
import io.github.batetolast1.springcms.dto.CategoryDto;
import io.github.batetolast1.springcms.service.AuthorService;
import io.github.batetolast1.springcms.service.CategoryService;
import io.github.batetolast1.springcms.service.DraftService;
import io.github.batetolast1.springcms.validation.groups.ArticleData;
import io.github.batetolast1.springcms.validation.groups.DraftData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/draft")
@RequiredArgsConstructor
public class DraftController {

    public static final String LIST = "draft/list";
    public static final String LIST_REDIRECT = "redirect:/draft/list";
    public static final String DELETE = "draft/delete";
    public static final String ADD = "draft/add";
    public static final String EDIT = "draft/edit";
    public static final String PUBLISH = "draft/publish";
    public static final String ARTICLE_LIST_REDIRECT = "redirect:/article/list";

    private final DraftService draftService;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    @ModelAttribute("authorDtos")
    public List<AuthorDto> authorDtos() {
        return authorService.getAll();
    }

    @ModelAttribute("categoryDtos")
    public List<CategoryDto> categoryDtos() {
        return categoryService.getAll();
    }

    @GetMapping("/list")
    public String displayList(Model model) {
        List<ArticleDto> draftDtos = draftService.getAll();
        model.addAttribute("draftDtos", draftDtos);
        return LIST;
    }

    @GetMapping("/confirm-delete")
    public String confirmDelete(Model model, @RequestParam(name = "id") Long id) {
        model.addAttribute("id", id);
        return DELETE;
    }

    @GetMapping("/delete")
    public String processDelete(@RequestParam(name = "id") Long id) {
        draftService.delete(id);
        return LIST_REDIRECT;
    }

    @GetMapping("/add")
    public String displayAddForm(Model model) {
        model.addAttribute("draftDto", new ArticleDto());
        return ADD;
    }

    @PostMapping("/add")
    public String processAddForm(@ModelAttribute("draftDto") @Validated(DraftData.class) ArticleDto draftDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ADD;
        }

        draftService.save(draftDto);
        return LIST_REDIRECT;
    }

    @GetMapping("/edit")
    public String displayEditForm(Model model, @RequestParam(name = "id") Long id) {
        ArticleDto draftDto = draftService.getById(id);

        if (draftDto != null) {
            model.addAttribute("draftDto", draftDto);
            return EDIT;
        }

        return LIST;
    }

    @PostMapping("/edit")
    public String processEditForm(@ModelAttribute("draftDto") @Validated(DraftData.class) ArticleDto draftDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return EDIT;
        }

        draftService.edit(draftDto);
        return LIST_REDIRECT;
    }

    @GetMapping("/publish")
    public String displayPublishForm(Model model, @RequestParam(name = "id") Long id) {
        ArticleDto draftDto = draftService.getById(id);
        model.addAttribute("draftDto", draftDto);
        return PUBLISH;
    }

    @PostMapping("/publish")
    public String processPublishForm(@ModelAttribute("draftDto") @Validated(ArticleData.class) ArticleDto draftDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return PUBLISH;
        }

        draftService.publish(draftDto);
        return ARTICLE_LIST_REDIRECT;
    }
}
