package com.thanatos.magicalgems.screen;

import com.thanatos.magicalgems.main;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.thanatos.magicalgems.container.AlchemyTableContainer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;


public class AlchemyTableScreen extends ContainerScreen<AlchemyTableContainer> {
    private final ResourceLocation GUI = new ResourceLocation(main.MODID,
            "textures/gui/alchemy_table_gui.png");

    public AlchemyTableScreen(AlchemyTableContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
    }
    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderHoveredTooltip(matrixStack, mouseX, mouseY);
    }
    private boolean inInterval(int x,int a,int b){
        if(x >= a && x <= b) return true;
        else return false;
    }
    private int setXValue(int x) {
        if (inInterval(x, 0, 30)) return x;
        else if (inInterval(x, 49, 77)) return x-19;
        else return 30;
    }
    private int setYValue(int x) {
        if (inInterval(x, 0, 28)) return 2;
        else if (inInterval(x, 49, 77)) return 23;
        else {
            return 2 - (30 - x);
        }
    }



    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y) {

        RenderSystem.color4f(1f, 1f, 1f, 1f);
        this.minecraft.getTextureManager().bindTexture(GUI);
        int i = this.guiLeft;
        int j = this.guiTop;
        this.blit(matrixStack, i, j, 0, 0, this.xSize, this.ySize);

        //progress Bar
        int FE = container.getFEFromTile();
        int resultSec = container.getResultSecFromTile();
        //arrow

        int arrowWidth = 6;

        this.blit(matrixStack,i+99,j+36,178,0,arrowWidth,resultSec);

        //blaze
        this.blit(matrixStack, i + 34, j + 28, 36, 168, setXValue(FE%74),setYValue(FE%74));
        if(FE == 0) this.blit(matrixStack, i + 34, j + 28, 34, 28, 58, 23);
    }
}