package embersified.power;

import embersified.init.ModConfig.Options;
import net.minecraft.nbt.NBTTagCompound;
import teamroots.embers.Embers;

public class SpecialEmberCapability implements teamroots.embers.api.power.IEmberCapability {
	public static boolean allAcceptVolatile = false;

	private double ember = 0;
	private double capacity = 0;
	public double overflow =0;
	@Override
	public double getEmber() {
		return ember;
	}

	@Override
	public double getEmberCapacity() {
		return capacity;
	}

	@Override
	public void setEmber(double value) {
		ember = value;
	}

	@Override
	public void setEmberCapacity(double value) {
		capacity = value;
	}

	@Override
	public double addAmount(double value, boolean doAdd) {
		double added = Math.min(capacity - ember,value);
		double buffer = Math.max(ember+value-capacity,0);
		double newEmber = ember + added;
		if (doAdd){
			if(newEmber != ember)
				onContentsChanged();
			ember += added;
			if(Options.enableOverflowBuffer) {
				overflow += buffer;
			}
		}
		return added;
	}
	
	public double handleOverflow() {
		if (overflow>0 && ember < capacity) {
			double handled = capacity -ember;
			ember=capacity;
			overflow-=handled;
			System.out.println(overflow);
		}
		return overflow;
	}

	@Override
	public double removeAmount(double value, boolean doRemove) {
		double removed = Math.min(ember,value);
		double newEmber = ember - removed;
		if (doRemove){
			if(newEmber != ember)
				onContentsChanged();
			ember -= removed;
		}
		return removed;
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		tag.setDouble(Embers.MODID+":ember", ember);
		tag.setDouble(Embers.MODID+":emberCapacity", capacity);
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		if (tag.hasKey(Embers.MODID+":ember")){
			ember = tag.getDouble(Embers.MODID+":ember");
		}
		if (tag.hasKey(Embers.MODID+":emberCapacity")){
			capacity = tag.getDouble(Embers.MODID+":emberCapacity");
		}
	}

	@Override
	public void onContentsChanged() {
		if(Options.enableOverflowBuffer) {
			handleOverflow();
		}
	}

	@Override
	public boolean acceptsVolatile() {
		return allAcceptVolatile;
	}
}