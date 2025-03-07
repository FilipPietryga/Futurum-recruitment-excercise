package com.futurum.excercise.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class FileTreeController {

    @GetMapping("/file-tree")
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
