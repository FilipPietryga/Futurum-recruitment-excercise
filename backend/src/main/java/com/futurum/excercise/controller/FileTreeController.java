package com.futurum.excercise.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipFile;

@Tag(name = "File tree", description = "Display the file tree")
@RestController
@RequestMapping("/api/file-tree")
public class FileTreeController {

    @Tag(name = "File tree")
    @Operation(summary = "Get all files in the static folder", description = "Retrieve a list of all static files in the jar.")
    @GetMapping
    public List<String> listFiles() throws IOException {
        List<String> fileList = new ArrayList<>();
        Resource resource = new ClassPathResource("BOOT-INF/classes/static");

        try (ZipFile jarFile = new ZipFile(resource.getFile())) {
            jarFile.stream()
                    .filter(entry -> entry.getName().startsWith("BOOT-INF/classes/static"))
                    .forEach(entry -> fileList.add(entry.getName()));
        }

        return fileList;
    }
}
