package com.futurum.excercise.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Tag(name = "File tree", description = "Display the file tree")
@RestController
@RequestMapping("/api/file-tree")
public class FileTreeController {

    @Tag(name = "File tree")
    @Operation(summary = "Get all files in the static folder", description = "Retrieve a list of all static files in the jar.")
    @GetMapping
    public List<String> listFiles() throws IOException {
        List<String> fileList = new ArrayList<>();
        File dir = new ClassPathResource("static").getFile();
        listFilesRecursively(dir, fileList);
        return fileList;
    }

    private void listFilesRecursively(File dir, List<String> fileList) {
        if (dir.isDirectory()) {
            for (File file : dir.listFiles()) {
                listFilesRecursively(file, fileList);
            }
        } else {
            fileList.add(dir.getPath());
        }
    }
}
