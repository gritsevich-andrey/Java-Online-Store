package com.gmail.andreygritsevich.web.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.gmail.andreygritsevich.service.ItemService;
import com.gmail.andreygritsevich.service.model.ItemDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import static com.gmail.andreygritsevich.web.constant.ControllerConstant.FIRST_PAGE_FOR_PAGINATION;

@Controller
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(
            ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public String getItemsPage(
            @RequestParam(name = "page", required = false) Integer page,
            Model model
    ) {
        if (page == null) {
            page = FIRST_PAGE_FOR_PAGINATION;
        }
        Long countItems = itemService.getCountItem();
        model.addAttribute("countItems", countItems);
        model.addAttribute("page", page);
        List<ItemDTO> itemsDTO = itemService.getItemsByPageSorted(page);
        model.addAttribute("items", itemsDTO);
        return "items";
    }

    @GetMapping("/{id}")
    public String getItemPage(@PathVariable Long id, Model model) {
        ItemDTO itemDTO = itemService.findById(id);
        if (itemDTO == null) {
            model.addAttribute("message", "Item not found for id='" + id + "'");
            model.addAttribute("redirect", "/items");
            return "message";
        }
        model.addAttribute("item", itemDTO);
        return "item";
    }

    @PostMapping("/delete")
    public String deleteItem(
            @RequestParam(name = "itemId") Long itemId,
            Model model) {
        itemService.deleteById(itemId);
        model.addAttribute("message", "Item was deleted successfully");
        model.addAttribute("redirect", "/items");
        return "message";
    }

    @PostMapping("/copy")
    public String copyItem(
            @RequestParam(name = "itemId") Long itemId,
            Model model) {
        itemService.copyById(itemId);
        model.addAttribute("message", "Item was copy successfully");
        model.addAttribute("redirect", "/items");
        return "message";
    }

    @GetMapping("/upload")
    public String getUploadItemsPage() {
        return "items_upload";
    }

    @PostMapping("/upload")
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
            Model model) {

        if (file.isEmpty()) {
            model.addAttribute("message", "Please select a file to upload");
            model.addAttribute("redirect", "/items/upload");
            return "message";
        }

        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(file.getOriginalFilename());
            Files.write(path, bytes);
            int countAddItems = itemService.addItemsAsJSON(path.toString());
            model.addAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'"
                            + " and add " + countAddItems + " items");
            model.addAttribute("redirect", "/items/upload");
        } catch (IOException e) {
            model.addAttribute("message", "Error upload file");
            model.addAttribute("redirect", "/items");
        }
        return "message";
    }

}
