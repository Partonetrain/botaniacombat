package info.partonetrain.botaniacombat.item;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import vazkii.botania.common.item.BotaniaItems;

public class BotaniaCombatTiers {
    //Botania has "dummy" tiers, so the actual in-game stats of BotaniaCombat items are defined here
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
            return Ingredient.of(BotaniaItems.manaSteel);
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
            return  Ingredient.of(BotaniaItems.elementium);
        }
    };
    public static final Tier TERRASTEEL_TIER = new Tier() {
        @Override
        public int getUses() { return 3200;}

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
            return  Ingredient.of(BotaniaItems.terrasteel);
        }
    };



}
