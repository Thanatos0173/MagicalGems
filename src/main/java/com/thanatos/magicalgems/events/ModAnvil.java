
package com.thanatos.magicalgems.events;

//import com.thanatos.magicalgems.Enchantments.EnchantementInit;
import com.thanatos.magicalgems.Enchantments.EnchantementInit;
import com.thanatos.magicalgems.init.ModItems;
import com.thanatos.magicalgems.main;


import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


import java.util.*;

@Mod.EventBusSubscriber(modid = main.MODID)
public class ModAnvil {
    @SubscribeEvent
    public static void AnvilCraft(AnvilUpdateEvent anvilUpdateEvent) {
        ItemStack left = anvilUpdateEvent.getLeft();
        ItemStack right = anvilUpdateEvent.getRight();

        if(right.isEnchanted() && left.isEnchanted()){
            Map<Enchantment, Integer> enchantmentsLeft = EnchantmentHelper.getEnchantments(left);
            Map<Enchantment, Integer> enchantmentsRight = EnchantmentHelper.getEnchantments(right);
            boolean[] leftCustomEnchantmentList = {false,false,false,false,false,false,false,false,false,false,false,false,false,false};
            boolean[] rightCustomEnchantmentList = {false,false,false,false,false,false,false,false,false,false,false,false,false,false};
            if(leftCustomEnchantmentList.length != rightCustomEnchantmentList.length){
                anvilUpdateEvent.getPlayer().closeScreen();
                System.out.println("[MAGICALSGEM] A problem occured when trying to compare the lenght of leftCustomEnchantmentList and rightCustomEnchantmentList." +
                        "Advert the creator of the mod.");
            }

            for (Map.Entry<Enchantment, Integer> entry : enchantmentsLeft.entrySet()) {
                Enchantment enchantment = entry.getKey();
                if (enchantment == EnchantementInit.POISON_ENCHANT.get()) {
                    leftCustomEnchantmentList[0] = true;
                }
                if (enchantment == EnchantementInit.SLOWNESS_ENCHANT.get()) {
                    leftCustomEnchantmentList[1] = true;
                }
                if (enchantment == EnchantementInit.STRENGHT_ENCHANT.get()) {
                    leftCustomEnchantmentList[2] = true;
                }
                if (enchantment == EnchantementInit.DOLPHINS_GRACE_ENCHANT.get()) {
                    leftCustomEnchantmentList[3] = true;
                }
                if (enchantment == EnchantementInit.FIRE_RESISTANCE_ENCHANT.get()) {
                    leftCustomEnchantmentList[4] = true;
                }
                if (enchantment == EnchantementInit.HASTE_ENCHANT.get()) {
                    leftCustomEnchantmentList[5] = true;
                }
                if (enchantment == EnchantementInit.HEALTH_BOOST_ENCHANT.get()) {
                    leftCustomEnchantmentList[6] = true;
                }
                if (enchantment == EnchantementInit.JUMP_BOOST_ENCHANT.get()) {
                    leftCustomEnchantmentList[7] = true;
                }
                if (enchantment == EnchantementInit.NIGHT_VISION_ENCHANT.get()) {
                    leftCustomEnchantmentList[8] = true;
                }
                if (enchantment == EnchantementInit.REGENERATION_ENCHANT.get()) {
                    leftCustomEnchantmentList[9] = true;
                }
                if (enchantment == EnchantementInit.RESISTANCE_ENCHANT.get()) {
                    leftCustomEnchantmentList[10] = true;
                }
                if (enchantment == EnchantementInit.SLOW_FALLING_ENCHANT.get()) {
                    leftCustomEnchantmentList[11] = true;
                }
                if (enchantment == EnchantementInit.SPEED_ENCHANT.get()) {
                    leftCustomEnchantmentList[12] = true;
                }
                if (enchantment == EnchantementInit.WATER_BREATHING_ENCHANT.get()) {
                    leftCustomEnchantmentList[13] = true;
                }

            }
            for (Map.Entry<Enchantment, Integer> entry : enchantmentsRight.entrySet()) {
                Enchantment enchantment = entry.getKey();


                if (enchantment == EnchantementInit.POISON_ENCHANT.get()) {
                    rightCustomEnchantmentList[0] = true;
                }
                if (enchantment == EnchantementInit.SLOWNESS_ENCHANT.get()) {
                    rightCustomEnchantmentList[1] = true;
                }
                if (enchantment == EnchantementInit.STRENGHT_ENCHANT.get()) {
                    rightCustomEnchantmentList[2] = true;
                }
                if (enchantment == EnchantementInit.DOLPHINS_GRACE_ENCHANT.get()) {
                    rightCustomEnchantmentList[3] = true;
                }
                if (enchantment == EnchantementInit.FIRE_RESISTANCE_ENCHANT.get()) {
                    rightCustomEnchantmentList[4] = true;
                }
                if (enchantment == EnchantementInit.HASTE_ENCHANT.get()) {
                    rightCustomEnchantmentList[5] = true;
                }
                if (enchantment == EnchantementInit.HEALTH_BOOST_ENCHANT.get()) {
                    rightCustomEnchantmentList[6] = true;
                }
                if (enchantment == EnchantementInit.JUMP_BOOST_ENCHANT.get()) {
                    rightCustomEnchantmentList[7] = true;
                }
                if (enchantment == EnchantementInit.NIGHT_VISION_ENCHANT.get()) {
                    rightCustomEnchantmentList[8] = true;
                }
                if (enchantment == EnchantementInit.REGENERATION_ENCHANT.get()) {
                    rightCustomEnchantmentList[9] = true;
                }
                if (enchantment == EnchantementInit.RESISTANCE_ENCHANT.get()) {
                    rightCustomEnchantmentList[10] = true;
                }
                if (enchantment == EnchantementInit.SLOW_FALLING_ENCHANT.get()) {
                    rightCustomEnchantmentList[11] = true;
                }
                if (enchantment == EnchantementInit.SPEED_ENCHANT.get()) {
                    rightCustomEnchantmentList[12] = true;
                }
                if (enchantment == EnchantementInit.WATER_BREATHING_ENCHANT.get()) {
                    rightCustomEnchantmentList[13] = true;
                }

            }
            for(int i = 0; i < leftCustomEnchantmentList.length; i++ ){
                if (leftCustomEnchantmentList[i] == rightCustomEnchantmentList[i]){
                    anvilUpdateEvent.setCanceled(true);
                }

            }
        }

            //POISON
            if (right.isItemEqual(new ItemStack(ModItems.poison_gem.get(), 1))) {
                //Netherite and Diamond Sword
                if (left.isItemEqual(new ItemStack(Items.NETHERITE_SWORD, 1)) || left.isItemEqual(new ItemStack(Items.DIAMOND_SWORD, 1)))
                    if (!left.isEnchanted()) {
                        ItemStack output = new ItemStack(left.getItem());
                        output.addEnchantment(EnchantementInit.POISON_ENCHANT.get(), 3);
                        anvilUpdateEvent.setOutput(output);
                        anvilUpdateEvent.setCost(6);
                    } else {
                        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(left);
                        ItemStack output = new ItemStack(left.getItem());
                        for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
                            Enchantment enchantment = entry.getKey();
                            if (enchantment == EnchantementInit.POISON_ENCHANT.get()) {
                                anvilUpdateEvent.setCanceled(true);
                            }
                            output.addEnchantment(enchantment, entry.getValue());
                            output.addEnchantment(EnchantementInit.POISON_ENCHANT.get(), 3);
                            anvilUpdateEvent.setOutput(output);
                            anvilUpdateEvent.setCost(6);
                        }
                    }
                //Iron Sword
                if (left.isItemEqual(new ItemStack(Items.IRON_SWORD, 1)))
                    if (!left.isEnchanted()) {
                        ItemStack output = new ItemStack(left.getItem());
                        output.addEnchantment(EnchantementInit.POISON_ENCHANT.get(), 2);
                        anvilUpdateEvent.setOutput(output);
                        anvilUpdateEvent.setCost(6);
                    } else {
                        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(left);
                        ItemStack output = new ItemStack(left.getItem());
                        for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
                            Enchantment enchantment = entry.getKey();
                            if (enchantment == EnchantementInit.POISON_ENCHANT.get()) {
                                anvilUpdateEvent.setCanceled(true);
                            }
                            output.addEnchantment(enchantment, entry.getValue());
                            output.addEnchantment(EnchantementInit.POISON_ENCHANT.get(), 2);
                            anvilUpdateEvent.setOutput(output);
                            anvilUpdateEvent.setCost(6);
                        }
                    }
                //Gold Sword and Stone Sword and Wooden Sword
                if (left.isItemEqual(new ItemStack(Items.GOLDEN_SWORD, 1)) || left.isItemEqual(new ItemStack(Items.STONE_SWORD, 1)) || left.isItemEqual(new ItemStack(Items.WOODEN_SWORD, 1)))
                    if (!left.isEnchanted()) {
                        ItemStack output = new ItemStack(left.getItem());
                        output.addEnchantment(EnchantementInit.POISON_ENCHANT.get(), 1);
                        anvilUpdateEvent.setOutput(output);
                        anvilUpdateEvent.setCost(6);
                    } else {
                        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(left);
                        ItemStack output = new ItemStack(left.getItem());
                        for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
                            Enchantment enchantment = entry.getKey();
                            if (enchantment == EnchantementInit.POISON_ENCHANT.get()) {
                                anvilUpdateEvent.setCanceled(true);
                            }
                            output.addEnchantment(enchantment, entry.getValue());
                            output.addEnchantment(EnchantementInit.POISON_ENCHANT.get(), 1);
                            anvilUpdateEvent.setOutput(output);
                            anvilUpdateEvent.setCost(6);
                        }
                    }

            }
            //SLOWNESS
            if (right.isItemEqual(new ItemStack(ModItems.slowness_gem.get(), 1))) {
                //Netherite and Diamond Sword
                if (left.isItemEqual(new ItemStack(Items.NETHERITE_SWORD, 1)) || left.isItemEqual(new ItemStack(Items.DIAMOND_SWORD, 1)))
                    if (!left.isEnchanted()) {
                        ItemStack output = new ItemStack(left.getItem());
                        output.addEnchantment(EnchantementInit.SLOWNESS_ENCHANT.get(), 3);
                        anvilUpdateEvent.setOutput(output);
                        anvilUpdateEvent.setCost(6);
                    } else {
                        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(left);
                        ItemStack output = new ItemStack(left.getItem());
                        for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
                            Enchantment enchantment = entry.getKey();
                            if (enchantment == EnchantementInit.SLOWNESS_ENCHANT.get()) {
                                anvilUpdateEvent.setCanceled(true);
                            }
                            output.addEnchantment(enchantment, entry.getValue());
                            output.addEnchantment(EnchantementInit.SLOWNESS_ENCHANT.get(), 3);
                            anvilUpdateEvent.setOutput(output);
                            anvilUpdateEvent.setCost(6);
                        }
                    }

                //Iron Sword
                if (left.isItemEqual(new ItemStack(Items.IRON_SWORD, 1)))
                    if (!left.isEnchanted()) {
                        ItemStack output = new ItemStack(left.getItem());
                        output.addEnchantment(EnchantementInit.SLOWNESS_ENCHANT.get(), 2);
                        anvilUpdateEvent.setOutput(output);
                        anvilUpdateEvent.setCost(6);
                    } else {
                        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(left);
                        ItemStack output = new ItemStack(left.getItem());
                        for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
                            Enchantment enchantment = entry.getKey();
                            if (enchantment == EnchantementInit.SLOWNESS_ENCHANT.get()) {
                                anvilUpdateEvent.setCanceled(true);
                            }
                            output.addEnchantment(enchantment, entry.getValue());
                            output.addEnchantment(EnchantementInit.SLOWNESS_ENCHANT.get(), 2);
                            anvilUpdateEvent.setOutput(output);
                            anvilUpdateEvent.setCost(6);
                        }
                    }
                //Gold Sword and Stone Sword and Wooden Sword
                if (left.isItemEqual(new ItemStack(Items.GOLDEN_SWORD, 1)) || left.isItemEqual(new ItemStack(Items.STONE_SWORD, 1)) || left.isItemEqual(new ItemStack(Items.WOODEN_SWORD, 1)))
                    if (!left.isEnchanted()) {
                        ItemStack output = new ItemStack(left.getItem());
                        output.addEnchantment(EnchantementInit.SLOWNESS_ENCHANT.get(), 1);
                        anvilUpdateEvent.setOutput(output);
                        anvilUpdateEvent.setCost(6);
                    } else {
                        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(left);
                        ItemStack output = new ItemStack(left.getItem());
                        for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
                            Enchantment enchantment = entry.getKey();
                            if (enchantment == EnchantementInit.SLOWNESS_ENCHANT.get()) {
                                anvilUpdateEvent.setCanceled(true);
                                //SLOWNESS
                            }
                            output.addEnchantment(enchantment, entry.getValue());
                            output.addEnchantment(EnchantementInit.SLOWNESS_ENCHANT.get(), 1);
                            anvilUpdateEvent.setOutput(output);
                            anvilUpdateEvent.setCost(6);
                        }
                    }
            }
            //STRENGTH
            if (right.isItemEqual(new ItemStack(ModItems.strength_gem.get(), 1))) {
                //Netherite and Diamond Sword
                if (left.isItemEqual(new ItemStack(Items.NETHERITE_CHESTPLATE, 1)) || left.isItemEqual(new ItemStack(Items.DIAMOND_CHESTPLATE, 1)))
                    if (!left.isEnchanted()) {
                        ItemStack output = new ItemStack(left.getItem());
                        output.addEnchantment(EnchantementInit.STRENGHT_ENCHANT.get(), 3);
                        anvilUpdateEvent.setOutput(output);
                        anvilUpdateEvent.setCost(6);
                    } else {
                        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(left);
                        ItemStack output = new ItemStack(left.getItem());
                        for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
                            Enchantment enchantment = entry.getKey();
                            if (enchantment == EnchantementInit.STRENGHT_ENCHANT.get()) {
                                anvilUpdateEvent.setCanceled(true);
                            }
                            output.addEnchantment(enchantment, entry.getValue());
                            output.addEnchantment(EnchantementInit.STRENGHT_ENCHANT.get(), 3);
                            anvilUpdateEvent.setOutput(output);
                            anvilUpdateEvent.setCost(6);
                        }
                    }

                //Iron Sword
                if (left.isItemEqual(new ItemStack(Items.IRON_CHESTPLATE, 1)))
                    if (!left.isEnchanted()) {
                        ItemStack output = new ItemStack(left.getItem());
                        output.addEnchantment(EnchantementInit.STRENGHT_ENCHANT.get(), 2);
                        anvilUpdateEvent.setOutput(output);
                        anvilUpdateEvent.setCost(6);
                    } else {
                        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(left);
                        ItemStack output = new ItemStack(left.getItem());
                        for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
                            Enchantment enchantment = entry.getKey();
                            if (enchantment == EnchantementInit.STRENGHT_ENCHANT.get()) {
                                anvilUpdateEvent.setCanceled(true);
                            }
                            output.addEnchantment(enchantment, entry.getValue());
                            output.addEnchantment(EnchantementInit.STRENGHT_ENCHANT.get(), 2);
                            anvilUpdateEvent.setOutput(output);
                            anvilUpdateEvent.setCost(6);
                        }
                    }
                //Gold Sword and Stone Sword and Wooden Sword
                if (left.isItemEqual(new ItemStack(Items.GOLDEN_CHESTPLATE, 1)) || left.isItemEqual(new ItemStack(Items.CHAINMAIL_CHESTPLATE, 1)) || left.isItemEqual(new ItemStack(Items.LEATHER_CHESTPLATE, 1)))
                    if (!left.isEnchanted()) {
                        ItemStack output = new ItemStack(left.getItem());
                        output.addEnchantment(EnchantementInit.STRENGHT_ENCHANT.get(), 1);
                        anvilUpdateEvent.setOutput(output);
                        anvilUpdateEvent.setCost(6);
                    } else {
                        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(left);
                        ItemStack output = new ItemStack(left.getItem());
                        for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
                            Enchantment enchantment = entry.getKey();
                            if (enchantment == EnchantementInit.STRENGHT_ENCHANT.get()) {
                                anvilUpdateEvent.setCanceled(true);
                            }
                            output.addEnchantment(enchantment, entry.getValue());
                            output.addEnchantment(EnchantementInit.STRENGHT_ENCHANT.get(), 1);
                            anvilUpdateEvent.setOutput(output);
                            anvilUpdateEvent.setCost(6);
                        }
                    }
            }
            //DOLPHINS'S GRACE
            if (right.isItemEqual(new ItemStack(ModItems.dolphins_grace_gem.get(), 1))) {
                //Netherite and Diamond Sword
                if (left.isItemEqual(new ItemStack(Items.NETHERITE_LEGGINGS, 1)) || left.isItemEqual(new ItemStack(Items.DIAMOND_LEGGINGS, 1)))
                    if (!left.isEnchanted()) {
                        ItemStack output = new ItemStack(left.getItem());
                        output.addEnchantment(EnchantementInit.DOLPHINS_GRACE_ENCHANT.get(), 3);
                        anvilUpdateEvent.setOutput(output);
                        anvilUpdateEvent.setCost(6);
                    } else {
                        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(left);
                        ItemStack output = new ItemStack(left.getItem());
                        for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
                            Enchantment enchantment = entry.getKey();
                            if (enchantment == EnchantementInit.DOLPHINS_GRACE_ENCHANT.get()) {
                                anvilUpdateEvent.setCanceled(true);
                            }
                            output.addEnchantment(enchantment, entry.getValue());
                            output.addEnchantment(EnchantementInit.DOLPHINS_GRACE_ENCHANT.get(), 3);
                            anvilUpdateEvent.setOutput(output);
                            anvilUpdateEvent.setCost(6);
                        }
                    }
                //Iron Sword
                if (left.isItemEqual(new ItemStack(Items.IRON_LEGGINGS, 1)))
                    if (!left.isEnchanted()) {
                        ItemStack output = new ItemStack(left.getItem());
                        output.addEnchantment(EnchantementInit.DOLPHINS_GRACE_ENCHANT.get(), 2);
                        anvilUpdateEvent.setOutput(output);
                        anvilUpdateEvent.setCost(6);
                    } else {
                        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(left);
                        ItemStack output = new ItemStack(left.getItem());
                        for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
                            Enchantment enchantment = entry.getKey();
                            if (enchantment == EnchantementInit.DOLPHINS_GRACE_ENCHANT.get()) {
                                anvilUpdateEvent.setCanceled(true);
                            }
                            output.addEnchantment(enchantment, entry.getValue());
                            output.addEnchantment(EnchantementInit.DOLPHINS_GRACE_ENCHANT.get(), 2);
                            anvilUpdateEvent.setOutput(output);
                            anvilUpdateEvent.setCost(6);
                        }
                    }
                //Gold Sword and Stone Sword and Wooden Sword
                if (left.isItemEqual(new ItemStack(Items.GOLDEN_LEGGINGS, 1)) || left.isItemEqual(new ItemStack(Items.LEATHER_LEGGINGS, 1)) || left.isItemEqual(new ItemStack(Items.CHAINMAIL_LEGGINGS, 1)))
                    if (!left.isEnchanted()) {
                        ItemStack output = new ItemStack(left.getItem());
                        output.addEnchantment(EnchantementInit.DOLPHINS_GRACE_ENCHANT.get(), 1);
                        anvilUpdateEvent.setOutput(output);
                        anvilUpdateEvent.setCost(6);
                    } else {
                        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(left);
                        ItemStack output = new ItemStack(left.getItem());
                        for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
                            Enchantment enchantment = entry.getKey();
                            if (enchantment == EnchantementInit.DOLPHINS_GRACE_ENCHANT.get()) {
                                anvilUpdateEvent.setCanceled(true);
                            }
                            output.addEnchantment(enchantment, entry.getValue());
                            output.addEnchantment(EnchantementInit.DOLPHINS_GRACE_ENCHANT.get(), 1);
                            anvilUpdateEvent.setOutput(output);
                            anvilUpdateEvent.setCost(6);
                        }
                    }

            }
            //FIRE RESISTANCE
            if (right.isItemEqual(new ItemStack(ModItems.fire_resistance_gem.get(), 1))) {
                //Netherite and Diamond Sword
                if (left.isItemEqual(new ItemStack(Items.NETHERITE_CHESTPLATE, 1)) || left.isItemEqual(new ItemStack(Items.DIAMOND_CHESTPLATE, 1)))
                    if (!left.isEnchanted()) {
                        ItemStack output = new ItemStack(left.getItem());
                        output.addEnchantment(EnchantementInit.FIRE_RESISTANCE_ENCHANT.get(), 3);
                        anvilUpdateEvent.setOutput(output);
                        anvilUpdateEvent.setCost(6);
                    } else {
                        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(left);
                        ItemStack output = new ItemStack(left.getItem());
                        for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
                            Enchantment enchantment = entry.getKey();
                            if (enchantment == EnchantementInit.FIRE_RESISTANCE_ENCHANT.get()) {
                                anvilUpdateEvent.setCanceled(true);
                            }
                            output.addEnchantment(enchantment, entry.getValue());
                            output.addEnchantment(EnchantementInit.FIRE_RESISTANCE_ENCHANT.get(), 3);
                            anvilUpdateEvent.setOutput(output);
                            anvilUpdateEvent.setCost(6);
                        }
                    }
                //Iron Sword
                if (left.isItemEqual(new ItemStack(Items.IRON_CHESTPLATE, 1)))
                    if (!left.isEnchanted()) {
                        ItemStack output = new ItemStack(left.getItem());
                        output.addEnchantment(EnchantementInit.FIRE_RESISTANCE_ENCHANT.get(), 2);
                        anvilUpdateEvent.setOutput(output);
                        anvilUpdateEvent.setCost(6);
                    } else {
                        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(left);
                        ItemStack output = new ItemStack(left.getItem());
                        for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
                            Enchantment enchantment = entry.getKey();
                            if (enchantment == EnchantementInit.FIRE_RESISTANCE_ENCHANT.get()) {
                                anvilUpdateEvent.setCanceled(true);
                            }
                            output.addEnchantment(enchantment, entry.getValue());
                            output.addEnchantment(EnchantementInit.FIRE_RESISTANCE_ENCHANT.get(), 2);
                            anvilUpdateEvent.setOutput(output);
                            anvilUpdateEvent.setCost(6);
                        }
                    }
                //Gold Sword and Stone Sword and Wooden Sword
                if (left.isItemEqual(new ItemStack(Items.GOLDEN_CHESTPLATE, 1)) || left.isItemEqual(new ItemStack(Items.LEATHER_CHESTPLATE, 1)) || left.isItemEqual(new ItemStack(Items.CHAINMAIL_CHESTPLATE, 1)))
                    if (!left.isEnchanted()) {
                        ItemStack output = new ItemStack(left.getItem());
                        output.addEnchantment(EnchantementInit.FIRE_RESISTANCE_ENCHANT.get(), 1);
                        anvilUpdateEvent.setOutput(output);
                        anvilUpdateEvent.setCost(6);
                    } else {
                        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(left);
                        ItemStack output = new ItemStack(left.getItem());
                        for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
                            Enchantment enchantment = entry.getKey();
                            if (enchantment == EnchantementInit.FIRE_RESISTANCE_ENCHANT.get()) {
                                anvilUpdateEvent.setCanceled(true);
                            }
                            output.addEnchantment(enchantment, entry.getValue());
                            output.addEnchantment(EnchantementInit.FIRE_RESISTANCE_ENCHANT.get(), 1);
                            anvilUpdateEvent.setOutput(output);
                            anvilUpdateEvent.setCost(6);
                        }
                    }

            }
            //HASTE
            if (right.isItemEqual(new ItemStack(ModItems.haste_gem.get(), 1))) {
                //Netherite and Diamond Sword
                if (left.isItemEqual(new ItemStack(Items.NETHERITE_CHESTPLATE, 1)) || left.isItemEqual(new ItemStack(Items.DIAMOND_CHESTPLATE, 1)))
                    if (!left.isEnchanted()) {
                        ItemStack output = new ItemStack(left.getItem());
                        output.addEnchantment(EnchantementInit.HASTE_ENCHANT.get(), 3);
                        anvilUpdateEvent.setOutput(output);
                        anvilUpdateEvent.setCost(6);
                    } else {
                        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(left);
                        ItemStack output = new ItemStack(left.getItem());
                        for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
                            Enchantment enchantment = entry.getKey();
                            if (enchantment == EnchantementInit.HASTE_ENCHANT.get()) {
                                anvilUpdateEvent.setCanceled(true);
                            }
                            output.addEnchantment(enchantment, entry.getValue());
                            output.addEnchantment(EnchantementInit.HASTE_ENCHANT.get(), 3);
                            anvilUpdateEvent.setOutput(output);
                            anvilUpdateEvent.setCost(6);
                        }
                    }
                //Iron Sword
                if (left.isItemEqual(new ItemStack(Items.IRON_CHESTPLATE, 1)))
                    if (!left.isEnchanted()) {
                        ItemStack output = new ItemStack(left.getItem());
                        output.addEnchantment(EnchantementInit.HASTE_ENCHANT.get(), 2);
                        anvilUpdateEvent.setOutput(output);
                        anvilUpdateEvent.setCost(6);
                    } else {
                        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(left);
                        ItemStack output = new ItemStack(left.getItem());
                        for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
                            Enchantment enchantment = entry.getKey();
                            if (enchantment == EnchantementInit.HASTE_ENCHANT.get()) {
                                anvilUpdateEvent.setCanceled(true);
                            }
                            output.addEnchantment(enchantment, entry.getValue());
                            output.addEnchantment(EnchantementInit.HASTE_ENCHANT.get(), 2);
                            anvilUpdateEvent.setOutput(output);
                            anvilUpdateEvent.setCost(6);
                        }
                    }
                //Gold Sword and Stone Sword and Wooden Sword
                if (left.isItemEqual(new ItemStack(Items.GOLDEN_CHESTPLATE, 1)) || left.isItemEqual(new ItemStack(Items.LEATHER_CHESTPLATE, 1)) || left.isItemEqual(new ItemStack(Items.CHAINMAIL_CHESTPLATE, 1)))
                    if (!left.isEnchanted()) {
                        ItemStack output = new ItemStack(left.getItem());
                        output.addEnchantment(EnchantementInit.HASTE_ENCHANT.get(), 1);
                        anvilUpdateEvent.setOutput(output);
                        anvilUpdateEvent.setCost(6);
                    } else {
                        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(left);
                        ItemStack output = new ItemStack(left.getItem());
                        for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
                            Enchantment enchantment = entry.getKey();
                            if (enchantment == EnchantementInit.HASTE_ENCHANT.get()) {
                                anvilUpdateEvent.setCanceled(true);
                            }
                            output.addEnchantment(enchantment, entry.getValue());
                            output.addEnchantment(EnchantementInit.HASTE_ENCHANT.get(), 1);
                            anvilUpdateEvent.setOutput(output);
                            anvilUpdateEvent.setCost(6);
                        }
                    }

            }
            //HEALTH BOOST
            if (right.isItemEqual(new ItemStack(ModItems.health_boost_gem.get(), 1))) {
                //Netherite and Diamond Sword
                if (left.isItemEqual(new ItemStack(Items.NETHERITE_CHESTPLATE, 1)) || left.isItemEqual(new ItemStack(Items.DIAMOND_CHESTPLATE, 1)))
                    if (!left.isEnchanted()) {
                        ItemStack output = new ItemStack(left.getItem());
                        output.addEnchantment(EnchantementInit.HEALTH_BOOST_ENCHANT.get(), 3);
                        anvilUpdateEvent.setOutput(output);
                        anvilUpdateEvent.setCost(6);
                    } else {
                        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(left);
                        ItemStack output = new ItemStack(left.getItem());
                        for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
                            Enchantment enchantment = entry.getKey();
                            if (enchantment == EnchantementInit.HEALTH_BOOST_ENCHANT.get()) {
                                anvilUpdateEvent.setCanceled(true);
                            }
                            output.addEnchantment(enchantment, entry.getValue());
                            output.addEnchantment(EnchantementInit.HEALTH_BOOST_ENCHANT.get(), 3);
                            anvilUpdateEvent.setOutput(output);
                            anvilUpdateEvent.setCost(6);
                        }
                    }
                //Iron Sword
                if (left.isItemEqual(new ItemStack(Items.IRON_CHESTPLATE, 1)))
                    if (!left.isEnchanted()) {
                        ItemStack output = new ItemStack(left.getItem());
                        output.addEnchantment(EnchantementInit.HEALTH_BOOST_ENCHANT.get(), 2);
                        anvilUpdateEvent.setOutput(output);
                        anvilUpdateEvent.setCost(6);
                    } else {
                        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(left);
                        ItemStack output = new ItemStack(left.getItem());
                        for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
                            Enchantment enchantment = entry.getKey();
                            if (enchantment == EnchantementInit.HEALTH_BOOST_ENCHANT.get()) {
                                anvilUpdateEvent.setCanceled(true);
                            }
                            output.addEnchantment(enchantment, entry.getValue());
                            output.addEnchantment(EnchantementInit.HEALTH_BOOST_ENCHANT.get(), 2);
                            anvilUpdateEvent.setOutput(output);
                            anvilUpdateEvent.setCost(6);
                        }
                    }
                //Gold Sword and Stone Sword and Wooden Sword
                if (left.isItemEqual(new ItemStack(Items.GOLDEN_CHESTPLATE, 1)) || left.isItemEqual(new ItemStack(Items.LEATHER_CHESTPLATE, 1)) || left.isItemEqual(new ItemStack(Items.CHAINMAIL_CHESTPLATE, 1)))
                    if (!left.isEnchanted()) {
                        ItemStack output = new ItemStack(left.getItem());
                        output.addEnchantment(EnchantementInit.HEALTH_BOOST_ENCHANT.get(), 1);
                        anvilUpdateEvent.setOutput(output);
                        anvilUpdateEvent.setCost(6);
                    } else {
                        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(left);
                        ItemStack output = new ItemStack(left.getItem());
                        for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
                            Enchantment enchantment = entry.getKey();
                            if (enchantment == EnchantementInit.HEALTH_BOOST_ENCHANT.get()) {
                                anvilUpdateEvent.setCanceled(true);
                            }
                            output.addEnchantment(enchantment, entry.getValue());
                            output.addEnchantment(EnchantementInit.HEALTH_BOOST_ENCHANT.get(), 1);
                            anvilUpdateEvent.setOutput(output);
                            anvilUpdateEvent.setCost(6);
                        }
                    }

            }
            //JUMP BOOST
            if (right.isItemEqual(new ItemStack(ModItems.jump_boost_gem.get(), 1))) {
                //Netherite and Diamond Sword
                if (left.isItemEqual(new ItemStack(Items.NETHERITE_LEGGINGS, 1)) || left.isItemEqual(new ItemStack(Items.DIAMOND_LEGGINGS, 1)))
                    if (!left.isEnchanted()) {
                        ItemStack output = new ItemStack(left.getItem());
                        output.addEnchantment(EnchantementInit.JUMP_BOOST_ENCHANT.get(), 3);
                        anvilUpdateEvent.setOutput(output);
                        anvilUpdateEvent.setCost(6);
                    } else {
                        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(left);
                        ItemStack output = new ItemStack(left.getItem());
                        for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
                            Enchantment enchantment = entry.getKey();
                            if (enchantment == EnchantementInit.JUMP_BOOST_ENCHANT.get()) {
                                anvilUpdateEvent.setCanceled(true);
                            }
                            output.addEnchantment(enchantment, entry.getValue());
                            output.addEnchantment(EnchantementInit.JUMP_BOOST_ENCHANT.get(), 3);
                            anvilUpdateEvent.setOutput(output);
                            anvilUpdateEvent.setCost(6);
                        }
                    }
                //Iron Sword
                if (left.isItemEqual(new ItemStack(Items.IRON_LEGGINGS, 1)))
                    if (!left.isEnchanted()) {
                        ItemStack output = new ItemStack(left.getItem());
                        output.addEnchantment(EnchantementInit.JUMP_BOOST_ENCHANT.get(), 2);
                        anvilUpdateEvent.setOutput(output);
                        anvilUpdateEvent.setCost(6);
                    } else {
                        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(left);
                        ItemStack output = new ItemStack(left.getItem());
                        for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
                            Enchantment enchantment = entry.getKey();
                            if (enchantment == EnchantementInit.JUMP_BOOST_ENCHANT.get()) {
                                anvilUpdateEvent.setCanceled(true);
                            }
                            output.addEnchantment(enchantment, entry.getValue());
                            output.addEnchantment(EnchantementInit.JUMP_BOOST_ENCHANT.get(), 2);
                            anvilUpdateEvent.setOutput(output);
                            anvilUpdateEvent.setCost(6);
                        }
                    }
                //Gold Sword and Stone Sword and Wooden Sword
                if (left.isItemEqual(new ItemStack(Items.GOLDEN_LEGGINGS, 1)) || left.isItemEqual(new ItemStack(Items.LEATHER_LEGGINGS, 1)) || left.isItemEqual(new ItemStack(Items.CHAINMAIL_LEGGINGS, 1)))
                    if (!left.isEnchanted()) {
                        ItemStack output = new ItemStack(left.getItem());
                        output.addEnchantment(EnchantementInit.JUMP_BOOST_ENCHANT.get(), 1);
                        anvilUpdateEvent.setOutput(output);
                        anvilUpdateEvent.setCost(6);
                    } else {
                        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(left);
                        ItemStack output = new ItemStack(left.getItem());
                        for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
                            Enchantment enchantment = entry.getKey();
                            if (enchantment == EnchantementInit.JUMP_BOOST_ENCHANT.get()) {
                                anvilUpdateEvent.setCanceled(true);
                            }
                            output.addEnchantment(enchantment, entry.getValue());
                            output.addEnchantment(EnchantementInit.JUMP_BOOST_ENCHANT.get(), 1);
                            anvilUpdateEvent.setOutput(output);
                            anvilUpdateEvent.setCost(6);
                        }
                    }

            }
            //NIGHT VISION
            if (right.isItemEqual(new ItemStack(ModItems.night_vision_gem.get(), 1))) {
                //Netherite and Diamond Sword
                if (left.isItemEqual(new ItemStack(Items.NETHERITE_HELMET, 1)) || left.isItemEqual(new ItemStack(Items.DIAMOND_HELMET, 1)))
                    if (!left.isEnchanted()) {
                        ItemStack output = new ItemStack(left.getItem());
                        output.addEnchantment(EnchantementInit.NIGHT_VISION_ENCHANT.get(), 3);
                        anvilUpdateEvent.setOutput(output);
                        anvilUpdateEvent.setCost(6);
                    } else {
                        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(left);
                        ItemStack output = new ItemStack(left.getItem());
                        for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
                            Enchantment enchantment = entry.getKey();
                            if (enchantment == EnchantementInit.NIGHT_VISION_ENCHANT.get()) {
                                anvilUpdateEvent.setCanceled(true);
                            }
                            output.addEnchantment(enchantment, entry.getValue());
                            output.addEnchantment(EnchantementInit.NIGHT_VISION_ENCHANT.get(), 3);
                            anvilUpdateEvent.setOutput(output);
                            anvilUpdateEvent.setCost(6);
                        }
                    }
                //Iron Sword
                if (left.isItemEqual(new ItemStack(Items.IRON_HELMET, 1)))
                    if (!left.isEnchanted()) {
                        ItemStack output = new ItemStack(left.getItem());
                        output.addEnchantment(EnchantementInit.NIGHT_VISION_ENCHANT.get(), 2);
                        anvilUpdateEvent.setOutput(output);
                        anvilUpdateEvent.setCost(6);
                    } else {
                        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(left);
                        ItemStack output = new ItemStack(left.getItem());
                        for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
                            Enchantment enchantment = entry.getKey();
                            if (enchantment == EnchantementInit.NIGHT_VISION_ENCHANT.get()) {
                                anvilUpdateEvent.setCanceled(true);
                            }
                            output.addEnchantment(enchantment, entry.getValue());
                            output.addEnchantment(EnchantementInit.NIGHT_VISION_ENCHANT.get(), 2);
                            anvilUpdateEvent.setOutput(output);
                            anvilUpdateEvent.setCost(6);
                        }
                    }
                //Gold Sword and Stone Sword and Wooden Sword
                if (left.isItemEqual(new ItemStack(Items.GOLDEN_HELMET, 1)) || left.isItemEqual(new ItemStack(Items.LEATHER_HELMET, 1)) || left.isItemEqual(new ItemStack(Items.CHAINMAIL_HELMET, 1)))
                    if (!left.isEnchanted()) {
                        ItemStack output = new ItemStack(left.getItem());
                        output.addEnchantment(EnchantementInit.NIGHT_VISION_ENCHANT.get(), 1);
                        anvilUpdateEvent.setOutput(output);
                        anvilUpdateEvent.setCost(6);
                    } else {
                        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(left);
                        ItemStack output = new ItemStack(left.getItem());
                        for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
                            Enchantment enchantment = entry.getKey();
                            if (enchantment == EnchantementInit.NIGHT_VISION_ENCHANT.get()) {
                                anvilUpdateEvent.setCanceled(true);
                            }
                            output.addEnchantment(enchantment, entry.getValue());
                            output.addEnchantment(EnchantementInit.NIGHT_VISION_ENCHANT.get(), 1);
                            anvilUpdateEvent.setOutput(output);
                            anvilUpdateEvent.setCost(6);
                        }
                    }

            }
            //REGENERATION
            if (right.isItemEqual(new ItemStack(ModItems.regeneration_gem.get(), 1))) {
                //Netherite and Diamond Sword
                if (left.isItemEqual(new ItemStack(Items.NETHERITE_CHESTPLATE, 1)) || left.isItemEqual(new ItemStack(Items.DIAMOND_CHESTPLATE, 1)))
                    if (!left.isEnchanted()) {
                        ItemStack output = new ItemStack(left.getItem());
                        output.addEnchantment(EnchantementInit.REGENERATION_ENCHANT.get(), 3);
                        anvilUpdateEvent.setOutput(output);
                        anvilUpdateEvent.setCost(6);
                    } else {
                        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(left);
                        ItemStack output = new ItemStack(left.getItem());
                        for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
                            Enchantment enchantment = entry.getKey();
                            if (enchantment == EnchantementInit.REGENERATION_ENCHANT.get()) {
                                anvilUpdateEvent.setCanceled(true);
                            }
                            output.addEnchantment(enchantment, entry.getValue());
                            output.addEnchantment(EnchantementInit.REGENERATION_ENCHANT.get(), 3);
                            anvilUpdateEvent.setOutput(output);
                            anvilUpdateEvent.setCost(6);
                        }
                    }
                //Iron Sword
                if (left.isItemEqual(new ItemStack(Items.IRON_CHESTPLATE, 1)))
                    if (!left.isEnchanted()) {
                        ItemStack output = new ItemStack(left.getItem());
                        output.addEnchantment(EnchantementInit.REGENERATION_ENCHANT.get(), 2);
                        anvilUpdateEvent.setOutput(output);
                        anvilUpdateEvent.setCost(6);
                    } else {
                        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(left);
                        ItemStack output = new ItemStack(left.getItem());
                        for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
                            Enchantment enchantment = entry.getKey();
                            if (enchantment == EnchantementInit.REGENERATION_ENCHANT.get()) {
                                anvilUpdateEvent.setCanceled(true);
                            }
                            output.addEnchantment(enchantment, entry.getValue());
                            output.addEnchantment(EnchantementInit.REGENERATION_ENCHANT.get(), 2);
                            anvilUpdateEvent.setOutput(output);
                            anvilUpdateEvent.setCost(6);
                        }
                    }
                //Gold Sword and Stone Sword and Wooden Sword
                if (left.isItemEqual(new ItemStack(Items.GOLDEN_CHESTPLATE, 1)) || left.isItemEqual(new ItemStack(Items.LEATHER_CHESTPLATE, 1)) || left.isItemEqual(new ItemStack(Items.CHAINMAIL_CHESTPLATE, 1)))
                    if (!left.isEnchanted()) {
                        ItemStack output = new ItemStack(left.getItem());
                        output.addEnchantment(EnchantementInit.REGENERATION_ENCHANT.get(), 1);
                        anvilUpdateEvent.setOutput(output);
                        anvilUpdateEvent.setCost(6);
                    } else {
                        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(left);
                        ItemStack output = new ItemStack(left.getItem());
                        for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
                            Enchantment enchantment = entry.getKey();
                            if (enchantment == EnchantementInit.REGENERATION_ENCHANT.get()) {
                                anvilUpdateEvent.setCanceled(true);
                            }
                            output.addEnchantment(enchantment, entry.getValue());
                            output.addEnchantment(EnchantementInit.REGENERATION_ENCHANT.get(), 1);
                            anvilUpdateEvent.setOutput(output);
                            anvilUpdateEvent.setCost(6);
                        }
                    }

            }
            //RESISTANCE
            if (right.isItemEqual(new ItemStack(ModItems.resistance_gem.get(), 1))) {
                //Netherite and Diamond Sword
                if (left.isItemEqual(new ItemStack(Items.NETHERITE_CHESTPLATE, 1)) || left.isItemEqual(new ItemStack(Items.DIAMOND_CHESTPLATE, 1)))
                    if (!left.isEnchanted()) {
                        ItemStack output = new ItemStack(left.getItem());
                        output.addEnchantment(EnchantementInit.RESISTANCE_ENCHANT.get(), 3);
                        anvilUpdateEvent.setOutput(output);
                        anvilUpdateEvent.setCost(6);
                    } else {
                        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(left);
                        ItemStack output = new ItemStack(left.getItem());
                        for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
                            Enchantment enchantment = entry.getKey();
                            if (enchantment == EnchantementInit.RESISTANCE_ENCHANT.get()) {
                                anvilUpdateEvent.setCanceled(true);
                            }
                            output.addEnchantment(enchantment, entry.getValue());
                            output.addEnchantment(EnchantementInit.RESISTANCE_ENCHANT.get(), 3);
                            anvilUpdateEvent.setOutput(output);
                            anvilUpdateEvent.setCost(6);
                        }
                    }
                //Iron Sword
                if (left.isItemEqual(new ItemStack(Items.IRON_CHESTPLATE, 1)))
                    if (!left.isEnchanted()) {
                        ItemStack output = new ItemStack(left.getItem());
                        output.addEnchantment(EnchantementInit.RESISTANCE_ENCHANT.get(), 2);
                        anvilUpdateEvent.setOutput(output);
                        anvilUpdateEvent.setCost(6);
                    } else {
                        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(left);
                        ItemStack output = new ItemStack(left.getItem());
                        for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
                            Enchantment enchantment = entry.getKey();
                            if (enchantment == EnchantementInit.RESISTANCE_ENCHANT.get()) {
                                anvilUpdateEvent.setCanceled(true);
                            }
                            output.addEnchantment(enchantment, entry.getValue());
                            output.addEnchantment(EnchantementInit.RESISTANCE_ENCHANT.get(), 2);
                            anvilUpdateEvent.setOutput(output);
                            anvilUpdateEvent.setCost(6);
                        }
                    }
                //Gold Sword and Stone Sword and Wooden Sword
                if (left.isItemEqual(new ItemStack(Items.GOLDEN_CHESTPLATE, 1)) || left.isItemEqual(new ItemStack(Items.LEATHER_CHESTPLATE, 1)) || left.isItemEqual(new ItemStack(Items.CHAINMAIL_CHESTPLATE, 1)))
                    if (!left.isEnchanted()) {
                        ItemStack output = new ItemStack(left.getItem());
                        output.addEnchantment(EnchantementInit.RESISTANCE_ENCHANT.get(), 1);
                        anvilUpdateEvent.setOutput(output);
                        anvilUpdateEvent.setCost(6);
                    } else {
                        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(left);
                        ItemStack output = new ItemStack(left.getItem());
                        for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
                            Enchantment enchantment = entry.getKey();
                            if (enchantment == EnchantementInit.RESISTANCE_ENCHANT.get()) {
                                anvilUpdateEvent.setCanceled(true);
                            }
                            output.addEnchantment(enchantment, entry.getValue());
                            output.addEnchantment(EnchantementInit.RESISTANCE_ENCHANT.get(), 1);
                            anvilUpdateEvent.setOutput(output);
                            anvilUpdateEvent.setCost(6);
                        }
                    }

            }
            //SLOW FALLING
            if (right.isItemEqual(new ItemStack(ModItems.slow_falling_gem.get(), 1))) {
                //Netherite and Diamond Sword
                if (left.isItemEqual(new ItemStack(Items.NETHERITE_BOOTS, 1)) || left.isItemEqual(new ItemStack(Items.DIAMOND_BOOTS, 1)))
                    if (!left.isEnchanted()) {
                        ItemStack output = new ItemStack(left.getItem());
                        output.addEnchantment(EnchantementInit.SLOW_FALLING_ENCHANT.get(), 3);
                        anvilUpdateEvent.setOutput(output);
                        anvilUpdateEvent.setCost(6);
                    } else {
                        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(left);
                        ItemStack output = new ItemStack(left.getItem());
                        for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
                            Enchantment enchantment = entry.getKey();
                            if (enchantment == EnchantementInit.SLOW_FALLING_ENCHANT.get()) {
                                anvilUpdateEvent.setCanceled(true);
                            }
                            output.addEnchantment(enchantment, entry.getValue());
                            output.addEnchantment(EnchantementInit.SLOW_FALLING_ENCHANT.get(), 3);
                            anvilUpdateEvent.setOutput(output);
                            anvilUpdateEvent.setCost(6);
                        }
                    }
                //Iron Sword
                if (left.isItemEqual(new ItemStack(Items.IRON_BOOTS, 1)))
                    if (!left.isEnchanted()) {
                        ItemStack output = new ItemStack(left.getItem());
                        output.addEnchantment(EnchantementInit.SLOW_FALLING_ENCHANT.get(), 2);
                        anvilUpdateEvent.setOutput(output);
                        anvilUpdateEvent.setCost(6);
                    } else {
                        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(left);
                        ItemStack output = new ItemStack(left.getItem());
                        for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
                            Enchantment enchantment = entry.getKey();
                            if (enchantment == EnchantementInit.SLOW_FALLING_ENCHANT.get()) {
                                anvilUpdateEvent.setCanceled(true);
                            }
                            output.addEnchantment(enchantment, entry.getValue());
                            output.addEnchantment(EnchantementInit.SLOW_FALLING_ENCHANT.get(), 2);
                            anvilUpdateEvent.setOutput(output);
                            anvilUpdateEvent.setCost(6);
                        }
                    }
                //Gold Sword and Stone Sword and Wooden Sword
                if (left.isItemEqual(new ItemStack(Items.GOLDEN_BOOTS, 1)) || left.isItemEqual(new ItemStack(Items.LEATHER_BOOTS, 1)) || left.isItemEqual(new ItemStack(Items.CHAINMAIL_BOOTS, 1)))
                    if (!left.isEnchanted()) {
                        ItemStack output = new ItemStack(left.getItem());
                        output.addEnchantment(EnchantementInit.SLOW_FALLING_ENCHANT.get(), 1);
                        anvilUpdateEvent.setOutput(output);
                        anvilUpdateEvent.setCost(6);
                    } else {
                        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(left);
                        ItemStack output = new ItemStack(left.getItem());
                        for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
                            Enchantment enchantment = entry.getKey();
                            if (enchantment == EnchantementInit.SLOW_FALLING_ENCHANT.get()) {
                                anvilUpdateEvent.setCanceled(true);
                            }
                            output.addEnchantment(enchantment, entry.getValue());
                            output.addEnchantment(EnchantementInit.SLOW_FALLING_ENCHANT.get(), 1);
                            anvilUpdateEvent.setOutput(output);
                            anvilUpdateEvent.setCost(6);
                        }
                    }

            }
            //SPEED
            if (right.isItemEqual(new ItemStack(ModItems.speed_gem.get(), 1))) {
                //Netherite and Diamond Sword
                if (left.isItemEqual(new ItemStack(Items.NETHERITE_LEGGINGS, 1)) || left.isItemEqual(new ItemStack(Items.DIAMOND_LEGGINGS, 1)))
                    if (!left.isEnchanted()) {
                        ItemStack output = new ItemStack(left.getItem());
                        output.addEnchantment(EnchantementInit.SPEED_ENCHANT.get(), 3);
                        anvilUpdateEvent.setOutput(output);
                        anvilUpdateEvent.setCost(6);
                    } else {
                        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(left);
                        ItemStack output = new ItemStack(left.getItem());
                        for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
                            Enchantment enchantment = entry.getKey();
                            if (enchantment == EnchantementInit.SPEED_ENCHANT.get()) {
                                anvilUpdateEvent.setCanceled(true);
                            }
                            output.addEnchantment(enchantment, entry.getValue());
                            output.addEnchantment(EnchantementInit.SPEED_ENCHANT.get(), 3);
                            anvilUpdateEvent.setOutput(output);
                            anvilUpdateEvent.setCost(6);
                        }
                    }
                //Iron Sword
                if (left.isItemEqual(new ItemStack(Items.IRON_LEGGINGS, 1)))
                    if (!left.isEnchanted()) {
                        ItemStack output = new ItemStack(left.getItem());
                        output.addEnchantment(EnchantementInit.SPEED_ENCHANT.get(), 2);
                        anvilUpdateEvent.setOutput(output);
                        anvilUpdateEvent.setCost(6);
                    } else {
                        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(left);
                        ItemStack output = new ItemStack(left.getItem());
                        for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
                            Enchantment enchantment = entry.getKey();
                            if (enchantment == EnchantementInit.SPEED_ENCHANT.get()) {
                                anvilUpdateEvent.setCanceled(true);
                            }
                            output.addEnchantment(enchantment, entry.getValue());
                            output.addEnchantment(EnchantementInit.SPEED_ENCHANT.get(), 2);
                            anvilUpdateEvent.setOutput(output);
                            anvilUpdateEvent.setCost(6);
                        }
                    }
                //Gold Sword and Stone Sword and Wooden Sword
                if (left.isItemEqual(new ItemStack(Items.GOLDEN_LEGGINGS, 1)) || left.isItemEqual(new ItemStack(Items.LEATHER_LEGGINGS, 1)) || left.isItemEqual(new ItemStack(Items.CHAINMAIL_LEGGINGS, 1)))
                    if (!left.isEnchanted()) {
                        ItemStack output = new ItemStack(left.getItem());
                        output.addEnchantment(EnchantementInit.SPEED_ENCHANT.get(), 1);
                        anvilUpdateEvent.setOutput(output);
                        anvilUpdateEvent.setCost(6);
                    } else {
                        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(left);
                        ItemStack output = new ItemStack(left.getItem());
                        for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
                            Enchantment enchantment = entry.getKey();
                            if (enchantment == EnchantementInit.SPEED_ENCHANT.get()) {
                                anvilUpdateEvent.setCanceled(true);
                            }
                            output.addEnchantment(enchantment, entry.getValue());
                            output.addEnchantment(EnchantementInit.SPEED_ENCHANT.get(), 1);
                            anvilUpdateEvent.setOutput(output);
                            anvilUpdateEvent.setCost(6);
                        }
                    }

            }
            //WATER BREATHING
            if (right.isItemEqual(new ItemStack(ModItems.water_breathing_gem.get(), 1))) {
                //Netherite and Diamond Sword
                if (left.isItemEqual(new ItemStack(Items.NETHERITE_HELMET, 1)) || left.isItemEqual(new ItemStack(Items.DIAMOND_HELMET, 1)))
                    if (!left.isEnchanted()) {
                        ItemStack output = new ItemStack(left.getItem());
                        output.addEnchantment(EnchantementInit.WATER_BREATHING_ENCHANT.get(), 3);
                        anvilUpdateEvent.setOutput(output);
                        anvilUpdateEvent.setCost(6);
                    } else {
                        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(left);
                        ItemStack output = new ItemStack(left.getItem());
                        for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
                            Enchantment enchantment = entry.getKey();
                            if (enchantment == EnchantementInit.WATER_BREATHING_ENCHANT.get()) {
                                anvilUpdateEvent.setCanceled(true);
                            }
                            output.addEnchantment(enchantment, entry.getValue());
                            output.addEnchantment(EnchantementInit.WATER_BREATHING_ENCHANT.get(), 3);
                            anvilUpdateEvent.setOutput(output);
                            anvilUpdateEvent.setCost(6);
                        }
                    }
                //Iron Sword
                if (left.isItemEqual(new ItemStack(Items.IRON_HELMET, 1)))
                    if (!left.isEnchanted()) {
                        ItemStack output = new ItemStack(left.getItem());
                        output.addEnchantment(EnchantementInit.WATER_BREATHING_ENCHANT.get(), 2);
                        anvilUpdateEvent.setOutput(output);
                        anvilUpdateEvent.setCost(6);
                    } else {
                        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(left);
                        ItemStack output = new ItemStack(left.getItem());
                        for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
                            Enchantment enchantment = entry.getKey();
                            if (enchantment == EnchantementInit.WATER_BREATHING_ENCHANT.get()) {
                                anvilUpdateEvent.setCanceled(true);
                            }
                            output.addEnchantment(enchantment, entry.getValue());
                            output.addEnchantment(EnchantementInit.WATER_BREATHING_ENCHANT.get(), 2);
                            anvilUpdateEvent.setOutput(output);
                            anvilUpdateEvent.setCost(6);
                        }
                    }
                //Gold Sword and Stone Sword and Wooden Sword
                if (left.isItemEqual(new ItemStack(Items.GOLDEN_HELMET, 1)) || left.isItemEqual(new ItemStack(Items.LEATHER_HELMET, 1)) || left.isItemEqual(new ItemStack(Items.CHAINMAIL_HELMET, 1)))
                    if (!left.isEnchanted()) {
                        ItemStack output = new ItemStack(left.getItem());
                        output.addEnchantment(EnchantementInit.WATER_BREATHING_ENCHANT.get(), 1);
                        anvilUpdateEvent.setOutput(output);
                        anvilUpdateEvent.setCost(6);
                    } else {
                        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(left);
                        ItemStack output = new ItemStack(left.getItem());
                        for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
                            Enchantment enchantment = entry.getKey();
                            if (enchantment == EnchantementInit.WATER_BREATHING_ENCHANT.get()) {
                                anvilUpdateEvent.setCanceled(true);
                            }
                            output.addEnchantment(enchantment, entry.getValue());
                            output.addEnchantment(EnchantementInit.WATER_BREATHING_ENCHANT.get(), 1);
                            anvilUpdateEvent.setOutput(output);
                            anvilUpdateEvent.setCost(6);
                        }
                    }

            }


        }
}
