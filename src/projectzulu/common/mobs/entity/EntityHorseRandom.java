package projectzulu.common.mobs.entity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import projectzulu.common.core.DefaultProps;

public class EntityHorseRandom extends EntityHorseBase{

	int horseType = -1;
	
	public EntityHorseRandom(World par1World) {
		super(par1World);
		horseType = rand.nextInt(7);
	}
	
	@Override
	protected void entityInit(){
		super.entityInit();
		/* Horse Type */
		this.dataWatcher.addObject(26, Short.valueOf((short) 0));
	}	
	
	public void updateHorseType(){
		this.dataWatcher.updateObject(26, (short)(horseType));
	}

	public int getHorseType(){
		return this.dataWatcher.getWatchableObjectShort(26);
	}
	
	@Override
	public String getTexture() {
		switch (horseType) {
		case 0:
			if(getSaddled()){
				this.texture = DefaultProps.mobDiretory + "Horse/horse_beige_saddled.png";
			}else{
				this.texture = DefaultProps.mobDiretory + "Horse/horse_beige.png";
			}
			break;
		case 1:
			if(getSaddled()){
				this.texture = DefaultProps.mobDiretory + "Horse/horse_black_saddled.png";
			}else{
				this.texture = DefaultProps.mobDiretory + "Horse/horse_black.png";
			}
			break;
		case 2:
			if(getSaddled()){
				this.texture = DefaultProps.mobDiretory + "Horse/horse_brown_saddled.png";
			}else{
				this.texture = DefaultProps.mobDiretory + "Horse/horse_brown.png";
			}
			break;
		case 3:
			if(getSaddled()){
				this.texture = DefaultProps.mobDiretory + "Horse/horse_dark_black_saddled.png";
			}else{
				this.texture = DefaultProps.mobDiretory + "Horse/horse_dark_black.png";
			}
			break;
		case 4:
			if(getSaddled()){
				this.texture = DefaultProps.mobDiretory + "Horse/horse_dark_brown_saddled.png";
			}else{
				this.texture = DefaultProps.mobDiretory + "Horse/horse_dark_brown.png";
			}
			break;
		case 5:
			if(getSaddled()){
				this.texture = DefaultProps.mobDiretory + "Horse/horse_grey_saddled.png";
			}else{
				this.texture = DefaultProps.mobDiretory + "Horse/horse_grey.png";
			}
			break;
		case 6:
			if(getSaddled()){
				this.texture = DefaultProps.mobDiretory + "Horse/horse_white_saddled.png";
			}else{
				this.texture = DefaultProps.mobDiretory + "Horse/horse_white.png";
			}
			break;
		default:
			if(getSaddled()){
				this.texture = DefaultProps.mobDiretory + "Horse/horse_beige_saddled.png";
			}else{
				this.texture = DefaultProps.mobDiretory + "Horse/horse_beige.png";
			}
			break;
		}
		return this.texture;
	}
	
	@Override
	public void onUpdate() {
		horseType = getHorseType();
		super.onUpdate();
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound par1nbtTagCompound) {
		super.readEntityFromNBT(par1nbtTagCompound);
		horseType = par1nbtTagCompound.getByte("HorseType");
		updateHorseType();

	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound par1nbtTagCompound) {
		super.writeEntityToNBT(par1nbtTagCompound);
		par1nbtTagCompound.setByte("HorseType", (byte) horseType);
		updateHorseType();

	}
}
