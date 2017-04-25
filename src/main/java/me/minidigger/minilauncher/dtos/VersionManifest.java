package me.minidigger.minilauncher.dtos;

import com.sun.istack.internal.Nullable;

import java.util.List;
import java.util.Map;

/**
 * Created by mbenndorf on 25.04.2017.
 */
public class VersionManifest {

    public AssetIndex assetIndex;
    public String assets;
    public Map<String, Download> downloads; // client and server
    public String id;
    public List<Library> libraries;
    public Logging logging;
    public String mainClass;
    public String minecraftArguments;
    public int minimumLauncherVersion;
    public String releaseTime;
    public String time;
    public String type;

    @Override
    public String toString() {
        return "VersionManifest{" +
                "assetIndex=" + assetIndex +
                ", assets='" + assets + '\'' +
                ", downloads=" + downloads +
                ", id='" + id + '\'' +
                ", libraries=" + libraries +
                ", logging=" + logging +
                ", mainClass='" + mainClass + '\'' +
                ", minecraftArguments='" + minecraftArguments + '\'' +
                ", minimumLauncherVersion=" + minimumLauncherVersion +
                ", releaseTime='" + releaseTime + '\'' +
                ", time='" + time + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    public class AssetIndex {
        public String id;
        public String sha1;
        public int size;
        public String url;
        public int totalSize;

        @Override
        public String toString() {
            return "AssetIndex{" +
                    "id='" + id + '\'' +
                    ", sha1='" + sha1 + '\'' +
                    ", size=" + size +
                    ", url='" + url + '\'' +
                    ", totalSize=" + totalSize +
                    '}';
        }
    }

    public class Download {
        public String sha1;
        public int size;
        public String url;

        @Override
        public String toString() {
            return "Download{" +
                    "sha1='" + sha1 + '\'' +
                    ", size=" + size +
                    ", url='" + url + '\'' +
                    '}';
        }
    }

    public class Library {
        public String name;
        public Downloads downloads;
        @Nullable
        public List<Rule> rules;
        @Nullable
        public Extract extract;
        @Nullable
        public Map<String, String> natives;

        @Override
        public String toString() {
            return "Library{" +
                    "name='" + name + '\'' +
                    ", downloads=" + downloads +
                    ", rules=" + rules +
                    ", extract=" + extract +
                    ", natives=" + natives +
                    '}';
        }

        public class Downloads {

            public Artifact artifact;
            @Nullable
            public Classifiers classifiers;

            @Override
            public String toString() {
                return "Downloads{" +
                        "artifact=" + artifact +
                        ", classifiers=" + classifiers +
                        '}';
            }

            public class Artifact {
                public int size;
                public String sha1;
                public String path;
                public String url;

                @Override
                public String toString() {
                    return "Artifact{" +
                            "size=" + size +
                            ", sha1='" + sha1 + '\'' +
                            ", path='" + path + '\'' +
                            ", url='" + url + '\'' +
                            '}';
                }
            }

            public class Classifiers {

                @Nullable
                public Artifact tests;
                @Nullable
                public Artifact nativesLinux;
                @Nullable
                public Artifact nativesOSX;
                @Nullable
                public Artifact nativesWindows;

                @Override
                public String toString() {
                    return "Classifiers{" +
                            "tests=" + tests +
                            ", nativesLinux=" + nativesLinux +
                            ", nativesOSX=" + nativesOSX +
                            ", nativesWindows=" + nativesWindows +
                            '}';
                }
            }
        }

        public class Rule {
            public String action;
            public OS os;

            @Override
            public String toString() {
                return "Rule{" +
                        "action='" + action + '\'' +
                        ", os=" + os +
                        '}';
            }

            public class OS {
                public String name;

                @Override
                public String toString() {
                    return "OS{" +
                            "name='" + name + '\'' +
                            '}';
                }
            }
        }

        public class Extract {
            public List<String> exclude;

            @Override
            public String toString() {
                return "Extract{" +
                        "exclude=" + exclude +
                        '}';
            }
        }
    }

    public class Logging {
        public Client client;

        @Override
        public String toString() {
            return "Logging{" +
                    "client=" + client +
                    '}';
        }

        public class Client {
            public Library.Downloads.Artifact file;
            public String argument;
            public String type;

            @Override
            public String toString() {
                return "Client{" +
                        "file=" + file +
                        ", argument='" + argument + '\'' +
                        ", type='" + type + '\'' +
                        '}';
            }
        }
    }
}
