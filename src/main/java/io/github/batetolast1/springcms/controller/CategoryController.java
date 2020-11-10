package io.github.batetolast1.springcms.controller;

import io.github.batetolast1.springcms.dto.CategoryDto;
import io.github.batetolast1.springcms.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    public static final String LIST = "category/list";
    public static final String LIST_REDIRECT = "redirect:/category/list";
    public static final String DELETE = "category/delete";
    public static final String ADD = "category/add";
    public static final String EDIT = "category/edit";

    private final CategoryService categoryService;

    @GetMapping("/list")
    public String displayList(Model model) {
        List<CategoryDto> categoryDtos = categoryService.getAll();
        model.addAttribute("categoryDtos", categoryDtos);
        return LIST;
    }

    @GetMapping("/confirm-delete")
    public String confirmDelete(Model model, @RequestParam(name = "id") Long id) {
        model.addAttribute("id", id);
        return DELETE;
    }

    @GetMapping("/delete")
    public String processDelete(@RequestParam(name = "id") Long id) {
        categoryService.delete(id);
        return LIST_REDIRECT;
    }

    @GetMapping("/add")
    public String displayAddForm(Model model) {
        model.addAttribute("categoryDto", new CategoryDto());
        return ADD;
    }

    @PostMapping("/add")
    public String processAddForm(@Valid CategoryDto categoryDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ADD;
        }

        categoryService.save(categoryDto);
        return LIST_REDIRECT;
    }

    @GetMapping("/edit")
    public String displayEditForm(Model model, @RequestParam(name = "id") Long id) {
        CategoryDto categoryDto = categoryService.getById(id);
        model.addAttribute("categoryDto", categoryDto);
        return EDIT;
    }

    @PostMapping("/edit")
    public String processEditForm(@Valid CategoryDto categoryDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return EDIT;
        }

        categoryService.edit(categoryDto);
        return LIST_REDIRECT;
    }
}
