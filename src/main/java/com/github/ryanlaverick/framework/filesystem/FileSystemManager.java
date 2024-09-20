package com.github.ryanlaverick.framework.filesystem;

import com.github.ryanlaverick.Skyblock;
import com.github.ryanlaverick.framework.filesystem.exceptions.FileNotFoundException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class FileSystemManager {
    private final Skyblock skyblock;
    private final List<CustomFile> files;

    private static final String DATABASE_FILE = "database.yml";
    private static final String AMQP_FILE = "amqp.yml";

    public FileSystemManager(Skyblock skyblock) {
        this.skyblock = skyblock;

        this.files = Arrays.asList(
                new CustomFile(DATABASE_FILE, this.skyblock.getDataFolder()),
                new CustomFile(AMQP_FILE, this.skyblock.getDataFolder())
        );
    }

    public void initFiles() {
        for (CustomFile file : this.getFiles()) {
            File configFile = new File(file.getDirectory(), file.getName());

            if (!configFile.exists()) {
                configFile.getParentFile().mkdirs();
                this.skyblock.saveResource(file.getName(), false);
            }

            file.setFileConfiguration(YamlConfiguration.loadConfiguration(configFile));
        }
    }

    public List<CustomFile> getFiles() {
        return files;
    }

    public CustomFile getDatabaseFile() {
        return this.getFile(DATABASE_FILE);
    }

    public CustomFile getAMQPFile() {
        return this.getFile(AMQP_FILE);
    }

    private CustomFile getFile(String file) {
        for (CustomFile customFile : this.getFiles()) {
            if (customFile.getName().equals(file)) {
                return customFile;
            }
        }

        throw new FileNotFoundException(file);
    }
}
