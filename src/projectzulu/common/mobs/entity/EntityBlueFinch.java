package projectzulu.common.mobs.entity;

import net.minecraft.world.World;
import projectzulu.common.core.DefaultProps;

public class EntityBlueFinch extends EntityFinch{

	public EntityBlueFinch(World par1World) {
		super(par1World);
	}
	
	@Override
	public String getTexture() {
		this.texture = DefaultProps.mobDiretory + "finch_blue.png";
		return this.texture;
	}
}
