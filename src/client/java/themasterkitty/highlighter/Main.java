package themasterkitty.highlighter;

import net.fabricmc.api.ClientModInitializer;

public class Main implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        Config.CONFIG.load();
    }
}
