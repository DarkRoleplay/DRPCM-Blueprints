package net.dark_roleplay.core_modules.blueprints.objects.tesrs;

import net.dark_roleplay.core_modules.blueprints.configs.ClientConfig;
import net.dark_roleplay.core_modules.blueprints.objects.other.Mode;
import net.dark_roleplay.core_modules.blueprints.objects.other.RenderMode;
import net.dark_roleplay.core_modules.blueprints.objects.tile_entities.TileEntityBlueprintController;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TESRBlueprintController extends TileEntitySpecialRenderer<TileEntityBlueprintController> {
	
	public void render(TileEntityBlueprintController te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		if (Minecraft.getMinecraft().player.canUseCommandBlock() || Minecraft.getMinecraft().player.isSpectator()) {
			super.render(te, x, y, z, partialTicks, destroyStage, alpha);
			BlockPos blockpos = te.getOffset();
			BlockPos blockpos1 = te.getSize();

			if (blockpos1.getX() >= 1 && blockpos1.getY() >= 1 && blockpos1.getZ() >= 1) {
				if (te.getMode() == Mode.SAVE || te.getMode() == Mode.LOAD) {
					double d1 = (double) blockpos.getX();
					double d2 = (double) blockpos.getZ();
					double d6 = y + (double) blockpos.getY() - 0.01D;
					double d9 = d6 + (double) blockpos1.getY() + 0.02D;
					double d3;
					double d4;
						
					d3 = (double) blockpos1.getX() + 0.02D;
						d4 = (double) blockpos1.getZ() + 0.02D;

					double d5;
					double d7;
					double d8;
					double d10;

					d5 = x + (d3 < 0.0D ? d1 + 1.0D + 0.01D : d1 - 0.01D);
					d7 = z + (d4 < 0.0D ? d2 + 1.0D + 0.01D : d2 - 0.01D);
					d8 = d5 + d3;
					d10 = d7 + d4;

					Tessellator tessellator = Tessellator.getInstance();
					BufferBuilder bufferbuilder = tessellator.getBuffer();
					GlStateManager.disableFog();
					GlStateManager.disableLighting();
					GlStateManager.disableTexture2D();
					GlStateManager.enableBlend();
					GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
					this.setLightmapDisabled(true);

					if (te.getRenderMode() == RenderMode.BOUNDING_BOX) {
						this.renderBox(tessellator, bufferbuilder, d5, d6, d7, d8, d9, d10, 255, 223, 0);
					}else if(te.getRenderMode() == RenderMode.INVISIBLE){
						this.renderBox(tessellator, bufferbuilder, d5, d6, d7, d8, d9, d10, 255, 223, 0);
						if(ClientConfig.HIGHLIGHT_INVISIBLE_BLOCKS)
							this.renderInvisibleBlocks(te, x, y, z, blockpos, tessellator, bufferbuilder, true);
						this.renderInvisibleBlocks(te, x, y, z, blockpos, tessellator, bufferbuilder, false);
					}
					this.setLightmapDisabled(false);
					GlStateManager.glLineWidth(1.0F);
					GlStateManager.enableLighting();
					GlStateManager.enableTexture2D();
					GlStateManager.enableDepth();
					GlStateManager.depthMask(true);
					GlStateManager.enableFog();
				}
			}
		}
	}

	private void renderInvisibleBlocks(TileEntityBlueprintController te, double sizeX, double sizeY, double sizeZ, BlockPos pos, Tessellator tes, BufferBuilder bufBuilder, boolean inside) {
		GlStateManager.glLineWidth(inside ? 3.0F : 1.0F);
		bufBuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
		World world = te.getWorld();
		BlockPos blockpos = te.getPos();
		BlockPos blockpos1 = blockpos.add(pos);

		for (BlockPos blockpos2 : BlockPos.getAllInBox(blockpos1, blockpos1.add(te.getSize()).add(-1, -1, -1))) {
			IBlockState iblockstate = world.getBlockState(blockpos2);
			boolean flag = iblockstate == Blocks.AIR.getDefaultState();
			boolean flag1 = iblockstate == Blocks.STRUCTURE_VOID.getDefaultState();

			if (flag || flag1) {
				float f = flag || ClientConfig.COLOR_BLIND_MODE ? 0.05F : 0.0F;
				double minX = (double) ((float) (blockpos2.getX() - blockpos.getX()) + 0.45F) + sizeX - (double) f;
				double minY = (double) ((float) (blockpos2.getY() - blockpos.getY()) + 0.45F) + sizeY - (double) f;
				double minZ = (double) ((float) (blockpos2.getZ() - blockpos.getZ()) + 0.45F) + sizeZ - (double) f;
				double maxX = (double) ((float) (blockpos2.getX() - blockpos.getX()) + 0.55F) + sizeX + (double) f;
				double maxY = (double) ((float) (blockpos2.getY() - blockpos.getY()) + 0.55F) + sizeY + (double) f;
				double maxZ = (double) ((float) (blockpos2.getZ() - blockpos.getZ()) + 0.55F) + sizeZ + (double) f;

				if (inside) {
					if(flag1 && ClientConfig.COLOR_BLIND_MODE)
						drawColorblindBox(bufBuilder, minX, minY, minZ, maxX, maxY, maxZ, 0.0F, 0.0F, 0.0F, 1.0F);
					else
						RenderGlobal.drawBoundingBox(bufBuilder, minX, minY, minZ, maxX, maxY, maxZ, 0.0F, 0.0F, 0.0F, 1.0F);
				} else if (flag) {
					RenderGlobal.drawBoundingBox(bufBuilder, minX, minY, minZ, maxX, maxY, maxZ, 
						ClientConfig.AIR_BLOCKS_COLOR.RED, ClientConfig.AIR_BLOCKS_COLOR.GREEN, 
						ClientConfig.AIR_BLOCKS_COLOR.BLUE, ClientConfig.AIR_BLOCKS_COLOR.ALPHA);
				} else {
					if(ClientConfig.COLOR_BLIND_MODE)
						drawColorblindBox(bufBuilder, minX, minY, minZ, maxX, maxY, maxZ, 
							ClientConfig.INVISIBLE_BLOCKS_COLOR.RED, ClientConfig.INVISIBLE_BLOCKS_COLOR.GREEN, 
							ClientConfig.INVISIBLE_BLOCKS_COLOR.BLUE, ClientConfig.INVISIBLE_BLOCKS_COLOR.ALPHA);
					else
						RenderGlobal.drawBoundingBox(bufBuilder, minX, minY, minZ, maxX, maxY, maxZ, 
							ClientConfig.INVISIBLE_BLOCKS_COLOR.RED, ClientConfig.INVISIBLE_BLOCKS_COLOR.GREEN, 
							ClientConfig.INVISIBLE_BLOCKS_COLOR.BLUE, ClientConfig.INVISIBLE_BLOCKS_COLOR.ALPHA);
				}
			}
		}

		tes.draw();
	}

	private void renderBox(Tessellator tes, BufferBuilder bufBuilder, double minX, double minY, double minZ, double maxX, double maxY, double maxZ, int alpha, int bright, int dark) {
		GlStateManager.glLineWidth(2.0F);
		bufBuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
		bufBuilder.pos(minX, minY, minZ).color((float) bright, (float) bright, (float) bright, 0.0F).endVertex();
		bufBuilder.pos(minX, minY, minZ).color(bright, bright, bright, alpha).endVertex();
		bufBuilder.pos(maxX, minY, minZ).color(bright, dark, dark, alpha).endVertex();
		bufBuilder.pos(maxX, minY, maxZ).color(bright, bright, bright, alpha).endVertex();
		bufBuilder.pos(minX, minY, maxZ).color(bright, bright, bright, alpha).endVertex();
		bufBuilder.pos(minX, minY, minZ).color(dark, dark, bright, alpha).endVertex();
		bufBuilder.pos(minX, maxY, minZ).color(dark, bright, dark, alpha).endVertex();
		bufBuilder.pos(maxX, maxY, minZ).color(bright, bright, bright, alpha).endVertex();
		bufBuilder.pos(maxX, maxY, maxZ).color(bright, bright, bright, alpha).endVertex();
		bufBuilder.pos(minX, maxY, maxZ).color(bright, bright, bright, alpha).endVertex();
		bufBuilder.pos(minX, maxY, minZ).color(bright, bright, bright, alpha).endVertex();
		bufBuilder.pos(minX, maxY, maxZ).color(bright, bright, bright, alpha).endVertex();
		bufBuilder.pos(minX, minY, maxZ).color(bright, bright, bright, alpha).endVertex();
		bufBuilder.pos(maxX, minY, maxZ).color(bright, bright, bright, alpha).endVertex();
		bufBuilder.pos(maxX, maxY, maxZ).color(bright, bright, bright, alpha).endVertex();
		bufBuilder.pos(maxX, maxY, minZ).color(bright, bright, bright, alpha).endVertex();
		bufBuilder.pos(maxX, minY, minZ).color(bright, bright, bright, alpha).endVertex();
		bufBuilder.pos(maxX, minY, minZ).color((float) bright, (float) bright, (float) bright, 0.0F).endVertex();
		tes.draw();
		GlStateManager.glLineWidth(1.0F);
	}

	@Override
	public boolean isGlobalRenderer(TileEntityBlueprintController te) {
		return true;
	}
	
	public static void drawColorblindBox(BufferBuilder buffer, double minX, double minY, double minZ, double maxX, double maxY, double maxZ, float red, float green, float blue, float alpha){
		double centerY = minY + 0.1F;
		double centerX = minX + 0.1F;
		double centerZ = minZ + 0.1F;
		
        buffer.pos(centerX, centerY, minZ).color(red, green, blue, 0.0F).endVertex();
        buffer.pos(centerX, centerY, minZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(centerX, centerY, maxZ).color(red, green, blue, alpha).endVertex();
        
        buffer.pos(centerX, minY, centerZ).color(red, green, blue, 0.0F).endVertex();
        buffer.pos(centerX, minY, centerZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(centerX, maxY, centerZ).color(red, green, blue, alpha).endVertex();

        buffer.pos(minX, centerY, centerZ).color(red, green, blue, 0.0F).endVertex();
        buffer.pos(minX, centerY, centerZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(maxX, centerY, centerZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(maxX, centerY, centerZ).color(red, green, blue, 0.0F).endVertex();
    }
}
