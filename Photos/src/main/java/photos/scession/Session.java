package photos.scession;

import photos.constructors.Album;
import photos.constructors.Photo;
import photos.constructors.User;

/**
 * Structure for a session
 * @author Joseph McQuigg
 */
public class Session {
    /** The current Logged-in user */
    private User currentUser;
    /** The current Album */
    private Album currentAlbum;

    /** The current Photo */
    private Photo currentPhoto;

    /**
     * Creates a new session
     * @param user The user to set as current
     */
    public Session(User user) {
        currentUser = user;
        currentAlbum = null;
        currentPhoto = null;
    }

    /**
     * gats the current user
     * @return The current user
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * Sets the current album
     * @param album The album to set as current
     */
    public void setCurrentAlbum(Album album) {
        currentAlbum = album;
    }

    /**
     * Gets the current album
     * @return The current album
     */
    public Album getCurrentAlbum() {
        return currentAlbum;
    }

    /**
     * Sets the current photo
     * @param photo The photo to set as current
     */
    public void setCurrentPhoto(Photo photo) {
        currentPhoto = photo;
    }

    /**
     * Gets the current photo
     * @return The current photo
     */
    public Photo getCurrentPhoto() {
        return currentPhoto;
    }

    /**
     * Logs out the current user
     */
    public void logout() {
        currentUser = null;
        currentAlbum = null;
        currentPhoto = null;
    }
}
