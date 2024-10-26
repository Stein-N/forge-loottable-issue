package com.example.examplemod;

import com.mojang.logging.LogUtils;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ExampleMod.MODID)
public class ExampleMod  {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "examplemod";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public ExampleMod(FMLJavaModLoadingContext context) {

    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("HELLO from server starting");
    }

    @SubscribeEvent
    public static void onLootTableLoad(LootTableLoadEvent event) {
        LOGGER.info("HELLO FROM LOOT TABLE LOAD EVENT - FROM MAIN CLASS");
        LOGGER.info("LOOT TABLE: {}", event.getTable().getLootTableId());
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class EventsOnForgeBus {

        @SubscribeEvent
        public static void onLootTableLoad(LootTableLoadEvent event) {
            // Some client setup code
            LOGGER.info("HELLO FROM LOOT TABLE LOAD EVENT - FROM SEPERATE CLASS - FORGE BUS");
            LOGGER.info("LOOT TABLE: {}", event.getTable().getLootTableId());
        }
    }
}
