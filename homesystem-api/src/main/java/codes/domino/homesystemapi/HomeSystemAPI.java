package codes.domino.homesystemapi;

import codes.domino.homesystemapi.manager.HomeManager;

public final class HomeSystemAPI {

    private static HomeManager manager;

    public static void setManager(HomeManager manager) {
        HomeSystemAPI.manager = manager;
    }

    public static HomeManager getManager() {
        return manager;
    }

}
