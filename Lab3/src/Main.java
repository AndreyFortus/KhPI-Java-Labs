import java.util.ArrayList;

class File {
    private final String fileName;
    private final int fileSize;

    public File(String fileName, int fileSize) {
        this.fileName = fileName;
        this.fileSize = fileSize;
    }

    public String getFileName() {
        return fileName;
    }

    public int getFileSize() {
        return fileSize;
    }
}

class AudioFile extends File {
    public int duration;
    public int bitrate;
    public String artist;
    public AudioFile(String fileName, int fileSize, int duration, int bitrate, String artist) {
        super(fileName, fileSize);
        this.duration = duration;
        this.bitrate = bitrate;
        this.artist = artist;
    }

    public int getFileDuration() {
        return duration;
    }
    public int getFileBitrate() {
        return bitrate;
    }
    public String getFileArtist() {
        return artist;
    }

}

class VideoFile extends File {
    public int duration;
    public String resolution;
    public String codec;
    public VideoFile(String fileName, int fileSize, int duration, String resolution, String codec) {
        super(fileName, fileSize);
        this.duration = duration;
        this.resolution = resolution;
        this.codec = codec;
    }

    public int getFileDuration() {
        return duration;
    }
    public String getFileResolution() {
        return resolution;
    }
    public String getFileCodec() {
        return codec;
    }
}

class Catalog {
    private final ArrayList<File> files;

    public Catalog() {
        files = new ArrayList<>();
    }

    public void addFile(File file) {
        files.add(file);
    }

    public int countAudioFiles() {
        int count = 0;
        for (File file : files) {
            if (file instanceof AudioFile) {
                count++;
            }
        }
        return count;
    }

    public int countVideoFiles() {
        int count = 0;
        for (File file : files) {
            if (file instanceof VideoFile) {
                count++;
            }
        }
        return count;
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("Lab3 Andrii Fortus IKM-221a");

        AudioFile customAudioFile = new AudioFile("audio", 400, 270,
                360, "Eminem");
        System.out.println(customAudioFile.getFileName() + " " + customAudioFile.getFileSize()+ " "
                + customAudioFile.getFileDuration() + " " + customAudioFile.getFileBitrate()+ " "
                + customAudioFile.getFileArtist());

        VideoFile customVideoFile = new VideoFile("video", 2450, 1300,
            "1920x1080", "aptX");
        System.out.println(customVideoFile.getFileName() + " " + customVideoFile.getFileSize()+ " "
            + customVideoFile.getFileDuration() + " " + customVideoFile.getFileResolution()+ " "
            + customVideoFile.getFileCodec());

        VideoFile customVideoFile2 = new VideoFile("video2", 3700, 1850,
                "1920x1080", "aptX");
        System.out.println(customVideoFile.getFileName() + " " + customVideoFile.getFileSize()+ " "
                + customVideoFile.getFileDuration() + " " + customVideoFile.getFileResolution()+ " "
                + customVideoFile.getFileCodec());


        Catalog customCatalog = new Catalog();
        customCatalog.addFile(customAudioFile);
        customCatalog.addFile(customVideoFile);
        customCatalog.addFile(customVideoFile2);

        int audioFileCount = customCatalog.countAudioFiles();
        int videoFileCount = customCatalog.countVideoFiles();

        System.out.println("Number of audio files: " + audioFileCount);
        System.out.println("Number of video files: " + videoFileCount);
        }
    }
