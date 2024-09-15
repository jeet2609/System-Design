/*
    1. This pattern provides a simplified interface to a complex system of class, libraries or frameworks.
    2. So facade provides single entry point for interacting with complex system.
*/

/*************************** Without Facade ****************************/
class DVDPlayer {
    public void on() {
        System.out.println("DVD player on");
    }

    public void play(String movie) {
        System.out.println("Playing movie : " + movie);
    }

    public void off() {
        System.out.println("DVD player off");
    }
}

class Projector {
    public void on() {
        System.out.println("Projector on");
    }

    public void setInput(DVDPlayer dvd) {
        System.out.println("Projector input set to dvd");
    }

    public void off() {
        System.out.println("Projector off");
    }
}

class Lights {
    public void dim(int level) {
        System.out.println("Light dimmed to : " + level + "%");
    }
}

class SoundSystem {
    public void on() {
        System.out.println("Sound System on");
    }

    public void setVolume(int level) {
        System.out.println("Setting volume to : " + level);
    }

    public void off() {
        System.out.println("Sound System off");
    }
}

// Client
class HomeTheaterTest {
    public static void main(String[] args) {
        DVDPlayer dvdPlayer = new DVDPlayer();
        Projector projector = new Projector();
        Lights lights = new Lights();
        SoundSystem soundSystem = new SoundSystem();

        dvdPlayer.on();
        projector.on();
        projector.setInput(dvdPlayer);
        lights.dim(30);
        soundSystem.on();
        soundSystem.setVolume(10);
        dvdPlayer.play("Inception");
    }
}

/*
    1. Client requires all work to do
*/


/*************************** With Facade ****************************/
class HomeTheaterFacade {
    private DVDPlayer dvdPlayer;
    private Projector projector;
    private Lights lights;
    private SoundSystem soundSystem;

    public HomeTheaterFacade(DVDPlayer dvdPlayer, Projector projector, Lights lights, SoundSystem soundSystem) {
        this.dvdPlayer = dvdPlayer;
        this.projector = projector;
        this.lights = lights;
        this.soundSystem = soundSystem;
    }

    public void watchMovie(String movie) {
        System.out.println("Get ready to watch the movie...");
        dvdPlayer.on();
        projector.on();
        projector.setInput(dvdPlayer);
        lights.dim(30);
        soundSystem.on();
        soundSystem.setVolume(10);
        dvdPlayer.play("Inception");
    }

    public void endMovie() {
        System.out.println("Shutting down the movie...");
        lights.dim(0);
        projector.off();
        soundSystem.off();
        dvdPlayer.off();
    }
}

// Client
class HomeTheaterTest2 {
    public static void main(String[] args) {
        DVDPlayer dvdPlayer = new DVDPlayer();
        Projector projector = new Projector();
        Lights lights = new Lights();
        SoundSystem soundSystem = new SoundSystem();

        HomeTheaterFacade homeTheaterFacade = new HomeTheaterFacade(dvdPlayer, projector, lights, soundSystem);

        // Simplified interface for the user
        homeTheaterFacade.watchMovie("Inception");
        homeTheaterFacade.endMovie();
    }
}