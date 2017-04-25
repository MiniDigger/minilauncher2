package me.minidigger.minilauncher.util;

import java.io.File;

public class SystemManager {

    private final Platform platform;

    public SystemManager() {
        OS os;
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("win")) {
            os = OS.WINDOWS;
        } else if (osName.contains("mac")) {
            os = OS.MAC;
        } else {
            os = OS.LINUX;
        }

        Arch arch;
        String archName = System.getProperty("os.arch");
        if (archName.contains("64")) {
            arch = Arch.X64;
        } else {
            arch = Arch.X86;
        }

        platform = new Platform(os, arch);
    }

    public Platform getPlatform() {
        return platform;
    }

    public File getLauncherFolder() {
        return new File(getAppDataFolder() + File.separator + ".minilauncher" + File.separator);
    }

    public File getAppDataFolder() {
        final String dirName;
        switch (platform.getOS()) {
            case WINDOWS:
                dirName = System.getenv("APPDATA");
                break;
            case MAC:
                dirName = System.getProperty("user.home") + File.separator + "Library" + File.separator + "Application Support";
                break;
            default:
                dirName = System.getProperty("user.home");
                break;
        }
        return new File(dirName);
    }

    public File getMinecraftFolder() {
        return new File(getAppDataFolder() + File.separator + (platform.getOS() == OS.MAC ? "" : ".") + "minecraft");
    }

    public File getLauncherTemFolder() {
        return new File(getLauncherFolder() + File.separator + "temp");
    }

    public enum OS {

        WINDOWS("Windows", "windows"),
        MAC("Mac OS X", "osx"),
        LINUX("Other (Linux ?)", "linux");

        private String name;
        private String minecraftName;

        OS(String name, String minecraftName) {
            this.name = name;
            this.minecraftName = minecraftName;
        }

        public String getName() {
            return name;
        }

        public String getMinecraftName() {
            return minecraftName;
        }

    }

    public enum Arch {

        X86("x86"),
        X64("x64");

        private String name;

        Arch(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

    }

    public class Platform {

        private OS os;
        private Arch arch;

        public Platform(OS os, Arch arch) {
            this.os = os;
            this.arch = arch;
        }

        public final OS getOS() {
            return os;
        }

        public final Arch getArch() {
            return arch;
        }
    }
}
