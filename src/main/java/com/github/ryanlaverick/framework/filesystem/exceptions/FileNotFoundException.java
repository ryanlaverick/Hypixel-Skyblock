package com.github.ryanlaverick.framework.filesystem.exceptions;

public final class FileNotFoundException extends RuntimeException {
    public FileNotFoundException(String file) {
        super("File :file could not be loaded!".replace(":file", file));
    }
}
