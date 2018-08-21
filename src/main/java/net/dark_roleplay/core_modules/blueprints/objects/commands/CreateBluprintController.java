package net.dark_roleplay.core_modules.blueprints.objects.commands;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import net.dark_roleplay.core_modules.blueprints.configs.ServerConfig;
import net.dark_roleplay.core_modules.blueprints.handler.Blocks;
import net.dark_roleplay.core_modules.blueprints.handler.Permissions;
import net.dark_roleplay.core_modules.blueprints.objects.blocks.BlueprintController;
import net.dark_roleplay.core_modules.blueprints.objects.other.Mode;
import net.dark_roleplay.core_modules.blueprints.objects.tile_entities.TileEntityBlueprintController;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.server.permission.PermissionAPI;
import scala.actors.threadpool.Arrays;

public class CreateBluprintController implements ICommand{

	@Override
	public int compareTo(ICommand o) {
		return 0;
	}

	@Override
	public String getName() {
		return "bpccreate";
	}

	@Override
	public List<String> getAliases() {
		return Arrays.asList(ServerConfig.COMMAND_CREATE_BPC_ALIASES);
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "/bpcontroller (corner1) <x1> <y1> <z1> (corner2) <x2> <y2> <z2> (bpc target pos) <x3> <y3> <z3>";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		System.out.println(args.length);
		if(args.length == 9) {
			int x1 = Integer.valueOf(args[0]), y1 = Integer.valueOf(args[1]), z1 = Integer.valueOf(args[2]);
			int x2 = Integer.valueOf(args[3]), y2 = Integer.valueOf(args[4]), z2 = Integer.valueOf(args[5]);
			int x3 = Integer.valueOf(args[6]), y3 = Integer.valueOf(args[7]), z3 = Integer.valueOf(args[8]);

			
			BlockPos pos1 = new BlockPos(x1 > x2 ? x2 : x1, y1 > y2 ? y2 : y1, z1 > z2 ? z2 : z1);
			BlockPos pos2 = new BlockPos(x1 < x2 ? x2 : x1, y1 < y2 ? y2 : y1, z1 < z2 ? z2 : z1);
			
			BlockPos size = new BlockPos(pos2.getX() - pos1.getX() + 1, pos2.getY() - pos1.getY() + 1, pos2.getZ() - pos1.getZ() + 1);
			
			BlockPos pos3 = new BlockPos(x3, y3, z3);
			
			sender.getEntityWorld().setBlockState(pos3, Blocks.BLUEPRINT_CONTROLLER.getDefaultState().withProperty(BlueprintController.MODE, Mode.SAVE));

			System.out.println(pos3.toString());
			
			TileEntity te = sender.getEntityWorld().getTileEntity(pos3);
			
			if(te != null && te instanceof TileEntityBlueprintController) {
				TileEntityBlueprintController teb = (TileEntityBlueprintController) te;

				teb.setMode(Mode.SAVE);
				teb.setSize(size);
				teb.setOffset(new BlockPos(pos1.getX() - pos3.getX(), pos1.getY() - pos3.getY(), pos1.getZ() - pos3.getZ()));
			}
		}
		
	}

	@Override
	public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
		return sender instanceof EntityPlayer && PermissionAPI.hasPermission((EntityPlayer) sender, Permissions.COMMAND_CREATE_BLUEPRINT);
	}

	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos targetPos) {
		List<String> completionList = new ArrayList<String>();
		switch((args.length - 1) % 3) {
			case 0:
				completionList.add(targetPos.getX() + "");
				break;
			case 1:			
				completionList.add(targetPos.getY() + "");
				break;
			case 2:			
				completionList.add(targetPos.getZ() + "");
				break;
		}
		return completionList;
	}

	@Override
	public boolean isUsernameIndex(String[] args, int index) {
		return false;
	}

	//TODO Improve and utilize
	private static BlockPos parsePos(String x, String y, String z) throws InvalidParameterException{
		int x1 = Integer.valueOf(x);
		int y1 = Integer.valueOf(y);
		int z1 = Integer.valueOf(z);
		
		
		return new BlockPos(x1, y1, z1);
	}
}
