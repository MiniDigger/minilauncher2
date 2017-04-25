package me.minidigger.minilauncher.dtos;

/**
 * Created by mbenndorf on 25.04.2017.
 */
public class Profile {

    public String name;
    public String gameDir;
    public String version;

    @Override
    public String toString() {
        return "Profile{" +
                "name='" + name + '\'' +
                ", gameDir='" + gameDir + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}
