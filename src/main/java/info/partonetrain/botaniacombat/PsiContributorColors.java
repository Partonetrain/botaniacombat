/*
 * This class reads Psi's contributors.properties file to get colors for Terrasteel weapons.
 * This class uses code from Psi.
 * https://github.com/VazkiiMods/Psi/blob/master/src/main/java/vazkii/psi/common/core/handler/ContributorSpellCircleHandler.java
 *
 * Psi is Open Source and distributed under the
 * Psi License: https://psi.vazkii.net/license.php
 */
package info.partonetrain.botaniacombat;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Stream;

public abstract class PsiContributorColors {
    private static volatile Map<String, int[]> colormap = Collections.emptyMap();

    public static void load(Properties props) {
        Map<String, int[]> map = new HashMap<>();

        for (String key : props.stringPropertyNames()) {
            String value = props.getProperty(key).replace("#", "0x");
            try {
                int[] values = Stream.of(value.split(",")).mapToInt(el -> Integer.parseInt(el.substring(2), 16)).toArray();
                map.put(key, values);
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                BotaniaCombat.LOGGER.error("Psi Contributor " + key + " has an invalid hexcode!");
            }
        }

        PsiContributorColors.colormap = map;
    }

    public static boolean isContributor(String name) {
        return colormap.containsKey(name);
    }

    public static int[] getColors(String name) {
        return colormap.getOrDefault(name, new int[]{ColorContainer.DEFAULT_COLOR});
    }

    public static void get() {
        try {
            URL url = new URL("https://raw.githubusercontent.com/Vazkii/Psi/master/contributors.properties");
            Properties props = new Properties();
            try (InputStreamReader reader = new InputStreamReader(url.openStream(), StandardCharsets.UTF_8)) {
                props.load(reader);
                load(props);
            }
        } catch (IOException e) {
            BotaniaCombat.LOGGER.info("Could not load Psi contributors list. Either you're offline or GitHub is down. Nothing to worry about, carry on~");
        }
    }
}
