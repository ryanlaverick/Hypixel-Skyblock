package com.github.ryanlaverick.framework.filesystem;

import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;

public class CustomFile {
    private final String name;
    private final File directory;
    private FileConfiguration fileConfiguration;

    public CustomFile(String name, File directory) {
        this.name = name;
        this.directory = directory;
    }

    public String getName() {
        return name;
    }

    public File getDirectory() {
        return directory;
    }

    public FileConfiguration getFileConfiguration() {
        return fileConfiguration;
    }

    public void setFileConfiguration(FileConfiguration fileConfiguration) {
        this.fileConfiguration = fileConfiguration;
    }
}
