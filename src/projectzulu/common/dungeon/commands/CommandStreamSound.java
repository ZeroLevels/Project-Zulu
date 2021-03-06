package projectzulu.common.dungeon.commands;

import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.NumberInvalidException;
import net.minecraft.command.PlayerNotFoundException;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import projectzulu.common.core.PacketIDs;
import projectzulu.common.core.packets.PacketManagerStreamSound;
import cpw.mods.fml.common.network.PacketDispatcher;

public class CommandStreamSound extends CommandBase{

	@Override
    public String getCommandName(){
        return "streamsound";
    }

    /**
     * Return the required permission level for this command.
     */
    public int getRequiredPermissionLevel(){
        return 2;
    }
	
    @Override
    public String getCommandUsage(ICommandSender par1ICommandSender){
        return par1ICommandSender.translateString("commands.streamsound.usage", new Object[0]);
    }
	
	/**
	 * Command stringArgs == 2: /streamsound [targetPlayer] [fileName] <range> <x> <y> <z>
	 * Command stringArgs == 2: /streamsound @p sounds.fileName
	 * Command stringArgs == 3: /streamsound @p sounds.fileName <range>
	 * Command stringArgs == 5: /streamsound @p sounds.fileName <xDouble, yDouble, zDouble>
	 * Command stringArgs == 6: /streamsound @p sounds.fileName <range> <xDouble, yDouble, zDouble>
	 */
    @Override
	public void processCommand(ICommandSender commandSender, String[] stringArgs) {
    	if(stringArgs.length < 2){
			 throw new WrongUsageException("commands.streamsound.usage", new Object[0]);
		}else{
			int soundTargetX = 0;
			int soundTargetY = 0;
			int soundTargetZ = 0;
			
			EntityPlayerMP targetPlayer = func_82359_c(commandSender, stringArgs[0]);
			if(targetPlayer == null){
				throw new PlayerNotFoundException();
			}
			
			if(stringArgs.length == 2 || stringArgs.length == 3){
				soundTargetX = (int)targetPlayer.posX;
				soundTargetY = (int)targetPlayer.posY;
				soundTargetZ = (int)targetPlayer.posZ;
			}else if(stringArgs.length == 5 || stringArgs.length == 6){
				int indexOfPos = stringArgs.length - 3;
				soundTargetX = (int)parsePosition(commandSender, targetPlayer.posX, stringArgs[indexOfPos++]);
				soundTargetY = (int)parsePositionWithBounds(commandSender, targetPlayer.posY, stringArgs[indexOfPos++], 0, 0);
				soundTargetZ = (int)parsePosition(commandSender, targetPlayer.posZ, stringArgs[indexOfPos++]);
			}
			
			int soundRange = stringArgs.length == 3 || stringArgs.length == 6 ? parseIntBounded(commandSender, stringArgs[2], 0, 120) : 60;
			
			PacketManagerStreamSound streamSound = PacketIDs.streamSound.createPacketManager();
			streamSound.setPacketData(soundTargetX, soundTargetY, soundTargetZ, stringArgs[1]);
			PacketDispatcher.sendPacketToAllAround(soundTargetX, soundTargetY, soundTargetZ, soundRange, targetPlayer.dimension, streamSound.createPacket());
		}
	}
	
    /**
     * Adds the strings available in this command to the given list of tab completion options.
     */
	@Override
    public List addTabCompletionOptions(ICommandSender par1ICommandSender, String[] par2ArrayOfStr){
        return par2ArrayOfStr.length != 1 && par2ArrayOfStr.length != 2 ? null : getListOfStringsMatchingLastWord(par2ArrayOfStr, MinecraftServer.getServer().getAllUsernames());
    }
	
	private double parsePosition(ICommandSender commandSender, double currentPos, String stringDouble){
		return this.parsePositionWithBounds(commandSender, currentPos, stringDouble, -30000000, 30000000);
	}

	private double parsePositionWithBounds(ICommandSender commandSender, double currentPos, String stringDouble, int lowerLimit, int upperLimit){
		boolean isRelativeCoords = stringDouble.startsWith("~");
		double targetPos = isRelativeCoords ? currentPos : 0.0D;

		if (!isRelativeCoords || stringDouble.length() > 1){
			boolean hasDecimal = stringDouble.contains(".");

			if (isRelativeCoords){
				stringDouble = stringDouble.substring(1);
			}

			targetPos += parseDouble(commandSender, stringDouble);

			if (!hasDecimal && !isRelativeCoords){
				targetPos += 0.5D;
			}
		}

		if (lowerLimit != 0 || upperLimit != 0){
			if (targetPos < (double)lowerLimit){
				throw new NumberInvalidException("commands.generic.double.tooSmall", new Object[] {Double.valueOf(targetPos), Integer.valueOf(lowerLimit)});
			}

			if (targetPos > (double)upperLimit){
				throw new NumberInvalidException("commands.generic.double.tooBig", new Object[] {Double.valueOf(targetPos), Integer.valueOf(upperLimit)});
			}
		}
		return targetPos;
	}
}
