package info.partonetrain.botaniacombat.item;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

public class BotaniaCombatTiers {
    //Botania has "dummy" tiers, so the actual in-game stats of BotaniaCombat items are defined here
    //(and... let's try to avoid classloading BotaniaItems!)
    public static final Tier MANASTEEL_TIER = new Tier() {
        @Override
        public int getUses() {
            return 300;
        }

        @Override
        public float getSpeed() {
            return 6.2f;
        }

        @Override
        public float getAttackDamageBonus() {
            return 6;
        }

        @Override
        public int getLevel() {
            return 3;
        }

        @Override
        public int getEnchantmentValue() {
            return 20;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.of(BuiltInRegistries.ITEM.get(new ResourceLocation("botania", "manasteel_ingot")));
        }
    };

    public static final Tier ELEMENTIUM_TIER = new Tier() {
        public int getUses() {
            return 720;
        }

        @Override
        public float getSpeed() {
            return 6.2f;
        }

        @Override
        public float getAttackDamageBonus() {
            return 6;
        }

        @Override
        public int getLevel() {
            return 3;
        }

        @Override
        public int getEnchantmentValue() {
            return 20;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.of(BuiltInRegistries.ITEM.get(new ResourceLocation("botania", "elementium_ingot")));
        }
    };
    public static final Tier TERRASTEEL_TIER = new Tier() {
        @Override
        public int getUses() {
            return 3200;
        }

        @Override
        public float getSpeed() {
            return 9;
        }

        @Override
        public float getAttackDamageBonus() {
            return 7;
        }

        @Override
        public int getLevel() {
            return 4;
        }

        @Override
        public int getEnchantmentValue() {
            return 26;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.of(BuiltInRegistries.ITEM.get(new ResourceLocation("botania", "terrasteel_ingot")));
        }
    };

    public static final Tier LIVINGWOOD_TIER = new Tier() {
        @Override
        public int getUses() {
            return 300;
        }

        @Override
        public float getSpeed() {
            return 6.2f;
        }

        @Override
        public float getAttackDamageBonus() {
            return 6;
        }

        @Override
        public int getLevel() {
            return 3;
        }

        @Override
        public int getEnchantmentValue() {
            return 20;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.of(BuiltInRegistries.ITEM.get(new ResourceLocation("botania", "manasteel_ingot")));
        }
    };

    public static final Tier CRYSTAL_TIER = new Tier() {
        @Override
        public int getUses() {
            return 500;
        }

        @Override
        public float getSpeed() {
            return 6.2f;
        }

        @Override
        public float getAttackDamageBonus() {
            return 6;
        }

        @Override
        public int getLevel() {
            return 3;
        }

        @Override
        public int getEnchantmentValue() {
            return 20;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.of(BuiltInRegistries.ITEM.get(new ResourceLocation("botania", "dragonstone")));
        }
    };
}
