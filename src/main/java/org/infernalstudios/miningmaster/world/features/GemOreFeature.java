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

package org.infernalstudios.miningmaster.world.features;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import org.infernalstudios.miningmaster.world.features.config.GemOreFeatureConfig;

public class GemOreFeature extends Feature<GemOreFeatureConfig> {

    public GemOreFeature(Codec<GemOreFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<GemOreFeatureConfig> context) {
        RandomSource rand = context.random();
        BlockPos pos = context.origin();
        WorldGenLevel level = context.level();
        GemOreFeatureConfig config = context.config();

        for (GemOreFeatureConfig.TargetWeightedState ruleTest : config.targetStates()) {
            if (ruleTest.target.test(level.getBlockState(pos), rand)) {
                if (ruleTest.states.isEmpty()) {
                    return false;
                }
                level.setBlock(pos, ruleTest.states.get(rand.nextInt(ruleTest.states.size())), 2);
            }
        }

        return true;
    }
}

