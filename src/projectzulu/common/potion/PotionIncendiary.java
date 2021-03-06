package projectzulu.common.potion;

import net.minecraft.entity.EntityLiving;

public class PotionIncendiary extends PotionZulu{
	
	protected PotionIncendiary(int par1, boolean par2, int par3) {
		super(par1, par2, par3);
		setIconIndex(1, 2);
		setEffectiveness(0.25D);
	}
	
	@Override
	public void performEffect(EntityLiving par1EntityLiving, int par2) {
		par1EntityLiving.setFire(par2);
		super.performEffect(par1EntityLiving, par2);
	}
	
	@Override
	public void affectEntity(EntityLiving par1EntityLiving, EntityLiving par2EntityLiving, int par3, double par4) {
		par2EntityLiving.setFire(20*3);
		super.affectEntity(par1EntityLiving, par2EntityLiving, par3, par4);
	}
	
	@Override
	public boolean isInstant() {
		return true;
//		return super.isInstant();
	}
	
	@Override
	public boolean isReady(int par1, int par2) {
		System.out.println("isReady");
		return true;
	}
}
