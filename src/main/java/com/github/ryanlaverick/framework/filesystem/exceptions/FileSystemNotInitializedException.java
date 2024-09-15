package com.github.ryanlaverick.framework.filesystem.exceptions;

public final class FileSystemNotInitializedException extends RuntimeException {
    public FileSystemNotInitializedException() {
        super("FileSystem module has not yet been initialized!");
    }
}
