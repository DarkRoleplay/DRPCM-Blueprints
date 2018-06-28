package net.dark_roleplay.core_modules.blueprints.objects.blocks;

import java.util.Random;

import net.dark_roleplay.core_modules.blueprints.References;
import net.dark_roleplay.core_modules.blueprints.objects.guis.GuiBlueprintController;
import net.dark_roleplay.core_modules.blueprints.objects.other.Mode;
import net.dark_roleplay.core_modules.blueprints.objects.tile_entities.TileEntityBlueprintController;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlueprintController extends Block{
    public static final PropertyEnum<Mode> MODE = PropertyEnum.<Mode>create("mode", Mode.class);

    public BlueprintController(String name){
        super(Material.IRON, MapColor.SILVER);
        this.setDefaultState(this.blockState.getBaseState());
        this.setRegistryName(name);
        this.setUnlocalizedName(References.MODID + "." + name);
        this.setBlockUnbreakable();
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ){
    	if(world.isRemote){
    		TileEntityBlueprintController te = (TileEntityBlueprintController) world.getTileEntity(pos);
    		Minecraft.getMinecraft().displayGuiScreen(new GuiBlueprintController((TileEntityBlueprintController)world.getTileEntity(pos)));
    	}
    	return true;
    }
    
    @Override
    public int quantityDropped(Random random){
        return 0;
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state){
        return EnumBlockRenderType.MODEL;
    }

    @Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer){
        return this.getDefaultState().withProperty(MODE, Mode.LOAD);
    }
    
    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos){
    	TileEntity te = world.getTileEntity(pos);
    	if(te != null && te instanceof TileEntityBlueprintController){
            return state.withProperty(MODE, ((TileEntityBlueprintController) te).getMode());
    	}
    	return state;
    }

    
    @Override
    public boolean hasTileEntity(IBlockState state){
        return true;
    }
    
    @Override
    public TileEntity createTileEntity(World world, IBlockState state){
        return new TileEntityBlueprintController();
    }
    
    /** ---- TODO: Remove in 1.13 ---- **/
    
    @Override
    public IBlockState getStateFromMeta(int meta){
        return this.getDefaultState();
    }

    @Override
    public int getMetaFromState(IBlockState state){
        return 0;
    }

    @Override
    protected BlockStateContainer createBlockState(){
        return new BlockStateContainer(this, MODE);
    }
}
