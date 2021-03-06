/*
 * Copyright 2021 Infernal Studios
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.infernalstudios.miningmaster.client;

import net.minecraft.client.gui.ScreenManager;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import org.infernalstudios.miningmaster.client.gui.screen.inventory.GemForgeScreen;
import org.infernalstudios.miningmaster.config.gui.ConfigScreen;
import org.infernalstudios.miningmaster.init.MMContainerTypes;
import org.infernalstudios.miningmaster.init.MMItems;

public class MiningMasterClient {
    public static void init() {
        // Registering Config GUI Extension Point
        ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.CONFIGGUIFACTORY, () -> (mc, screen) -> new ConfigScreen());

        ItemModelsProperties.registerProperty(MMItems.AIR_MALACHITE_BOW.get(), new ResourceLocation("pull"), (itemStack, clientWorld, livingEntity) -> {
            if (livingEntity == null) {
                return 0.0F;
            } else {
                return livingEntity.getActiveItemStack() != itemStack ? 0.0F : (float) (itemStack.getUseDuration() - livingEntity.getItemInUseCount()) / 20.0F;
            }
        });

        ItemModelsProperties.registerProperty(MMItems.AIR_MALACHITE_BOW.get(), new ResourceLocation("pulling"), (itemStack, clientWorld, livingEntity) -> livingEntity != null && livingEntity.isHandActive() && livingEntity.getActiveItemStack() == itemStack ? 1.0F : 0.0F);

        ScreenManager.registerFactory(MMContainerTypes.GEM_FORGE_CONTAINER.get(), GemForgeScreen::new);
    }
}
