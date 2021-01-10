package me.stevetech.pvpholograms;

import com.gmail.filoghost.holographicdisplays.api.placeholder.PlaceholderReplacer;
import me.MathiasMC.PvPLevels.api.PvPLevelsAPI;
import me.MathiasMC.PvPLevels.managers.StatsManager;

public class PvPPlaceholders implements PlaceholderReplacer {
    StatsManager pvpStats = PvPLevelsAPI.getInstance().getStatsManager();
    String placeholder;
    int number;

    public PvPPlaceholders(String placeholderArg, int numberArg) {
        placeholder = placeholderArg;
        number = numberArg;
    }

    @Override
    public String update() {
        switch (placeholder) {
            case "kills_name":
            case "kills":
                return pvpStats.getTopKills(number, placeholder.endsWith("_name"));
            case "deaths_name":
            case "deaths":
                return pvpStats.getTopDeaths(number, placeholder.endsWith("_name"));
            case "xp_name":
            case "xp":
                return pvpStats.getTopXp(number, placeholder.endsWith("_name"));
            case "level_name":
            case "level":
                return pvpStats.getTopLevel(number, placeholder.endsWith("_name"));
            case "killstreak_name":
            case "killstreak":
                return pvpStats.getTopKillStreak(number, placeholder.endsWith("_name"));
            case "killstreak_top_name":
            case "killstreak_top":
                return pvpStats.getTopKillStreakTop(number, placeholder.endsWith("_name"));
            default:
                return null;
        }
    }

}
