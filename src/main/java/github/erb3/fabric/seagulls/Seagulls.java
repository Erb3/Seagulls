package github.erb3.fabric.seagulls;

import github.erb3.fabric.seagulls.entity.SeagullEntity;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class Seagulls implements ModInitializer {

    public static final EntityType<SeagullEntity> SEAGULL = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier("seagulls", "seagull"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, SeagullEntity::new)
                    .dimensions(EntityDimensions.fixed(0.75f, 0.75f)).build());

    public static final Item SEAGULL_SPAWN_EGG =
            new SpawnEggItem(Seagulls.SEAGULL, 0xc4c4c4c, 0xadadad, new FabricItemSettings());

    @Override
    public void onInitialize() {
        FabricDefaultAttributeRegistry.register(SEAGULL, SeagullEntity.attributes());
        Registry.register(Registries.ITEM, new Identifier("seagulls", "seagull_spawn_egg"), SEAGULL_SPAWN_EGG);


        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(content -> {
            content.add(SEAGULL_SPAWN_EGG);
        });
    }
}
