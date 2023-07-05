package com.thanatos.magicalgems.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.thanatos.magicalgems.container.DistilleryContainer;
import com.thanatos.magicalgems.main;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;


public class DistilleryScreen extends ContainerScreen<DistilleryContainer> {
    private final ResourceLocation GUI = new ResourceLocation(main.MODID,
            "textures/gui/distillery_gui.png");

    public DistilleryScreen(DistilleryContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
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



    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y) {
        RenderSystem.color4f(1f, 1f, 1f, 1f);
        this.minecraft.getTextureManager().bindTexture(GUI);
        int i = this.guiLeft;
        int j = this.guiTop;
        this.blit(matrixStack, i, j, 0, 0, this.xSize, this.ySize);

        int resultSec = container.getResultSecFromTile();
        int FEtick = (container.getFEtickFromTile())%115;
        if(inInterval(FEtick,0,15)) this.blit(matrixStack,i+33,j+27,185,0,FEtick,2);
        if(inInterval(FEtick,16,37)) {
            this.blit(matrixStack,i+33,j+27,185,0,16,2);
            this.blit(matrixStack, i + 41, j + 28, (FEtick - 15) * 11, 167, 11, 7);
        }
        if(inInterval(FEtick,38,49)){
            this.blit(matrixStack,i+33,j+27,185,0,16,2);
            this.blit(matrixStack, i + 41, j + 28, 242, 167, 11, 7);
            this.blit(matrixStack, i + 41, j + 32, (FEtick-37)*12, 176, 11, 7);
        }
        if(inInterval(FEtick,50,61)){
            this.blit(matrixStack,i+33,j+27,185,0,16,2);
            this.blit(matrixStack, i + 41, j + 28, 242, 167, 11, 7);
            this.blit(matrixStack, i + 41, j + 32, 156, 176, 11, 7);
            this.blit(matrixStack, i + 41, j + 35, (FEtick-49)*12, 176, 11, 7);
        }
        if(inInterval(FEtick,62,73)){
            this.blit(matrixStack,i+33,j+27,185,0,16,2);
            this.blit(matrixStack, i + 41, j + 28, 242, 167, 11, 7);
            this.blit(matrixStack, i + 41, j + 32, 156, 176, 11, 7);
            this.blit(matrixStack, i + 41, j + 35, 156, 176, 11, 7);
            this.blit(matrixStack, i + 41, j + 38, (FEtick-61)*12, 176, 11, 7);
        }
        if(inInterval(FEtick,74,85)){
            this.blit(matrixStack,i+33,j+27,185,0,16,2);
            this.blit(matrixStack, i + 41, j + 28, 242, 167, 11, 7);
            this.blit(matrixStack, i + 41, j + 32, 156, 176, 11, 7);
            this.blit(matrixStack, i + 41, j + 35, 156, 176, 11, 7);
            this.blit(matrixStack, i + 41, j + 38, 156, 176, 11, 7);
            this.blit(matrixStack, i + 41, j + 41, (FEtick-73)*12, 176, 11, 7);
        }
        if(inInterval(FEtick,86,115)){
            this.blit(matrixStack,i+33,j+27,185,0,16,2);
            this.blit(matrixStack, i + 41, j + 28, 242, 167, 11, 7);
            this.blit(matrixStack, i + 41, j + 32, 156, 176, 11, 7);
            this.blit(matrixStack, i + 41, j + 35, 156, 176, 11, 7);
            this.blit(matrixStack, i + 41, j + 38, 156, 176, 11, 7);
            this.blit(matrixStack, i + 41, j + 41, 156, 176, 11, 7);
            this.blit(matrixStack,i + 50,j + 44, 177,23,(FEtick-85),6);
        }




        int arrowWidth = 6;

        this.blit(matrixStack, i + 91, j + 34, 177, 0, arrowWidth, resultSec);


    }
}