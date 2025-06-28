package com.example.controller;

import com.example.service.FileService;
import com.example.service.ImageService;
import com.example.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Controller
@RequestMapping("/")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private FileService fileService;

    @Value("${file.upload-dir}")
    private String uploadDir;

    @GetMapping
    public String viewHomePage(Model model) {
        model.addAttribute("images", imageService.getAllItems());
        return "index";
    }

    @PostMapping("/images/add")
    public String addFile(@RequestParam("file") MultipartFile file,
                          @RequestParam("description") String description,
                          @RequestParam("name") String name, Model model) throws IOException {
        // Проверка на пустой файл
        if (file.isEmpty()) {
            model.addAttribute("error", "Файл не выбран!");
            return "redirect:/#gallery-headline";
        }

        // Генерация уникального имени для файла
        String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path uploadPath = Paths.get(uploadDir);

        // Создание директории, если она не существует
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Путь для сохранения файла
        Path filePath = uploadPath.resolve(filename);
        Files.write(filePath, file.getBytes());

        // Создание объекта изображения
        Item item = new Item();
        item.setName(name);
        item.setDescription(description);
        item.setLink(filename); // Ссылка на файл

        // Сохранение изображения в базу данных
        imageService.saveItem(item);

        // Обновление модели с новыми изображениями
        model.addAttribute("images", imageService.getAllItems());

        // Перенаправление на галерею изображений
        return "redirect:/#gallery-headline";
    }
}
