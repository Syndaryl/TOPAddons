package io.github.drmanganese.topaddons.elements.forestry;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import io.github.drmanganese.topaddons.addons.AddonForestry;

import forestry.core.PluginCore;
import forestry.core.circuits.EnumCircuitBoardType;
import io.netty.buffer.ByteBuf;
import mcjty.theoneprobe.api.IElement;
import mcjty.theoneprobe.network.NetworkTools;
import mcjty.theoneprobe.rendering.RenderHelper;

import static mcjty.theoneprobe.rendering.RenderHelper.renderItemStack;

public class ElementForestryFarm implements IElement {

    private final ItemStack[] farmIcons;
    private String oneDirection;

    private ItemStack[] inventoryStacks;

    public ElementForestryFarm(ItemStack[] farmIcons, String oneDirection, boolean renderInventory, ItemStack[] inventoryStacks) {
        this.farmIcons = farmIcons;
        this.oneDirection = oneDirection;
        this.inventoryStacks = inventoryStacks;
    }

    public ElementForestryFarm(ByteBuf buf) {
        this.farmIcons = new ItemStack[4];
        for (int i = 0; i < 4; i++) {
            this.farmIcons[i] = NetworkTools.readItemStack(buf);
        }
        oneDirection = NetworkTools.readString(buf);
        if (buf.readBoolean()) {
            this.inventoryStacks = new ItemStack[20];
            for (int i = 0; i < 20; i++) {
                this.inventoryStacks[i] = NetworkTools.readItemStack(buf);
            }
        } else {
            this.inventoryStacks = new ItemStack[]{};
        }
    }

    @Override
    public void render(int x, int y) {
        Minecraft minecraft = Minecraft.getMinecraft();
        int centerX = x + 31;
        int centerY = y + 18;

        drawPlus(centerX - 22, centerY - 22, centerX + 38, centerY + 38, 0xff969696, 0x44969696);

        renderItemStack(minecraft, minecraft.getRenderItem(), PluginCore.items.circuitboards.get(EnumCircuitBoardType.INTRICATE), centerX, centerY + 2, "");
        renderItemStack(minecraft, minecraft.getRenderItem(), farmIcons[0], centerX, centerY - 17, oneDirection);
        renderItemStack(minecraft, minecraft.getRenderItem(), farmIcons[1], centerX + 19, centerY + 2, nextDirection());
        renderItemStack(minecraft, minecraft.getRenderItem(), farmIcons[2], centerX, centerY + 21, nextDirection());
        renderItemStack(minecraft, minecraft.getRenderItem(), farmIcons[3], centerX - 19, centerY + 2, nextDirection());
        nextDirection();

        if (this.inventoryStacks.length > 0) {
            RenderHelper.drawBeveledBox(x, y + 60, x + 38, y + 116, 0xff969696, 0xff969696, 0x44969696);
            RenderHelper.drawBeveledBox(x + 40, y + 60, x + 78, y + 116, 0xff969696, 0xff969696, 0x44969696);
            RenderHelper.drawBeveledBox(x , y + 120, x + 38, y + 158, 0xff969696, 0xff969696, 0x44969696);
            RenderHelper.drawBeveledBox(x + 40, y + 120, x + 78, y + 158, 0xff969696, 0xff969696, 0x44969696);
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 3; j++) {
                    for (int k = 0; k < 2; k++) {
                        int xOffset = x + 2 + 4 * i + (2 * i + (k % 2)) * 18;
                        int yOffset = y + 62 + j * 18;
                        int slot = i * 6 + j * 2 + k;

                        if (inventoryStacks[slot].getItem() != Item.getItemFromBlock(Blocks.BARRIER)) {
                            renderItemStack(minecraft, minecraft.getRenderItem(), inventoryStacks[slot], xOffset, yOffset, inventoryStacks[slot].stackSize + "");
                        }
                    }
                }
            }

            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    for (int k = 0; k < 2; k++) {
                        int xOffset = x + 2 + 4 * i + (2 * i + (k % 2)) * 18;
                        int yOffset = y + 122 + j * 18;
                        int slot = i * 4 + j * 2 + k + 12;

                        if (inventoryStacks[slot].getItem() != Item.getItemFromBlock(Blocks.BARRIER)) {
                            renderItemStack(minecraft, minecraft.getRenderItem(), inventoryStacks[slot], xOffset, yOffset, inventoryStacks[slot].stackSize + "");
                        }
                    }
                }
            }
        }
    }

    @Override
    public int getWidth() {
        return 60;
    }

    @Override
    public int getHeight() {
        return (inventoryStacks.length > 0) ? 160 : 60;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        for (ItemStack farmIcon : farmIcons) {
            NetworkTools.writeItemStack(buf, farmIcon);
        }

        NetworkTools.writeString(buf, oneDirection);
        if (inventoryStacks.length > 0) {
            buf.writeBoolean(true);
            for (ItemStack inventoryStack : inventoryStacks) {
                if (inventoryStack != null)
                    NetworkTools.writeItemStack(buf, inventoryStack);
                else
                    NetworkTools.writeItemStack(buf, new ItemStack(Blocks.BARRIER, 0));
            }
        } else {
            buf.writeBoolean(false);
        }
    }

    @Override
    public int getID() {
        return AddonForestry.ELEMENT_FARM;
    }

    private String nextDirection() {
        switch (this.oneDirection) {
            case "N":
                this.oneDirection = "E";
                return "E";
            case "E":
                this.oneDirection = "S";
                return "S";
            case "S":
                this.oneDirection = "W";
                return "W";
            case "W":
                this.oneDirection = "N";
                return "N";
            default:
                return this.oneDirection;
        }
    }

    private static void drawPlus(int x1, int y1, int x2, int y2, int border, int fillcolor) {
        if (fillcolor != -1) {
            Gui.drawRect(x1 + 21, y1 + 4, x2 - 21, y2 - 37, fillcolor);
            Gui.drawRect(x1 + 21, y1 + 41, x2 - 21, y2, fillcolor);
            Gui.drawRect(x1 + 2, y1 + 23, x2 - 2, y2 - 19, fillcolor);
        }

        //TOP
        RenderHelper.drawHorizontalLine(x1 + 21, y1 + 3, x2 - 21, border);
        RenderHelper.drawVerticalLine(x1 + 20, y1 + 3, y1 + 23, border);
        RenderHelper.drawVerticalLine(x2 - 21, y1 + 3, y1 + 23, border);

        //RIGHT
        RenderHelper.drawHorizontalLine(x1 + 40, y1 + 22, x2 - 2, border);
        RenderHelper.drawVerticalLine(x2 - 2, y1 + 22, y1 + 42, border);
        RenderHelper.drawHorizontalLine(x1 + 40, y1 + 41, x2 - 2, border);

        //BOTTOM
        RenderHelper.drawVerticalLine(x1 + 20, y1 + 41, y1 + 61, border);
        RenderHelper.drawVerticalLine(x2 - 21, y1 + 41, y1 + 61, border);
        RenderHelper.drawHorizontalLine(x1 + 21, y1 + 60, x2 - 21, border);

        //LEFT
        RenderHelper.drawHorizontalLine(x1 + 2, y1 + 22, x2 - 40, border);
        RenderHelper.drawVerticalLine(x1 + 1, y1 + 22, y1 + 42, border);
        RenderHelper.drawHorizontalLine(x1 + 2, y1 + 41, x2 - 40, border);
    }
}